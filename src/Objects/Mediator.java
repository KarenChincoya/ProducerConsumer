/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objects;

/**
 *
 * @author karen
 */
public class Mediator extends Thread{
    private boolean status;
/*    private Producer[] producers;
    private Consumer[] consumers;
*/  
    private Integer sleepSeconds;
    private Integer numProducers;
    private Integer numConsumers;
    private Integer turno;
    
    private Integer[] productsItems;
    private Integer[] demandItems;
    private boolean[] permission;
    /*
        ,
    */
    public Mediator(Integer sleepSeconds, Integer numProducers, Integer numConsumers){
        this.status = true;
        this.sleepSeconds = sleepSeconds;
        this.numConsumers = numConsumers;
        this.numProducers = numProducers;
        this.productsItems = new Integer[numProducers];
        this.demandItems = new Integer[numConsumers];
        
        for(int i=0; i< numProducers; i++){
            productsItems[i] = 0;
        }
        for(int i=0; i< numConsumers; i++){
            demandItems[i] = 0;
        }
        this.permission = new boolean[numConsumers];
        for(int i=0; i<this.numConsumers; i++){
            permission[i] = true;
        }
        this.turno = 0;
    }
    /*
    public void setData(Producer[] producers, Consumer[] consumers, Integer numProducers, Integer numConsumers){
        this.producers = producers;
        this.consumers = consumers;
        this.numConsumers = numConsumers;
        this.numProducers = numProducers;
    }
    */
    public boolean isStatus() {
        return status;
    }
    
    public void endGame(){
        this.status = false;
    }
    public synchronized void setItems(Integer producer, Integer items){
        this.productsItems[producer]+=items;
        System.out.println("Se agregaron "+items+" items a la bodega del productor "+producer);
    }
    public synchronized void setRequieredItems(Integer consumer, Integer items){
        this.demandItems[consumer] += items;
        System.out.println("Se agregaron "+items+" items a la deuda del consumidor "+consumer);
    } 
    
    private boolean allBlocked(){
        int cont = 0;
        for(int i=0; i<this.numConsumers; i++){
            if(this.permission[i]==false){
                cont++;
            }
        }
        if(cont==this.numConsumers) return true;
        return false;
    }
    private boolean isTurno(Integer consumer){
        if(this.allBlocked()){
             //desbloquea todas
             for(int i=0; i<this.numConsumers; i++){
                permission[i]=true;
            }
            return true;
        }else{
            return permission[consumer];
        }
    }
    
    public synchronized void getItems(Integer producer, Integer consumer, Integer dItems){
            /*Le quita a producer para darle a consumer*/
            System.out.println("``````````````````````````````````````````getItems");
        int enBodega = this.productsItems[producer];
        if(this.isTurno(consumer)){
                System.out.println("hay "+enBodega+" items en la bodega del producer "+producer);
            
            if(dItems==0){           
                this.permission[consumer] = false;            
            } else if(enBodega>0){
                if(enBodega>dItems){
                    System.out.println("******************* *********** Productor = "+producer+" tiene en bodega = "
                        +this.productsItems[producer]+" ; el consumidor  "+consumer+" está pidiendo "+dItems+
                        " Se consumieron "+dItems+" items");
                    
                    this.productsItems[producer]-=dItems;
                    this.demandItems[consumer]-=dItems;
                    this.permission[consumer] = false;
                        
                }else {
                    System.out.println("******************* *********** Productor = "+producer+" tiene en bodega = "
                        +this.productsItems[producer]+" ; el consumidor  "+consumer+" está pidiendo "+dItems+
                        " Se consumieron "+enBodega+" items");
                    
                    this.productsItems[producer]=0;
                    this.demandItems[consumer]-=enBodega;
                    this.permission[consumer] = false;
                        
                }                   
            }else{
                System.out.println("******************* *********** Productor = "+
                        producer+" ; consumidor = "+consumer+" Se consumieron "+0+" items");
            }        
        }   
        
    }
    
    public void run(){
        //Funcion principal = asignar recursos, es un observador
        int num = 0;
        while(this.status){
            System.out.println("Status: iterando -> turno = "+num+" ~~~~~~~~~~~~~~~~~~~~~~");
                this.turno = num;
                num++;
                if(num == numConsumers){
                    num = 0;
                }
            try {
                Thread.sleep(sleepSeconds*1000);
            } catch (Exception e) {
            }
        }
    }

    public Integer getTurno() {
        return turno;
    }
    
    
    public static void main(String[] args) {
        Producer[] producers = new Producer[3];
        Consumer[] consumers = new Consumer[7];
        Integer numProducers = 1;
        Integer numConsumers = 2;
        //primero el mediador
        Mediator mediator = new Mediator(3, numProducers, numConsumers);
        mediator.start();
        
        for(int i=0; i<numProducers; i++){
            producers[i] = new Producer(i, 0, mediator, 5);
        }
        for(int j=0; j<numConsumers; j++){
            consumers[j] =  new Consumer(j, numProducers, mediator, 5);
        }
        //comenzar a correr los hilos
        for(int i=0; i<numProducers; i++){
            producers[i].start();
        }
        for(int j=0; j<numConsumers; j++){
            consumers[j].start();
        }
    }

}
