/*
 *Faca um programa multithreaded em Java que ordene um vetor usando o Merge Sort 
 * recursivo. Faca com que a thread principal dispare duas threads para 
 * classificar cada metade do vetor.
 */
package AtividadeSlide3Parte3;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rafael
 */
public class Atividade5 {
    
    public static void main(String[] args) {
        
        int n = (int)(Math.random()*100);
        int vetor[] = new int[n];
        
        for (int i = 0; i < n; i++) {
            vetor[i] = (int)Math.random()*100;
        }
        
        Thread thread = new Thread(new Merge(vetor, 0, vetor.length - 1));
        thread.start();
    }

}

class Merge implements Runnable {

    int vetor[];
    int inicio;
    int fim;

    public Merge(int[] vetor, int inicio, int fim) {
        this.vetor = vetor;
        this.inicio = inicio;
        this.fim = fim;
    }

    @Override
    public void run() {
        int meio;
        try {
            if (inicio < fim) {
                meio = (inicio + fim) / 2;
                Thread tm1 = new Thread(new Merge(this.vetor, this.inicio, meio));
                tm1.start();
                tm1.join();
                Thread tm2 = new Thread(new Merge(this.vetor, meio + 1, this.fim));
                tm2.start();
                tm2.join();
                Thread ti = new Thread(new Intercala(this.vetor, this.inicio, meio, this.fim));
                ti.start();
                ti.join();
            }
            System.out.println(this.vetor.toString());
        } catch (InterruptedException ex) {
            Logger.getLogger(Merge.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

class Intercala implements Runnable {

    int vetor[];
    int inicio;
    int meio;
    int fim;

    public Intercala(int[] vetor, int inicio, int meio, int fim) {
        this.vetor = vetor;
        this.inicio = inicio;
        this.meio = meio;
        this.fim = fim;
    }

    @Override
    public void run() {
        int i, j, k;
        int vetorB[] = new int[vetor.length];
        for (i = inicio; i < meio; i++) {
            vetorB[i] = vetor[i];
        }
        for (j = meio + 1; j < fim; j++) {
            vetorB[fim + meio + 1 - j] = vetor[j];
        }
        i = inicio;
        j = fim;
        for (k = inicio; k < fim; k++) {
            if (vetorB[i] <= vetorB[j]) {
                vetor[k] = vetorB[i];
                i = i + 1;
            } else {
                vetor[k] = vetorB[j];
                j = j - 1;
            }
        }
    }
}
