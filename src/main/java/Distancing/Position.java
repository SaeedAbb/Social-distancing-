package Distancing;

import javafx.scene.layout.Pane;

/**
 * this method represents the position of a person
 *
 * @author Saeed
 * @since 2020-12-04
 */

public class Position {
    private double x;
    private double y;

    public Position(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Position(Pane world, int radius) {
        this(radius + Math.random() * (world.getWidth() - 2 * radius),
                radius + Math.random() * (world.getHeight() - 2 * radius));
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double distance(Position other) {
        return Math.sqrt(Math.pow(this.x - other.x, 2) +
                Math.pow(this.y - other.y, 2));
    }

    public void move(Heading heading, Pane world, int radius, Position origin) {
        x += heading.getDx();
        y += heading.getDy();
        //to make x Fall back when it Crashes into the wall
        if (x < radius || x > world.getWidth() - radius || distance(origin) > Person.distance) {
            heading.bounceX();
            x += heading.getDx();
        }
        //to make y Fall back when it Crashes into the wall
        if (y < radius || y > world.getHeight() - radius || distance(origin) > Person.distance) {
            heading.bounceY();
            y += heading.getDy();
        }
    }
    //to check if the person has been in Contact with another person
    public boolean collision(Position other) {
        return distance(other) < 2 * Person.radius;

    }
}
