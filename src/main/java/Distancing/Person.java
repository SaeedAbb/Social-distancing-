package Distancing;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * This method represents the person
 *
 * @author Saeed
 * @since 2020-12-04
 */
public class Person {
    private State state;
    private Position loc;
    private Heading heading;
    private Circle circle;
    private Pane world;
    private int sickTime = 0;
    private Position origin;


    public static int radius = 5;
    public static int healTime = 5 * 50;
    public static int distance = 200;

    public Person(State state, Pane world) {

        this.state = state;
        this.heading = new Heading();
        this.loc = new Position(world, radius);
        this.origin = new Position(loc.getX(), loc.getY());
        this.circle = new Circle(radius, state.getColor());
        circle.setStroke(Color.BLACK);
        world.getChildren().add(circle);
        this.world = world;
    }

    public State getState() {
        return this.state;
    }

    public void setState(State state) {
        this.state = state;
        circle.setFill(state.getColor());
    }

    public void move() {
        loc.move(heading, world, radius, origin);
    }

    public void draw() {
        circle.setRadius(radius);
        circle.setTranslateX(loc.getX());
        circle.setTranslateY(loc.getY());
    }

    public void collisionCheck(Person other) {
        if (loc.collision(other.loc)) {
            if (other.getState() == State.INFECTED && state == State.SUSCEPTIBLE) {
                setState(State.INFECTED);
            }
        }
    }

    public void feelBetter() {
        if (state == State.INFECTED) {
            sickTime++;
            if (sickTime > healTime) {
                setState(State.RECOVERED);
            }
        }
    }
}
