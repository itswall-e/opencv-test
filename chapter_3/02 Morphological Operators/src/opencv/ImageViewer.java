/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opencv;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.opencv.core.Mat;

/**
 *
 * @author Paulo Andrade
 */
public class ImageViewer extends JFrame implements ItemListener
{
    String title;
    int width, height;
    JLabel lblImg;
    JPanel mainPanel, sPanel, rPanel, oPanel;
    JScrollPane imageScrollPane;
    JSlider sKernel;
    JRadioButton rRect, rCross, rEllipse, rErode, rDilate, rOpen, rClose;
    ButtonGroup btnGroup, btnGroupOp;
    Mat source;
    
    // constructor
    public ImageViewer(String title, Mat source)
    {
        this.title = title;
        this.source = source;
        width = 400;
        height = 630;
        
        initComponents();
    }
    
    // Inicializamos los componentes
    protected void initComponents()
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
        
        lblImg = new JLabel();
        Image img = ImageProcessor.toBufferedImage(source);
        lblImg.setIcon(new ImageIcon(img));
        
        imageScrollPane = new JScrollPane(lblImg);
        imageScrollPane.setPreferredSize(new Dimension(380, 400));
        imageScrollPane.setBounds(10, 10, 380, 400);
        
        sPanel = new JPanel();
        sPanel.setBounds(10, 420, 380, 80);
        sPanel.setBorder(BorderFactory.createTitledBorder("Tamaño del kernel"));
        sPanel.setBackground(Color.WHITE);
        sPanel.setLayout(null);
        
        rPanel = new JPanel();
        rPanel.setBounds(10, 510, 380, 50);
        rPanel.setBorder(BorderFactory.createTitledBorder("Forma del punto de anclaje"));
        rPanel.setBackground(Color.WHITE);
        rPanel.setLayout(null);
        
        oPanel = new JPanel();
        oPanel.setBounds(10, 570, 380, 50);
        oPanel.setBorder(BorderFactory.createTitledBorder("Operador morfologico"));
        oPanel.setBackground(Color.WHITE);
        oPanel.setLayout(null);
        
        sKernel = new JSlider(JSlider.HORIZONTAL, 3, 18, 3);
        sKernel.setBounds(10, 20, 360, 50);
        sKernel.setInverted(false); //se invierte el relleno del JSlider (desde donde comienza)
        sKernel.setPaintTicks(true); //las rayitas que marcan los números
        sKernel.setMajorTickSpacing(2); // de cuanto en cuanto los números en el slider
        sKernel.setMinorTickSpacing(1); //las rayitas de cuanto en cuanto
        sKernel.setPaintLabels(true); // Muestra las etiquetas de los valores
        sKernel.setSnapToTicks(true); // Solo elegir divisiones
        sKernel.setBackground(Color.WHITE);
        
        rRect = new JRadioButton("Rectangular");
        rRect.setBounds(10, 20, 120, 20);
        rRect.setBackground(Color.WHITE);
        rRect.setSelected(true);
        
        rCross = new JRadioButton("Cruz");
        rCross.setBounds(130, 20, 120, 20);
        rCross.setBackground(Color.WHITE);
        
        rEllipse = new JRadioButton("Elipse");
        rEllipse.setBounds(250, 20, 120, 20);
        rEllipse.setBackground(Color.WHITE);
        
        rErode = new JRadioButton("Erode");
        rErode.setBounds(10, 20, 82, 20);
        rErode.setBackground(Color.WHITE);
        
        rDilate = new JRadioButton("Elipse");
        rDilate.setBounds(102, 20, 82, 20);
        rDilate.setBackground(Color.WHITE);
        
        rOpen = new JRadioButton("Open");
        rOpen.setBounds(204, 20, 82, 20);
        rOpen.setBackground(Color.WHITE);
        
        rClose = new JRadioButton("Close");
        rClose.setBounds(306, 20, 60, 20);
        rClose.setBackground(Color.WHITE);
        
        // añadimos a radio buttons a un group
        btnGroup = new ButtonGroup();
        btnGroup.add(rRect);
        btnGroup.add(rCross);
        btnGroup.add(rEllipse);
        btnGroupOp = new ButtonGroup();
        btnGroupOp.add(rErode);
        btnGroupOp.add(rDilate);
        btnGroupOp.add(rOpen);
        btnGroupOp.add(rClose);
        
        // añadimos componentes
        add(mainPanel);
        mainPanel.add(imageScrollPane, BorderLayout.CENTER);
        mainPanel.add(sPanel);
        mainPanel.add(rPanel);
        mainPanel.add(oPanel);
        sPanel.add(sKernel);
        rPanel.add(rRect);
        rPanel.add(rCross);
        rPanel.add(rEllipse);
        oPanel.add(rErode);
        oPanel.add(rDilate);
        oPanel.add(rOpen);
        oPanel.add(rClose);
        
        // eventos
        sKernel.add
        rRect.addItemListener(this);
        rCross.addItemListener(this);
        rEllipse.addItemListener(this);
        rErode.addItemListener(this);
        rDilate.addItemListener(this);
        rOpen.addItemListener(this);
        rClose.addItemListener(this);
        
        // Mostramos la ventana
        setVisible(true);
    }
    
    /*
     * mostramos la imagen
     *
     * @param m Matriz a mostrar
     */
    public void view(Mat m)
    {
        Image img = ImageProcessor.toBufferedImage(m);
        lblImg.setIcon(new ImageIcon(img));
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        // Verificamos si el elemento fue seleccionado
        if(e.getStateChange() == ItemEvent.SELECTED){
            // Obtenemos el tamaño del kernel
            int sizeKernel = sKernel.getValue();

            // Obtenemos la forma seleccionada
            int shape = 0;
            if(rRect.isSelected()) shape = 0;
            if(rCross.isSelected()) shape = 1;
            if(rEllipse.isSelected()) shape = 2;

            // Aplicamos el operador morfologico
            if(e.getSource().equals(rErode) || rErode.isSelected()){
                this.view(MorphologicalOperator.erode(source, sizeKernel, shape));
            } else if(e.getSource().equals(rDilate) || rDilate.isSelected()){
                this.view(MorphologicalOperator.dilate(source, sizeKernel, shape));
            } else if(e.getSource().equals(rOpen) || rOpen.isSelected()){
                this.view(MorphologicalOperator.open(source, sizeKernel, shape));
            } else if(e.getSource().equals(rClose) || rClose.isSelected()){
                this.view(MorphologicalOperator.close(source, sizeKernel, shape));
            }
        }
    }
}
