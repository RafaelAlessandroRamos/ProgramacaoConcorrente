/*
 * Implemente o exemplo de c´odigo que gera a condicao de disputa e tente gerar 
 * um teste para que ocorra um problema de seguran¸ca (safety).
 */
package AtividadeSlide3Parte1;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rafael
 */
public class Atividade3 extends Thread {

    Contador contador = new Contador();

    public void run() {
        this.contador.cont++;
        System.out.println(this.contador.cont);
        try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                System.out.println("Deu merda no sleep");
            }

    }

    public static void main(String[] args) {

        for (int i = 0; i < 500; i++) {
            Atividade3 atividade3 = new Atividade3();
            atividade3.start();
            
        }
    }
}

class Contador {

    int cont = 0;
}
