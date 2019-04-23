/*
 * Faca uma thread Java que realize a leitura de um arquivo texto com frases e 
 * exiba as frases a cada 10 segundos.
 */
package AtividadeSlide3Parte2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rafael
 */
public class Atividade2 {

    public static void main(String[] args) {

        Thread t = new Thread(() -> {
            FileReader arq;
            BufferedReader lerArq = null;
            try {
                arq = new FileReader("src/AtividadeSlide3Parte2/frases.txt");
                lerArq = new BufferedReader(arq);
                while (lerArq.readLine() != null) {
                    System.out.println(lerArq.readLine());
                    Thread.sleep(10000);
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Atividade2.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Atividade2.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(Atividade2.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        t.start();
    }
}
