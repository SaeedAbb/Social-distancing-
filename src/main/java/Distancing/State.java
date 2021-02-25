package Distancing;

import javafx.scene.paint.Color;

/**
 * this class represents the situation of an person
 *
 * @author Saeed
 * @since 2020-12-04
 */

public enum State {
    SUSCEPTIBLE {
        @Override
        public Color getColor() {
            return Color.BLUE;
        }
    },

    INFECTED {
        @Override
        public Color getColor() {
            return Color.RED;
        }
    }, RECOVERED {
        @Override
        public Color getColor() {
            return Color.GREEN;
        }
    };


    public abstract Color getColor();
}
