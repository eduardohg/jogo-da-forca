/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forca;

import java.util.Random;

/**
 *
 * @author eduardo and renan
 */
public class Palavra {
    private String temas[] = {"Fruta","Carro"};
    private String palavras[] = {"Maça","Laranja","Limão","Banana","Melancia","Siena","Corsa","Camaro","Celta","Ferrari"};
    
    
    String getPalavra(int index){
        return palavras[index];
    }
    
    int getIndexAleatorio(){
        Random r = new Random();
        return r.nextInt(10);
    }
    
    String getTema(int index){
        int id = index/5;
        return temas[id];
    }
    
}
