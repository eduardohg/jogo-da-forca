package forca;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Client {

	public static Socket socket;

//	public Socket getSocket(){
//		return this.socket;
//	}

	public void runClient(String ip) throws Exception{
		this.socket = new Socket(ip, 12346);
		System.out.println("TESTEE");
		System.out.println("SOCKET >"+socket);
	}

	public String esperaTema() throws IOException {
		String receiveMessage;

		InputStream istream = socket.getInputStream();
        BufferedReader receiveRead = new BufferedReader(new InputStreamReader(istream));

		while (true) {
			if ((receiveMessage = receiveRead.readLine()) != null) { //receive from server
				System.out.println("O tema é " + receiveMessage); // displaying at DOS prompt
				break;
			}
		}
		return receiveMessage;
	}

	public int esperaTamPalavra(Socket cliente) throws IOException {
		String receiveMessage;
		int tam;

		InputStream istream = cliente.getInputStream();
		BufferedReader receiveRead = new BufferedReader(new InputStreamReader(istream));

		System.out.println("\nEsperando a palavra...");
		while (true) {
			if ((receiveMessage = receiveRead.readLine()) != null) //receive from server
			{
				tam = receiveMessage.length();
				System.out.println("A palavra tem tamanho "+tam+"\n\n");
				//          System.out.println(receiveMessage); // displaying at DOS prompt
				break;
			}
		}
		return tam;
	}
//		// TODO Auto-generated method stub
//		Scanner ler = new Scanner(System.in);
//		System.out.println("Digite seu nick de Jogador: ");
//		String nick = ler.next();
//
//		Socket cliente = new Socket("191.52.64.26", 12346);
//        System.out.println("O Jogador "+nick+" se conectou ao Jogo!");
//
//        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
////      PrintStream saida = new PrintStream(cliente.getOutputStream());
//
//
//        OutputStream ostream = cliente.getOutputStream();
//        PrintWriter pwrite = new PrintWriter(ostream, true);
//
//                                 // receiving from server ( receiveRead  object)
//        InputStream istream = cliente.getInputStream();
//        BufferedReader receiveRead = new BufferedReader(new InputStreamReader(istream));
//
////        System.out.println("Start the chitchat, type and press Enter key");
//
//        String receiveMessage, sendMessage;
//
//        System.out.println("\nEsperando o tema...");
//        while (true) {
//             if((receiveMessage = receiveRead.readLine()) != null) { //receive from server
//            	 System.out.println("O tema é "+receiveMessage); // displaying at DOS prompt
//            	 break;
//             }
//
//        }
//        System.out.println("\nEsperando a palavra...");
//        while (true) {
//        	if ((receiveMessage = receiveRead.readLine()) != null) //receive from server
//        	{
//        		int tam = receiveMessage.length();
//			   	System.out.println("A palavra tem tamanho "+tam+"\n\n");
//	//          System.out.println(receiveMessage); // displaying at DOS prompt
//			   	break;
//        	}
//        }
//
//        boolean logic = true;
//        boolean logic2;
//        while (logic) {
//        	logic2 = false;
//        	System.out.print("1 - Chutar Letra\n2 - Chutar palavra\n");
//        	int op = ler.nextInt();
//        	System.out.println("Digite:");
//        	if (op == 1) {
//        		sendMessage = read.readLine();  // keyboard reading
//            	if (sendMessage.length() == 1) {
//            		pwrite.println(sendMessage);       // sending to server
//            		pwrite.flush();                    // flush the data
//            	}
//            	else if (sendMessage.length() == 0) {
//            		System.out.println("Deve digitar pelo menos uma letra!\n");
//            		logic2 = true;
//            	}
//            	else {
//            		System.out.println("Só é permitido uma letra!\n");
//            		logic2 = true;
//            	}
//        	}
//        	else if (op == 2) {
//        		sendMessage = read.readLine();  // keyboard reading
//        		pwrite.println(sendMessage);    // sending to server
//        		pwrite.flush();                 // flush the data
//        	}
//        	else {
//        		System.out.println("Opção inválida!");
//        	}
//
//        	while (true && !logic2) {
//        		if ((receiveMessage = receiveRead.readLine()) != null) { //receive from server
//        			System.out.println(receiveMessage + "\n"); // displaying at DOS prompt
//
//			       	while (true) {
//		                if((receiveMessage = receiveRead.readLine()) != null) { //receive from server
//					       	if (receiveMessage.equals("Se fudeu...") ||  receiveMessage.equals("Finalmente acertou!")) {
//						       	System.out.println(receiveMessage); // displaying at DOS prompt
//					       		logic = false;
//					       	}
//
//					       	break;
//		                }
//			       	}
//
//			       	break;
//        		}
//        	}
//        }
//
//        ler.close();


}