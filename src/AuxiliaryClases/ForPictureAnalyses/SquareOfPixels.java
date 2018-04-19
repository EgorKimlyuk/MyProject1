package AuxiliaryClases.ForPictureAnalyses;


import Jama.Matrix;

import java.awt.*;
import java.util.ArrayList;

public class SquareOfPixels {
    private ArrayList<ArrayList<Color>> squareOfColors;
    private int dimension;


    public SquareOfPixels(ArrayList<ArrayList<Color>> squareOfColors,int dimension) {
        this.squareOfColors = squareOfColors;
        this.dimension = dimension;
    }

    public ArrayList<ArrayList<Color>> getSquareOfColors() {
        return squareOfColors;
    }

    public void setSquareOfColors(ArrayList<ArrayList<Color>> squareOfColors) {
        this.squareOfColors = squareOfColors;
    }

    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }


}
