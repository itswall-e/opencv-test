/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opencv;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

/**
 * Filtros para imagenes utilizando convolution
 * @author paulo-andrade
 */
public class Main {
    // Cargamos las librerias dinamicas de OpenCV
    static{ System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }
    
    public static void main(String[] args)
    {
        String path = "paisaje.jpg";

        // cargamos la imagen
        Mat img = Imgcodecs.imread(path);
        
        // creamos la ventana
        ImageViewer view = new ImageViewer("Filtros de imagenes", img);
    }
}
