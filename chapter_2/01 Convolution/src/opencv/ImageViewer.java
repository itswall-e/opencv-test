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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.opencv.core.Mat;

/**
 *
 * @author paulo-andrade
 */
public class ImageViewer extends JFrame implements ActionListener
{
    String title;
    int width, height;
    JLabel lblImg;
    JButton btnOriginal, btnCopia;
    JPanel mainPanel;
    ImageProcessor ip;
    Mat original, copia;
    
    // constructor
    public ImageViewer(String title, int width, int height)
    {
        this.title = title;
        this.width = width;
        this.height = height + 40;
        
        ip = new ImageProcessor();
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
        lblImg.setBounds(0, 0, width, height - 40);
        
        btnOriginal = new JButton("Mostrar original");
        btnOriginal.setBounds(10, height - 30, 120, 20);
        btnOriginal.setEnabled(false);
        
        btnCopia = new JButton("Filtrar");
        btnCopia.setBounds(140, height - 30, 120, 20);
        
        // añadimos componentes
        add(mainPanel);
        mainPanel.add(lblImg);
        mainPanel.add(btnOriginal);
        mainPanel.add(btnCopia);
        
        // eventos
        btnOriginal.addActionListener(this);
        btnCopia.addActionListener(this);
        
        // Mostramos la ventana
        setVisible(true);
    }
    
    // configuramos la simagenes a utilizar
    public void setImg(Mat original, Mat copia)
    {
        this.original = original;
        this.copia = copia;
    }
    
    // mostramos la imagen
    public void view(Mat m)
    {
        Image img = ip.toBufferedImage(m);
        lblImg.setIcon(new ImageIcon(img));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Seleccionamos el boton
        if(e.getSource().equals(btnOriginal)){
            // desactivamos el boton original
            btnOriginal.setEnabled(false);
            // activamos el boton copia
            btnCopia.setEnabled(true);
            
            // Mostramos la imagen original
            view(original);
        } else if(e.getSource().equals(btnCopia)){
            // desactivamos el boton copia
            btnCopia.setEnabled(false);
            // activamos el boton original
            btnOriginal.setEnabled(true);
            
            // mostramos la imagen filtrada
            view(copia);
        }
    }
}
