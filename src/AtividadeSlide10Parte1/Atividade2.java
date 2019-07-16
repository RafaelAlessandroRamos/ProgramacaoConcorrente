/*
 * Implemente um programa que calcule o fatorial de um numero em uma thread 
 * usando o Callable.
 */
package AtividadeSlide10Parte1;

import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rafael
 */
public class Atividade2 implements Callable<Integer> {

    public static void main(String[] args) {
        Atividade2 atividade2 = new Atividade2(4);
        try {
            System.out.println("Resultado: " + atividade2.call());
        } catch (Exception ex) {
            Logger.getLogger(Atividade2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    int n;

    public Atividade2(int n) {
        this.n = n;
    }

    @Override
    public Integer call() throws Exception {
        int result = 1;
        for (int i = 1; i <= this.n; i++) {
            result = result * i;
        }
        return result;
    }
}
