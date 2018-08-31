package forca;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.*;
import java.util.Scanner;

public class Server {
	public static String str1;
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		int erro=0;
		ServerSocket server = new ServerSocket(12346);
		System.out.println("Porta 12346 aberta!");
		System.out.println("Esperando Jogador...");
		Socket client = server.accept();
		System.out.println("Jogador: "+client.getInetAddress().getHostAddress()+" conectado na porta 12345");
		
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        // sending to client (pwrite object)
		OutputStream ostream = client.getOutputStream(); 
		PrintWriter pwrite = new PrintWriter(ostream, true);

            // receiving from server ( receiveRead  object)
		InputStream istream = client.getInputStream();
		BufferedReader receiveRead = new BufferedReader(new InputStreamReader(istream));

		String receiveMessage, sendMessage; 
		
		System.out.println("Digite o tema: ");
		sendMessage = read.readLine(); 
		pwrite.println(sendMessage);             
		pwrite.flush();
		
		System.out.println("Digite a palavra: ");
		sendMessage = read.readLine(); 
		pwrite.println(sendMessage);             
		pwrite.flush();
		
		sendMessage = sendMessage.toLowerCase();
//		String string = sendMessage;
		formataString(sendMessage.length());//Coloca underlines
//				String str2 = str1; //String cópia para comparações
		
		while (true) {
			if ((receiveMessage = receiveRead.readLine()) != null) {
				if (receiveMessage.length()>1) {//Se jogador optou por digitar palavra completa
					
					if (receiveMessage.equals(sendMessage)) {
						pwrite.println(sendMessage);
						pwrite.flush();
						
						//Caso o jogador tenha acertado a palavra
						pwrite.println("Finalmente acertou!");
						pwrite.flush();
						break;
					}
					else {
						pwrite.println(str1);
						pwrite.flush();
						
						pwrite.println("Se fudeu...");
						pwrite.flush();
						break;
					}
				}
				else {//Se jogador optou por digitar letra
					
					//Pega char
					char c = receiveMessage.charAt(0); 
					//Verifica se existe a letra na string
					if(!trataRetorno(c, sendMessage)) {								
						erro++;
					}
					
					pwrite.println(str1);
					pwrite.flush();
					
					if (erro == 6) {
						pwrite.println("Se fudeu...");             
						pwrite.flush();
//								System.out.println("Encerra");
						break;
					}
					else {								
						if (str1.equals(sendMessage)) {
							//Caso o jogador tenha acertado a palavra		
							pwrite.println("Finalmente acertou!");             
							pwrite.flush();
							break;
						}
						else {
							//Printa na tela do jogador a string formatada
							pwrite.println("Continua");
							pwrite.flush();
						}
//						str2 = str1;
					}
				}
			}	
		}
		
		server.close();
	}
	
	public static boolean trataRetorno(char c, String sendMessage) {
		boolean logic = false;
		
		for (int i =0; i<sendMessage.length(); i++) {
			if (c == sendMessage.charAt(i)) {
				str1 = str1.substring (0, i) + c + str1.substring (i+1);
				logic = true;
			}
		}
		return logic;
	}
	
	public static void formataString(int tam) {
		str1 = "";
		for(int i=0;i<tam;i++) {
			str1 = str1.concat("_");
		}
//		return str1;
	}
	
	public static String espacoString(String str) {
		
		return null;
	}

}