/*
 * Fa¸ca um programa que receba um valor indicando um numero
 * de threads a serem instanciadas e teste os dois modos de criar threads em Java.
 * dica: use o Thread.sleep para pausar as threads por um intervalo de tempo.
 */
package AtividadeSlide3Parte1;

/**
 *
 * @author rafael
 */
public class Atividade2 {

    public static void criaThreads(int n) {
        for (int i = 0; i < n; i++) {
            new Thread(() -> {
                System.out.println("Hello from a thread");
            }).start();
        }
    }

    public static void main(String[] args) {
        criaThreads(5);
    }
}
