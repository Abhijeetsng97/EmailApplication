import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.CardLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JSeparator;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JList;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.swing.AbstractListModel;
import javax.swing.ListSelectionModel;

public class MainRunner implements ActionListener{

	private JFrame frame;
	private JTextField username;
	private JPasswordField password;
	private JTextField to;
	private JTextField subject;
	private JTextArea body;
	public JTextArea readmailmsg;
	private JButton SendMail, readMail;
	private JList list;
	private JButton folder;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainRunner window = new MainRunner();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainRunner() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("unchecked")
	public void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1000, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("CN Theory Assignment Email Application for GMAIL");
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(423, 11, 78, 14);
		frame.getContentPane().add(lblUsername);
		
		username = new JTextField();
		username.setBounds(511, 8, 173, 20);
		frame.getContentPane().add(username);
		username.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(711, 11, 70, 14);
		frame.getContentPane().add(lblPassword);
		
		password = new JPasswordField();
		password.setBounds(789, 8, 185, 20);
		frame.getContentPane().add(password);
		
		JLabel lblSendMail = new JLabel("Send Mail");
		lblSendMail.setBounds(174, 47, 78, 14);
		frame.getContentPane().add(lblSendMail);
		
		JLabel lblReadInboxMail = new JLabel("Read Mail");
		lblReadInboxMail.setBounds(711, 47, 151, 14);
		frame.getContentPane().add(lblReadInboxMail);
		
		JLabel lblTo = new JLabel("TO :");
		lblTo.setBounds(10, 89, 46, 14);
		frame.getContentPane().add(lblTo);
		
		JLabel lblSubject = new JLabel("SUBJECT:");
		lblSubject.setBounds(10, 113, 57, 14);
		frame.getContentPane().add(lblSubject);
		
		JLabel lblBody = new JLabel("BODY:");
		lblBody.setBounds(10, 138, 46, 14);
		frame.getContentPane().add(lblBody);
		
		to = new JTextField();
		to.setBounds(66, 86, 350, 20);
		frame.getContentPane().add(to);
		to.setColumns(10);
		
		subject = new JTextField();
		subject.setBounds(66, 110, 350, 20);
		frame.getContentPane().add(subject);
		subject.setColumns(10);
		
		body = new JTextArea();
		body.setLineWrap(true);
		body.setBounds(66, 138, 350, 313);
		body.setColumns(10);

	    JScrollPane scroll = new JScrollPane (body);
	    scroll.setLocation(66, 138);
	    scroll.setSize(350, 313);
	    frame.getContentPane().add(scroll); //Object of Jpanel
		
		textField = new JTextField();
		textField.setBounds(66, 135, 86, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		SendMail = new JButton("SEND MAIL");
		SendMail.setBounds(66, 512, 357, 23);
		frame.getContentPane().add(SendMail);
		SendMail.addActionListener(this);
		
		readMail = new JButton("READ MAIL");
		readMail.setBounds(736, 163, 238, 23);
		frame.getContentPane().add(readMail);
		readMail.addActionListener(this);
		
		readmailmsg = new JTextArea();
		readmailmsg.setLineWrap(true);
		readmailmsg.setEditable(false);
		readmailmsg.setBounds(508, 197, 466, 338);
//		frame.getContentPane().add(readmailmsg);
		readmailmsg.setColumns(10);		
		
		JScrollPane scroll2 = new JScrollPane (readmailmsg);
	    scroll2.setLocation(508, 197);
	    scroll2.setSize(466, 338);
	    frame.getContentPane().add(scroll2);
		
		JLabel lblCnTheoryAssignment = new JLabel("CN Theory Assignment Email Application for GMAIL");
		lblCnTheoryAssignment.setBounds(66, 11, 351, 14);
		frame.getContentPane().add(lblCnTheoryAssignment);
		
		list = new JList();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"INBOX"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list.setSelectedIndex(0);
		list.setBounds(636, 72, 238, 80);
		frame.getContentPane().add(list);
		
		folder = new JButton("GET FOLDERS");
		folder.setBounds(508, 163, 214, 23);
		frame.getContentPane().add(folder);
		folder.addActionListener(this);
	}
	
	@SuppressWarnings("unchecked")
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == SendMail) {
			SendingMail sendingMail = new SendingMail();
			int result = sendingMail.send(
						username.getText().toLowerCase().toString(),
						password.getText().toString(),
						to.getText().toString(),
						subject.getText().toString(),
						body.getText().toString()
					);
			if(result == 1) {
				JOptionPane.showMessageDialog(null, "<-- Email is Delivered -->");
			}
			else {
				JOptionPane.showMessageDialog(null, "<-- Email Delivery Failed -->");
			}
		}
		else if(ae.getSource() == readMail) {
			CheckingMail newGmailClient=new CheckingMail();
			newGmailClient.setAccountDetails(
						username.getText().toLowerCase().toString(),
						password.getText().toString()
					);
			newGmailClient.readGmail(list.getSelectedValue().toString(),readmailmsg);
		}
		else if(ae.getSource() == folder) {	
			try {
				Properties props = System.getProperties();
				props.setProperty("mail.store.protocol", "imaps");
				Session session = Session.getDefaultInstance(props, null);
				Store store = session.getStore("imaps");
				store.connect("imap.gmail.com", username.getText().toString(), password.getText().toString());
//				System.out.println(store);

				Folder[] f = store.getDefaultFolder().list();
				int i=0;
				for(Folder fd:f) {
					i++;
					System.out.println(fd.getName());
				}
				String[] values = new String[i];
				for(int j=0;j<i;j++) {
					values[j]=f[j].getName().toString();
				}
				System.out.print("asd");
				list.setModel(new AbstractListModel() {
					
					public int getSize() {
						return values.length;
					}
					public Object getElementAt(int index) {
						return values[index];
					}
				});
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
