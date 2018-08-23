/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objects;

import java.util.Random;

/**
 *
 * @author karen
 */
public class Consumer extends Thread{
    private Integer[] productsRequiered;
    private Mediator mediator;
    private Integer producers;
    private Integer sleepSeconds;
    private Integer id;
    
    public Consumer(Integer id, Integer producers, Mediator mediator, Integer sleepSeconds){
        this.id = id;
        productsRequiered = new Integer[producers];
        for(int i=0; i<producers; i++){
            productsRequiered[i]=0;
        }
        this.producers = producers;
        this.mediator = mediator;
        this.sleepSeconds = sleepSeconds;
    }
    
    
    public void run(){
        Random random = new Random();
        int r;
        while(mediator.isStatus()){   
           //es por turnos, entonces
/*            System.out.println("Consumer "+this.id+" running .......................................");*/
           int turno = mediator.getTurno();
           if(turno==id){
            for(int i=0; i<producers;i++){
               r = random.nextInt(5)+1;           
               mediator.setRequieredItems(turno, r);
               r = random.nextInt(5)+1;
               System.out.println("Consumidor ---> "+this.id+" realizo una solicitud al productor "+i+" de "+r+" elementos");
               mediator.getItems(i, this.id, r);       
                try {
                    yield();
                    Thread.sleep(sleepSeconds*1000);
                } catch (Exception e) {
                }
            }
           }else {
/*               System.out.println("NO ES EL TUNO DEL CONSUMER "+this.id);*/
           }
        }
    }
}
