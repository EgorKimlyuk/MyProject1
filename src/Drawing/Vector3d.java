/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Drawing;


import java.util.ArrayList;
import java.util.List;

public class Vector3d{
    private ArrayList<Double> vector3d;
    public Vector3d(List<Double> list){
        vector3d= new ArrayList<>(list);
    }
    public Vector3d(){
        vector3d=new ArrayList<>();
    }
    public void add(Double xyz){
        vector3d.add(xyz);
    }
    public Double get(int i) {
        return vector3d.get(i);
    }
    public void set(int i, Double d){
        vector3d.set(i,d);
    }
}
