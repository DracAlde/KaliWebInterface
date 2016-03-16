package webproject.shell;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.UIKeyboardInteractive;
import com.jcraft.jsch.UserInfo;

import webproject.commun.Constants;

public class Shell{
	public static void main(String[] arg){

		try{
			JSch jsch=new JSch();

			//jsch.setKnownHosts("/home/foo/.ssh/known_hosts");

			Session session=jsch.getSession(Constants.SSH_USERNAME, Constants.SSH_HOST, 22);

			session.setPassword(Constants.SSH_PASSWORD);

			UserInfo ui = new MyUserInfo(){
				public boolean promptYesNo(String message){
					return true;
				}
			};

			session.setUserInfo(ui);

			// It must not be recommended, but if you want to skip host-key check,
			// invoke following,
			// session.setConfig("StrictHostKeyChecking", "no");

			//session.connect();
			session.connect(30000);   // making a connection with timeout.

			Channel channel=session.openChannel("shell");

			// Enable agent-forwarding.
			//((ChannelShell)channel).setAgentForwarding(true);

			channel.setInputStream(System.in);
			/*
      // a hack for MS-DOS prompt on Windows.
      channel.setInputStream(new FilterInputStream(System.in){
          public int read(byte[] b, int off, int len)throws IOException{
            return in.read(b, off, (len>1024?1024:len));
          }
        });
			 */

			channel.setOutputStream(System.out);

			/*
      // Choose the pty-type "vt102".
      ((ChannelShell)channel).setPtyType("vt102");
			 */

			/*
      // Set environment variable "LANG" as "ja_JP.eucJP".
      ((ChannelShell)channel).setEnv("LANG", "ja_JP.eucJP");
			 */

			//channel.connect();
			channel.connect(3*1000);
		}
		catch(Exception e){
			System.out.println(e);
		}
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