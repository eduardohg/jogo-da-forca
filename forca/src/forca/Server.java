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
				Socket cliente = server.accept();
				System.out.println("Jogador: "+cliente.getInetAddress().getHostAddress()+" conectado na porta 12345");
				
				BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
                // sending to client (pwrite object)
				OutputStream ostream = cliente.getOutputStream(); 
				PrintWriter pwrite = new PrintWriter(ostream, true);

                    // receiving from server ( receiveRead  object)
				InputStream istream = cliente.getInputStream();
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
				str1 = formataString(sendMessage.length());//Coloca underlines
				String str2 = str1; //String cópia para comparações
				
				while(true)
				{
					if((receiveMessage = receiveRead.readLine()) != null)  
					{
						if(receiveMessage.length()>1) {//Se jogador optou por digitar palavra completa
							
						}
						else {//Se jogador optou por digitar letra
							
							//Pega char
							char c = receiveMessage.charAt(0); 
							//Verifica se existe a letra na string e faz o retorno da String
							str1 = trataRetorno(c,str1,sendMessage);
							
							System.out.println("String: "+str1);//String
							
							//Se str1 igual a str2 então jogador não acertou a letra
							if(str1.equals(str2))
								erro++;
							
							//Printa na tela do jogador a string formatada
							//Não está funcionando
//							pwrite.println(str1);             
//							pwrite.flush();     
							
							str2 = str1;
						}   
					}	
					if(erro == 5) {
						System.out.println("Encerra");
						break;
					}
				}
				
	}
	
	public static String trataRetorno(char c,String str,String sendMessage) {
		
		for(int i =0;i<sendMessage.length();i++) {
			if(c == sendMessage.charAt(i)) {
				str = str.substring (0, i) + c + str.substring (i+1);
			
			}
		}
		return str;
	}
	
	public static String formataString(int tam) {
		String str = "";
		for(int i=0;i<tam;i++) {
			str = str.concat("_");
		}
		return str;
	}
	
	public static String espacoString(String str) {
		
		return null;
	}

}
