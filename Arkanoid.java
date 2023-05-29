package assignment1_000907515;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.Scanner;

/**
 * Assignment 1, Arkanoid Screen
 * Created May 21, 2023.
 *
 * @author Phaedra Buzek
 */

public class Arkanoid extends Application{

    /**
     * start method is used to draw the arkanoid game.
     *
     * @param stage the FX stage to draw on.
     */

    @Override
    public void start(Stage stage) {
        Scanner sc = new Scanner(System.in);

        //BRICK NUMBERS
        int brickRows = 0;
        int brickCols = 0;

        // *** Data validation for number of rows and columns ***
        while (brickRows < 1 || brickRows > 10 ){
            System.out.println("How many rows of bricks do you want?");
            brickRows = sc.nextInt();
        }

        while (brickCols < 1 || brickCols > 10) {
            System.out.println("How many columns do you want?");
            brickCols = sc.nextInt();
        }

        //SCORE AND LEVEL
        System.out.println("What is the current score?");
        int score = sc.nextInt();
        System.out.println("What is the high score?");
        int highScore = sc.nextInt();

        String highScoreString = Integer.toString(highScore);
        String scoreString = Integer.toString(score);

        // *** set high score to score if score is higher ***
        if (score > highScore){
            highScoreString = scoreString;
        }

        System.out.println("What level are you on?");
        int level = sc.nextInt();
        String levelString = Integer.toString(level);

        //POSITIONS
        int ballX = 0;
        int ballY = 0;
        int paddlePos = 0;

        // *** Data validation for each variable -- cannot be outside of ranges ***
        while (ballX < 10 || ballX > 720){
            System.out.println("What is the ball's x-position? (Between 10 - 720)");
            ballX = sc.nextInt();
        }
        while (ballY < 300 || ballY > 785){
            System.out.println("What is the ball's y-position? (Between 300 - 785");
            ballY = sc.nextInt();
        }
        while (paddlePos < 10|| paddlePos > 720) {
            System.out.println("What is the paddle position? (Between 10 - 720)");
            paddlePos = sc.nextInt();
        }

        //CANVAS SET UP
        Group root = new Group();
        Scene scene = new Scene(root, Color.LIGHTGRAY);
        Canvas canvas = new Canvas(1000, 800); // Set canvas Size in Pixels
        stage.setTitle("Arkanoid"); // Set window title
        root.getChildren().add(canvas);
        stage.setScene(scene);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        //BACKGROUND AND BORDER
        gc.setFill(Color.LIGHTSKYBLUE);
        gc.fillRect(5,5,725,790);

        gc.setFill(Color.BLACK);
        gc.setLineWidth(5);
        gc.strokeRect(5,5,725,790);


        //BRICK SET UP
        // *** finding and setting the size of bricks
        int width = (700/brickRows) - 20;
        int height = (300/brickCols) - 10;

        int rows = 0;
        int cols = 0;
        while (rows <= brickRows && cols <= brickCols){
            for (int y = 25; y < 300; y += (height + 10)){

                // *** Changing the colour each row ***
                int red = (int)(Math.random()*100);
                int green = (int)(Math.random()*100);
                int blue = (int)(Math.random()*100);
                gc.setFill(Color.rgb(red,green,blue));

                for (int x = 25; x < 700; x += (width + 20)){
                    gc.fillRect(x, y, width, height);
                    rows ++;
                }
                cols ++;
            }
        }

        //BALL SET UP
        gc.setFill(Color.BLUE);
        gc.fillOval(ballX, ballY, 25,25);

        //PADDLE SET UP
        gc.setFill(Color.CYAN);
        gc.fillRect(paddlePos, 750, 50, 15);
        gc.setLineWidth(2);
        gc.strokeRect(paddlePos, 750, 50, 15);

        //TEXT SET UP
        Font titleFont = Font.font("System", FontWeight.BOLD, 20);
        Font otherFont = Font.font("System",15);

        gc.setFont(titleFont);
        gc.setFill(Color.BLUE);
        gc.fillText("ARKANOID", 800, 50);
        gc.setFill(Color.RED);
        gc.fillText("SCORE", 750, 150);
        gc.fillText("HIGHSCORE", 750, 225);
        gc.fillText("WAVE", 750, 300);

        gc.setFont(otherFont);
        gc.setFill(Color.BLACK);
        gc.fillText(scoreString, 750, 175);
        gc.fillText(highScoreString, 750, 250);
        gc.fillText(levelString, 750, 325);

        //SHOW
        stage.show();
    }

    /**
     * The actual main method that launches the app.
     *
     * @param args unused
     */
    public static void main(String[] args) {
        launch(args);
    }
}
