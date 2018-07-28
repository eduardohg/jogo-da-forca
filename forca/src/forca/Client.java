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

public class Client {
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		// TODO Auto-generated method stub
		Scanner ler = new Scanner(System.in);
		System.out.println("Digite seu nick de Jogador: ");
		String nick = ler.next();
		
		Socket cliente = new Socket("localhost", 12346);
        System.out.println("O Jogador "+nick+" se conectou ao Jogo!");

        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        PrintStream saida = new PrintStream(cliente.getOutputStream());
      

        OutputStream ostream = cliente.getOutputStream(); 
        PrintWriter pwrite = new PrintWriter(ostream, true);
    
                                 // receiving from server ( receiveRead  object)
        InputStream istream = cliente.getInputStream();
        BufferedReader receiveRead = new BufferedReader(new InputStreamReader(istream));
    
        System.out.println("Start the chitchat, type and press Enter key");
    
        String receiveMessage, sendMessage;
        
        System.out.println("Esperando o tema...");
        while(true) {
             if((receiveMessage = receiveRead.readLine()) != null) { //receive from server
            	 System.out.println("O tema é "+receiveMessage); // displaying at DOS prompt
            	 break;
             }
             	
        }
       System.out.println("Esperando a palavra...");
       while(true) {
    	   if((receiveMessage = receiveRead.readLine()) != null) //receive from server
           {
           	int tam = receiveMessage.length();
           	System.out.println("A palavra tem tamanho "+tam);
//          System.out.println(receiveMessage); // displaying at DOS prompt
           	break;
            
           }       
       }
               
        while(true)
        {

        	System.out.print("1 - Chutar Letra\n2 - Chutar palavra\n");
        	int op = ler.nextInt();
        	System.out.println("Digite:");
        	if(op == 1) {
        		sendMessage = read.readLine();  // keyboard reading
            	if(sendMessage.length() == 1) {
            		pwrite.println(sendMessage);       // sending to server
            		pwrite.flush();                    // flush the data
            		    
            	}
            	else {
            		System.out.println("Só é permitido uma letra!");
            	}       
        	}
        	else {
        		sendMessage = read.readLine();  // keyboard reading
        		pwrite.println(sendMessage);       // sending to server
        		pwrite.flush();                    // flush the data
        	}
//        	if((receiveMessage = receiveRead.readLine()) != null) //receive from server
//    		{
//    			System.out.println(receiveMessage); // displaying at DOS prompt
//    		}     
//        	receiveRead.readLine();
        	System.out.println(Server.str1);
        	 	   
         } 
	}

}
