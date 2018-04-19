package AuxiliaryClases.ForPictureAnalyses;

import Forms.SettingsForms.PicSettings;
import Jama.Matrix;
import MainPackage.SettingsClass;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GramMatrix {
    private static ArrayList<Double> gramSquaresOfPoints = new ArrayList<>();

    public static void fromImageToSquaresOfPixels(BufferedImage image){
        int dimension = PicSettings.getDimensionOfMatrix();
        System.out.println("Di "+dimension);



        for (int i = 0; i < image.getHeight() - dimension + 1; i++) {
            for (int j = 0; j < image.getWidth() - dimension + 1; j++) {
                SquareOfPixels sqOfPixnew = new SquareOfPixels(
                        SupFunctions.createNewSquare(i,j,image,dimension),
                        dimension);
                fromSquaresOfPixelsToPoints(sqOfPixnew,dimension);

            }
        }
    }

    private static void fromSquaresOfPixelsToPoints(SquareOfPixels squareOfPixels,int dimension){
        //squaresOfPoints.clear();

        double[][] doubles = new double[dimension][dimension];
        //Заполняем массив
        for (int j = 0; j < dimension; j++) {
            for (int k = 0; k < dimension; k++) {
                Color color = squareOfPixels.getSquareOfColors().get(j).get(k);
                doubles[j][k] = SupFunctions.getNecessaryColor(color);
            }
        }

        double[][] gramMatrix  = new double[dimension][dimension];

        Matrix A = new Matrix(doubles);
        Matrix B = new Matrix(doubles);

        B = B.transpose();

        Matrix C = A.times(B);

        try {
            gramSquaresOfPoints.add(C.det());
        }catch (Exception e){
            System.out.println("Ошибка");
        }

    }

    private static void makeListOfProcessedPoints(){
        ArrayList<double[]> arrayList = new ArrayList<>();
        System.out.println("size ="+gramSquaresOfPoints.size());

        if(PicSettings.getChosenColor() == 1)
        {
            for (int i = 0; i < gramSquaresOfPoints.size(); i++) {
                arrayList.add(new double[]{gramSquaresOfPoints.get(i),0,0});
            }
        }
        if(PicSettings.getChosenColor() == 2)
            for (int i = 0; i < gramSquaresOfPoints.size(); i++) {
                arrayList.add(new double[]{0,gramSquaresOfPoints.get(i),0});
            }
        if(PicSettings.getChosenColor() == 3)
            for (int i = 0; i < gramSquaresOfPoints.size(); i++) {
                arrayList.add(new double[]{0,0,gramSquaresOfPoints.get(i)});
            }


        arrayList = SupFunctions.normalaizeArrayList(arrayList);
        SettingsClass.setListOfProcessedPoints(arrayList);
    }

    public static void doSequenceOfActions(BufferedImage image){
        fromImageToSquaresOfPixels(image);
        makeListOfProcessedPoints();
        SupFunctions.createListOfProcessedColors(SettingsClass.getListOfProcessedPoints());

    }





}
