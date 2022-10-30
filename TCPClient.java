import java.net.*;
import java.io.*;
import java.util.Scanner;


public class TCPClient {
	public static void main (String args[]) {


		// arguments supply message and hostname
		Socket s = null;

		// Scanner para ler os dados do usuário
		Scanner leitor;

		try{
			
			int     leitura;

			// InetAddress aHost = ; InetAddress.getByName(InetAddress.getLocalHost().getHostName())
			int serverPort = 7896;
			s = new Socket("127.0.0.1" , serverPort);   
			
			// while(true){



				System.out.println("Digite o numero para selecionar a opcao e aperte enter.");
				System.out.println("1 - Criar diretorio");
				System.out.println("2 - Remover diretorio");
				System.out.println("3 - Listar conteudo de diretorio");
				System.out.println("4 - Enviar arquivo");
				System.out.println("5 - Remover arquivo");
				
				// recolhe texto digitado pelo usuário. A própria mensagem.
				leitor = new Scanner(System.in);
				leitura = leitor.nextInt();
				
				


				switch(leitura){
					
					
					/**
					 * CASO 1: cria um diretório.
					 */
					case 1:
						// bloco de código que será executado
						System.out.println("digite o diretório que será criado:");
						leitor = new Scanner(System.in);
						String mensagem = leitor.nextLine();
						
						DataInputStream in = new DataInputStream( s.getInputStream());
			
						DataOutputStream out =new DataOutputStream( s.getOutputStream());
		
						out.writeUTF(mensagem);      	// UTF is a string encoding see Sn. 4.4
						
						String data = in.readUTF();	    // read a line of data from the stream
		
						System.out.println("Received: "+ data) ; 
						
					break;
					
					/**
					 * CASO 2: Remove diretório.
					 */
					case 2:
						// bloco de código que será executado
					break;

					/**
					 * CASO 3: Lista conteudo de diretório
					 */
					case 3:
						// bloco de código que será executado
					break;

					/**
					 * CASO 4: Envia arquivo.
					 */
					case 4:
						// bloco de código que será executado
					break;
					

					/**
					 * CASO 5: Remover arquivo.
					 */
					case 5:
						// bloco de código que será executado
					break;

					default:
						System.out.println("digite uma opcao valida.");

				}

	

			// }
			
			
		}catch (UnknownHostException e){System.out.println("Socket:"+e.getMessage());
		}catch (EOFException e){System.out.println("EOF:"+e.getMessage());
		}catch (IOException e){System.out.println("readline:"+e.getMessage());
		}finally {if(s!=null) try {s.close();}catch (IOException e){System.out.println("close:"+e.getMessage());}}
     }
}