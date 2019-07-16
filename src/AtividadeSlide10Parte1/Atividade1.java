/*
 * Implemente um programa que calcule o fatorial de um numero em uma thread 
 * usando o Runnable
 */
package AtividadeSlide10Parte1;

/**
 *
 * @author rafael
 */
public class Atividade1 implements Runnable {
    
    public static void main(String[] args) {
        Atividade1 atividade1 = new Atividade1(4);
        atividade1.run();
        while (true) {
            if (atividade1.isFinish()) {
                System.out.println("Resultado: " + atividade1.getResult());
                break;
            }
        }
    }

    int result;
    int n;
    boolean finish;
    
    public Atividade1(int n) {
        this.finish = false;
        this.n = n;
        this.result = 1;
    }

    @Override
    public void run() {
        for (int i = 1; i <= this.n; i++) {
            this.result = this.result * i;
        }
        this.finish=true;
    }

    public boolean isFinish() {
        return finish;
    }

    public int getResult() {
        return result;
    }
}
