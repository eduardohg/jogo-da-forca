package forca;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
//import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
	public static String desenho;
	private Socket socket;
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		// TODO Auto-generated method stub
		
		Scanner ler = new Scanner(System.in);
		System.out.println("Digite o IP: ");
		String ip = ler.next();

		Socket cliente = new Socket(ip , 12346);

        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));

        OutputStream ostream = cliente.getOutputStream();
        PrintWriter pwrite = new PrintWriter(ostream, true);

        InputStream istream = cliente.getInputStream();
        BufferedReader receiveRead = new BufferedReader(new InputStreamReader(istream));

        String receiveMessage, sendMessage;
        String tema, palavra;

        System.out.println("\nEsperando o tema...");
        tema = receiveFromServer(cliente);
        System.out.println("O tema é " + tema);
        
        System.out.println("\nEsperando a palavra...");
        palavra = receiveFromServer(cliente);
        System.out.println("A palavra tem tamanho " + palavra.length() + "\n\n");

        boolean logic, gameOver = false;
        int erro;
        int op;
        
        while (!gameOver) {
        	logic = true;

        	//Confirmação
        	pwrite.println("OK!");
        	pwrite.flush();
        	
        	palavra = receiveFromServer(cliente);
        	erro = Integer.parseInt(palavra);
        	draw(erro);
        	System.out.println(desenho);
        	
        	//Confirmação
        	pwrite.println("OK!");
        	pwrite.flush();    
        	
        	palavra = receiveFromServer(cliente);
        	System.out.println("                   " + palavra);
        	
        	while (logic) {
	        	System.out.println("Digite:");
	        	System.out.print("1 - Chutar Letra\n2 - Chutar palavra\n");
	        	op = ler.nextInt();
	        	
	        	if (op == 1) {
	        		System.out.println("\nDigite a letra: ");
	        		sendMessage = read.readLine();  // keyboard reading
	        		
	            	if (sendMessage.length() == 1) {
	            		pwrite.println(sendMessage);       // sending to server
	            		pwrite.flush();                    // flush the data
	            		logic = false;
	            	}
	            	else if (sendMessage.length() == 0) {
	            		System.out.println("Deve digitar pelo menos uma letra!\n");
	            	}
	            	else {
	            		System.out.println("Só é permitido uma letra!\n");
	            	}
	        	}
	        	else if (op == 2) {
	        		System.out.println("\nDigite a palavra: ");
	        		sendMessage = read.readLine();  // keyboard reading
	        		
	        		pwrite.println(sendMessage);    // sending to server
	        		pwrite.flush();                 // flush the data
	        		logic = false;
	        	}
	        	else {
	        		System.out.println("Opção inválida!");
	        	}
        	}
        	
        	while (true) {
                if((receiveMessage = receiveRead.readLine()) != null) { //receive from server
			       	if (receiveMessage.equals("Você perdeu...") ||  receiveMessage.equals("Finalmente acertou!")) {//Processo imediatamente anterior ao encerramento do programa
			       		String finalMessage = receiveMessage;
			       		
			       		if (finalMessage.equals("Você perdeu...")) {//Caso o jogador tenha perdido
			       			draw(6);					       			
			       		}
			       		else draw(erro);
			       		
				       	System.out.println(desenho);
				       	
				       	//Confirmação
				       	pwrite.println("OK!");       // sending to server
			        	pwrite.flush();
			        	
			        	palavra = receiveFromServer(cliente);
			        	System.out.println("                   " + palavra);
				       	
			        	System.out.println("\n" + finalMessage);
			       		gameOver = true;
			       	}
			       	else if (receiveMessage.equals("Continua"))  {
			       		//Não faz nada
			       	}
			       	else { //Caso chute uma letra repetida
			       		System.out.println("\n>>>>>>>>>>>>>>>>>>>> A letra \"" + receiveMessage + "\" já foi chutada <<<<<<<<<<<<<<<<<<<<\n");
			       	}

			       	break;
                }
        	}
        }

        ler.close();
        cliente.close();
	}
	
	public static String receiveFromServer(Socket cliente) throws IOException {
		String receiveMessage;

		InputStream istream = cliente.getInputStream();
        BufferedReader receiveRead = new BufferedReader(new InputStreamReader(istream));

		while (true) {
			if ((receiveMessage = receiveRead.readLine()) != null) { //receive from server
				break;
			}
		}
		return receiveMessage;
	}
	
	public static void draw(int erro) {
		String str;
		
		switch (erro) {
			case 0:
				str = "      ***************\n";
				str+= "      ***************\n";
				str+= "      ***        *** \n";
				str+= "      ***            \n";
				str+= "      ***            \n";
				str+= "      ***            \n";
				str+= "      ***            \n";
				str+= "      ***            \n";
				str+= "      ***            \n";
				str+= "      ***            \n";
				str+= "      ***            \n";
				str+= "      ***            \n";
				str+= "      ***            \n";
				str+= "      ***            \n";
				str+= "      ***            \n";
				str+= "      ***            \n";
				str+= "      ***            \n";
				str+= "      ***            \n";
				str+= "     *****           \n";
//				str+= str1;
				desenho = str;
				
				break;
				
			case 1:
				str = "      ***************\n";
				str+= "      ***************\n";
				str+= "      ***        *** \n";
				str+= "      ***        * *  \n";
				str+= "      ***       *   * \n";
				str+= "      ***        * *  \n";
				str+= "      ***            \n";
				str+= "      ***            \n";
				str+= "      ***            \n";
				str+= "      ***            \n";
				str+= "      ***            \n";
				str+= "      ***            \n";
				str+= "      ***            \n";
				str+= "      ***            \n";
				str+= "      ***            \n";
				str+= "      ***            \n";
				str+= "      ***            \n";
				str+= "      ***            \n";
				str+= "     *****           \n";
//				str+= str1;
				desenho = str;
				
				break;
				
			case 2:
				str = "      ***************\n";
				str+= "      ***************\n";
				str+= "      ***        *** \n";
				str+= "      ***        * *  \n";
				str+= "      ***       *   * \n";
				str+= "      ***        * *  \n";
				str+= "      ***         |   \n";
				str+= "      ***         |   \n";
				str+= "      ***         |   \n";
				str+= "      ***         |   \n";
				str+= "      ***         |   \n";
				str+= "      ***         |   \n";
				str+= "      ***            \n";
				str+= "      ***            \n";
				str+= "      ***            \n";
				str+= "      ***            \n";
				str+= "      ***            \n";
				str+= "      ***            \n";
				str+= "      ***            \n";
				str+= "     *****           \n";
//				str+= str1;
				desenho = str;
				
				break;
							
			case 3:
				str = "      ***************\n";
				str+= "      ***************\n";
				str+= "      ***        *** \n";
				str+= "      ***        * *  \n";
				str+= "      ***       *   * \n";
				str+= "      ***        * *  \n";
				str+= "      ***         |   \n";
				str+= "      ***        /|   \n";
				str+= "      ***       / |   \n";
				str+= "      ***      *  |   \n";
				str+= "      ***         |   \n";
				str+= "      ***         |   \n";
				str+= "      ***            \n";
				str+= "      ***            \n";
				str+= "      ***            \n";
				str+= "      ***            \n";
				str+= "      ***            \n";
				str+= "      ***            \n";
				str+= "      ***            \n";
				str+= "     *****           \n";
//				str+= str1;
				desenho = str;

				break;
				
			case 4:
				str = "      ***************\n";
				str+= "      ***************\n";
				str+= "      ***        *** \n";
				str+= "      ***        * *  \n";
				str+= "      ***       *   * \n";
				str+= "      ***        * *  \n";
				str+= "      ***         |   \n";
				str+= "      ***        /|\\   \n";
				str+= "      ***       / | \\  \n";
				str+= "      ***      *  |  * \n";
				str+= "      ***         |   \n";
				str+= "      ***         |   \n";
				str+= "      ***            \n";
				str+= "      ***            \n";
				str+= "      ***            \n";
				str+= "      ***            \n";
				str+= "      ***            \n";
				str+= "      ***            \n";
				str+= "      ***            \n";
				str+= "     *****           \n";
//				str+= str1;
				desenho = str;

				break;
				
			case 5:
				str = "      ***************\n";
				str+= "      ***************\n";
				str+= "      ***        *** \n";
				str+= "      ***        * *  \n";
				str+= "      ***       *   * \n";
				str+= "      ***        * *  \n";
				str+= "      ***         |   \n";
				str+= "      ***        /|\\   \n";
				str+= "      ***       / | \\  \n";
				str+= "      ***      *  |  * \n";
				str+= "      ***         |   \n";
				str+= "      ***         |   \n";
				str+= "      ***        /    \n";
				str+= "      ***       /     \n";
				str+= "      ***     _/      \n";
				str+= "      ***            \n";
				str+= "      ***            \n";
				str+= "      ***            \n";
//				str+= str1;
				desenho = str;

				break;
				
			case 6:
				str = "      ***************\n";
				str+= "      ***************\n";
				str+= "      ***        *** \n";
				str+= "      ***        * *  \n";
				str+= "      ***       *   * \n";
				str+= "      ***        * *  \n";
				str+= "      ***         |   \n";
				str+= "      ***        /|\\   \n";
				str+= "      ***       / | \\  \n";
				str+= "      ***      *  |  * \n";
				str+= "      ***         |   \n";
				str+= "      ***         |   \n";
				str+= "      ***        / \\   \n";
				str+= "      ***       /   \\  \n";
				str+= "      ***     _/     \\_ \n";
				str+= "      ***            \n";
				str+= "      ***            \n";
				str+= "      ***            \n";
//				str+= str1;
				desenho = str;

				break;
				
			default:
				break;
				
		}
	}
}