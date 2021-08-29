package bsu.edummf.task11a.entity;

import java.util.Hashtable;

public class Polynomial {
    private Hashtable<Integer, Double> monomials;
    private int degree;
    private int size;

    public Polynomial(Hashtable<Integer, Double> monomials, int degree) {
        this.monomials = (Hashtable<Integer, Double>) monomials.clone();
        this.degree = degree;
        this.size = monomials.size();
    }

    public void addMonomial(int power, double coefficient) {
        if (monomials.containsKey(power)) {
            monomials.replace(power, monomials.get(power) + coefficient);
        } else {
            monomials.put(power, coefficient);
            if (power > degree) degree = power;
            this.size++;
        }
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("{");
        monomials.forEach((p, c) -> {
            if (c > 0) {
                sb.append("+");
            }
            sb.append(c).append("*").append("x^").append(p);
        });
        sb.append(", degree=").append(degree);
        sb.append(", size=").append(size);
        sb.append('}');
        return sb.toString();
    }

    public Hashtable<Integer, Double> getMonomials() {
        return (Hashtable<Integer, Double>) monomials.clone();
    }

    public int getDegree() {
        return degree;
    }

    public void setMonomials(Hashtable<Integer, Double> monomials, int degree) {
        this.monomials = (Hashtable<Integer, Double>) monomials.clone();
        this.degree = degree;
        this.size = monomials.size();
    }

}
