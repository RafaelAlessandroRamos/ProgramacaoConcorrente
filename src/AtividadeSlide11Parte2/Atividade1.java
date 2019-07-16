/*
 * Faca um programa usando Threads e ConcurrentMap para calcular a frequencia
 * de cada letra em um texto.
 */
package AtividadeSlide11Parte2;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rafael
 */
public class Atividade1 {
    static int linhasToSerach = 3;
    static int numTasks = 10;
    
    
    public static void main(String[] args) {
        try {
            ConcurrentMap<String, Integer> letters = new ConcurrentHashMap<>();
            ExecutorService threadPool = Executors.newFixedThreadPool(numTasks);
            ArrayList<Future> listTarefas =  new ArrayList<>();
            
            Scanner file = new Scanner(new FileReader("src/AtividadeSlide11Parte2/frases.txt"));
            int countLinha = 0;
            String conteudo = "";
            
            while(file.hasNext()){
                if(countLinha >= linhasToSerach){
                    Task_CountLetters task = new Task_CountLetters(conteudo, letters);
                    Future future = threadPool.submit(task);
                    listTarefas.add(future);
                    countLinha = 0;
                    conteudo = "";
                }
                conteudo += file.nextLine();
                countLinha++;
            }
            System.out.println("Esperando Conclusão das Tarefas...");
            for(Future ft : listTarefas){
                while(!ft.isDone()){
                    Thread.sleep(1000);
                }
            }
            System.out.println("Resultado: ");
            for(String key : letters.keySet()){
                System.out.println(key+" "+letters.get(key));
            }
            System.out.println("EndCode!");
            threadPool.shutdown();
            
        } catch (FileNotFoundException | InterruptedException ex) {
            Logger.getLogger(Atividade1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

class Task_CountLetters implements Runnable {

    ConcurrentMap<String, Integer> letters;
    String conteudo;

    public Task_CountLetters(String conteudo, ConcurrentMap<String, Integer> letters) {
        this.letters = letters;
        this.conteudo = conteudo;
    }

    @Override
    public void run() {
        for (char letra : this.conteudo.toCharArray()) {
            if (this.letters.containsKey(Character.toString(letra))) {
                this.letters.put(Character.toString(letra), this.letters.get(Character.toString(letra))+1);
            } else {
                this.letters.put(Character.toString(letra), 1);
            }
        }
    }
}
