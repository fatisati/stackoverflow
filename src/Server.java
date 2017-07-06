/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Projections.excludeId;
import static com.mongodb.client.model.Projections.fields;
import static com.mongodb.client.model.Projections.include;
import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.*;
import org.bson.Document;

public class Server {

	// a unique ID for each connection
	private static int uniqueId;
	// an ArrayList to keep the list of the Client
	private ArrayList<ClientThread> al;
	// to display time
	private SimpleDateFormat sdf;
	// the port number to listen for connection
	private int port;
	// the boolean that will be turned of to stop the Server
	private boolean keepGoing;
	// to make a connection to a running MongoDB instance
	private MongoClient mongoClient;
	// to access a database
	private MongoDatabase database;
	BasicDBObject doc;

	private Server(int port) {
		// the port
		this.port = port;
		// to display hh:mm:ss
		sdf = new SimpleDateFormat("HH:mm:ss");
		// ArrayList for the Client list
		al = new ArrayList<ClientThread>();
		mongoClient = new MongoClient("localhost", 27017);
		database = mongoClient.getDatabase("QASystem");
		// database.createCollection("question");
		doc = new BasicDBObject("title", "MongoDB").append("description", "database").append("likes", 100);
	}

	private void start() {
		keepGoing = true;
		/* create socket Server and wait for connection requests */
		try {
			// the socket used by the Server
			ServerSocket serverSocket = new ServerSocket(port);

			// infinite loop to wait for connections
			while (keepGoing) {
				// format message saying we are waiting
				display("Server waiting for Clients on port " + port + ".");

				Socket socket = serverSocket.accept(); // accept connection
				// if I was asked to stop
				if (!keepGoing) {
					break;
				}
				ClientThread t = new ClientThread(socket);// make a thread of it
				System.out.println(socket.getPort());
				al.add(t); // save it in the ArrayList
				t.start();
			}
			// I was asked to stop
			try {
				serverSocket.close();
				for (ClientThread tc : al) {
					try {
						tc.sInput.close();
						tc.sOutput.close();
						tc.socket.close();
					} catch (IOException ioE) {
						// not much I can do
					}
				}
			} catch (Exception e) {
				display("Exception closing the Server and clients: " + e);
			}
		} // something went bad
		catch (IOException e) {
			String msg = sdf.format(new Date()) + " Exception on new ServerSocket: " + e + "\n";
			display(msg);
		}
	}

	// Display an event
	private void display(String msg) {
		String time = sdf.format(new Date()) + " " + msg;
		// System.out.println(time);
	}

