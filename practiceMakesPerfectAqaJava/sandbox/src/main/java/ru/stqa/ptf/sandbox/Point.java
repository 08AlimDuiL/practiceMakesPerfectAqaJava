package ru.stqa.ptf.sandbox;

public class Point {

    double x;
    double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double distanceMethod(Point otherPoints) {
// Теорема Пифагора: В прямоугольном треугольнике квадрат гипотенузы равен сумме квадратов катетов

        double differenceX = this.x - otherPoints.x;
        double differenceY = this.y - otherPoints.y;

        double distance = Math.sqrt(Math.pow(differenceX, 2) + Math.pow(differenceY, 2));

        return distance;
    }
}
