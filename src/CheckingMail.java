import java.util.Properties;

import javax.mail.*;
import javax.swing.JTextArea;

public class CheckingMail {
	private String userName;
	private String password;
	private String receivingHost;

	public void readGmail(String folderToRead, JTextArea ta) {
		this.receivingHost = "imap.gmail.com";
		Properties props2 = System.getProperties();
		props2.setProperty("mail.store.protocol", "imaps");
		Session session2 = Session.getDefaultInstance(props2, null);
		try {
			Store store = session2.getStore("imaps");
			store.connect(this.receivingHost, this.userName, this.password);
			Folder folder = store.getFolder(folderToRead);
			folder.open(Folder.READ_ONLY);
			Message message[] = folder.getMessages();
			ta.append("Total Number of Messages ---> " + message.length);
			for (int i = message.length-1 ; i>0; i--) {
				ta.append("\n"+"---------------------------------------------------------------");
				ta.append("\n"+message[i].getFrom()[0].toString());
				ta.append("\n"+message[i].getSubject());
				ta.append("\n"+message[i].getReceivedDate().toString());
			}
			folder.close(true);
			store.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	public void setAccountDetails(String userName, String password) {
		this.userName = userName;
		this.password = password;

	}

}