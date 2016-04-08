package webproject.shell;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.UIKeyboardInteractive;
import com.jcraft.jsch.UserInfo;

import webproject.commun.Constants;

public class Shell{

	private static JSch jsch;
	private static Session session;
	private static UserInfo ui;
	private static Channel channel;

	public void initConnection(){
		try{
			jsch=new JSch();

			session=jsch.getSession(Constants.SSH_USERNAME, Constants.SSH_HOST, 22);
			session.setPassword(Constants.SSH_PASSWORD);

			ui = new MyUserInfo(){
				public boolean promptYesNo(String message){
					return true;
				}
			};
			session.setUserInfo(ui);
			session.connect(30000);
			
			channel=session.openChannel("shell");
			channel.setInputStream(System.in);
			channel.setOutputStream(System.out);
			channel.connect(3*1000);
			
		}catch(Exception e){
			System.out.println(e);
		}
	}

	public String sendCommand(String command){
		channel.setInputStream(System.in);

		return null;
	}

	public static abstract class MyUserInfo
	implements UserInfo, UIKeyboardInteractive{
		public String getPassword(){ return null; }
		public boolean promptYesNo(String str){ return false; }
		public String getPassphrase(){ return null; }
		public boolean promptPassphrase(String message){ return false; }
		public boolean promptPassword(String message){ return false; }
		public void showMessage(String message){ }
		public String[] promptKeyboardInteractive(String destination,
				String name,
				String instruction,
				String[] prompt,
				boolean[] echo){
			return null;
		}
	}
}