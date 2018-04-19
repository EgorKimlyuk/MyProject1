
package Drawing;

import java.awt.*;
import java.util.*;


public class MatrixMath{
    private static Matrix unitMatrix(){
        ArrayList<Vector3d> es= new ArrayList<Vector3d>();
        es.add(new Vector3d(Arrays.asList(new Double(1),new Double(0),new Double(0))));
        es.add(new Vector3d(Arrays.asList(new Double(0),new Double(1),new Double(0))));
        es.add(new Vector3d(Arrays.asList(new Double(0),new Double(0),new Double(1))));
        return new Matrix(es);
    }
    private static Matrix matrixMultiMatrix(Matrix a, Matrix b){
        Matrix result = new Matrix();
        for (int i = 0; i < 3; i++){
            Vector3d resultVector=new Vector3d();
            for (int j = 0; j < 3; j++) {
                Double r= a.get(i,0)*b.get(0,j)+
                        a.get(i,1)*b.get(1,j)+
                        a.get(i,2)*b.get(2,j);
                resultVector.add(r);
            }
            result.add(resultVector);
        }
        return result;
    }
    private static Vector3d matrixMultiVector3d(Matrix a, Vector3d v) {
        Vector3d result = new Vector3d();
        for (int i = 0; i < 3; i++){
            Double r= a.get(i,0)*+v.get(0)+
                    a.get(i,1)*v.get(1)+
                    a.get(i,2)*v.get(2);
            result.add(r);
        }
        return result;
    }
    public static Matrix createRotationMatrix(Double x,Double y,Double z){
        Matrix p = new Matrix();
        Matrix q = new Matrix();
        p=unitMatrix();
        p.set(1,1,Math.cos(x));
        p.set(1,2,-Math.sin(x));
        p.set(2,1,Math.sin(x));
        p.set(2,2,Math.cos(x));
        q=unitMatrix();
        q.set(2,2,Math.cos(y));
        q.set(2,0,-Math.sin(y));
        q.set(0,2,Math.sin(y));
        q.set(0,0,Math.cos(y));
        Matrix result = matrixMultiMatrix(p,q);
        p=unitMatrix();
        p.set(0,0,Math.cos(z));
        p.set(0,1,-Math.sin(z));
        p.set(1,0,Math.sin(z));
        p.set(1,1,Math.cos(z));
        result = matrixMultiMatrix(result,p);
        return result;
    }
    public static Point point3d(Double x, Double y, Double z, Matrix rotationMatrix){
        Vector3d v = new Vector3d();
        v.add(x);
        v.add(y);
        v.add(z);
        v= matrixMultiVector3d(rotationMatrix,v);
        Point p = new Point();
        p.x=v.get(0).intValue();
        p.y=v.get(1).intValue();
        return p;
    }
  
}
