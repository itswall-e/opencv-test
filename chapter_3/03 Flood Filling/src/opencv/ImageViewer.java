/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opencv;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import org.opencv.core.Mat;

/**
 *
 * @author Paulo Andrade
 */
public class ImageViewer extends JFrame implements MouseListener, ActionListener
{
    JPanel mainPanel, optionPanel;
    JLabel lblSource, lblDiff, lblConnectivity;
    JSlider sDiff;
    JButton btnReset;
    JRadioButton r4, r8;
    ButtonGroup btnGroup;
    Mat source, temp;
    String title;
    int width, height;
    
    /*
     * Constructor
     *
     * @param title Titulo de la ventana
     * @param source Matriz de la imagen inicial a mostrar
     */
    public ImageViewer(String title, Mat source)
    {
        this.title = title; // Titulo de la ventana
        this.source = source; // Matriz original
        temp = new Mat();
        source.copyTo(temp); // Copia de la matriz original
        width = 800;
        height = 440;
        
        initComponents();
    }
    
    /*
     * Inicializamos los componentes
     */
    private void initComponents()
    {
        setTitle(title);
        setSize(width, height + 25);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        
        // componentes
        mainPanel = new JPanel();
        mainPanel.setBounds(0, 0, width, height);
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setLayout(null);
        
        lblSource = new JLabel();
        Image img = ImageProcessor.toBufferedImage(source);
        lblSource.setIcon(new ImageIcon(img));
        lblSource.setBounds(20, 20, 400, 400);
        
        optionPanel = new JPanel();
        optionPanel.setBounds(440, 20, 340, 400);
        optionPanel.setBorder(BorderFactory.createTitledBorder("Options"));
        optionPanel.setBackground(Color.WHITE);
        optionPanel.setLayout(null);
        
        lblDiff = new JLabel("Lower and upper diff");
        lblDiff.setBounds(10, 20, 320, 20);
        
        sDiff = new JSlider(JSlider.HORIZONTAL, 0, 100, 25);
        sDiff.setBounds(10, 50, 320, 50);
        sDiff.setInverted(false); //se invierte el relleno del JSlider (desde donde comienza)
        sDiff.setPaintTicks(true); //las rayitas que marcan los números
        sDiff.setMajorTickSpacing(25); // de cuanto en cuanto los números en el slider
        sDiff.setMinorTickSpacing(1); //las rayitas de cuanto en cuanto
        sDiff.setPaintLabels(true); // Muestra las etiquetas de los valores
        sDiff.setSnapToTicks(true); // Solo elegir divisiones
        sDiff.setBackground(Color.WHITE);
        
        lblConnectivity = new JLabel("Connectivity");
        lblConnectivity.setBounds(10, 110, 320, 20);
        
        r4 = new JRadioButton("Neighborhood 4");
        r4.setBounds(10, 140, 320, 20);
        r4.setBackground(Color.WHITE);
        r4.setSelected(true);
        
        r8 = new JRadioButton("Neighborhood 8");
        r8.setBounds(10, 170, 320, 20);
        r8.setBackground(Color.WHITE);
        
        btnGroup = new ButtonGroup();
        btnGroup.add(r4);
        btnGroup.add(r8);
        
        btnReset = new JButton("Reset");
        btnReset.setBounds(10, 200, 100, 20);
        
        // añadimos componentes
        add(mainPanel);
        mainPanel.add(lblSource);
        mainPanel.add(optionPanel);
        optionPanel.add(lblDiff);
        optionPanel.add(sDiff);
        optionPanel.add(lblConnectivity);
        optionPanel.add(r4);
        optionPanel.add(r8);
        optionPanel.add(btnReset);
        
        // eventos
        lblSource.addMouseListener(this);
        btnReset.addActionListener(this);
        
        // Mostramos la ventana
        setVisible(true);
    }
    
    /**
     * Cambiamos la imagen a mostrar
     * 
     * @param m Matriz a mostrar
     */
    private void view(Mat m)
    {
        lblSource.setIcon(new ImageIcon(ImageProcessor.toBufferedImage(m)));
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // Code
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // Obtenemos las coordenadas
        int x = e.getX();
        int y = e.getY();
        int diff = sDiff.getValue();
        int connectivity = 0;
        
        // Obtenemos la conectividad
        if(r4.isSelected()){
            connectivity = 4;
        } else if(r8.isSelected()){
            connectivity = 8;
        }
        
        source = FloodFill.fill(source, x, y, diff, connectivity);
        view(source);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // Code
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // Code
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // Code
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(btnReset)){
            // Reseteamos la imagen
            temp.copyTo(source);
            // Mostramos la imagen reseteada
            view(source);
        }
    }
}
