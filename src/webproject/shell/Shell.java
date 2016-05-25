package webproject.shell;

import java.io.InputStream;
import java.util.LinkedList;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.UserInfo;

import webproject.commun.AsyncItem;
import webproject.commun.Config;
import webproject.main.AsyncRequest;

/**
 * Class used to send a command in the shell
 * Use a specific thread
 * @author kilian
 *
 */
public class Shell extends Thread{

	private String tool;
	private String command;
	private String sessionID;

	/**
	 * Create a shell object and call run();
	 * @param sessionID
	 * @param tool
	 * @param command
	 */
	public Shell(String sessionID, String tool,String command){
		this.sessionID = sessionID;
		this.tool = tool;
		this.command = command;
		this.start();
	}

	/**
	 * run() is called by the constructor
	 * Create a shell session
	 * send the command
	 * receive the response
	 * close the shell
	 * create an AsyncItem and store it in AsyncRequest class
	 */
	public void run(){

		String res = "";
		try{
			JSch jsch=new JSch();  

			String host = Config.getProperties("host");
			String user = Config.getProperties("user");

			Session session=jsch.getSession(user, host, 22);

			// username and password will be given via UserInfo interface.
			UserInfo ui=new MyUserInfo();
			session.setUserInfo(ui);
			session.connect();

			Channel channel=session.openChannel("exec");
			((ChannelExec)channel).setCommand(command);
			
			channel.setInputStream(null);
			
			((ChannelExec)channel).setErrStream(System.err);

			InputStream in=channel.getInputStream();

			channel.connect();

			res = "> "+ command + "<br>";
			
			byte[] tmp=new byte[1024];
			while(true){
				while(in.available()>0){
					int i=in.read(tmp, 0, 1024);
					if(i<0)break;
					res += new String(tmp, 0, i);
				}
				if(channel.isClosed()){
					if(in.available()>0) continue; 
					break;
				}
			}
			
			
			String sTemp = "";
			
			for (int i = 0; i < res.length(); i++) 
			{
				if((int)res.charAt(i) == 10)
				{
					sTemp += "<br>";
				}
				else if (false)
				{
					sTemp += "&nbsp;&nbsp;&nbsp;&nbsp;";
				}
				else
				{
					System.out.println(res.charAt(i) + " " + (int)res.charAt(i));
					sTemp += res.charAt(i);
				}
			}
			
			res = sTemp;
			
			channel.disconnect();
			session.disconnect();

		}
		catch(Exception e){
			res += "ERROR: failed to execute request";
			System.err.println(e.getMessage());
		}
		
		AsyncItem item = new AsyncItem(sessionID, tool, res);
		AsyncRequest.addAsyncItem(item);
		System.err.println(AsyncRequest.getResponse(sessionID, tool));
	}

	/**
	 * Simple user class, implement the password to connect to the shell
	 * @author kilian
	 *
	 */
	public static class MyUserInfo implements UserInfo{
		public String getPassword(){ return passwd; }
		public boolean promptYesNo(String str){ return true;}

		String passwd = Config.getProperties("password");
		JTextField passwordField=(JTextField)new JPasswordField(20);

		public String getPassphrase(){ return null; }
		public boolean promptPassphrase(String message){ return true; }
		public boolean promptPassword(String message){ return true; }

		public void showMessage(String message){}
	}
}