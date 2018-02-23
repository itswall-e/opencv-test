/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opencv;

// Importamos las librerias
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.CvType;
import org.opencv.core.Scalar;
import org.opencv.core.Size;

/**
 *
 * @author paulo
 */
public class Main
{
    // Cargamos las librerias dinamicas de OpenCV
    static{ System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }
    
    // Metodo principal
    public static void main(String[] args)
    {
        // Creamos una matriz de 5 x 10
        Mat img = new Mat(new Size(3, 3), CvType.CV_8UC3, new Scalar(new double[]{1,2,3}));
        // Imprimimos la matriz original
        System.out.println(img.dump());
        // Obtenemos el tamaño total de la imagen
        int size = (int) (img.total() * img.elemSize());
        // Creamos una matriz de bytes del tamaño de la matriz original
        byte buffer[] = new byte[size];
        // volcamos la imagen de OpenCV a la de Java
        img.get(0, 0, buffer);
        // Recorremos cada uno de los dato de la imagen
        for(int i=0; i < size; i++){
            // manipulamos la imagen
            if(i % 3 == 0) buffer[i] = 0; 
        }
        // volcamos la imagen de Java a la de OpenCV
        img.put(0, 0, buffer);
        // Imprimimos la matriz modificada
        System.out.println(img.dump());
    }
}