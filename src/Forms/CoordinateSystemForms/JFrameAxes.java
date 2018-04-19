package Forms.CoordinateSystemForms;

import Drawing.CoordinateSystem;
import Drawing.LinePoint;
import Drawing.Plane4p2D;
import MainPackage.SettingsClass;
import RegressionPackage.MultiRegress;
import RegressionPackage.SplitPoints;
import javafx.geometry.Point3D;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class JFrameAxes extends JFrame {

    public double MaxX = 100;
    public double MaxY = 100;
    public double MaxZ = 100;
    public double MinX = -100;
    public double MinY = -100;
    public double MinZ = -100;

    public JFrameAxes(CoordinateSystemForm coordinateSystem) {
        this.setSize(JFrameAxes.DEFAULT_WIDTH,JFrameAxes.DEFAULT_HEIGHE);
        this.coordinateSystem = coordinateSystem;
        this.point3dMin = new Point3D(MinX,MinY,MinZ);
        this.point3dMax = new Point3D(MaxX,MaxY,MaxZ);
        CS = new CoordinateSystem();
        CS.setMinMax(this.point3dMin,this.point3dMax);
        //this.len = len;
        //this.step = step;
        //this.listOfList = SplitPoints.splitPoints(len,step,point3Ds);

        this.listOfProcessedPoints = SettingsClass.getListOfProcessedPoints();

        addPointsToCS(CS,this.listOfProcessedPoints);
        initWindowActions();

    }

    private void addPointsToCS(CoordinateSystem CS,ArrayList<double[]> listOfProcessedPoints){
        for(double[] a:listOfProcessedPoints){
            //double mas[] = MultiRegress.regress(a);
            //listOfProcessedPoints.add(mas);
            CS.addPoint3D(new Point3D(a[0], a[1], a[2]));
        }
    }
    private void initWindowActions()
    {
        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) { }

            @Override
            public void windowClosing(WindowEvent e) {coordinateSystem.dispose();}

            @Override
            public void windowClosed(WindowEvent e) {}

            @Override
            public void windowIconified(WindowEvent e) {}

            @Override
            public void windowDeiconified(WindowEvent e) {}

            @Override
            public void windowActivated(WindowEvent e) {}

            @Override
            public void windowDeactivated(WindowEvent e) {}
        });
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(new Color(255, 255, 255));
        g.fillRect(0, 0, this.getWidth(),this.getHeight());
        g.setColor(new Color(0, 0, 0));
        List<LinePoint> linePointList = CS.getCoordinateAxis();
        Integer divWidth=this.getWidth()/2;
        Integer divHeigth=this.getHeight()/2;
        for (LinePoint linePoint: linePointList) {
            g.drawLine(divWidth + linePoint.start.x, divHeigth + linePoint.start.y, divWidth + linePoint.end.x, divHeigth + linePoint.end.y);
        }
        linePointList.clear();
        linePointList = CS.getCoordinateNet();


        for (LinePoint linePoint: linePointList) {
            g.setColor(new Color(150, 150, 150));
            g.drawLine(divWidth + linePoint.start.x, divHeigth + linePoint.start.y, divWidth + linePoint.end.x, divHeigth + linePoint.end.y);
            g.setColor(new Color(0, 0, 0));
            if (linePoint.text!=null) g.drawString(linePoint.text.toString(),divWidth+linePoint.end.x,divHeigth+linePoint.end.y);
        }

        Polygon polygon = new Polygon();
        List<Plane4p2D> plane4p2DList = CS.getListPlane();
        for (Plane4p2D plane4p2D : plane4p2DList) {
            polygon.reset();
            for (Point point : plane4p2D.PointList) {
                polygon.addPoint(divWidth + point.x, divHeigth + point.y);
            }
            g.setColor(new Color(197, 184, 225));
            g.fillPolygon(polygon);
            g.setColor(new Color(0, 0, 0));
            g.drawPolygon(polygon);
        }
        List<Point> pointList=CS.getPointList();
        final int SIZE_POINT=1;
        for (Point point: pointList) {
            polygon.reset();
            polygon.addPoint(divWidth+point.x-SIZE_POINT,divHeigth+point.y-SIZE_POINT);
            polygon.addPoint(divWidth+point.x+-SIZE_POINT,divHeigth+point.y+SIZE_POINT);
            polygon.addPoint(divWidth+point.x+SIZE_POINT,divHeigth+point.y+SIZE_POINT);
            polygon.addPoint(divWidth+point.x+SIZE_POINT,divHeigth+point.y-SIZE_POINT);
            g.setColor(new Color(32, 26, 249));
            g.fillPolygon(polygon);
        }
    }



    //Изменение масштаба
    public void ButtonPerfomed(double newValue,int parametr, boolean PlusMinus) throws NumberFormatException
    {
        if (PlusMinus) {
            switch (parametr) {
                case 0:
                    this.MaxX = newValue;
                    break;
                case 1:
                    this.MaxY = newValue;
                    break;
                case 2:
                    this.MaxZ = newValue;
                    break;
            }
            point3dMax = new Point3D(this.MaxX,this.MaxY,this.MaxZ);
        }
        else {
            switch (parametr) {
                case 0:
                    this.MinX = newValue;
                    break;
                case 1:
                    this.MinY = newValue;
                    break;
                case 2:
                    this.MinZ = newValue;
                    break;
            }
            point3dMin = new Point3D(this.MinX,this.MinY,this.MinZ);
        }
        //CS = new CoordinateSystem();              //пока не понятно зачем создавать новый объект
        CS.clearPoint3D();
        CS.setMinMax(point3dMin,point3dMax);

        for (double[] a:this.listOfProcessedPoints) {
            CS.addPoint3D(new Point3D(a[0],a[1],a[2]));
        }

        if(this.coordinateSystem.getjCheckBoxChangeInRealTime()) {
            repaint();
        }
    }

    //Поворот вокруг оси
    public void TurnCS(int parametr,boolean PlusMinus)
    {
        if (PlusMinus) {
            switch (parametr) {
                case 0:
                    CS.recreateRotationMatrix(0.05, 0.0, 0.0);
                    break;
                case 1:
                    CS.recreateRotationMatrix(0.00, 0.05, 0.0);
                    break;
                case 2:
                    CS.recreateRotationMatrix(0.0, 0.0, 0.05);
                    break;
            }
        }
        else {
            switch (parametr) {
                case 0:
                    CS.recreateRotationMatrix(-0.05, 0.0, 0.0);
                    break;
                case 1:
                    CS.recreateRotationMatrix(0.00, -0.05, 0.0);
                    break;
                case 2:
                    CS.recreateRotationMatrix(0.0, 0.0, -0.05);
                    break;
            }

        }
        if(this.coordinateSystem.getjCheckBoxChangeInRealTime()) {
            this.repaint();
        }
    }

    public void refreshCoordinateSystem(){
        this.repaint();
    }
    private static final int DEFAULT_WIDTH = 700;
    private static final int DEFAULT_HEIGHE = 700;
    private static CoordinateSystem CS =null;
    private ArrayList<ArrayList<Point3D>>  listOfList = null;
    private ArrayList<double[]> listOfProcessedPoints; //хранит точкки, которые мы уже прогнали через скользящую регрессию
    private Point3D point3dMax = null;
    private Point3D point3dMin = null;
    private int step = 1;
    private int len = 3;
    private CoordinateSystemForm coordinateSystem;
}
