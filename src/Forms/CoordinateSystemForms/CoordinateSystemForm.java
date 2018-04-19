package Forms.CoordinateSystemForms;

import Drawing.*;
import javafx.geometry.Point3D;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.*;

public class CoordinateSystemForm extends JFrame{
    public static double MaxX = 100;
    public static double MaxY = 100;
    public static double MaxZ = 100;
    public static double MinX = -100;
    public static double MinY = -100;
    public static double MinZ = -100;


    //public CoordinateSystemForm()
   // {
    //    initComponents();
  //  }

    public CoordinateSystemForm() {
        super("Панель управления графиком");
        initComponents();
        initWindowActions();
        this.setBackground(Color.WHITE);
    }

    private  void initComponents(){

        buttonGroup = new ButtonGroup();
        jRadioButtonX = new JRadioButton("X",false);
        jRadioButtonX.setVisible(true);
        jRadioButtonY = new JRadioButton("Y",true);
        jRadioButtonZ = new JRadioButton("Z",false);
        buttonGroup.add(jRadioButtonX);
        buttonGroup.add(jRadioButtonY);
        buttonGroup.add(jRadioButtonZ);
        //Кнопки
        ButtonRight   = new JButton(">");           //>
        ButtonLeft  = new JButton("<");           //<
        jButtonrefreshCoordinateSystem = new JButton("Обновить график");

        //Надписи и поля
        labelX = new JLabel("X: ");
        labelY = new JLabel("Y: ");
        labelZ = new JLabel("Z: ");
        textMaxX = new JTextField(4);
        textMaxY = new JTextField(4);
        textMaxZ = new JTextField(4);
        textMinX = new JTextField(4);
        textMinY = new JTextField(4);
        textMinZ = new JTextField(4);
        labelMax = new JLabel("Max");
        labelMin = new JLabel("Min");

        jCheckBoxChangeInRealTime = new JCheckBox("Рисовать в реальном времени");


        jpanelAxes = new JFrameAxes(this);

        jpanelAxes.setLocationRelativeTo(null);
        jpanelAxes.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jpanelAxes.setVisible(true);

        this.textMaxX.setText(String.valueOf(jpanelAxes.MaxX));
        this.textMaxY.setText(String.valueOf(jpanelAxes.MaxY));
        this.textMaxZ.setText(String.valueOf(jpanelAxes.MaxZ));
        this.textMinX.setText(String.valueOf(jpanelAxes.MinX));
        this.textMinY.setText(String.valueOf(jpanelAxes.MinY));
        this.textMinZ.setText(String.valueOf(jpanelAxes.MinZ));

        initButtonsActions(jpanelAxes);
        initTextChanges(jpanelAxes);

        Container container = getContentPane();
        container.setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        //Описание взаимного расположения((
        {
            gridBagConstraints.fill = GridBagConstraints.CENTER;
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new Insets(15, 10, 0, 0);
            container.add(jRadioButtonX, gridBagConstraints);

            gridBagConstraints.fill = GridBagConstraints.CENTER;
            gridBagConstraints.gridx = 1;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new Insets(15, 10, 0, 0);
            container.add(jRadioButtonY, gridBagConstraints);

            gridBagConstraints.fill = GridBagConstraints.CENTER;
            //gridBagConstraints.anchor = GridBagConstraints.EAST;
            gridBagConstraints.gridx = 2;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new Insets(15, 10, 0, 0);
            container.add(jRadioButtonZ, gridBagConstraints);

            gridBagConstraints.fill = GridBagConstraints.CENTER;
            //gridBagConstraints.anchor = GridBagConstraints.EAST;
            gridBagConstraints.gridx = 3;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new Insets(15, 20, 0, 0);
            container.add(ButtonLeft, gridBagConstraints);

            gridBagConstraints.fill = GridBagConstraints.CENTER;
            //gridBagConstraints.anchor = GridBagConstraints.EAST;
            gridBagConstraints.gridx = 4;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new Insets(15, 5, 0, 20);
            container.add(ButtonRight, gridBagConstraints);

            gridBagConstraints.fill = GridBagConstraints.CENTER;
            gridBagConstraints.gridx = 1;
            gridBagConstraints.gridy = 1;
            gridBagConstraints.insets = new Insets(10, 10, 0, 0);
            container.add(labelMax, gridBagConstraints);

            gridBagConstraints.fill = GridBagConstraints.CENTER;
            gridBagConstraints.gridx = 2;
            gridBagConstraints.gridy = 1;
            gridBagConstraints.insets = new Insets(10, 10, 0, 0);
            container.add(labelMin, gridBagConstraints);

            gridBagConstraints.fill = GridBagConstraints.NONE;
            gridBagConstraints.anchor = GridBagConstraints.EAST;
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 3;
            gridBagConstraints.insets = new Insets(10, 0, 0, 0);
            container.add(labelX, gridBagConstraints);

            gridBagConstraints.fill = GridBagConstraints.NONE;
            gridBagConstraints.anchor = GridBagConstraints.CENTER;
            gridBagConstraints.gridx = 1;
            gridBagConstraints.gridy = 3;
            gridBagConstraints.insets = new Insets(10, 10, 0, 0);
            container.add(textMaxX, gridBagConstraints);

            gridBagConstraints.fill = GridBagConstraints.NONE;
            gridBagConstraints.anchor = GridBagConstraints.CENTER;
            gridBagConstraints.gridx = 2;
            gridBagConstraints.gridy = 3;
            gridBagConstraints.insets = new Insets(10, 10, 0, 0);
            container.add(textMinX, gridBagConstraints);

            gridBagConstraints.fill = GridBagConstraints.NONE;
            gridBagConstraints.anchor = GridBagConstraints.EAST;
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 4;
            gridBagConstraints.insets = new Insets(10, 0, 0, 0);
            container.add(labelY, gridBagConstraints);

            gridBagConstraints.fill = GridBagConstraints.NONE;
            gridBagConstraints.anchor = GridBagConstraints.CENTER;
            gridBagConstraints.gridx = 1;
            gridBagConstraints.gridy = 4;
            gridBagConstraints.insets = new Insets(10, 10, 0, 0);
            container.add(textMaxY, gridBagConstraints);

            gridBagConstraints.fill = GridBagConstraints.NONE;
            gridBagConstraints.anchor = GridBagConstraints.CENTER;
            gridBagConstraints.gridx = 2;
            gridBagConstraints.gridy = 4;
            gridBagConstraints.insets = new Insets(10, 10, 0, 0);
            container.add(textMinY, gridBagConstraints);

            gridBagConstraints.fill = GridBagConstraints.NONE;
            gridBagConstraints.anchor = GridBagConstraints.EAST;
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 5;
            gridBagConstraints.insets = new Insets(10, 0, 10, 0);
            container.add(labelZ, gridBagConstraints);

            gridBagConstraints.fill = GridBagConstraints.NONE;
            gridBagConstraints.anchor = GridBagConstraints.CENTER;
            gridBagConstraints.gridx = 1;
            gridBagConstraints.gridy = 5;
            gridBagConstraints.insets = new Insets(10, 10, 10, 0);
            container.add(textMaxZ, gridBagConstraints);

            gridBagConstraints.fill = GridBagConstraints.NONE;
            gridBagConstraints.anchor = GridBagConstraints.CENTER;
            gridBagConstraints.gridx = 2;
            gridBagConstraints.gridy = 5;
            gridBagConstraints.insets = new Insets(10, 10, 10, 0);
            container.add(textMinZ, gridBagConstraints);

            gridBagConstraints.fill = GridBagConstraints.NONE;
            gridBagConstraints.anchor = GridBagConstraints.CENTER;
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 6;
            gridBagConstraints.gridwidth = 4;
            gridBagConstraints.insets = new Insets(10, 10, 10, 0);
            container.add(jCheckBoxChangeInRealTime, gridBagConstraints);

            gridBagConstraints.fill = GridBagConstraints.NONE;
            gridBagConstraints.anchor = GridBagConstraints.CENTER;
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 7;
            gridBagConstraints.gridwidth = 3;
            gridBagConstraints.insets = new Insets(10, 10, 10, 0);
            container.add(jButtonrefreshCoordinateSystem, gridBagConstraints);
        }

        this.setVisible(true);
        pack();

    }

