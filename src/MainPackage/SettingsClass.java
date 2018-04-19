package MainPackage;

import Forms.PictureForms.PictureJPanel;
import RegressionPackage.MultiRegress;
import RegressionPackage.SplitPoints;
import javafx.geometry.Point3D;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class SettingsClass {

    private static boolean OpenTheCoordinateSystem = false;
    private static int len = 3;
    private static int step = 1;
    private static boolean isThisPicture;
    private static ArrayList<Point3D> point3Ds;//набор точек полученный из изображения
    private static ArrayList<double[]> listOfProcessedPoints = new ArrayList<>();//набор точек, после проведения скользящей регрессии( которые попадут на график)
    private static BufferedImage bfImage;
    private static ArrayList<Color> listOfPixels;


    public static ArrayList<Point3D> getPoint3Ds() {
        return point3Ds;
    }

    public static void setPoint3Ds(ArrayList<Point3D> point3Ds) {
        SettingsClass.point3Ds = point3Ds;

        ArrayList<ArrayList<Point3D>> listOfList = SplitPoints.splitPoints(len,step,point3Ds);

        listOfProcessedPoints.clear();

        for(ArrayList<Point3D> a:listOfList){
            double mas[] = MultiRegress.regress(a);
            listOfProcessedPoints.add(mas);
        }
    }

    public static void setPoint3Ds(ArrayList<Point3D> point3Ds,boolean bool) {
        SettingsClass.point3Ds = point3Ds;

    }



    public static void setPoint3Ds(BufferedImage bfImage) {
        SettingsClass.bfImage = bfImage;
        listOfPixels = PictureJPanel.makingListOfPixels(bfImage,SettingsClass.getLen(),SettingsClass.getStep());

    }

    public static ArrayList<double[]> getListOfProcessedPoints() {
        return listOfProcessedPoints;
    }

    public static void setListOfProcessedPoints(ArrayList<double[]> listOfProcessedPoints) {
        //SettingsClass.listOfProcessedPoints.clear();
        SettingsClass.listOfProcessedPoints = listOfProcessedPoints;
    }

    public static BufferedImage getBfImage() {
        return bfImage;
    }

    public static void setBfImage(BufferedImage bfImage) {
        SettingsClass.bfImage = bfImage;
    }

    public static ArrayList<Color> getListOfPixels() {
        return listOfPixels;
    }

    public static void setListOfPixels(ArrayList<Color> listOfPixels) {
        SettingsClass.listOfPixels = listOfPixels;
    }

    public static boolean isIsThisPicture() {
        return isThisPicture;
    }

    public static void setIsThisPicture(boolean isThisPicture) {
        SettingsClass.isThisPicture = isThisPicture;
    }

    public static boolean isOpenTheCoordinateSystem() {
        return OpenTheCoordinateSystem;
    }

    public static void setOpenTheCoordinateSystem(boolean openTheCoordinateSystem) {
        OpenTheCoordinateSystem = openTheCoordinateSystem;
    }

    public static int getLen() {
        return len;
    }

    public static void setLen(int len) {
        SettingsClass.len = len;
    }

    public static int getStep() {
        return step;
    }

    public static void setStep(int step) {
        SettingsClass.step = step;
    }
}
