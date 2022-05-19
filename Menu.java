import javafx.animation.AnimationTimer;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
// import javafx.geometry.Rectangle2D;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Menu extends Application {
    Rectangle[] songs = new Rectangle[10];

    Stage s = new Stage();
    int stage = 0;
    boolean moveUp, moveDown, selected;
    Rectangle bad, snow;
    Rectangle badleaderboardRect, snowleaderboardRect;
    Image badBack, snowBack;
    ImageView badiv, snowiv;

    Rectangle temp;
    Text badSelect, snowSelect;

    public void start(Stage outStage) throws Exception {

        Image badleaderboard = new Image("https://i.imgur.com/jAfTBXO.png", 500, 500, true, true);
        ImageInput badleaderboardInput = new ImageInput();
        badleaderboardRect = new Rectangle();
        badleaderboardInput.setSource(badleaderboard);
        badleaderboardRect.setEffect(badleaderboardInput);
        badleaderboardInput.setX(100);
        badleaderboardInput.setY(200);
        badleaderboardRect.setOpacity(1);

        Image snowleaderboard = new Image("https://i.imgur.com/qhWcIoQ.png", 500, 500, true, true);
        ImageInput snowleaderboardInput = new ImageInput();
        snowleaderboardRect = new Rectangle();
        snowleaderboardInput.setSource(snowleaderboard);
        snowleaderboardRect.setEffect(snowleaderboardInput);
        snowleaderboardInput.setX(100);
        snowleaderboardInput.setY(200);
        snowleaderboardRect.setOpacity(0);

        // get bounds of screen
        // Rectangle2D r = Screen.getPrimary().getBounds();

        // temporary game interface
        temp = new Rectangle(1440, 900);
        Paint tempColor = Paint.valueOf("white");
        temp.setFill(tempColor);
        temp.setOpacity(0);
        badSelect = new Text(200, 500, "Bad Apple selected");
        badSelect.setFont(new Font(100));
        badSelect.setOpacity(0);
        snowSelect = new Text(200, 500, "Snow halation selected");
        snowSelect.setFont(new Font(100));
        snowSelect.setOpacity(0);

        // add snow background
        snowBack = new Image("https://i.imgur.com/6fyRfHb.jpeg", 1920, 1080, true, true);
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
        Rectangle backdrop = new Rectangle(588, 0, 1920, 3000);
        backdrop.setFill(backdropColor);
        backdrop.setOpacity(0.5);

        // add select box
        Paint paint = Paint.valueOf("white");
        Rectangle select = new Rectangle(600, 360, 1920, 160);
        select.setFill(paint);
        select.setOpacity(0.5);

        // add bad menu option
        Image badApple = new Image("https://i.imgur.com/V47CJZ5.png", 1440, 900, true, true);
        ImageInput badAppleSource = new ImageInput();

        badAppleSource.setSource(badApple);
        badAppleSource.setX(600);
        badAppleSource.setY(680);
        bad = new Rectangle();
        bad.setEffect(badAppleSource);

        // add snow menu option
        Image snowHalation = new Image("https://i.imgur.com/C1iJXix.png", 1440, 900, true, true);
        ImageInput snowHalationSource = new ImageInput();

        snowHalationSource.setSource(snowHalation);
        snowHalationSource.setX(600);
        snowHalationSource.setY(520);
        snow = new Rectangle();
        snow.setEffect(snowHalationSource);

        // loop the two songs in the menu
        for (int i = 0; i < 10; i += 2) {
            Image badTemp = new Image("https://i.imgur.com/V47CJZ5.png", 1440, 900, true, true);
            ImageInput badTempSource = new ImageInput();
            Rectangle badRectTemp = new Rectangle();

            Image snowTemp = new Image("https://i.imgur.com/C1iJXix.png", 1440, 900, true, true);
            ImageInput snowTempSource = new ImageInput();
            Rectangle snowRectTemp = new Rectangle();

            badTempSource.setSource(badTemp);
            badTempSource.setX(600);
            badTempSource.setY(1000 - i * 160);
            badRectTemp.setEffect(badTempSource);
            songs[i] = badRectTemp;

            snowTempSource.setSource(snowTemp);
            snowTempSource.setX(600);
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
        root.getChildren().add(temp);
        root.getChildren().add(badSelect);
        root.getChildren().add(snowSelect);
        root.getChildren().add(badleaderboardRect);
        root.getChildren().add(snowleaderboardRect);

        Scene scene = new Scene(root, 1440, 900, Color.WHITE);

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
                    case ENTER:
                        selected = true;
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
                if (moveDown) {
                    scrollDown();
                    moveDown = false;
                }
                if (selected) {
                    select();
                    selected = false;
                }

            }
        };
        timer.start();

    }

    public void select() {
        if (temp.getOpacity() != 0) {
            temp.setOpacity(0);
            badSelect.setOpacity(0);
            snowSelect.setOpacity(0);
        } else {
            temp.setOpacity(0.7);
            if (stage % 2 == 0)
                badSelect.setOpacity(1);
            else
                snowSelect.setOpacity(1);
        }

    }

    public void scrollDown() {
        stage--;

        if (Math.abs(stage) % 2 == 1) {
            badiv.setOpacity(0);
            badleaderboardRect.setOpacity(0);
            snowleaderboardRect.setOpacity(1);
        } else {
            badiv.setOpacity(1);
            badleaderboardRect.setOpacity(1);
            snowleaderboardRect.setOpacity(0);
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
            badleaderboardRect.setOpacity(0);
            snowleaderboardRect.setOpacity(1);
        } else {
            badiv.setOpacity(1);
            badleaderboardRect.setOpacity(1);
            snowleaderboardRect.setOpacity(0);
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