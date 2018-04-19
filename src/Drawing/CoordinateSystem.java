/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Drawing;

import javafx.geometry.Point3D;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CoordinateSystem{
    private static Double x=2.0, y=0.0, z=-2.0;
    private Matrix RotationMatrix;
    private List<Plane4p2D> plane4p2DList= new ArrayList<>();
    private List<Plane4p3D> plane4p3DList= new ArrayList<>();
    private List<Point3D> point3DList =new ArrayList<>();
    private static List<Point> pointList = new ArrayList<>();
    private Point3D min,max;
    private static Point3D coefficient;

    public CoordinateSystem(){
        RotationMatrix= MatrixMath.createRotationMatrix(x,y,z);
        min=new Point3D(0,0,0);
        max=new Point3D(300,300,300);
        coefficient =new Point3D(1,1,1);
    }
    private Point point3DIn2D(Point3D p3D){
        return MatrixMath.point3d((p3D.getX()-min.getX())* coefficient.getX(),
                (p3D.getY()-min.getY())* coefficient.getY(),
                (p3D.getZ()-min.getZ())* coefficient.getZ(),RotationMatrix);
    }
    private void refreshCoefficient(){
        Double x = 280/Math.abs(this.max.getX()- this.min.getX());
        Double y = 280/Math.abs(this.max.getY()-this.min.getY());
        Double z = 280/Math.abs(this.max.getZ()-this.min.getZ());
        coefficient=new Point3D(x,y,z);
    }
    public void recreateRotationMatrix(Double alfaX, Double alfaY, Double alfaZ){
        x=x+alfaX;
        y=y+alfaY;
        z=z+alfaZ;
        RotationMatrix = MatrixMath.createRotationMatrix(x,y,z);
       /* plane4p2DList.clear();
        for (Plane4p3D plane4p3D : plane4p3DList) {
            Plane4p2D buffPlane4p=new Plane4p2D();
            for (Point3D p3D: plane4p3D.Point3DList) {
                buffPlane4p.PointList.add(point3DIn2D(p3D));
            }
            plane4p2DList.add(buffPlane4p);
        }*/
        pointList.clear();
        for (Point3D point3D: point3DList) {
            pointList.add(point3DIn2D(point3D));
        }
    }
    public void setMinMax(Point3D min, Point3D max){
        this.min=min;
        this.max=max;

        RotationMatrix= MatrixMath.createRotationMatrix(x,y,z);
       /* plane4p2DList.clear();
        for (Plane4p3D plane4p3D : plane4p3DList) {
            Plane4p2D buffPlane4p=new Plane4p2D();
            for (Point3D p3D: plane4p3D.Point3DList) {
                buffPlane4p.PointList.add(point3DIn2D(p3D));
            }
            plane4p2DList.add(buffPlane4p);
        }*/
        pointList.clear();
        /*for (Point3D point3D: point3DList) {
            pointList.add(point3DIn2D(point3D));
        }*/ //ВОЗМОЖНО ЭТО ОЧЕНЬ ВАЖНО

        refreshCoefficient();
        //recreateRotationMatrix(x,y,z);
    }

    public List<LinePoint> getCoordinateAxis(){
        List<LinePoint> result= new ArrayList<>();
        LinePoint linePoint=new LinePoint();
        linePoint.start=MatrixMath.point3d(0.0,0.0,0.0,RotationMatrix);
        linePoint.end=MatrixMath.point3d(300.0,0.0,0.0,RotationMatrix);
        result.add(new LinePoint(linePoint));
        linePoint.end=MatrixMath.point3d(0.0,300.0,0.0,RotationMatrix);
        result.add(new LinePoint(linePoint));
        linePoint.end=MatrixMath.point3d(0.0,0.0,300.0,RotationMatrix);
        result.add(new LinePoint(linePoint));
        return result;
    }

    public List<LinePoint> getCoordinateNet() {
        List<LinePoint> result= new ArrayList<>();
        LinePoint linePoint=new LinePoint();
        for (int i = 0; i <= 10; i++) {
            linePoint.start=MatrixMath.point3d(0.0,new Double(i*30),0.0,RotationMatrix);
            linePoint.end=MatrixMath.point3d(300.0,new Double(i*30),0.0,RotationMatrix);
            linePoint.text = null;
            result.add(new LinePoint(linePoint));
            linePoint.start=MatrixMath.point3d(0.0,new Double(i*30),0.0,RotationMatrix);
            linePoint.end=MatrixMath.point3d(0.0,new Double(i*30),300.0,RotationMatrix);
            linePoint.text = i*Math.abs(min.getY()-max.getY())/10+min.getY();//по другому сделать
            result.add(new LinePoint(linePoint));
            linePoint.start=MatrixMath.point3d(new Double(i*30),0.0,0.0,RotationMatrix);
            linePoint.end=MatrixMath.point3d(new Double(i*30),300.0,0.0,RotationMatrix);
            linePoint.text = i*Math.abs(min.getX()-max.getX())/10+min.getX();
            result.add(new LinePoint(linePoint));
            linePoint.start=MatrixMath.point3d(new Double(i*30),0.0,0.0,RotationMatrix);
            linePoint.end=MatrixMath.point3d(new Double(i*30),0.0,300.0,RotationMatrix);
            linePoint.text = null;
            result.add(new LinePoint(linePoint));
            linePoint.start=MatrixMath.point3d(0.0,0.0,new Double(i*30),RotationMatrix);
            linePoint.end=MatrixMath.point3d(0.0,300.0,new Double(i*30),RotationMatrix);
            linePoint.text = i*Math.abs(min.getZ()-max.getZ())/10+min.getZ();
            result.add(new LinePoint(linePoint));
            linePoint.start=MatrixMath.point3d(0.0,0.0,new Double(i*30),RotationMatrix);
            linePoint.end=MatrixMath.point3d(300.0,0.0,new Double(i*30),RotationMatrix);
            linePoint.text = null;
            result.add(new LinePoint(linePoint));
        }
        return result;
    }
    public void addPlane(Plane4p3D plane4p3D){
        Plane4p2D buffPlane4p=new Plane4p2D();
        plane4p3DList.add(plane4p3D);
        for (Point3D p3D: plane4p3D.Point3DList) {
            buffPlane4p.PointList.add(point3DIn2D(p3D));
        }
        plane4p2DList.add(buffPlane4p);
    }
    public void addPoint3D(Point3D point3D){
        point3DList.add(point3D);
        pointList.add(point3DIn2D(point3D));
    }

    public void clearPoint3D()
    {
        point3DList.clear();
    }
    public List<Point> getPointList(){
        return pointList;
    }
    public List<Plane4p2D> getListPlane(){return plane4p2DList;}
}
