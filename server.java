/**
 * Sockets são suportados em java desde o JDK 1.0
 * e, para sua utilização, devemos fazer uso das
 * classes contidas no pacote java.net.
 */
import java.net.*;

/**
 * O pacote java.io contém uma série de classes 
 * para a manipulação de entrada e saída. 
 * 
 * * Manipulação de entrada e saída de bytes;
 * * Manipulação de entrada e saída de caracteres;
 * * Buffers;
 * * Conversão de formatos.
 * 
 */
import java.io.*;


/**
 * Aqui se inicia a classe TCPserver
 */
public class TCPServer {
	public static void main (String args[]) {

		/**
		 * 
		 */
		try{

			/**
			 * Atribui na variável serverPort
			 * o número de uma possível porta.
			 */
			int serverPort = 7896; // the server port

			/**
			 * Instancia o ServerSocket olhando
			 * para a porta serverPort.
			 */
			ServerSocket listenSocket = new ServerSocket(serverPort);

			/**
			 * Aqui é executado um loop infinito
			 */
			while(true) {

				/**
				 * O método accept() bloqueia a execução
				 * até que o servidor receba um pedido
				 * de conexão. No caso, o pedido de conexão
				 * vem direto do cliente, obviamente.
				 */
				Socket clientSocket = listenSocket.accept();

				/**
				 * Instancia uma nova conexão.
				 */
				Connection c = new Connection(clientSocket);
				
			}
		} 
		catch(IOException e) {
			System.out.println("Listen socket:"+e.getMessage());
		}
	}
}

/**
 * Aqui se inicializa a Classe Connection
 */
class Connection extends Thread {
	
	/**
	 * Declerava variável para entrada
	 */
	DataInputStream in;

	/**
	 * Declara variável para saída
	 */
	DataOutputStream out;

	/**
	 * Declara variável para socket
	 */
	Socket clientSocket;

	/**
	 * Metodo para recolher entrada e
	 * gerar uma saída para o client. 
	 */
	public Connection (Socket aClientSocket) {

		/**
		 * 
		 */
		try {

			/**
			 * Recebe o socket do cliente passado
			 */
			clientSocket = aClientSocket;

			/**
			 * Instancia a entrada do cliente
			 */
			in = new DataInputStream( clientSocket.getInputStream());

			/**
			 * Instancia a saída para o cliente
			 */
			out =new DataOutputStream( clientSocket.getOutputStream());

			/**
			 * Inicia o que?
			 */
			this.start();
		} 
		catch(IOException e) {
			System.out.println("Connection:"+e.getMessage());
		}
	}

	public void run(){
		try {			                 // an echo server
			
			/**
			 * Recolhe uma string UniCode // read a line of data from the stream
			 */
			String data = in.readUTF();	                  

			/**
			 * Imprime uma mensagem recebida do Client
			 */
			System.out.println("Message received from Client: "+data);

			/**
			 * O que isso significa?
			 */
			out.writeUTF(data+"\nServer add msg!");
		}
		catch (EOFException e){
			System.out.println("EOF:"+e.getMessage());
		} 
		catch(IOException e) {
			System.out.println("readline:"+e.getMessage());
		} 
		finally{ 
			try {
				clientSocket.close();
			}
			catch (IOException e){
				/*close failed*/
			}
		}
		

	}
}