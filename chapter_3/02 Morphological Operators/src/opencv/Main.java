/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opencv;

// Importamos las librerias
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

/**
 *
 * @author Paulo Andrade
 */
public class Main
{   
    // Cargamos las librerias dinamicas de OpenCV
    static{ System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        // Ruta de la imagen a mostrar
        String path = "img_morphological.jpg";

        // cargamos la imagen
        Mat img = Imgcodecs.imread(path);
        
        // creamos la ventana y mostramos la imagen
        ImageViewer view = new ImageViewer("Operaciones morfologicas", img);
    }
}
