import javafx.stage.*;
import javafx.scene.*;

import javafx.scene.effect.ImageInput;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.geometry.Rectangle2D;

import javafx.animation.TranslateTransition;
import javafx.util.Duration;
import javafx.application.Application;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class Menu extends Application {
    Rectangle[] songs = new Rectangle[10];

    Stage s = new Stage();
    int stage = 0;
    boolean moveUp, moveDown;
    Rectangle bad;
    Rectangle snow;
    Image badBack;
    Image snowBack;
    ImageView badiv;
    ImageView snowiv;

    public void start(Stage outStage) throws Exception {

        // get bounds of screen
        Rectangle2D r = Screen.getPrimary().getBounds();

        System.out.println(r.getWidth());
        System.out.println(r.getHeight());

        // add snow background
        snowBack = new Image("https://i.imgur.com/6fyRfHb.jpeg", r.getWidth(), r.getHeight(), true, true);
        ImageInput sbInput = new ImageInput();
        snowiv = new ImageView();

        sbInput.setSource(snowBack);
        snowiv.setImage(snowBack);
        snowiv.setOpacity(1); // not technically necessary b/c always underneath

        // add bad background
        badBack = new Image("https://i.imgur.com/vxCf2kH.jpg", 1920, 1080, true, true);
        ImageInput bInput = new ImageInput();
        badiv = new ImageView();

        bInput.setSource(badBack);
        badiv.setImage(badBack);
        badiv.setOpacity(1);

        // add gray backdrop
        Paint backdropColor = Paint.valueOf("white");
        Rectangle backdrop = new Rectangle(888, 0, 1920, 3000);
        backdrop.setFill(backdropColor);
        backdrop.setOpacity(0.5);

        // add select box
        Paint paint = Paint.valueOf("white");
        Rectangle select = new Rectangle(900, 360, 1920, 160);
        select.setFill(paint);
        select.setOpacity(0.5);

        // add bad menu option
        Image badApple = new Image("https://i.imgur.com/V47CJZ5.png", r.getWidth(), r.getHeight(), true, true);
        ImageInput badAppleSource = new ImageInput();

        badAppleSource.setSource(badApple);
        badAppleSource.setX(900);
        badAppleSource.setY(680);
        bad = new Rectangle();
        bad.setEffect(badAppleSource);

        // add snow menu option
        Image snowHalation = new Image("https://i.imgur.com/C1iJXix.png", r.getWidth(), r.getHeight(), true, true);
        ImageInput snowHalationSource = new ImageInput();

        snowHalationSource.setSource(snowHalation);
        snowHalationSource.setX(900);
        snowHalationSource.setY(520);
        snow = new Rectangle();
        snow.setEffect(snowHalationSource);

        // loop the two songs in the menu
        for (int i = 0; i < 10; i += 2) {
            Image badTemp = new Image("https://i.imgur.com/V47CJZ5.png", r.getWidth(), r.getHeight(), true, true);
            ImageInput badTempSource = new ImageInput();
            Rectangle badRectTemp = new Rectangle();

            Image snowTemp = new Image("https://i.imgur.com/C1iJXix.png", r.getWidth(), r.getHeight(), true, true);
            ImageInput snowTempSource = new ImageInput();
            Rectangle snowRectTemp = new Rectangle();

            badTempSource.setSource(badTemp);
            badTempSource.setX(900);
            badTempSource.setY(1000 - i * 160);
            badRectTemp.setEffect(badTempSource);
            songs[i] = badRectTemp;

            snowTempSource.setSource(snowTemp);
            snowTempSource.setX(900);
            snowTempSource.setY(1000 - (i + 1) * 160);
            snowRectTemp.setEffect(snowTempSource);
            songs[i + 1] = snowRectTemp;
        }

        // add everything to group and display
        Group root = new Group(snowiv);

        root.getChildren().add(badiv);

        root.getChildren().add(backdrop);

        for (int i = 0; i < 10; i++) {
            root.getChildren().add(songs[i]);
        }
        root.getChildren().add(select);
        Scene scene = new Scene(root, 1920, 1080, Color.WHITE);

        // detect keypresses
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:
                        moveUp = true;
                        break;
                    case DOWN:
                        moveDown = true;
                        break;
                    default:
                }
            }
        });

        outStage.setScene(scene);
        outStage.setTitle("joe-su!");
        outStage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {

                if (moveUp) {
                    scrollUp();
                    moveUp = false;
                }
                if (moveDown)
                    scrollDown();
                moveDown = false;

            }
        };
        timer.start();

    }

    public void scrollDown() {
        stage--;

        if (Math.abs(stage) % 2 == 1) {
            badiv.setOpacity(0);
        } else {
            badiv.setOpacity(1);
        }
        for (int i = 0; i < 10; i++) {
            TranslateTransition translateTransition = new TranslateTransition();

            translateTransition.setNode(songs[i]);

            // if (songs[i].getY() == 680) {
            // songs[i].setX((songs[i].getX() - 100));
            // translateTransition.setToX(-100);
            // } else {
            // translateTransition.setToX(0);
            // songs[i].setX((songs[i].getX() - 100));
            // }

            translateTransition.setByY(-160);
            translateTransition.setDuration(Duration.millis(100));
            translateTransition.play();

        }
    }

    public void scrollUp() {
        stage++;

        if (Math.abs(stage) % 2 == 1) {
            badiv.setOpacity(0);
        } else {
            badiv.setOpacity(1);
        }

        for (int i = 0; i < 10; i++) {
            TranslateTransition translateTransition = new TranslateTransition();
            translateTransition.setNode(songs[i]);

            // if (songs[i].getY() == 680) {
            // songs[i].setX((songs[i].getX() - 100));
            // translateTransition.setToX(-100);
            // } else {
            // translateTransition.setToX(0);
            // songs[i].setX((songs[i].getX() - 100));
            // }

            translateTransition.setByY(160);
            translateTransition.setDuration(Duration.millis(100));
            translateTransition.play();

        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}