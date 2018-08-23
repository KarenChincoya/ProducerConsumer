/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Objects.Consumer;
import Objects.Mediator;
import Objects.Producer;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author karen
 */
public class MainFrame extends JFrame{
    private JPanel datosPnl;
    
    public MainFrame(Mediator mediator, Producer[] producers, Consumer[] consumers, Integer numProducers, Integer numConsumers){
        super.setLayout(new FlowLayout());
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setSize(400, 400);
        
        datosPnl = new JPanel();
        
        super.add(datosPnl);
        super.setVisible(true);
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
        new MainFrame(mediator, producers, consumers, numProducers, numConsumers);
    }
}
