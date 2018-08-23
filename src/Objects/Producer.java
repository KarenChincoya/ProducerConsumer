package Objects;

import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author karen
 */
public class Producer extends Thread{
    private Integer id;
    private Integer items;
    private boolean limit;
    private Integer limitC;
    private Mediator mediator;
    private Integer sleepSeconds;
    
    public Producer(Integer id, Integer capacidad, Mediator mediator, Integer sleepSeconds){
        this.id = id;
        this.items = 0;
        this.mediator = mediator;
        if(capacidad!=0){
            this.limit = true;
            this.limitC = capacidad;
        }else{
            this.limit = false;
        }
        this.sleepSeconds = sleepSeconds;
    }
     
    public void run() {
        //su funcion principal es producir
        Random random = new Random();
        int r;
        while(this.mediator.isStatus()){
/*            System.out.println("Producer "+this.id+" running .......................................");*/

            r = random.nextInt(5)+1;
            mediator.setItems(this.id, r);
            
            yield();
            try {
                Thread.sleep(sleepSeconds*1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
}
