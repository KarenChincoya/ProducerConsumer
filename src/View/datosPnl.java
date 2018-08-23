/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author karen
 */
public class datosPnl extends JPanel{
    private JLabel lblConsumers;
    private JTextField txtConsumers;
    private JLabel lblProducers;
    private JTextField txtProducers;
    private JLabel lblInstructions;
    private JButton btnAceptar;
    
    public datosPnl(){
        super();
//        super.setLayout(new BoxLayout(datosPnl.this, BoxLayout.Y_AXIS));
        super.setLayout(new FlowLayout());
        lblInstructions = new JLabel("Ingrese los datos que se indican: ");
        lblConsumers = new JLabel("Consumidores: ");
        
        txtConsumers = new JTextField();
        txtConsumers.setPreferredSize(new Dimension(40,40));
        
        lblProducers = new JLabel("Productores: ");
 
        txtProducers = new JTextField();
        txtProducers.setPreferredSize(new Dimension(40,40));
        
        
        add(lblInstructions);
        add(lblConsumers);
        add(txtConsumers);
        add(lblProducers);
        add(txtProducers);
    }
}