	private synchronized int answer(int type, Object message, ObjectOutputStream sOutput) {
		// String[] words = message.split("\\s");//splits the string based on
		// whitespace
		MongoCollection<Document> collection = database.getCollection("question");
		Userz u = (Userz) message;
		if (type == 1) {

			Document doc = new Document("username", u.username).append("password", u.pass);
			Document doc1 = collection.find(eq("username", u.username)).first();

			if (doc1 != null) {
				Document doc2 = collection.find(and(eq("username", u.username), eq("password", u.pass))).first();
				if (doc2 != null) {
					String intrest = collection.find(and(eq("username", u.username), eq("password", u.pass)))
							.projection(fields(include("intrest"), excludeId())).first().toJson();
					System.out.println(intrest);
					int[] intrestList = new int[5];
					for (int i = 0; i < 5; i++) {
						if (intrest != null)
							intrestList[i] = (int) intrest.charAt(i) - 48;
					}
					u.interest = intrestList;
					//Message msg = new Message(Message.LOGIN, u);
					try {
						sOutput.writeObject(u);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return 0;
				} else {
					return 2;
					
				}
			} else {
				return 2;
			}
		}

		//
		// BasicDBObject Query = new BasicDBObject();
		// List<BasicDBObject> obj = new ArrayList<BasicDBObject😠);
		// obj.add(new BasicDBObject("username", u.username));
		// obj.add(new BasicDBObject("password", u.pass));
		//
		// System.out.println(Query.toString());
		//
		// Document myDoc = collection.find(Query).first();
		// System.out.println(myDoc.toJson());
		if (type == 3) {
			int m = 0;
			//String intrest = new String();

//			for (int i = 4; i >= 0; i--) {
//				intrest.concat(Integer.toString(u.interest[i]));
//			}
			String arr[] = new String[3];
			arr[0] = "fat";
			arr[1] = "sat";
			Document doc = new Document("name", u.user).append("username", u.username).append("email", u.email)
					.append("password", u.pass).append("intrest", arr);
			// collection.deleteMany("name",123);

			MongoCursor<Document> cursor = collection.find().iterator();
			try {
				while (cursor.hasNext()) {
					System.out.println(cursor.next().toJson());
				}
			} finally {
				cursor.close();
			}

			Document doc1 = collection.find(eq("username", u.username)).first();
			if (doc1 == null) {
				collection.insertOne(doc);
				return 0;
			} else {
				return 1;
			}
		}
		// Document doc1 = collection.find(eq("username",u.username )).first();
		// System.out.println(doc.toJson());
		// System.out.println(collection.count());
		// MongoCursor<Document> cursor = collection.find().iterator();
		// try {
		// while (cursor.hasNext()) {
		// System.out.println(cursor.next().toJson());
		// }
		// } finally {
		// cursor.close();
		// }
		// delete furmula
		// MongoCursor<Document> cursor1 = collection.find().iterator();
		// try {
		// while (cursor1.hasNext()) {
		// collection.deleteOne(cursor.next());
		// }
		// } finally {
		// cursor1.close();
		// }
		// BasicDBObject Query = new BasicDBObject();
		// List<BasicDBObject> obj = new ArrayList<BasicDBObject😠);
		// obj.add(new BasicDBObject("username",u.username));
		// obj.add(new BasicDBObject("password",u.pass));
		// System.out.println(Query.toString()+"query is rinting");
		// Document myDoc = collection.find(Query).first();
		// System.out.println(myDoc.toJson()+"your search answer is printed");

		// collection.insertOne(new Document("name ", message));
		// System.out.println(
		// collection.find().projection(fields(include("name"),excludeId()))
		// );
		// Vector<String> keywords = new Vector<😠);
		// Document allKeywords = collection.find(new Document("allKeywords",
		// new Document("$exists", true))).first();
		// ArrayList<String> q = (ArrayList<String>)
		// allKeywords.get("allKeywords");
		// for(String w:words){
		// if (q.contains(w)){
		// keywords.add(w);
		// System.out.println(w);
		// }
		// }
		// System.out.println(message);
		// database.insert(new Document("name ", message));
		return 0;
	}

	// for a Client who logoff using the LOGOUT message
	private synchronized void remove(int id) {
		// scan the array list until we found the Id
		for (int i = 0; i < al.size(); ++i) {
			ClientThread ct = al.get(i);
			// found it
			if (ct.id == id) {
				al.remove(i);
				return;
			}
		}
	}

	public static void main(String[] args) {
		// start Server on port 8000 unless a PortNumber is specified
		int portNumber = 8000;

		// create a Server object and start it
		Server server = new Server(portNumber);
		server.start();
	}

	/**
	 * One instance of this thread will run for each Client
	 */
	class ClientThread extends Thread {
		// the socket where to listen/talk

		Socket socket;
		ObjectInputStream sInput;
		ObjectOutputStream sOutput;
		// my unique id (easier for disconnection)
		int id;
		// the Username of the Client
		String username;
		// the only type of message a will receive
		Message cm;
		// the date I connect
		String date;

		ClientThread(Socket socket) {
			// a unique id
			id = ++uniqueId;
			this.socket = socket;
			/* Creating both Data Stream */
			System.out.println("Thread trying to create Object Input/Output Streams");
			try {
				// create output first
				sOutput = new ObjectOutputStream(socket.getOutputStream());
				sInput = new ObjectInputStream(socket.getInputStream());
				// read the username
				username = (String) sInput.readObject();
				display(username + " just connected.");
			} catch (IOException e) {
				display("Exception creating new Input/output Streams: " + e);
				return;
			} // have to catch ClassNotFoundException
				// but I read a String, I am sure it will work
			catch (ClassNotFoundException ignored) {
			}
			date = new Date().toString() + "\n";
		}

		// what will run forever
		public void run() {
			// to loop until LOGOUT
			boolean keepGoing = true;
			while (keepGoing) {
				// read a String (which is an object)
				try {
					cm = (Message) sInput.readObject();

				} catch (IOException e) {
					display(username + " Exception reading Streams: " + e);
					break;
				} catch (ClassNotFoundException e2) {
					break;
				}
				// the message part of the Message
				Object message = cm.messageObject;

				// Switch on the type of message receive
				switch (cm.getType()) {

				case Message.REGISTER:
					// System.out.println("humm");
					if (answer(3, message, sOutput) == 1) {
						writeMsg("ErrUsrExist");
					}
					break;
				case Message.LOGOUT:
					display(username + " disconnected with a LOGOUT message.");
					keepGoing = false;
					break;
				case Message.LOGIN:

					if (answer(1, message, sOutput) == 2) {
						// System.out.println("wrong found");
						writeMsg("WrongUserOrPassword");
					} // else
						// writeMsg(intrestList);
					// answer(1, message, sOutput);
					// writeMsg("List of the users connected at " +
					// sdf.format(new Date()) + "\n");
					// // scan al the users connected
					// for (int i = 0; i < al.size(); ++i) {
					// ClientThread ct = al.get(i);
					// writeMsg((i + 1) + ") " + ct.username + " since " +
					// ct.date);
					// }
					break;
				}
			}
			// remove myself from the arrayList containing the list of the
			// connected Clients
			remove(id);
			close();
		}

		// try to close everything
		private void close() {
			// try to close the connection
			try {
				if (sOutput != null) {
					sOutput.close();
				}
			} catch (Exception ignored) {
			}
			try {
				if (sInput != null) {
					sInput.close();
				}
			} catch (Exception ignored) {
			}
			try {
				if (socket != null) {
					socket.close();
				}
			} catch (Exception ignored) {
			}
		}

		/*
		 * Write a String to the Client output stream
		 */
		private boolean writeMsg(String msg) {
			// if Client is still connected send the message to it
			if (!socket.isConnected()) {
				close();
				return false;
			}
			// write the message to the stream
			try {
				sOutput.writeObject(msg);
			} // if an error occurs, do not abort just inform the user
			catch (IOException e) {
				display("Error sending message to " + username);
				display(e.toString());
			}
			return true;
		}
	}

	private void userNameExist() {

	}
}
