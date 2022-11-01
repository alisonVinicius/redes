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

			// int opcao = in.readInt();

			String data = in.readUTF();	                  // read a line of data from the stream

			System.out.println("Client escolheu caso: "+ data);
	
			out.writeUTF("\nServidor recebeu a escolha do client." + data);			

			switch(data){
				
				
				/**
				* CASO 1: cria um diretório.
				*/
				case "1":

					DataInputStream diretorioIn = new DataInputStream( clientSocket.getInputStream());

					DataOutputStream diretorioOut = new DataOutputStream( clientSocket.getOutputStream());
					
					data = diretorioIn.readUTF();

					System.out.println("Client enviou diretorio: "+ data);
			
					diretorioOut.writeUTF("\nServidor criou diretorio " + data);
					
				break;
				
				/**
				* CASO 2: Remove diretório.
				*/
				case "2":
					System.out.println("Client escolheu caso: "+ data);
			
					out.writeUTF("\nServidor recebeu a escolha do client." + data);

				break;

				/**
				* CASO 3: Lista conteudo de diretório
				*/
				case "3":
					System.out.println("Client escolheu caso: "+ data);
			
					out.writeUTF("\nServidor recebeu a escolha do client." + data);

				break;

				/**
				* CASO 4: Envia arquivo.
				*/
				case "4":
					System.out.println("Client escolheu caso: "+ data);
			
					out.writeUTF("\nServidor recebeu a escolha do client." + data);

				break;
				

				/**
				* CASO 5: Remover arquivo.
				*/
				case "5":
					System.out.println("Client escolheu caso: "+ data);
			
					out.writeUTF("\nServidor recebeu a escolha do client." + data);
				break;

				default:
					System.out.println("Client escolheu caso que não existe. ");
			
					out.writeUTF("\nClient precisa fazer outra escolha.");

			}


			
		}catch (EOFException e){System.out.println("EOF:"+e.getMessage());
		} catch(IOException e) {System.out.println("readline:"+e.getMessage());
		} finally{ try {clientSocket.close();}catch (IOException e){/*close failed*/}}
		

	}
}
