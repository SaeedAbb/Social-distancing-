package Distancing;

/**
 * this class represents the heading of an person
 *
 * @author Saeed
 * @since 2020-12-04
 */

public class Heading {

    public static final double SPEED = 3;
    private double dx;
    private double dy;

    public Heading(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public Heading() {
        double dir = Math.random() * 2 * Math.PI;
        this.dx = Math.sin(dir);
        this.dy = Math.cos(dir);
    }

    public double getDx() {
        return dx * SPEED;
    }

    public double getDy() {
        return dy * SPEED;
    }

    public void bounceX() {
        dx *= -1;
    }

    public void bounceY() {
        dy *= -1;
    }
}
