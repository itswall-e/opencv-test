/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opencv;

// Importamos las librerias
import java.awt.Color;
import java.awt.Image;
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
    
    // Constructor
    public ImageViewer(Mat matrix, String text)
    {   
        m = matrix;
        title = text;
        // valores de ancho y alto
        width = m.cols();
        height = m.rows();
    }
    
    // Inicializamos componentes
    public void show()
    {
        v = new JFrame(title);
        v.setSize(width + 20, height + 20);
        v.setResizable(false);
        v.setLayout(null);
        
        // Componentes
        mainPanel = new JPanel();
        mainPanel.setBounds(10, 10, width, height);
        mainPanel.setBackground(Color.white);
        mainPanel.setLayout(null);
        
        lblImg = new JLabel();
        lblImg.setBounds(0, 0, width, height);
        ImageProcessor imgPro = new ImageProcessor();
        Image img = imgPro.toBufferedImage(m);
        lblImg.setIcon(new ImageIcon(img));
        
        // Añadimos componentes
        v.add(mainPanel);
        mainPanel.add(lblImg);
        
        // Mostramos ventana
        v.setVisible(true);
    }
}
