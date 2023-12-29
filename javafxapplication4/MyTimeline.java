/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author
 */
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

public class MyTimeline {

    private Timeline timeline;
    private EventHandler<ActionEvent> onTimeElapsed;

    public MyTimeline(EventHandler<ActionEvent> onTimeElapsed) {
        this.onTimeElapsed = onTimeElapsed;

        // Create a timeline that triggers every 5 seconds
        timeline = new Timeline(new KeyFrame(Duration.seconds(5), onTimeElapsed));
        timeline.setCycleCount(Timeline.INDEFINITE);
    }

    public void startTimeline() {
        // Start the timeline
        timeline.play();
    }

    public void stopTimeline() {
        // Stop the timeline
        timeline.stop();
    }
}

