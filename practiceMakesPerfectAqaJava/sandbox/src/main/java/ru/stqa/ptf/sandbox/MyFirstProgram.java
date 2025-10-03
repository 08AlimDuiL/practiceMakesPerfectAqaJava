package ru.stqa.ptf.sandbox;

public class MyFirstProgram {

    public static void main(String[] args) {

        System.out.println("Hello yall!");

        Point p1 = new Point(2, 2);
        Point p2 = new Point(5, 5);

        System.out.println(distance(p1, p2));

        Point p3 = new Point(2, 2);
        Point p4 = new Point(5, 5);

        System.out.println(p3.distanceMethod(p4));

    }

    public static double distance(Point pOne, Point pTwo) {
// Теорема Пифагора: В прямоугольном треугольнике квадрат гипотенузы равен сумме квадратов катетов

        double differenceX = pTwo.x - pOne.x;
        double differenceY = pTwo.y - pOne.y;

        double distance = Math.sqrt(Math.pow(differenceX, 2) + Math.pow(differenceY, 2));

        return distance;
    }
}