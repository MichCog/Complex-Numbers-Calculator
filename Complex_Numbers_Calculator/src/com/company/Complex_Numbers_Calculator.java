package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Complex_Numbers_Calculator {
    public static List<Double> pullOutTheNumbers(String s) {
        ArrayList<Double> list = new ArrayList<Double>();

        Matcher matcher = Pattern.compile("-?\\d*\\.?\\d+").matcher(s);
        while (matcher.find()) {
            list.add(Double.parseDouble(matcher.group()));
        }
        return list;
    }

    public static void main(String[] args) {
        String z1, z2;
        int operation;
        Scanner scan = new Scanner(System.in);

        System.out.println("Podaj pierwszą liczbę zespoloną:");
        z1 = scan.nextLine();
        ComplexNumbers a = new ComplexNumbers(pullOutTheNumbers(z1).get(0), pullOutTheNumbers(z1).get(1));

        System.out.println("Podaj drugą liczbę zespoloną:");
        z2 = scan.nextLine();
        ComplexNumbers b = new ComplexNumbers(pullOutTheNumbers(z2).get(0), pullOutTheNumbers(z2).get(1));

        System.out.println("Jakie działanie chcesz wykonać?: 1. Dodawanie, 2. Odejmowanie, 3. Mnożenie, 4. Dzielenie");
        operation = scan.nextInt();
        System.out.println("WYNIK: ");
        if (operation == 1) {
            ComplexNumbers add = a.addition(b);
            add.describe();
        }

        if (operation == 2) {
            ComplexNumbers sub = a.subtraction(b);
            sub.describe();
        }

        if (operation == 3) {
            ComplexNumbers multi = a.multiplication(b);
            multi.describe();
        }

        if (operation == 4) {
            ComplexNumbers div = a.division(b);
            div.describe();
        }
    }

    static class ComplexNumbers {
        double re;
        double im;

        ComplexNumbers(double re, double im) {
            this.re = re;
            this.im = im;
        }

        ComplexNumbers addition(ComplexNumbers b) {
            double re = this.re + b.re;
            double im = this.im + b.im;
            return new ComplexNumbers(re, im);
        }

        ComplexNumbers subtraction(ComplexNumbers b) {
            double re = this.re - b.re;
            double im = this.im - b.im;
            return new ComplexNumbers(re, im);
        }

        ComplexNumbers multiplication(ComplexNumbers b) {
            double re = this.re * b.re - this.im * b.im;
            double im = this.re * b.im + this.im * b.re;
            return new ComplexNumbers(re, im);
        }

        ComplexNumbers division(ComplexNumbers b) {
            double denominator = (b.re * b.re + b.im * b.im);
            double re = (this.re * b.re + this.im * b.im)/denominator;
            double im = (this.re * b.im - this.im * b.re)/denominator;
            return new ComplexNumbers(re, im);
        }

        void describe() {
            if(this.im > 0) {
                System.out.print(this.re + "+" + this.im + "i");
            }
            else if(this.re ==0 && this.im == 0){
                System.out.println('0');
            }
            else if (this.im == 0){
                System.out.print(this.re);
            }
            else if (this.re == 0){
                System.out.print(this.im + "i");
            }
            else{
                System.out.print(this.re + "" + this.im + "i");
            }
        }
    }
}
