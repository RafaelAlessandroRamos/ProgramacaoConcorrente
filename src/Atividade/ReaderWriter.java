/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Atividade;

import java.util.concurrent.Semaphore;

/**
 *
 * @author rafael
 */
public class ReaderWriter {
    
    int numRedero = 0;
    Semaphore mutex = new Semaphore(1);
    Semaphore vlock = new Semaphore(1);
    
    public void startReader(){
        
    }
    
    
    
}
