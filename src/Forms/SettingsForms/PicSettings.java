package Forms.SettingsForms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PicSettings extends JFrame {
    private static int WayOfProcessingSeries = 1;
    private static int dimensionOfMatrix = 3;
    private static int chosenColor = 1; // 1 - это красный, 2 - Зеленый, 3 - Синий

    public static int getChosenColor() {
        return chosenColor;
    }

    public static void setChosenColor(int chosenColor) {
        PicSettings.chosenColor = chosenColor;
    }

    public static int getWayOfProcessingSeries() {
        return WayOfProcessingSeries;
    }

    public static void setWayOfProcessingSeries(int wayOfProcessingSeries) {
        WayOfProcessingSeries = wayOfProcessingSeries;
    }

    public PicSettings() throws HeadlessException {
        super("Настройки");
        initComponents();
        //setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        initValues();
    }

    private void  initComponents(){

        buttonGroup = new ButtonGroup();
        jRadioButtonWayOne = new JRadioButton("1");
        jRadioButtonWayTwo = new JRadioButton("Определитель Казорати");
        jRadioButtonWayThree = new JRadioButton("Определитель Грамма");
        buttonGroup.add(jRadioButtonWayOne);
        buttonGroup.add(jRadioButtonWayTwo);
        buttonGroup.add(jRadioButtonWayThree);

        buttonGroupColor = new ButtonGroup();
        jRadioRed = new JRadioButton("Красный");
        jRadioGreen = new JRadioButton("Зеленый");
        jRadioBlue = new JRadioButton("Синий");
        buttonGroupColor.add(jRadioRed);
        buttonGroupColor.add(jRadioGreen);
        buttonGroupColor.add(jRadioBlue);

        jLabelWayOfProcessSeries = new JLabel("Способ анализа изображений:");
        jLabelWayOfProcessSeries.setFont(new Font(null,Font.BOLD,13));

        jLabelFieldDimensionOfMatrix = new JLabel("Размерность матриц: ");
        jLabelFieldDimensionOfMatrix.setFont(new Font(null,Font.BOLD,13));

        jLabelColor = new JLabel("Цвет: ");
        jLabelColor.setFont(new Font(null,Font.BOLD,13));

        jTextFieldDimensionOfMatrix = new JTextField(3);
        jTextFieldDimensionOfMatrix.setText(String.valueOf(dimensionOfMatrix));
        jButtonCloseAndSave = new JButton("Закрыть и сохранить");

        iniButtonActions();


        Container container = getContentPane();
        container.setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        //Описание взаимного расположения((
        gridBagConstraints.fill = GridBagConstraints.CENTER;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 2;
        gridBagConstraints.insets = new Insets(15,10,0,0);
        container.add(jLabelWayOfProcessSeries,gridBagConstraints);

        gridBagConstraints.fill = GridBagConstraints.WEST;
        gridBagConstraints.anchor =  17;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 2;
        gridBagConstraints.insets = new Insets(15,30,0,0);
        container.add(jRadioButtonWayOne,gridBagConstraints);

        gridBagConstraints.fill = GridBagConstraints.WEST;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 2;
        gridBagConstraints.insets = new Insets(5,30,0,0);
        container.add(jRadioButtonWayTwo,gridBagConstraints);

        gridBagConstraints.fill = GridBagConstraints.WEST;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weightx = 2;
        gridBagConstraints.insets = new Insets(5,30,0,0);
        container.add(jRadioButtonWayThree,gridBagConstraints);

        gridBagConstraints.fill = GridBagConstraints.EAST;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new Insets(15,10,0,0);
        container.add(jLabelFieldDimensionOfMatrix,gridBagConstraints);

        gridBagConstraints.fill = GridBagConstraints.WEST;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.insets = new Insets(5,30,0,0);
        container.add(jTextFieldDimensionOfMatrix,gridBagConstraints);

        gridBagConstraints.fill = GridBagConstraints.WEST;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.insets = new Insets(15,10,0,0);
        container.add(jLabelColor,gridBagConstraints);

        gridBagConstraints.fill = GridBagConstraints.WEST;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.insets = new Insets(5,30,0,0);
        container.add(jRadioRed,gridBagConstraints);

        gridBagConstraints.fill = GridBagConstraints.WEST;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.insets = new Insets(5,30,0,0);
        container.add(jRadioGreen,gridBagConstraints);

        gridBagConstraints.fill = GridBagConstraints.WEST;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.insets = new Insets(5,30,0,0);
        container.add(jRadioBlue,gridBagConstraints);


        //Сохранить
        gridBagConstraints.fill = GridBagConstraints.CENTER;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.insets = new Insets(15,10,15,15);
        container.add(jButtonCloseAndSave,gridBagConstraints);

        this.setVisible(true);
        pack();
    }

    private static void initValues(){
        if(WayOfProcessingSeries == 1 )
            jRadioButtonWayOne.setSelected(true);
        else if(WayOfProcessingSeries == 2)
            jRadioButtonWayTwo.setSelected(true);
        else if(WayOfProcessingSeries == 3)
            jRadioButtonWayThree.setSelected(true);

        if(chosenColor == 1 )
            {jRadioRed.setSelected(true);}
        else if(chosenColor == 2)
            {jRadioGreen.setSelected(true);}
        else if(chosenColor == 3)
            {jRadioBlue.setSelected(true);}

    }

    public static int getDimensionOfMatrix() {
        return dimensionOfMatrix;
    }

    public static void setDimensionOfMatrix(int dimensionOfMatrix) {
        PicSettings.dimensionOfMatrix = dimensionOfMatrix;
    }

    private void iniButtonActions(){
        jButtonCloseAndSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CloseAndSaveAction();
            }
        });
    }

    private void CloseAndSaveAction(){
        int ans  = JOptionPane.showOptionDialog(
                this,
                "Сохранить изменения?",
                "Сохранение настроек",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                new Object[]{"Да", "Нет"},
                "Да");

        if(ans == 0) //Yes
        {
            saveSettings();
            this.dispose();
        }
        else if(ans == 1) //No
        {
            this.dispose();
        }

    }

    private static void saveSettings(){
        //Способ обработки ряда
        if(jRadioButtonWayOne.isSelected()) WayOfProcessingSeries = 1;
        else if(jRadioButtonWayTwo.isSelected()) WayOfProcessingSeries = 2;
        else if(jRadioButtonWayThree.isSelected()) WayOfProcessingSeries = 3;

        try {
            dimensionOfMatrix = Integer.parseInt(jTextFieldDimensionOfMatrix.getText());
        }catch (Exception e){

        }

        if(jRadioRed.isSelected())
            {chosenColor = 1;}
        else if(jRadioGreen.isSelected()) chosenColor = 2;
        else if(jRadioBlue.isSelected()) chosenColor = 3;


    }


    private static JLabel jLabelWayOfProcessSeries;
    private static JLabel jLabelColor;
    private static JLabel jLabelFieldDimensionOfMatrix;


    private static ButtonGroup buttonGroup;
    private static JRadioButton jRadioButtonWayOne;
    private static JRadioButton jRadioButtonWayTwo; //Казорати
    private static JRadioButton jRadioButtonWayThree; //Грамма

    private static ButtonGroup buttonGroupColor;
    private static JRadioButton jRadioRed; //Красный
    private static JRadioButton jRadioGreen; //Зеленый
    private static JRadioButton jRadioBlue; //Синий


    private static JTextField jTextFieldDimensionOfMatrix;


    private JButton jButtonCloseAndSave;


    public static void main(String[] args) {
        PicSettings coordinateSystemForm = new PicSettings();
        //coordinateSystemForm.setSize(700,800);
        coordinateSystemForm.setVisible(true);
    }


}
