package AuxiliaryClases.ForPictureAnalyses;

import Forms.SettingsForms.PicSettings;
import MainPackage.SettingsClass;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class SupFunctions {
    public static double[] FindMaxMin(ArrayList<double[]> tempList,int pl ){
        double maxmin[] = new double[2];
        double max = 0;
        double min = tempList.get(0)[pl];
        if(pl==0) {
            for (int i = 0; i < tempList.size(); i++) {
                if (tempList.get(i)[pl] >= max
                        //&& tempList.get(i)[pl] < 1000
                        ) {
                    max = tempList.get(i)[pl];
                }
            }
            for (int i = 0; i < tempList.size(); i++) {
                if (tempList.get(i)[pl] < min
                        //&& tempList.get(i)[pl] > -1000
                        ) {
                    min = tempList.get(i)[pl];
                }
            }
        }
        else if (pl == 1){
            for (int i = 0; i < tempList.size(); i++) {
                if (tempList.get(i)[pl] >= max
                       //&& tempList.get(i)[pl] < 1000
                        ) {
                    max = tempList.get(i)[pl];
                }
            }
            for (int i = 0; i < tempList.size(); i++) {
                if (tempList.get(i)[pl] < min
                        //&& tempList.get(i)[pl] > -1000
                        ) {
                    min = tempList.get(i)[pl];
                }
            }
        }else if (pl == 2){
            for (int i = 0; i < tempList.size(); i++) {
                if (tempList.get(i)[pl] >= max
                        //&& tempList.get(i)[pl] < 1000
                        ) {
                    max = tempList.get(i)[pl];
                }
            }
            for (int i = 0; i < tempList.size(); i++) {
                if (tempList.get(i)[pl] < min
                        //&& tempList.get(i)[pl] > -1000
                        ) {
                    min = tempList.get(i)[pl];
                }
            }
        }


        maxmin[0] = max;
        maxmin[1] = min;
        return maxmin;
        
    }

    protected static ArrayList<ArrayList<Color>> createNewSquare(int height, int width, BufferedImage image, int dimension){
        ArrayList<ArrayList<Color>> squareOfColors = new ArrayList<>();

        for (int i = 0; i < dimension; i++) {
            ArrayList<Color> row = new ArrayList<>();
            for (int j = 0; j < dimension; j++) {
                row.add(new Color(image.getRGB(width+j,height+i)));
            }
            squareOfColors.add(row);
        }
        return squareOfColors;
    }

    public static double getNecessaryColor(Color color){
        if(PicSettings.getChosenColor() == 1)
            return (double) color.getRed();
        if(PicSettings.getChosenColor() == 2)
            return (double) color.getGreen();
        if(PicSettings.getChosenColor() == 3)
            return (double) color.getBlue();
        return 0.0;
    }

    protected static ArrayList<double[]> normalaizeArrayList(ArrayList<double[]> arrayList){

        double[] MaxMinX;
        double[] MaxMinY;
        double[] MaxMinZ;

        ArrayList<double[]> returnArrayList = new ArrayList<>();
        if(PicSettings.getChosenColor() == 1)
        {
            MaxMinX = SupFunctions.FindMaxMin(arrayList,0);
            //System.out.println("Max = " + MaxMinX[0]+", Min = " + MaxMinX[1]);
            for (int i = 0; i <  arrayList.size(); i++) {
                double mas[] = new double[3];

                mas[0] = ((arrayList.get(i)[0]-MaxMinX[1])/(MaxMinX[0]-MaxMinX[1]))*256;
                if(mas[0]>255){
                    mas[0]=255;
                }
                else  if(mas[0]<0){
                    mas[0] = 0;
                }

                mas[1] = 0;
                mas[2] = 0;
                returnArrayList.add(mas);
                // System.out.println("Mas = "+mas[0]+" "+mas[1]+" "+mas[2]);
            }
        }
        else if(PicSettings.getChosenColor() == 2){
            MaxMinY = SupFunctions.FindMaxMin(arrayList,1);

            for (int i = 0; i <  arrayList.size(); i++) {
                double mas[] = new double[3];

                mas[0] = 0;
                mas[1] = ((arrayList.get(i)[1]-MaxMinY[1])/(MaxMinY[0]-MaxMinY[1]))*256;
                if(mas[1]>255){
                    mas[1]=255;
                }
                else  if(mas[1]<0){
                    mas[1] = 0;
                }
                mas[2] = 0;
                returnArrayList.add(mas);
                // System.out.println("Mas = "+mas[0]+" "+mas[1]+" "+mas[2]);
            }
        }
        else if(PicSettings.getChosenColor() == 3)
        {
            MaxMinZ = SupFunctions.FindMaxMin(arrayList, 2);

            for (int i = 0; i <  arrayList.size(); i++) {
                double mas[] = new double[3];

                mas[0] = 0;
                mas[1] = 0;
                mas[2] = ((arrayList.get(i)[1]-MaxMinZ[1])/(MaxMinZ[0]-MaxMinZ[1]))*256;
                if(mas[2]>255){
                    mas[2]=255;
                }
                else  if(mas[2]<0){
                    mas[2] = 0;
                }

                returnArrayList.add(mas);
                //System.out.println("Mas = "+mas[0]+" "+mas[1]+" "+mas[2]);
            }
        }
        return returnArrayList;

    }

    protected static void createListOfProcessedColors(ArrayList<double[]> arrayList){
        ArrayList<Color> colorList = new ArrayList<>();

        for (int i = 0; i < arrayList.size(); i++) {
            colorList.add(new Color((int)arrayList.get(i)[0],(int)arrayList.get(i)[1],(int)arrayList.get(i)[2]));
        }

        SettingsClass.setListOfPixels(colorList);
    }
}
