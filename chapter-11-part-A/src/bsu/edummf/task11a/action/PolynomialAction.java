package bsu.edummf.task11a.action;

import bsu.edummf.task11a.entity.Polynomial;

import java.util.Hashtable;
import java.util.Map;

public class PolynomialAction {
    public static Polynomial polynomialSum(Polynomial p1, Polynomial p2) {
        Polynomial result = new Polynomial(p1.getMonomials(), p1.getDegree());
        p2.getMonomials().forEach((power, coeff) -> {
                result.addMonomial(power, coeff);
        });
        return result;
    }
}
