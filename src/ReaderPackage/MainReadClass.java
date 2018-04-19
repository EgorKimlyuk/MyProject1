package ReaderPackage;

import javafx.geometry.Point3D;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class MainReadClass {


    public static ArrayList<Point3D> readData(String fileName, int pointSize) throws Exception {
        String search = ",";
        String replace = ".";
        Charset charset = StandardCharsets.US_ASCII;
        Path path = Paths.get(fileName);
        Files.write(path, new String(Files.readAllBytes(path), charset).replace(search, replace)
                .getBytes(charset));

        ArrayList<Point3D> point3Ds = new ArrayList<>();
        try {
            File fin = new File(fileName);
            BufferedReader reader = new BufferedReader(new FileReader(fin));
            String line;
            while ((line = reader.readLine()) != null && point3Ds.size() < pointSize) {
                 String[] s = splitStrings(line);

                point3Ds.add(new Point3D(Double.valueOf(s[0]),Double.valueOf(s[1]),Double.valueOf(s[2])));
            }
        } catch (Exception e) {
        }

        //for(Point3D X:point3Ds){System.out.println(X.getX()+" "+X.getY()+" "+X.getZ());}
        return point3Ds;
    }

    private static String[] splitStrings(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '\t') {
                s = s.substring(i + 1);
                i = -1;
            } else i = s.length();
        }
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '\t' ) {
            }
        }

        s = s.replaceAll("\\p{Cntrl}", " ");
        while(s.contains("  ")) {
            String replace = s.replace("  ", " ");
            s=replace;
        }

        String[] st = s.split(" ");
        return st;
    }
}
