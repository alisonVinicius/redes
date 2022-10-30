import java.net.*;
import java.io.*;
public class TCPServer {

	public static void main (String args[]) {
		
		try{
			
			int serverPort = 7896; // the server port

			ServerSocket listenSocket = new ServerSocket(serverPort);

			while(true) {

				Socket clientSocket = listenSocket.accept();
				System.out.println("Conexão estabelecida com cliente");
				Connection c = new Connection(clientSocket);

			}

		} 
		catch(IOException e) {
			System.out.println("Listen socket:"+e.getMessage());
		}
	}
}


class Connection extends Thread {
	DataInputStream in;
	DataOutputStream out;
	Socket clientSocket;
	public Connection (Socket aClientSocket) {
		try {
			clientSocket = aClientSocket;
			in = new DataInputStream( clientSocket.getInputStream());
			out =new DataOutputStream( clientSocket.getOutputStream());
			this.start();
		} catch(IOException e) {System.out.println("Connection:"+e.getMessage());}
	}
	public void run(){
		try {			                 // an echo server

			String data = in.readUTF();	                  // read a line of data from the stream
			
			System.out.println("Message received from Client: "+data);
			
			out.writeUTF(data+"\nServer add msg!");
			
		}catch (EOFException e){System.out.println("EOF:"+e.getMessage());
		} catch(IOException e) {System.out.println("readline:"+e.getMessage());
		} finally{ try {clientSocket.close();}catch (IOException e){/*close failed*/}}
		

	}
}

