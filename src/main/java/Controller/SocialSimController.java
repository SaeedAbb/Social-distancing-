package Controller;

import Distancing.Person;
import Distancing.Simulation;
import Distancing.State;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;


import java.util.EnumMap;

/**
 * this class controls the SocialSim.fxml
 *
 * @author Saeed
 * @since 2020-12-04
 */
public class SocialSimController {

    @FXML
    Pane world;
    @FXML
    Button startButton;
    @FXML
    Button stopButton;
    @FXML
    Button stepButton;

    @FXML
    Slider sizeSlider;

    @FXML
    Slider recoverySlider;

    @FXML
    Slider distanceSlider;

    @FXML
    TextField textField;

    @FXML
    Pane chart;
    @FXML
    Pane histogram;

    Simulation sim;

    private Movement clock;
    EnumMap<State, Rectangle> hrestcs = new EnumMap<State, Rectangle>(State.class);

    @FXML
    public void initialize() {
        disableButtons(true, true, true);

        sizeSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                setSize();
            }
        });
        recoverySlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                setRecovery();
            }
        });
        distanceSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                setDistance();
            }
        });

        clock = new Movement(this);

        world.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
    }

    @FXML
    public void onResetButton() {
        disableButtons(false, true, false);
        clock.stop();
        clock.resetTicks();
        world.getChildren().clear();
        histogram.getChildren().clear();
        chart.getChildren().clear();


        sim = new Simulation(world, 100);
        setSize();
        setDistance();
        setRecovery();
        textField.setText("" + clock.getTicks());
        int offset = 0;

        for (State s : State.values()) {
            Rectangle r = new Rectangle(60, 0, s.getColor());
            r.setTranslateX(offset);
            offset += 65;
            hrestcs.put(s, r);
            histogram.getChildren().add(r);
        }
        drawCharts();


    }

    @FXML
    public void onStepButton() {
        sim.move();
        sim.resolveCollision();
        sim.feelBetter();
        sim.draw();
        clock.tick();
        textField.setText("" + clock.getTicks());
        drawCharts();
    }

    @FXML
    public void onStartButton() {
        clock.start();
        disableButtons(true, false, true);
    }

    public void onStopButton() {
        clock.stop();
        disableButtons(false, true, false);
    }

    public void disableButtons(boolean start, boolean stop, boolean step) {
        startButton.setDisable(start);
        stopButton.setDisable(stop);
        stepButton.setDisable(step);
    }

    public void setSize() {
        Person.radius = (int) sizeSlider.getValue();
        sim.draw();
    }

    public void setRecovery() {
        Person.healTime = 50 * (int) recoverySlider.getValue();
    }

    public void setDistance() {
        Person.distance = (int) distanceSlider.getValue();
    }

    public void drawCharts() {
        EnumMap<State, Integer> currentPop = new EnumMap<State, Integer>(State.class);
        for (Person p : sim.getPeople()) {
            if (!currentPop.containsKey(p.getState())) {
                currentPop.put(p.getState(), 0);
            }
            currentPop.put(p.getState(), 1 + currentPop.get(p.getState()));
        }
        for (State state : hrestcs.keySet()) {
            if (currentPop.containsKey(state)) {
                hrestcs.get(state).setHeight(currentPop.get(state));
                hrestcs.get(state).setTranslateY(30 + 100 - currentPop.get(state));

                Circle c = new Circle(1, state.getColor());
                c.setTranslateX(clock.getTicks() / 5.0);
                c.setTranslateY(130 - currentPop.get(state));
                chart.getChildren().add(c);
            }
        }
        if (!currentPop.containsKey(State.INFECTED)) {
            onStopButton();
        }
    }

}
