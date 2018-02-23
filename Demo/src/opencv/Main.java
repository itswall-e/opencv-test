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
        // Mostramos la version actual de OpenCV
        System.out.println("Welcome to OpenCV " + Core.VERSION);
        // Creamos una matriz de 5 x 10
        Mat m = new Mat(5, 10, CvType.CV_8UC1, new Scalar(0));
        // Mostramos los detalles de la matriz
        System.out.println("OpenCV Mat: " + m);
        // Obtenemos la fila 1 de la matriz
        Mat mr1 = m.row(1);
        // Llenamos la fila con el escalar 1
        mr1.setTo(new Scalar(1));
        // Obtenemos la columna 5 de la matriz
        Mat mc5 = m.col(5);
        // Llenamos la columna con el escalar 5
        mc5.setTo(new Scalar(5));
        // Mostramos el contenido de la matriz.
        System.out.println("OpenCV Mat data:\n" + m.dump());
    }
}
