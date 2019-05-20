/*
 * Faca um programa em Java que realize uma busca paralela em um vetor de 
 * inteiros. Informe para o metodo: valor procurado, vetor de inteiros e o 
 * numero de threads.
 */
package AtividadeSlide3Parte3;

import java.util.Arrays;
import javax.swing.JOptionPane;

/**
 *
 * @author rafael
 */
public class Atividade4 {

    public static int[] subArray(int[] array, int beg, int end) {
        return Arrays.copyOfRange(array, beg, end + 1);
    }

    public static void setupBusca(int vp, int vetor[], int nt) {
        int n = (int)(vetor.length / nt);
        int inicio = 0;
        int fim = n;
        for (int i = 0; i < nt; i++) {
            Thread t = new Thread(new Busca(subArray(vetor, inicio, fim)));
            inicio = n;
            fim -= vetor.length - n;
        }
    }

    public static void main(String[] args) {

        int n = (int) (Math.random() * 100);
        int vetor[] = new int[n];

        for (int i = 0; i < n; i++) {
            vetor[i] = (int) (Math.random() * 100);
        }

        int vp = Integer.parseInt(JOptionPane.showInputDialog("valor procurado: "));
        int nt = Integer.parseInt(JOptionPane.showInputDialog("numero de threads: "));

        setupBusca(vp, vetor, nt);
    }
}

class Busca implements Runnable {

    int vetor[];

    public Busca(int vetor[]) {
        this.vetor = vetor;
    }

    @Override
    public void run() {

    }

}
