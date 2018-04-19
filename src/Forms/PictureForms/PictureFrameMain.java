package Forms.PictureForms;

import Drawing.CoordinateSystem;
import Forms.CoordinateSystemForms.CoordinateSystemForm;
import Forms.MainFrame;
import MainPackage.SettingsClass;
import ReaderPackage.MainReadClass;
import javafx.geometry.Point3D;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Set;

public class PictureFrameMain extends JFrame {
    private ArrayList<Color> listOfPixels;


    public PictureFrameMain(BufferedImage image,int width,int height)
    {
        this.bfImage = image;
        initComponents(image,width,height);
    }

    public PictureFrameMain(BufferedImage image)
    {
        this.bfImage = image;
        initComponents(image,image.getWidth(),image.getHeight());
    }

    private void initComponents(BufferedImage image,int width,int height){


        saveNewPic = new JButton("Сохранить изображение");
        saveNewPic.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveNewPicAction(e);
            }
        });
        jButtonOpenCoordinateSystemForm = new JButton("Открыть график");
        jButtonOpenCoordinateSystemForm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionOpenCoordinateSystemForm(e);
            }
        });
        pictureJPanel = new PictureJPanel(this.bfImage,width,height);
        this.canvas = pictureJPanel;

        canvas.setPreferredSize(new Dimension(width, height));
        JScrollPane jScrollPane = new JScrollPane(canvas);

        setLayout(new GridBagLayout());


        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(5,5,5,0);
        add(saveNewPic,gridBagConstraints);

        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(5,5,5,0);
        add(jButtonOpenCoordinateSystemForm,gridBagConstraints);

        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2 ;
        gridBagConstraints.weightx = 100;
        gridBagConstraints.weighty = 100;
        gridBagConstraints.insets = new Insets(0,0,0,0);
        add(jScrollPane,gridBagConstraints);

        this.setSize(600,600);
    }

    //ПОВЕДЕНИЕ
    //Сохранить изображение в файл
    private void saveNewPicAction(ActionEvent e) {
        BufferedImage savingPicture = this.canvas.returnNewPicture();

        String lastDirectory = MainFrame.returnLastDirectory();

        JFileChooser saveFileChooser;
        if(lastDirectory.isEmpty())
            saveFileChooser = new JFileChooser();
        else
            saveFileChooser = new JFileChooser(lastDirectory);

        saveFileChooser.setFileFilter(new FileNameExtensionFilter("Изображения",ImageIO.getReaderFileSuffixes()));
        int ret = saveFileChooser.showDialog(null, "Сохранить изображение");
        if(ret == JFileChooser.APPROVE_OPTION){
            try{
                File file = new File(saveFileChooser.getSelectedFile().toString()+".JPG");
                ImageIO.write(savingPicture,"JPEG",file);
                JOptionPane.showMessageDialog(null, "Изрображение успешно сохранено");
            }
            catch(Exception exception){
                JOptionPane.showMessageDialog(null, "Не удалось сохранить файл");
            }

        }
    }

    private void actionOpenCoordinateSystemForm(ActionEvent e){
        ArrayList<Color> listOfPixels = SettingsClass.getListOfPixels();
        ArrayList<double[]> listofPoints = new ArrayList<>();
        for(Color x:listOfPixels){
            listofPoints.add(new double[]{x.getRed(),x.getGreen(),x.getBlue()});
            //System.out.println(x.getRed()+" "+x.getGreen()+" "+x.getBlue());
        }

        SettingsClass.setListOfProcessedPoints(listofPoints);
        CoordinateSystemForm jFFCS = new CoordinateSystemForm();
        jFFCS.setDefaultCloseOperation(CoordinateSystemForm.DISPOSE_ON_CLOSE);
        jFFCS.setVisible(true);
    }




    //private PicDrawJPanel picDrawJPanel;

    private BufferedImage bfImage;
    private JButton saveNewPic;
    private JButton jButtonOpenCoordinateSystemForm;
    private PictureJPanel canvas;
    private PictureJPanel pictureJPanel;

}
