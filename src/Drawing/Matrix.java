
package Drawing;

import java.util.*;
import java.util.List;


public class Matrix{
    private ArrayList<Vector3d> matrix;
    public Matrix(List<Vector3d> list){
        matrix=new ArrayList<>(list);
    }
    public Matrix(){
        matrix=new ArrayList<>();
    }
    public Matrix(Matrix m){
        this(m.matrix);
    }
    public void add(Vector3d vector3d){
        matrix.add(vector3d);
    }
    public Double get(int i, int j){
        return matrix.get(i).get(j);
    }
    public void set(int i, int j, Double d){
        matrix.get(i).set(j,d);
    }
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }


}
