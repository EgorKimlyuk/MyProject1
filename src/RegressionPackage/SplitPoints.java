/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RegressionPackage;

import javafx.geometry.Point3D;

import java.util.ArrayList;

//Этот класс разбивает общий набор данных на множество наборов по которым далее будут строиться 
//плоскости, из которых мы получим 2 угловых коэфициента и свободный член
public class SplitPoints {
     public static ArrayList<ArrayList<Point3D>> splitPoints(int len,int step,ArrayList<Point3D> list){
        ArrayList<Point3D> mainList = list;

       if(list == null) return null;

        int count=0;
        for(int i = len;i<mainList.size();i+=step){
            count++;
        }

        ArrayList<ArrayList<Point3D>> listOfLists = new ArrayList<>();
        int k = 0;
        for (int j = 0; j < count; j++) {
            listOfLists.add(new ArrayList<Point3D>());
            for (int i = 0; i < len ; i++) {
                listOfLists.get(j).add(mainList.get(k+i));
            }
            k+=step;
        }
        return listOfLists;
    }

//    public static void main(String[] args) {
//       /* ArrayList<ArrayList<Point3D>> listOfLists = splitPoints(4,2);
//         for(ArrayList<Point3D> x:listOfLists){
//             for(Point3D y:x){
//                 System.out.println(y.getX()+" "+y.getY()+" "+y.getZ());
//             }
//             System.out.println();
//         }*/
//
//    }
}
