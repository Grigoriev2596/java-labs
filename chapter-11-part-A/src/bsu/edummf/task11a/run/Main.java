package bsu.edummf.task11a.run;

import bsu.edummf.task11a.action.PolynomialAction;
import bsu.edummf.task11a.entity.Polynomial;
import java.util.Hashtable;


//9.  Сложить два многочлена заданной степени, если коэффициенты многочленов
// хранятся в объекте HashMap
public class Main {

    public static void main(String[] args) {
        Hashtable<Integer, Double> monomials = new Hashtable<>();
        Polynomial p1;
        Polynomial p2;
        monomials.put(1, 1.2);
        monomials.put(3, -11.2);
        monomials.put(4, 3.5);
        p1 = new Polynomial(monomials, 4);
        monomials.clear();
        monomials.put(1, 2.6);
        monomials.put(2, 6.1);
        monomials.put(4, 5.8);
        p2 = new Polynomial(monomials, 4);
        System.out.println("Polynomial1 = " + p1);
        System.out.println("Polynomial2 = " + p2);
        System.out.println("Sum = " + PolynomialAction.polynomialSum(p1,p2));
    }
}
