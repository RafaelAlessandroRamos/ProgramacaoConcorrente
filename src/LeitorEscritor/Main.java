/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LeitorEscritor;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rafael
 */
public class Main {

}

class Leitor extends Thread {

    String texto;

    public Leitor(String texto) {
        this.texto = texto;
    }

    @Override
    public void run() {
        while (true) {

        }
    }

    public void sleep(int val) {
        try {
            Thread.sleep(val);
            System.out.println("Thread Consumidor Acordou!");
        } catch (InterruptedException ex) {
            Logger.getLogger(Leitor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

class Escritor extends Thread {

    String texto;

    public Escritor(String texto) {
        this.texto = texto;
    }

    @Override
    public void run() {
        while (true) {

        }
    }

    public void sleep(int val) {
        try {
            Thread.sleep(val);
            System.out.println("Thread Consumidor Acordou!");
        } catch (InterruptedException ex) {
            Logger.getLogger(Escritor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
