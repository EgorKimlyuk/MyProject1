package MainPackage;

import Forms.MainFrame;

public class Main {
    public static void main(String[] args)   {
        MainFrame mainFrame = new MainFrame();
        //mainFrame.setSize(600,200);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
        mainFrame.setResizable(false);
    }
}
