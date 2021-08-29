package bsu.edummf.task11b.reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileReader {
    public static double[] readNumbers(String path) {
        List<Double> numbersList = new ArrayList<>();
        double[] numbersArr;
        try{
            Scanner scanner = new Scanner(new File(path));
            while(scanner.hasNext()) {
                numbersList.add(Double.valueOf(scanner.next()));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("data is not valid");
        }
        numbersArr = new double[numbersList.size()];
        for(int i = 0; i < numbersArr.length; i++) {
            numbersArr[i] = numbersList.get(i);
        }
        return numbersArr;
    }
}
