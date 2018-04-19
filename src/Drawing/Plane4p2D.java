/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Drawing;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Егор
 */
public class Plane4p2D 
{
    public List<Point> PointList=new ArrayList<>();
    public Plane4p2D(){
    }
    public Plane4p2D(Plane4p2D plane4p2D){
        this.PointList=plane4p2D.PointList;
    }

  
}