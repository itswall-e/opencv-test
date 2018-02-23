/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opencv;

// Importamos las librerias
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.opencv.core.Mat;

/**
 *
 * @author paulo
 */
public class ImageViewer
{
    JFrame v;
    JLabel lblImg;
    JPanel mainPanel;
    int width, height;
    String title;
    Mat m;
    ImageProcessor imgPro;
    
    // Constructor
    public ImageViewer(String text)
    {   
        // titulo de la ventana
        title = text;
        // valores de ancho y alto por defecto
        width = 640;
        height = 480;
        // Inicializamos el procesador de imagenes
        imgPro = new ImageProcessor();
        // Inicializamos los componentes de swing
        initComponents();
    }
    
    // Inicializamos componentes Swing
    public void initComponents()
    {
        v = new JFrame(title);
        v.setSize(width + 20, height + 20);
        v.setResizable(false);
        v.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        v.setLayout(null);
        
        // Componentes
        mainPanel = new JPanel();
        mainPanel.setBounds(10, 10, width, height);
        mainPanel.setBackground(Color.white);
        mainPanel.setLayout(null);
        
        lblImg = new JLabel();
        lblImg.setBounds(0, 0, width, height);
        
        // Añadimos componentes
        v.add(mainPanel);
        mainPanel.add(lblImg);
        
        // Mostramos ventana
        v.setVisible(true);
    }
    
    // Inicializamos componentes
    public void show(Mat m)
    {
        // Convertimos la matriz en imagen y la mostramos
        lblImg.setIcon(new ImageIcon(imgPro.toBufferedImage(m)));
    }
    
    // configuración
    public void set(Mat m)
    {
        // Actualizamos los valores
        width = m.cols();
        height = m.rows();
        // Redimencionamos los elementos
        v.setSize(width + 20, height + 20);
        lblImg.setBounds(0, 0, width, height);
        mainPanel.setBounds(10, 10, width, height);
    }
    
    // configuración
    public void set(int w, int h)
    {
        // Actualizamos los valores
        width = w;
        height = h;
    }
}
