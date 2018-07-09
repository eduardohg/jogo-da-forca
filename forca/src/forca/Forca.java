/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forca;

import java.util.Scanner;

/**
 *
 * @author eduardo and renan
 */
public class Forca {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String nome;
        int index;
        
        Scanner ler = new Scanner(System.in);
        
        System.out.println("Digite seu nome:");
        nome =ler.next();
        
        Palavra p1 = new Palavra();    
        index = p1.getIndexAleatorio();
        System.out.println("Palavra aleatoria: "+p1.getPalavra(index));
        System.out.println("O tema é : "+p1.getTema(index));
        
    }
    
}
