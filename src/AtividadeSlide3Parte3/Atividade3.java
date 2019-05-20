/*
 * Faca um programa em Java com threads que exiba os numeros primos entre 0 e 100000.
 */
package AtividadeSlide3Parte3;

/**
 *
 * @author rafael
 */
public class Atividade3 {

    public static void main(String[] args) {

        Thread t1 = new Thread(new ThreadA());
        Thread t2 = new Thread(new ThreadB());
        Thread t3 = new Thread(new ThreadC());
        Thread t4 = new Thread(new ThreadD());

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}

class ThreadA implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 25000; i++) {
            
            int divCount = 0;
            
            for (int j = 1; j <= i; j++) {
                if (i % j == 0) {
                    divCount++;
                }
            }
            
            if (divCount == 2) {
                System.out.println("Primo: " + i);
            }
        }
    }
}

class ThreadB implements Runnable {

    @Override
    public void run() {
        for (int i = 25000; i < 50000; i++) {
            int divCount = 0;
            
            for (int j = 1; j <= i; j++) {
                if (i % j == 0) {
                    divCount++;
                }
            }
            
            if (divCount == 2) {
                System.out.println("Primo: " + i);
            }
        }
    }
}

class ThreadC implements Runnable {

    @Override
    public void run() {
        for (int i = 50000; i < 75000; i++) {
            int divCount = 0;
            
            for (int j = 1; j <= i; j++) {
                if (i % j == 0) {
                    divCount++;
                }
            }
            
            if (divCount == 2) {
                System.out.println("Primo: " + i);
            }
        }
    }
}

class ThreadD implements Runnable {

    @Override
    public void run() {
        for (int i = 75000; i <= 100000; i++) {
            int divCount = 0;
            
            for (int j = 1; j <= i; j++) {
                if (i % j == 0) {
                    divCount++;
                }
            }
            
            if (divCount == 2) {
                System.out.println("Primo: " + i);
            }
        }
    }
}
