package ru.stqa.ptf.sandbox;

import org.testng.annotations.Test;
import org.testng.Assert;

public class PointTests {

    @Test
    public void testDistanceSamePoint() {
        Point point1 = new Point(5, 5);
        Point point2 = new Point(5, 5);

        Assert.assertEquals(point1.distanceMethod(point2), 0.0);
    }

    @Test
    public void testDistanceZeroX() {
        Point point1 = new Point(0, 5);
        Point point2 = new Point(0, 3);

        Assert.assertEquals(point1.distanceMethod(point2), 2.0);
    }

    @Test
    public void testDistanceZeroY() {
        Point point1 = new Point(5, 0);
        Point point2 = new Point(3, 0);

        // Вертикальное расстояние (только по Y)
        Assert.assertEquals(point1.distanceMethod(point2), 2.0);
    }

    @Test
    public void testDistanceNegativeAllCoordinates() {
        Point point1 = new Point(-2, -1);
        Point point2 = new Point(-1, -2);

        // Расстояние с отрицательными координатами
        Assert.assertEquals(point1.distanceMethod(point2), 1.4142135623730951);
    }

    @Test
    public void testPoint() {
        Point point1 = new Point(5, 5);
        Point point2 = new Point(10, 10);

        Assert.assertEquals(point1.distanceMethod(point2), 7.0710678118654755);
    }
}
