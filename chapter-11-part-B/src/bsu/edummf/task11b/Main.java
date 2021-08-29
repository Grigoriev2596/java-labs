package bsu.edummf.task11b;

import bsu.edummf.task11b.filler.ListFiller;
import bsu.edummf.task11b.reader.FileReader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


//9. Во входном файле расположены два набора положительных чисел; между
//наборами стоит отрицательное число. Построить два списка C1 и С2, эле-
//менты которых содержат соответственно числа 1-го и 2-го набора таким
//образом, чтобы внутри одного списка числа были упорядочены по возра-
//станию. Затем объединить списки C1 и С2 в один упорядоченный список,
//изменяя только значения полей ссылочного типа.
public class Main {

    public static void main(String[] args) {
        double[] numbers = FileReader.readNumbers("numbers.txt");
        List<Double> list1 = new ArrayList<>();
        List<Double> list2 = new ArrayList<>();
        ListFiller.fillListWithNumbersDividedByNegativeNumber(list1, list2, numbers);
        Collections.sort(list1);
        list1.addAll(list2);
        Collections.sort(list1);
        System.out.println(list1);

    }
}
