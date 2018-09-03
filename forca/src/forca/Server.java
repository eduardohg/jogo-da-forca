package forca;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.*;
import java.util.Hashtable;

public class Server {
	public static String str1;
	private static int acertos=0;
	private static int port = 12346;
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		Hashtable letras = new Hashtable(30);
		int erro=0;
		String err;
		
		ServerSocket server = new ServerSocket(port);
		System.out.println("Porta " + port + " aberta!");
		System.out.println("Esperando Jogador...");
		Socket client = server.accept();		
		System.out.println("Jogador: " + client.getInetAddress().getHostAddress() + " conectado na porta " + port);
		
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		OutputStream ostream = client.getOutputStream();
		PrintWriter pwrite = new PrintWriter(ostream, true);

		InputStream istream = client.getInputStream();
		BufferedReader receiveRead = new BufferedReader(new InputStreamReader(istream));

		String receiveMessage, sendMessage;
		
		System.out.println("\n\nDigite o tema: ");
		sendMessage = read.readLine();
		pwrite.println(sendMessage);
		pwrite.flush();
		
		System.out.println("Digite a palavra: ");
		sendMessage = read.readLine();
		pwrite.println(sendMessage);
		pwrite.flush();
		
		sendMessage = sendMessage.toLowerCase();
		
//		Coloca underlines
		formataString(sendMessage.length());
		
		while (true) {
			while (true) {//Aguarda confirmação
				if ((receiveMessage = receiveRead.readLine()) != null) { //receive from server
					break;
				}
			}
			
			//Envia a quantidade de erros atual
			err = convertErro(erro);
			pwrite.println(err);
			pwrite.flush();
			
			while (true) {//Aguarda confirmação
				if ((receiveMessage = receiveRead.readLine()) != null) { //receive from server
					break;
				}
			}
			
			//Envia a string correspondente a palavra a ser descoberta
			pwrite.println(str1);             
			pwrite.flush();
			
			if ((receiveMessage = receiveRead.readLine()) != null) {
				if (receiveMessage.length()>1) {//Se jogador optou por digitar palavra completa
					
					if (receiveMessage.equals(sendMessage)) {//Caso o jogador tenha acertado a palavra			
						acertouPalavra(receiveMessage);
						
						pwrite.println("Finalmente acertou!");
						pwrite.flush();
						
						while (true) {//Aguarda confirmação
							if ((receiveMessage = receiveRead.readLine()) != null) { //receive from server
								break;
							}
						}
						
						//Envia a string correspondente a palavra a ser descoberta
						pwrite.print(str1);
						pwrite.flush();
						
						break;
					}
					else {//Se o jogador perdeu...
						pwrite.println("Você perdeu...");
						pwrite.flush();
						
						while (true) {//Aguarda confirmação
							if ((receiveMessage = receiveRead.readLine()) != null) { //receive from server
								break;
							}
						}
						
						//Envia a string correspondente a palavra a ser descoberta
						pwrite.print(str1);
						pwrite.flush();
						
						break;
					}
				}
				else {//Se jogador optou por digitar letra
					
					//Pega char
					char c = receiveMessage.charAt(0);
					boolean letraRepetida = false;
					
					if (!letras.contains(c)) {						
						letras.put((int) c, (char) c); 

						//Verifica se existe a letra na string
						if(!trataRetorno(c, sendMessage)) {								
							erro++;
						}
					}
					else letraRepetida = true;
					
					if (erro == 6) {//Se o jogador perdeu...
						pwrite.println("Você perdeu...");             
						pwrite.flush();
						
						while (true) {//Aguarda confirmação
							if ((receiveMessage = receiveRead.readLine()) != null) { //receive from server
								break;
							}
						}
						
						//Envia a string correspondente a palavra a ser descoberta
						pwrite.print(str1);
						pwrite.flush();
						
						break;
					}
					else {
						if (acertos == sendMessage.length()) {//Caso o jogador tenha acertado a palavra		
							pwrite.println("Finalmente acertou!");
							pwrite.flush();
							
							while (true) {//Aguarda confirmação
								if ((receiveMessage = receiveRead.readLine()) != null) { //receive from server
									break;
								}
							}
							
							//Envia a string correspondente a palavra a ser descoberta
							pwrite.print(str1);
							pwrite.flush();
							
							break;
						}
						else {//Printa na tela do jogador a string formatada
							if (letraRepetida) {//Envia letra repetida para ser exibida na tela do jogador
								pwrite.println(c);
								pwrite.flush();
							}
							else {
								pwrite.println("Continua");
								pwrite.flush();
							}
						}
					}
				}
			}	
		}
		
		server.close();
	}
	
	public static boolean trataRetorno(char c, String sendMessage) {
		boolean logic = false;
		
		for (int i=0; i<sendMessage.length(); i++) {
			if (c == sendMessage.charAt(i)) {
				if (str1.charAt(i*2) != c) {					
					str1 = str1.substring (0, i*2) + c + str1.substring (i*2+1);
					acertos++;
				}
				logic = true;
			}
		}
		return logic;
	} 
	
	public static void acertouPalavra(String palavra) {
		str1 = "";
		
		for (int i=0; i<palavra.length(); i++) {
			str1 += palavra.charAt(i);
			str1 += " ";
		}
	}
	
	public static void formataString(int tam) {
		str1 = "";
		for(int i=0;i<tam-1;i++) {
			str1 = str1.concat("_ ");
		}
		str1 = str1.concat("_");
	}
	
	public static String convertErro(int erro) {
		switch(erro) {
			case 0:
				return "0";
			
			case 1:
				return "1";
				
			case 2:
				return "2";
			
			case 3:
				return "3";
				
			case 4:
				return "4";
			
			case 5:
				return "5";
				
			case 6:
				return "6";
				
			default:
				return null;
		}
	}

}