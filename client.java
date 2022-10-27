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
 * Inicia a classe Client
 */
public class TCPClient {
	public static void main (String args[]) {


		// arguments supply message and hostname
		Socket s = null;
		
		try{
			
			/**
			 * Inicializa a porta do servidor
			 */
			int serverPort = 7896;

			/**
			 * Instancia um novo socket com a mensagem (args[1])
			 * e porta serverPort
			 */
			s = new Socket(args[1], serverPort);    

			/**
			 * Instancia a entrada
			 */
			DataInputStream in = new DataInputStream( s.getInputStream());

			/**
			 * Instacia a saída
			 */
			DataOutputStream out =new DataOutputStream( s.getOutputStream());

			/**
			 * ??????
			 */
			out.writeUTF(args[0]);      	// UTF is a string encoding see Sn. 4.4

			/**
			 * Recolhe os dados em uma string
			 */
			String data = in.readUTF();	    // read a line of data from the stream

			/**
			 * Imprime o dado recolhido
			 */
			System.out.println("Received: "+ data) ; /**
														* Talvez seja a resposta do servidor
													 */

		}
		catch (UnknownHostException e){

			System.out.println("Socket:"+e.getMessage());

		}
		catch (EOFException e){
			
			System.out.println("EOF:"+e.getMessage());

		}
		catch (IOException e){
			System.out.println("readline:"+e.getMessage());

		}
		finally {
			if(s!=null) try {s.close();}catch (IOException e){System.out.println("close:"+e.getMessage());}}
     }
}