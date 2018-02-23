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
public class Main {
    // Cargamos las librerias dinamicas de OpenCV
    static{ System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }
    
    // Metodo principal
    public static void main(String[] args)
    {
        // Creamos una matriz de 5 x 10
        Mat m = new Mat(new Size(3, 3), CvType.CV_8UC3, new Scalar(new double[]{1,2,3}));
        // Mostramos las filas
        System.out.println("rows: " + m.rows());
        // Mostramos las columnas
        System.out.println("cols: " + m.cols());
        // Mostramos el numero de elementos
        System.out.println("elem: " + m.elemSize());
        // Mostramos los detalles de la matris
        System.out.println("details: " + m);
        // Mostramos el contenido de la matriz.
        System.out.println(m.dump());
    }
}