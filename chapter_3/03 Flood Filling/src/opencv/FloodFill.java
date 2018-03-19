/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opencv;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import static org.opencv.imgproc.Imgproc.FLOODFILL_FIXED_RANGE;

/**
 *
 * @author Paulo Andrade
 */
public final class FloodFill
{
    /**
     * Utilizamos el filtro flood filling
     * 
     * @param source Matriz original
     * 
     * @return Devolvemos la matriz con el filtro aplicado
     */
    public static Mat fill(Mat source, int x, int y, int diff, int connectivity)
    {
        // Area afectada
        int area = 0;
        
        // Optenemos el punto
        Point p = new Point(x, y);
        
        // Obtenemos el lower y upper diff (tolerancia)
        Scalar lowerDiff = new Scalar(diff, diff, diff);
        Scalar upperDiff = new Scalar(diff, diff, diff);
        
        // Color a aplicar
        Scalar color = new Scalar(0, 255, 0);
        
        // Banderas
        int flags = connectivity | FLOODFILL_FIXED_RANGE;
        
        // Aplicamos el filtro
        area = Imgproc.floodFill(source, new Mat(), p, color, new Rect(),
                lowerDiff, upperDiff, flags);
        
        return source;
    }
}
