
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.*;
import java.net.*;
import java.util.*;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Client {

	// for I/O
	ObjectInputStream sInput; // to read from the socket
	ObjectOutputStream sOutput; // to write on the socket
	private Socket socket;

	// the Server, the port and the username
	private String server, username;
	private int port;

	boolean islogin;
	JLabel label;
	Userz u;
	String keywords[];
	// ListenFromServer lf;

	Client(String server, int port, String username) {
		this.server = server;
		this.port = port;
		this.username = username;
		islogin = false;
		// lf = new ListenFromServer(this);
		// this.label = label;
	}

	boolean start() {
		// try to connect to the Server
		try {
			socket = new Socket(server, port);
		}
		// if it failed not much I can so
		catch (Exception ec) {
			System.out.println("Error connecting to Server:" + ec);
			return false;
		}

		String msg = "Connection accepted " + socket.getInetAddress() + ":" + socket.getPort();
		System.out.println(msg);

		/* Creating both Data Stream */
		try {
			sInput = new ObjectInputStream(socket.getInputStream());
			sOutput = new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException eIO) {
			System.out.println("Exception creating new Input/output Streams: " + eIO);
			return false;
		}

		// creates the Thread to listen from the Server
		new ListenFromServer(this).start();
		// lf.start();
		// Send our username to the Server this is the only message that we
		// will send as a String. All other messages will be Message objects
		try {
			sOutput.writeObject(username);
		} catch (IOException eIO) {
			System.out.println("Exception doing login : " + eIO);
			disconnect();
			return false;
		}
		// success we inform the caller that it worked
		return true;
	}

	/*
	 * To send a message to the Server
	 */
	void sendMessage(Message msg) {
		try {
			// System.out.println("send mes");
			sOutput.writeObject(msg);
		} catch (IOException e) {
			System.out.println("Exception writing to Server: " + e);
		}
	}

	/*
	 * When something goes wrong Close the Input/Output streams and disconnect
	 * not much to do in the catch clause
	 */
	void disconnect() {
		try {
			if (sInput != null)
				sInput.close();
		} catch (Exception ignored) {
		} // not much else I can do
		try {
			if (sOutput != null)
				sOutput.close();
		} catch (Exception ignored) {
		} // not much else I can do
		try {
			if (socket != null)
				socket.close();
		} catch (Exception ignored) {
		} // not much else I can do

	}

	public static void main(String[] args) {
		// default values
		// int portNumber = 8000;
		// String serverAddress = "DESKTOP-59E54A2";
		// String userName = "Anonymous1";
		//
		// // create the Client object
		// Client client = new Client(serverAddress, portNumber, userName);
		// // test if we can start the connection to the Server
		// // if it failed nothing we can do
		// if(!client.start())
		// return;
		//
		// // wait for messages from user
		// Scanner scan = new Scanner(System.in);
		// // loop forever for message from the user
		// while(true) {
		// // read message from user
		// String msg = scan.nextLine();
		//
		// // message SEARCH
		// if(msg.equalsIgnoreCase("SEARCH")) {
		// System.out.println("Enter the your query:");
		// String content = scan.nextLine();
		// client.sendMessage(new Message(Message.SEARCH, content));
		// }
		// // message LOGIN
		// else if(msg.equalsIgnoreCase("LOGIN")) {
		// client.sendMessage(new Message(Message.LOGIN, ""));
		// }
		// // message LOGOUT
		// else if(msg.equalsIgnoreCase("LOGOUT")) {
		// client.sendMessage(new Message(Message.LOGOUT, ""));
		// break;
		// }
		// // message REGISTER
		// else if(msg.equalsIgnoreCase("REGISTER")) {
		// client.sendMessage(new Message(Message.REGISTER, ""));
		// }
		// }
		// // done disconnect
		// client.disconnect();
	}

	/*
	 * a class that waits for the message from the Server and append them to the
	 * JTextArea if we have a GUI or simply System.out.println() it in console
	 * mode
	 */
	class ListenFromServer extends Thread {
		Client client;
		int i = 0;

		public ListenFromServer(Client client) {
			this.client = client;
		}

		public void run() {
			while (true) {

				try {

					Object obj = sInput.readObject();

					if (obj instanceof String) {
						String msg = (String) obj;
						// System.out.println(msg);
						if (msg.equals("WrongUserOrPassword")) {
							// System.out.println("hey i'm here");
							JOptionPane.showMessageDialog(null, "wrong username or password");
						} else {
							System.out.println("client msg: "+msg);;
						}
					}

					if (obj instanceof Userz) {
						Userz user = (Userz) obj;
						u = user;
						islogin = true;
						// System.out.println("welcom " + user.interest[0]);
						System.out.println(user.interest.size());
						new MainPage(client, user);
					}

					if (obj instanceof ArrayList) {
						System.out.println("hey here");
						ArrayList<Question> q = (ArrayList<Question>) obj;

						// System.out.println(q.get(0).content);
						new QuestionPage(client, u, q);

						// System.out.println("here");
					}

					if (obj instanceof String[]) {
						// System.out.println("accepted");
						client.keywords = (String[]) obj;
						// System.out.println(client.keywords[0]);
					}

					// if console mode print the message and add back the prompt
					// System.out.println(msg);
				} catch (IOException e) {
					System.out.println("Server has close the connection: " + e);
					break;
				}
				// can't happen with a String object but need the catch anyhow
				catch (ClassNotFoundException ignored) {
					System.out.println("class");
				}
			}
		}
	}
}
