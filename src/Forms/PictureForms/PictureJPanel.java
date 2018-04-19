package Forms.PictureForms;

import AuxiliaryClases.ForPictureAnalyses.SupFunctions;
import Forms.SettingsForms.PicSettings;
import MainPackage.SettingsClass;
import RegressionPackage.MultiRegress;
import RegressionPackage.SplitPoints;
import com.sun.xml.internal.ws.api.config.management.policy.ManagementAssertion;
import javafx.geometry.Point3D;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class PictureJPanel extends JPanel {
    private BufferedImage image,storeNewPicture;
    private int width;
    private int height;

    //private static ArrayList<Color> listOfPixels;
    private static boolean hasStoreNewPicture;

    public PictureJPanel(BufferedImage bfImage,int width,int height)
    {
        this.image = bfImage;
        this.width = width;
        this.height = height;
        hasStoreNewPicture = false;

        storeNewPicture = new BufferedImage(width,height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = storeNewPicture.createGraphics();
        paintComponent(g2);
        hasStoreNewPicture = true;
    }


    //Превращаем рисунок в набор пикселей
    public static ArrayList<Color> makingListOfPixels(BufferedImage image,int len,int step) {
        ArrayList<Color> listOfPixels = fromImageToListOfPixels(image);

        //System.out.println(listOfPixels.size());
        ArrayList<Point3D> listofPoint = new ArrayList<>();
        for(Color x:listOfPixels){
            listofPoint.add(new Point3D(x.getRed(),x.getGreen(),x.getBlue()));
        }
        /*for (int i = 0; i <listOfPixels.size() ; i++) {
            System.out.println(i);
            System.out.println("point = "+listofPoint.get(i).getX()+" "+listofPoint.get(i).getY()+" "+listofPoint.get(i).getZ());
            System.out.println("pixle = "+listOfPixels.get(i).getRed()+" "+listOfPixels.get(i).getGreen()+" "+listOfPixels.get(i).getBlue());
        }*/
        listOfPixels.clear();
        ArrayList<ArrayList<Point3D>>  listOfList = SplitPoints.splitPoints(len,step,listofPoint);
        ArrayList<Point3D> anotherListofPoint = new ArrayList<>();
        int mass[] = new int[3];
        ArrayList<double[]> tempList = new ArrayList<>();
        for (int i = 0; i < listOfList.size(); i++) {
            try{
                //System.out.println(i);
                double mas[] = MultiRegress.regress(listOfList.get(i));
                double d[] = new double[3];
                d[0] = mas[0];
                d[1] = mas[1];
                d[2] = mas[2];
                //System.out.println(d[0]+" "+d[1]+" "+d[2]);
                tempList.add(d);

            }
            catch (Exception e){ }
        }
        double[] MaxMinX = SupFunctions.FindMaxMin(tempList,0);
        double[] MaxMinY = SupFunctions.FindMaxMin(tempList,1);
        double[] MaxMinZ = SupFunctions.FindMaxMin(tempList,2);


        for (int i = 0; i < listOfList.size(); i++) {
            try{
                //System.out.println(i);
                double mas[] = MultiRegress.regress(listOfList.get(i));
                mas[0] = ((mas[0]-MaxMinX[1])/(MaxMinX[0]-MaxMinX[1]))*256;
                mas[1] = ((mas[1]-MaxMinY[1])/(MaxMinY[0]-MaxMinY[1]))*256;
                mas[2] = ((mas[2]-MaxMinZ[1])/(MaxMinZ[0]-MaxMinZ[1]))*256;
                //System.out.println(mas[0]+" "+mas[1]+" "+mas[2]);
                if(mas[0]>255){
                    mas[0]=255;
                }
                if(mas[0]<0){
                    mas[0]=mas[0]*-1;
                }
                if(mas[1]>255){
                    mas[1]=255;
                }
                if(mas[1]<0){
                    mas[1]=-1*mas[1];
                    mas[1]=0;
                }
                if(mas[2]>255){
                    mas[2]=255;
                }
                if(mas[2]<0){
                    mas[2]=-1*mas[2];
                    mas[2]=0;
                }
                listOfPixels.add(new Color((int)mas[0],(int)mas[1],(int)mas[2]));
            }
            catch (Exception e){
                listOfPixels.add(new Color(255,255,255));

            }
        }
        return listOfPixels;

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(!hasStoreNewPicture) {
            int shift = 0;
            //System.out.println(listOfPixels.size());
            ArrayList<Color> listOfPixels = SettingsClass.getListOfPixels();

            if(PicSettings.getWayOfProcessingSeries() == 1){
                int k = 0;
                try {
                    for (int i = 0; i < height; i++) {
                        if (i % 2 == 0) {
                            for (int j = 0; j < width; j++) {
                                g.drawLine(j + shift, i + shift, j + shift, i + shift);
                                g.setColor(listOfPixels.get(k));
                                k++;
                            }
                        } else {
                            for (int j = width - 1; j >= 0; j--) {
                                g.drawLine(j + shift, i + shift, j + shift, i + shift);
                                g.setColor(listOfPixels.get(k));
                                k++;
                            }
                        }
                    }
                } catch (Exception e) {}
            }else if(PicSettings.getWayOfProcessingSeries() == 2 || PicSettings.getWayOfProcessingSeries() == 3){

                int k = 0;
                try{
                    for (int i = 0; i < height; i++) {
                        for (int j = 0; j < width; j++) {
                            g.drawLine(j,i,j,i);
                            g.setColor(listOfPixels.get(k));
                            k++;
                            System.out.println(listOfPixels.get(k).getRed()+" "+listOfPixels.get(k).getGreen()+" "+listOfPixels.get(k).getBlue());
                        }
                    }
                }catch (Exception e){}
            }
        }
        else {
            g.drawImage(storeNewPicture,0,0,null);
        }
    }


    //Вспомогательные функции

    protected static ArrayList<Color> fromImageToListOfPixels(BufferedImage img){
        ArrayList<Color> list = new ArrayList<>();
        int k=0,p=0;
        try {
            for (int i = 0; i < img.getHeight(); i++) {
                k=i;
                if (i % 2 == 0) {
                    for (int j = 0; j < img.getWidth(); j++) {
                        p=j;
                        list.add(new Color(img.getRGB(j, i)));
                    }
                } else {
                    for (int j = img.getWidth()-1 ; j >= 0; j--) {
                        list.add(new Color(img.getRGB(j, i)));
                    }
                }
            }
        }catch(Exception e){}
        return list;
    }

    public BufferedImage returnNewPicture()
    {
        return this.storeNewPicture;
    }
}
