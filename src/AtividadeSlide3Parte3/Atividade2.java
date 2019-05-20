/*
 * Faça um programa em Java para testar um conjunto de operações sobre um grupo 
 * de threads. Use o ThreadGroup.
 */
package AtividadeSlide3Parte3;

/**
 *
 * @author rafael
 */
public class Atividade2 implements Runnable {

    @Override
    public void run() {

    }

    public static void main(String[] args) {

        System.out.println("Start main...");

        ThreadGroup group = new ThreadGroup("ThreadGoup");

        System.out.println("Group name : " + group.getName());

        Thread t1 = new Thread(group, "t1");
        Thread t2 = new Thread(group, "t1");
        Thread t3 = new Thread(group, "t1");
        Thread t4 = new Thread(group, "t1");

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        System.out.println("Threads ativas " + group.activeCount());
        System.out.println("Threads priority " + group.getMaxPriority());
        group.isDaemon();
        try {
            group.destroy();
        } catch (Exception e) {
            System.out.println("Sem threads ativas para destruir");
        }

        System.out.println("Stop main.");

    }
}
