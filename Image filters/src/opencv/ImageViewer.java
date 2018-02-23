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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.opencv.core.Mat;

/**
 *
 * @author paulo-andrade
 */
public class ImageViewer extends JFrame implements ActionListener, ChangeListener
{
    String title;
    int width, height;
    JLabel lblImg;
    JButton btnOriginal, btnCopia;
    JPanel mainPanel, filterPanel;
    JScrollPane imageScrollPane;
    JRadioButton rSource, rSmoothing, rHighlightEdges, rBlur;
    ButtonGroup btnGroup;
    Mat source;
    ImageFiltering filter;
    
    // constructor
    public ImageViewer(String title, Mat source)
    {
        this.title = title;
        this.source = source;
        width = 800;
        height = 600;
        filter = new ImageFiltering();
        
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
        imageScrollPane.setPreferredSize(new Dimension(400, 400));
        imageScrollPane.setBounds(20, 20, 400, 400);
        
        filterPanel = new JPanel();
        filterPanel.setBounds(440, 20, 320, 560);
        filterPanel.setBorder(BorderFactory.createTitledBorder("Filtros disponibles"));
        filterPanel.setBackground(Color.WHITE);
        filterPanel.setLayout(null);
        
        rSource = new JRadioButton("Original");
        rSource.setBounds(10, 30, 300, 20);
        rSource.setBackground(Color.WHITE);
        rSource.setSelected(true);
        
        rSmoothing = new JRadioButton("Suavizado");
        rSmoothing.setBounds(10, 60, 300, 20);
        rSmoothing.setBackground(Color.WHITE);
        
        rHighlightEdges = new JRadioButton("Realzar bordes");
        rHighlightEdges.setBounds(10, 90, 300, 20);
        rHighlightEdges.setBackground(Color.WHITE);
        
        rBlur = new JRadioButton("Desenfoque");
        rBlur.setBounds(10, 120, 300, 20);
        rBlur.setBackground(Color.WHITE);
        
        btnGroup = new ButtonGroup();
        btnGroup.add(rSource);
        btnGroup.add(rSmoothing);
        btnGroup.add(rHighlightEdges);
        btnGroup.add(rBlur);
        
        // añadimos componentes
        add(mainPanel);
        mainPanel.add(imageScrollPane, BorderLayout.CENTER);
        mainPanel.add(filterPanel);
        filterPanel.add(rSource);
        filterPanel.add(rSmoothing);
        filterPanel.add(rHighlightEdges);
        filterPanel.add(rBlur);
        
        // eventos
        rSource.addChangeListener(this);
        rSmoothing.addChangeListener(this);
        rHighlightEdges.addChangeListener(this);
        rBlur.addChangeListener(this);
        
        // Mostramos la ventana
        setVisible(true);
    }
    
    // mostramos la imagen
    public void view(Mat m)
    {
        Image img = ImageProcessor.toBufferedImage(m);
        lblImg.setIcon(new ImageIcon(img));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Seleccionamos el boton
        
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if(e.getSource().equals(rSource)){
            if(rSource.isSelected()) view(source);
        } else if(e.getSource().equals(rSmoothing)){
            if(rSmoothing.isSelected()) view(filter.smoothing(source));
        } else if(e.getSource().equals(rHighlightEdges)){
            if(rHighlightEdges.isSelected()) view(filter.highlightEdges(source));
        } else if(e.getSource().equals(rBlur)){
            if(rBlur.isSelected()) view(filter.blur(source));
        }
    }
}