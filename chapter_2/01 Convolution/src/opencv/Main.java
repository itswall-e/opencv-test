/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opencv;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

/**
 *
 * @author paulo-andrade
 */
public class Main
{    
    // Cargamos las librerias dinamicas de OpenCV
    static{ System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }
    
    public static void main(String[] args)
    {
        String path = "paisaje.jpg";
        int kernelSize = 3;

        // cargamos la imagen en escala de grises
        Mat img = Imgcodecs.imread(path, Imgcodecs.CV_LOAD_IMAGE_GRAYSCALE);

        // Creamos la matriz para la imagen filtrada
        Mat dst = new Mat(img.rows(), img.cols(), img.type());

        // Creamos la matriz kernel
        Mat kernel = new Mat(kernelSize, kernelSize, CvType.CV_32F){
            {
                // fila 0
                put(0, 0, 0);
                put(0, 1, 0);
                put(0, 2, 0);
                
                // fila 2
                put(1, 0, -1);
                put(1, 1, 1);
                put(1, 2, 0);
                
                // fila 3
                put(2, 0, 0);
                put(2, 1, 0);
                put(2, 2, 0);
            }
        };
        
        // Obtenemos la imagen con filtro
        Imgproc.filter2D(img, dst, -1, kernel);
        
        // Creamos la ventana
        ImageViewer v = new ImageViewer("Convolution 2D", img.cols(), img.rows());
        // Pasamos las imagenes
        v.setImg(img, dst);
        // Mostramos la imagen original
        v.view(img);
    }
}
