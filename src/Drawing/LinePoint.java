/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Drawing;

import java.awt.*;

/**
 *
 * @author Егор
 */
public class LinePoint{
    public Point start=null;
    public Point end=null;
    public Double text=null;
    public LinePoint(){
    }
    public LinePoint(LinePoint linePoint){
        this.start=linePoint.start;
        this.end=linePoint.end;
        this.text=linePoint.text;
    }
}
