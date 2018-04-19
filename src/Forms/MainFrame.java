package Forms;

import AuxiliaryClases.ForPictureAnalyses.Casorati;
import AuxiliaryClases.ForPictureAnalyses.GramMatrix;
import Forms.CoordinateSystemForms.CoordinateSystemForm;
import Forms.PictureForms.PictureFrameMain;
import Forms.SettingsForms.PicSettings;
import MainPackage.SettingsClass;
import javafx.geometry.Point3D;

import ReaderPackage.MainReadClass;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainFrame extends JFrame {

    private static String lastDirectory="";//предыдущая директория
    private static ArrayList<Point3D> point3Ds = new ArrayList<>();
    private static boolean textFileIsChosen = false;
    private static boolean picFileIsChosen = false;

    public MainFrame() throws HeadlessException {
        super("Основная Форма");
        initComponents();
    }

    //Заполним компоненты формы
    private  void initComponents()
    {
        pointOfRegression        = new JTextField(10); //количество точек регрессии
        pointOfRegression.setText("3");
        stepOfRegression         = new JTextField(10); //шаг регрессии
        stepOfRegression.setText("1");
        buttonMain               = new JButton("Выполнить"); //выполнить
        pointOfRegressionLabel   = new JLabel();// надпись количество точек регрессии
        stepOfRegressionLabel    = new JLabel(); //надпись шаг регресии
        chosenFile               = new JLabel(); //Выбранный Файл наименование
        chosenFileLabel          = new JLabel(); // Надпись выбранный файл

        jMenuBar1                = new JMenuBar(); //Выбрать файл
        chooseFile               = new JMenu();
        chooseTextFile           = new  JMenuItem();
        choosePictureFile        = new JMenuItem();
        jMenuSettings            = new JMenu(); //НАСТРОЙКИ
        jSettingsForPic          = new JMenuItem();

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //При изменении полей ввода, перезаполним константы
        pointOfRegression.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                String pointOfRegressionText = pointOfRegression.getText();
                if(pointOfRegressionText.equals(""))
                    SettingsClass.setLen(0);
                else
                    SettingsClass.setLen(Integer.parseInt(pointOfRegressionText));


            }
        });

        stepOfRegression.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                String stepOfRegressionText = stepOfRegression.getText();
                if(stepOfRegressionText.equals(""))
                    SettingsClass.setStep(0);
                else
                    SettingsClass.setStep(Integer.parseInt(stepOfRegressionText));
            }
        });

        //Описание кнопки
        buttonMain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainButtonPerfomingAction(e);
            }
        });


        pointOfRegressionLabel.setText("Количество точек регрессии: ");
        stepOfRegressionLabel.setText("Шаг регрессии: ");
        chosenFileLabel.setText("Выбранный файл :");
        chooseFile.setText("Выбрать файл");

        //Заполнение меню((
        //первый пункт меню
        chooseTextFile.setText("Текстовый файл (файл с данными)");
        chooseTextFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chooseTextFileActionPerfomed(e);
            }
        });
        choosePictureFile.setText("Изображение");
        choosePictureFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                choosePictureFileActionPerfomed(e);
            }
        });
        chooseFile.add(chooseTextFile);
        chooseFile.add(choosePictureFile);
        jMenuBar1.add(chooseFile);
        //второй пункт меню
        jMenuSettings.setText("Доп. настройки");
        jSettingsForPic.setText("Настройки обработки изображений");
        jSettingsForPic.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openSettingsForm(e);
            }
        });
        jMenuSettings.add(jSettingsForPic);
        jMenuBar1.add(jMenuSettings);
        setJMenuBar(jMenuBar1);
        //Заполненение меню))


        Container container = getContentPane();
        container.setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        //Описание взаимного расположения((
        gridBagConstraints.fill = GridBagConstraints.NONE;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(15,20,0,0);
        container.add(pointOfRegressionLabel,gridBagConstraints);

        gridBagConstraints.fill = GridBagConstraints.NONE;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(15,10,0,0);
        container.add(pointOfRegression,gridBagConstraints);

        gridBagConstraints.fill = GridBagConstraints.NONE;
        gridBagConstraints.anchor = GridBagConstraints.EAST;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(10,10,20,0);
        container.add(stepOfRegressionLabel,gridBagConstraints);

        gridBagConstraints.fill = GridBagConstraints.NONE;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(10,10,20,0);
        container.add(stepOfRegression,gridBagConstraints);

        gridBagConstraints.fill = GridBagConstraints.VERTICAL;
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.insets = new Insets(15,10,20,20);
        container.add(buttonMain,gridBagConstraints);

        gridBagConstraints.fill = GridBagConstraints.NONE;
        gridBagConstraints.anchor = GridBagConstraints.EAST;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new Insets(15,10,20,0);
        container.add(chosenFileLabel,gridBagConstraints);

        gridBagConstraints.fill = GridBagConstraints.NONE;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new Insets(15,10,20,0);
        container.add(chosenFile,gridBagConstraints);
        //Описание взаимного расположения))
        pack();


    }


    //ПОВЕДЕНИЕ
    //нажатие на кнопкку ВЫПОЛНИТЬ
    private void  mainButtonPerfomingAction(ActionEvent e)
    {
        //Запомним константы len  и Step

        //не выбран файл
        if (!picFileIsChosen && !textFileIsChosen  )
        {
            JOptionPane.showMessageDialog(this, "Не выбран файл11");
            return;
        }

        if(pointOfRegression.getText().equals("") ||stepOfRegression.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Заполните поля для регрессии");
        } else{
            try{
                pictureOrDataAction(picFileIsChosen);
            }
            catch(Exception exception){
                System.out.println(exception.getMessage());
                JOptionPane.showMessageDialog(null, "Не выбран файл");
                throw(exception);
            }
        }
    }

    private void chooseTextFileActionPerfomed(ActionEvent e){   //создаем объект для чтения из файла
        //Если уже открывали файл, то откроем предыдущую директорию
        JFileChooser fileopen;
        if(lastDirectory.isEmpty())
            fileopen = new JFileChooser();
        else
            fileopen = new JFileChooser(lastDirectory);

        //Выбираем только текстовые файлы
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Текстовые файлы (*.txt)", "txt");
        fileopen.setFileFilter(filter);

        int ret = fileopen.showDialog(null, "Выбрать текстовый файл");
        if(ret == JFileChooser.APPROVE_OPTION){
            File file = fileopen.getSelectedFile();

            try{
                System.setErr(new PrintStream(new File("C:/Users/Егорка/Desktop/1.txt")));//УДАЛИТЬ

                lastDirectory = file.getParent();
                point3Ds = MainReadClass.readData(file.getAbsolutePath(), 500);

                System.out.println("Текстовый файл загружен");

                chosenFile.setText(file.getName());
                textFileIsChosen = true;
                picFileIsChosen = false;

                JOptionPane.showMessageDialog(null, "Текстовый файл загружен");
                //throw  new NullPointerException();
            }
            catch(Exception exception){
                exception.printStackTrace();
                JOptionPane.showMessageDialog(null, "Не удалось загрузить файл");
            }

        }
    }

    private void choosePictureFileActionPerfomed( ActionEvent e){
        JFileChooser fileopen;
        if(lastDirectory.isEmpty())
            fileopen = new JFileChooser();
        else
            fileopen = new JFileChooser(lastDirectory);

        //Выбираем изображения
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Выберете картинку ", ImageIO.getReaderFileSuffixes());
        fileopen.setFileFilter(filter);

        int ret = fileopen.showDialog(null, "Выбрать изображение");
        if(ret == JFileChooser.APPROVE_OPTION){
            File file = fileopen.getSelectedFile();



            try{


                lastDirectory = file.getParent();
                bfImage = ImageIO.read(file);
                System.out.println("Изображение загружено");

                //chooseFile.setText("Изображение :" + file.getName());
                chosenFile.setText(file.getName());
                textFileIsChosen = false;
                picFileIsChosen = true;
                SettingsClass.setIsThisPicture(true);//работаем с картинкой


                JOptionPane.showMessageDialog(null, "Изображение загружено");
            }
            catch(Exception exception){
                exception.printStackTrace();
                JOptionPane.showMessageDialog(null, "Не удалось загрузить файл");
            }

        }
    }

    //переведем изображение в нужный нам набор точек
    private static void downloadBFImage(BufferedImage bfImage){
        //Перевели в набор пикселей
        SettingsClass.setPoint3Ds(bfImage);
    }

    private static void downloadTxtFile(){
        SettingsClass.setIsThisPicture(false);//работаем с текстовым файлом
        SettingsClass.setPoint3Ds(point3Ds);

    }

    //открытие формы с настройками
    private void openSettingsForm(ActionEvent e){
        PicSettings picSettings = new PicSettings();
        picSettings.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        picSettings.setVisible(true);
    }


    //Вспомогательные функции
    //
    private void pictureOrDataAction(Boolean isThisPicture){
        if(!isThisPicture) {
            //загрузим и обработаем данные из файла
            downloadTxtFile();

            if (jFFCS != null) {
                jFFCS.closeJFrameAxes();
                jFFCS.dispose();
            }
            jFFCS = new CoordinateSystemForm();
            jFFCS.setDefaultCloseOperation(CoordinateSystemForm.DISPOSE_ON_CLOSE);
            jFFCS.setVisible(true);
        }
        else
        {
            if(PicSettings.getWayOfProcessingSeries() == 1 ){ //ЧЕРЕЗ МЕТОД БУСТРАФЕДОНА И ДАЛЕЕ СКОЛЬЗЯЩАЯ РЕГРЕССИЯ

                //преобразовали набор пикселей в набор точек и провели регрессию
                downloadBFImage(bfImage);
                pictureFrameMain = new PictureFrameMain(bfImage);
                pictureFrameMain.setDefaultCloseOperation(pictureFrameMain.DISPOSE_ON_CLOSE);
                pictureFrameMain.setVisible(true);
                //Разложили по казорати и посчитали определители
            }else if(PicSettings.getWayOfProcessingSeries() == 2){
                Casorati.doSequenceOfActions(bfImage);
                pictureFrameMain = new PictureFrameMain(bfImage,bfImage.getWidth()-PicSettings.getDimensionOfMatrix()+1,bfImage.getHeight()-PicSettings.getDimensionOfMatrix()+1);
                pictureFrameMain.setDefaultCloseOperation(pictureFrameMain.DISPOSE_ON_CLOSE);
                pictureFrameMain.setVisible(true);
            }
            else if (PicSettings.getWayOfProcessingSeries() == 3){
                GramMatrix.doSequenceOfActions(bfImage);
                pictureFrameMain = new PictureFrameMain(bfImage,bfImage.getWidth()-PicSettings.getDimensionOfMatrix()+1,bfImage.getHeight()-PicSettings.getDimensionOfMatrix()+1);
                pictureFrameMain.setDefaultCloseOperation(pictureFrameMain.DISPOSE_ON_CLOSE);
                pictureFrameMain.setVisible(true);
            }
        }
    }

    public static String returnLastDirectory(){
        return lastDirectory;
    }
    //
    //ВСПОМОГАТЕЛЬНЫЕ ФУНКЦИИ


    //ОПИСАНИЕ ПОЛЕЙ НА ФОРМЕ
    private JTextField pointOfRegression;  //количество точек регрессии
    private JTextField stepOfRegression;   //шаг регрессии
    private JButton buttonMain;            //выполнить
    private JLabel pointOfRegressionLabel; // надпись количество точек регрессии
    private JLabel stepOfRegressionLabel;  //надпись шаг регресии
    private JLabel chosenFile;             //Выбранный Файл наименование
    private JLabel chosenFileLabel;        // Надпись выбранный файл

    private JMenuBar jMenuBar1;
    private JMenu chooseFile;                    //Выбрать файл
    private JMenuItem chooseTextFile;           //Выбрать текстовый файл
    private JMenuItem choosePictureFile;           //Выбрать изображение

    private JMenu jMenuSettings;                   //НАСТРОЙКИ
    private JMenuItem jSettingsForPic;

    private static BufferedImage bfImage; //загруженная картинка


    private  static CoordinateSystemForm jFFCS;
    private static PictureFrameMain pictureFrameMain;



}
