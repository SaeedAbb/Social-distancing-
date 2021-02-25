package Controller;

import javafx.animation.AnimationTimer;

/**
 * this class represents the timer on the screen
 *
 * @author Saeed
 * @since 2020-12-04
 */

class Movement extends AnimationTimer {
    private final SocialSimController socialSimController;
    private long framesPerSec = 50L;
    private long interval = 1000000000L / framesPerSec;
    private long last = 0;
    private int ticks = 0;

    public Movement(SocialSimController socialSimController) {
        this.socialSimController = socialSimController;
    }

    @Override
    public void handle(long now) {
        if (now - last > interval) {
            socialSimController.onStepButton();
            last = now;
            tick();
            socialSimController.textField.setText("" + ticks);
        }
    }

    public int getTicks() {
        return ticks;
    }

    public void resetTicks() {
        ticks = 0;
    }

    public void tick() {
        ticks++;
    }
}
