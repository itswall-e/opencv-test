/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opencv;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

/**
 *
 * @author paulo-andrade
 */
public class ImageFiltering {
    Mat kernel3, kernel5;
    
    // constructor de la clase
    public ImageFiltering()
    {
        kernel3 = new Mat(3, 3, CvType.CV_32F);
        kernel5 = new Mat(5, 5, CvType.CV_32F);
    }
    
    // llenado de matrices
    private void refill(int[] values)
    {
        int size = values.length;
        int count = 0;
        
        // verificamos que tipo de matriz utilizamos
        switch (size) {
            case 9:
                // recorremos la matriz
                for(int i = 0; i < 3; i++){
                    for(int j = 0; j < 3; j++){
                        // llenamos la matriz con los valores dados
                        kernel3.put(i, j, values[count]);
                        count++; // aumentamos el indice
                    }
                }
                break;
            case 25:
                // recorremos la matriz
                for(int i = 0; i < 5; i++){
                    for(int j = 0; j < 5; j++){
                        // llenamos la matriz con los valores dados
                        kernel5.put(i, j, values[count]);
                        count++; // aumentamos el indice
                    }
                }
                break;
            default:
                System.out.println("Error: Matriz kernel incompleta");
                break;
        }
    }
    
    // filtro para suavizado de imagenes
    public Mat smoothing(Mat source){
        // llenamos la matriz kernel
        int[] values = new int[]{0, 0, 0, 0, 1, 0, 0, 0, 0};
        refill(values);
        // Creamos una matriz destino
        Mat dst = new Mat(source.rows(), source.cols(), source.type());
        // aplicamos el filtro
        Imgproc.filter2D(source, dst, -1, kernel3);
        // retornamos la imagen filtrada
        return dst;
    }
    
    // realzado de bordes highlight edges
    public Mat highlightEdges(Mat source){
        // llenamos la matriz kernel
        int[] values = new int[]{0, 0, 0, -1, 1, 0, 0, 0, 0};
        refill(values);
        // Creamos una matriz destino
        Mat dst = new Mat(source.rows(), source.cols(), source.type());
        // aplicamos el filtro
        Imgproc.filter2D(source, dst, -1, kernel3);
        // retornamos la imagen filtrada
        return dst;
    }
    
    // desenfoque
    public Mat blur(Mat source){
        // llenamos la matriz kernel
        int[] values = new int[]{0,0,0,0,0,0,1,1,1,0,0,1,1,1,0,0,1,1,1,0,0,0,0,0,0};
        refill(values);
        // Creamos una matriz destino
        Mat dst = new Mat(source.rows(), source.cols(), source.type());
        // aplicamos el filtro
        Imgproc.filter2D(source, dst, -1, kernel5);
        // retornamos la imagen filtrada
        return dst;
    }
}
