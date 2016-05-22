package webproject.shell;

import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.UserInfo;

import webproject.commun.Constants;
import webproject.commun.History;
import webproject.main.AsyncRequest;

public class Shell extends Thread{

	private static Channel channel;
	private HttpServletRequest request;
	private String tool;
	private String command;
	
	public Shell(HttpServletRequest request, String tool,String command){
		this.request = request;
		this.tool = tool;
		this.command = command;
		this.start();
	}

	public void run(){

		String res = "";
		try{
			JSch jsch=new JSch();  

			String host = "root@192.168.56.101";
			String user=host.substring(0, host.indexOf('@'));
			host=host.substring(host.indexOf('@')+1);

			Session session=jsch.getSession(user, host, 22);

			/*
		      String xhost="127.0.0.1";
		      int xport=0;
		      String display=JOptionPane.showInputDialog("Enter display name", 
		                                                 xhost+":"+xport);
		      xhost=display.substring(0, display.indexOf(':'));
		      xport=Integer.parseInt(display.substring(display.indexOf(':')+1));
		      session.setX11Host(xhost);
		      session.setX11Port(xport+6000);
			 */

			// username and password will be given via UserInfo interface.
			UserInfo ui=new MyUserInfo();
			session.setUserInfo(ui);
			session.connect();

			Channel channel=session.openChannel("exec");
			((ChannelExec)channel).setCommand(command);

			// X Forwarding
			// channel.setXForwarding(true);

			//channel.setInputStream(System.in);
			channel.setInputStream(null);

			//channel.setOutputStream(System.out);

			//FileOutputStream fos=new FileOutputStream("/tmp/stderr");
			//((ChannelExec)channel).setErrStream(fos);
			((ChannelExec)channel).setErrStream(System.err);

			InputStream in=channel.getInputStream();

			channel.connect();
			
			byte[] tmp=new byte[1024];
			while(true){
				while(in.available()>0){
					int i=in.read(tmp, 0, 1024);
					if(i<0)break;
					res += new String(tmp, 0, i);
				}
				if(channel.isClosed()){
					if(in.available()>0) continue; 
					res += "done";
					break;
				}
			}
			channel.disconnect();
			session.disconnect();
			
			System.out.println(res);
		}
		catch(Exception e){
			res += "ERROR: failed to execute request";
			System.out.println(e);
		}
		
		HttpSession session = request.getSession();
		if(session == null){
			System.err.println("Thread: session is null");
		}
		History history = (History) session.getAttribute(Constants.ATT_SESSION_HISTORY);
		
		if( history == null){
			history = new History();
		}
		
		history.addResponse(tool, res);
		
		session.setAttribute(Constants.ATT_SESSION_HISTORY, history);
		AsyncRequest.read(request, tool);
	}

	public String sendCommand(String command){
		channel.setInputStream(System.in);

		return null;
	}

	public static class MyUserInfo implements UserInfo{
		public String getPassword(){ return passwd; }
		public boolean promptYesNo(String str){ return true;}

		String passwd = "toor";
		JTextField passwordField=(JTextField)new JPasswordField(20);

		public String getPassphrase(){ return null; }
		public boolean promptPassphrase(String message){ return true; }
		public boolean promptPassword(String message){ return true; }

		public void showMessage(String message){}
	}
}