    private void initWindowActions()
    {
        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) { }

            @Override
            public void windowClosing(WindowEvent e) {jpanelAxes.dispose();}

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

    //Прочие вспомогательные функции
    //Возвращает значения параметра для x, y,z
    private int returnXYZParam()
    {
        if(jRadioButtonX.isSelected())param = 0;
        else if (jRadioButtonY.isSelected()) param = 1;
        else if (jRadioButtonZ.isSelected()) param = 2;
        return param;
    }

    public void closeJFrameAxes()
    {
        jpanelAxes.dispose();
    }

    public boolean getjCheckBoxChangeInRealTime(){
        return this.jCheckBoxChangeInRealTime.isSelected();
    }



    //Обработки нажатий
    private void initButtonsActions(JFrameAxes jpanelAxes)
    {

        ButtonLeft.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                CoordinateSystemForm.jpanelAxes.TurnCS(returnXYZParam(),false);
            }
        });

        ButtonRight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CoordinateSystemForm.jpanelAxes.TurnCS(returnXYZParam(),true);
            }
        });

        jButtonrefreshCoordinateSystem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CoordinateSystemForm.jpanelAxes.refreshCoordinateSystem();
            }
        });
    }

    private void initTextChanges(JFrameAxes jpanelAxes)
    {
        try {
        textMaxX.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {

                    CoordinateSystemForm.jpanelAxes.ButtonPerfomed(Double.parseDouble(textMaxX.getText()), 0, true);

            }
        });


        textMaxY.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                CoordinateSystemForm.jpanelAxes.ButtonPerfomed(Double.parseDouble(textMaxY.getText()),1,true);
            }
        });

        textMaxZ.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                CoordinateSystemForm.jpanelAxes.ButtonPerfomed(Double.parseDouble(textMaxZ.getText()),2,true);
            }
        });

        textMinX.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                CoordinateSystemForm.jpanelAxes.ButtonPerfomed(Double.parseDouble(textMinX.getText()),0,false);
            }
        });

        textMinY.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                CoordinateSystemForm.jpanelAxes.ButtonPerfomed(Double.parseDouble(textMinY.getText()),1,false);
            }
        });

        textMinZ.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                CoordinateSystemForm.jpanelAxes.ButtonPerfomed(Double.parseDouble(textMinZ.getText()),2,false);
            }
        });
        }catch (Exception ee){}


    }





    private static final int DEFAULT_WIDTH = 700;
    private static final int DEFAULT_HEIGHE = 700;
    private static CoordinateSystem CS =null;
    private static ArrayList<ArrayList<Point3D>>  listOfList = null;
    private Point3D point3dMax = null;
    private Point3D point3dMin = null;
    private int step = 1;
    private int len = 3;
    private static int param = 0;//Если 0, то Икс, Если 1, то Y,если 2 то Z

    //Кнопки

    private JButton ButtonRight;            //+10
    private JButton ButtonLeft;           //-10
    private JButton jButtonrefreshCoordinateSystem;           //обновить изображение


    public  static JFrameAxes jpanelAxes = null;

    //Элементы формы
    private ButtonGroup buttonGroup;
    private JRadioButton jRadioButtonX;
    private JRadioButton jRadioButtonY;
    private JRadioButton jRadioButtonZ;

    private JLabel labelMax;
    private JLabel labelMin;

    private JTextField textMaxX;
    private JTextField textMinX;
    private JTextField textMaxY;
    private JTextField textMinY;
    private JTextField textMaxZ;
    private JTextField textMinZ;
    private JLabel labelX;
    private JLabel labelY;
    private JLabel labelZ;



    private JCheckBox jCheckBoxChangeInRealTime;


    public static void main(String[] args) {
        CoordinateSystemForm coordinateSystemForm = new CoordinateSystemForm();
        //coordinateSystemForm.setSize(700,800);
        coordinateSystemForm.setVisible(true);
    }
}
