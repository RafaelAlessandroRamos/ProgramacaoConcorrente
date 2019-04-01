/*
 * Implemente o exemplo anterior usando Lambda Expression
 */
package AtividadeSlide3Parte1;

/**
 *
 * @author rafael
 */
public class Atividade1{

    public static void main(String[] args) {
        new Thread(() -> {
            System.out.println("Hello from a thread");
        }).start();
    }

}
