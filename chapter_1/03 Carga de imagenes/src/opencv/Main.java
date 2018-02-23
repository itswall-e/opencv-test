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
 * @author paulo
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
        // cargamos la imagen
        String path = "api.png";
        Mat m = Imgcodecs.imread(path);
        
        // verificamos la carga de la imagen
        if(m.dataAddr() == 0){
            // mostramos error de carga
            System.out.println("Error al cargar la imagen: "+path);
        } else {
            // Mostramos la imagen
            ImageViewer view = new ImageViewer(m, "Cargando imagen con openCV");
            view.show();
        }
    }
}
