/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opencv;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

/**
 *
 * @author Paulo Andrade
 */
public final class MorphologicalOperator
{
    /*
     * Genera el kernel para los operadores morfologicos
     *
     * @param sizeKernel Dimensión del kernel a utilizar
     * @param elemntShape Tipo de forma a utilizar [0-rect,1-cross,2-ellipse]
     */
    private static Mat getKernelFromShape(int sizeKernel, int elementShape)
    {
        // Obtenemos el punto de anclaje para el kernel
        int elementSize = (sizeKernel - 1) / 2;
        
        // Creamos el kernel para los operadores morfologicos
        return Imgproc.getStructuringElement(elementShape,
                new Size(sizeKernel, sizeKernel),
                new Point(elementSize, elementSize));
    }
    
    /*
     * Operacion morfologica de erocion (reduce el brillo en la imagen)
     *
     * @param source Matriz de entrada
     * @param sizeKernel Dimensión del kernel a utilizar
     * @param elemntShape Tipo de forma a utilizar [0-rect,1-cross,2-ellipse]
     */
    public static Mat erode(Mat source, int sizeKernel, int elementShape){
        // Creamos la matriz destino
        Mat target = new Mat();
        // Obtenemos el kernel
        Mat kernel = getKernelFromShape(sizeKernel, elementShape);
        // aplicamos el operador morfologico
        Imgproc.erode(source, target, kernel);
        
        // retornamos la matriz modificada
        return target;
    }
    
    /*
     * Operacion morfologica de dilatacion (aumenta el brillo en la imagen)
     *
     * @param source Matriz de entrada
     * @param sizeKernel Dimensión del kernel a utilizar
     * @param elemntShape Tipo de forma a utilizar [0-rect,1-cross,2-ellipse]
     */
    public static Mat dilate(Mat source, int sizeKernel, int elementShape){
        // Creamos la matriz destino
        Mat target = new Mat();
        // Obtenemos el kernel
        Mat kernel = getKernelFromShape(sizeKernel, elementShape);
        // aplicamos el operador morfologico
        Imgproc.dilate(source, target, kernel);
        
        // retornamos la matriz modificada
        return target;
    }
    
    /*
     * Operacion morfologica de apertura
     *
     * @param source Matriz de entrada
     * @param sizeKernel Dimensión del kernel a utilizar
     * @param elemntShape Tipo de forma a utilizar [0-rect,1-cross,2-ellipse]
     */
    public static Mat open(Mat source, int sizeKernel, int elementShape){
        // Creamos la matriz destino
        Mat target = new Mat();
        // Obtenemos el kernel
        Mat kernel = getKernelFromShape(sizeKernel, elementShape);
        // aplicamos el operador morfologico
        Imgproc.morphologyEx(source, target, Imgproc.MORPH_OPEN, kernel);
        
        // retornamos la matriz modificada
        return target;
    }
    
    /*
     * Operacion morfologica de cierre
     *
     * @param source Matriz de entrada
     * @param sizeKernel Dimensión del kernel a utilizar
     * @param elemntShape Tipo de forma a utilizar [0-rect,1-cross,2-ellipse]
     */
    public static Mat close(Mat source, int sizeKernel, int elementShape){
        // Creamos la matriz destino
        Mat target = new Mat();
        // Obtenemos el kernel
        Mat kernel = getKernelFromShape(sizeKernel, elementShape);
        // aplicamos el operador morfologico
        Imgproc.morphologyEx(source, target, Imgproc.MORPH_CLOSE, kernel);
        
        // retornamos la matriz modificada
        return target;
    }
}
