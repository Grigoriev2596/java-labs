package bsu.edummf.task11b.filler;

import java.util.List;

public class ListFiller {
    public static void fillListWithNumbersDividedByNegativeNumber(List<Double> list1, List<Double> list2, double[] numbers) {
        boolean negativeNumberPassed = false;
        for(int i = 0; i < numbers.length; i++) {
            if (numbers[i] < 0) {
                negativeNumberPassed = true;
                continue;
            }
            if (!negativeNumberPassed) {
                list1.add(numbers[i]);
                continue;
            }
            list2.add(numbers[i]);
        }
    }
}
