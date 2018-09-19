package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Controller.Controller;
import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.powerups.LaserPowerUp;
import com.codecool.snake.entities.powerups.MousePowerup;
import com.codecool.snake.entities.powerups.SimplePowerup;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.*;

import javafx.scene.layout.VBoxBuilder;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

public class Game extends Pane {

    private Button restartButton = new Button("", new ImageView(Globals.restartImage));

    public Game(Stage primaryStage) {
        gameInit(primaryStage);
        setPane();
    }

    public void gameSetter(Game game){
        Globals.game = game;
    }

    public void setPane(){
        Globals.pane = this;
    }
    public void gameInit(Stage primaryStage) {
        Controller controller = new Controller(this);
        new LaserPowerUp(this);
        new SnakeHead(this, 500, 500);

        new SimpleEnemy(this);
        new SimpleEnemy(this);
        new SimpleEnemy(this);
        new SimpleEnemy(this);

        new SimplePowerup(this);
        new SimplePowerup(this);
        new SimplePowerup(this);
        new SimplePowerup(this);

        new MousePowerup(this);

        Globals.leftKeyDown = false;
        Globals.rightKeyDown = false;

        Globals.primaryStage = primaryStage;
    }


    public void setTableBackground(Image tableBackground) {
        setBackground(new Background(new BackgroundImage(tableBackground,
                BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
                BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
    }

    // creates end game buttons
    public void endGameButtonsInit() {
        Button exitButton = new Button("Exit");
        Button endGamerestartButton = new Button("Restart");

        exitButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent arg0) {
                Globals.primaryStage.close();
            }
        });

        endGamerestartButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent arg0) {
                Globals.gameObjects.clear();
                getChildren().clear();
                gameInit(Globals.primaryStage);
                start();
            }
        });

        exitButton.setLayoutX(400);
        exitButton.setLayoutY(400);
        endGamerestartButton.setLayoutX(500);
        endGamerestartButton.setLayoutY(400);

        getChildren().add(exitButton);
        getChildren().add(endGamerestartButton);
    }


    public void start() {
        Scene scene = getScene();
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case LEFT:  Globals.leftKeyDown  = true; break;
                case RIGHT: Globals.rightKeyDown  = true; break;
            }
        });

        scene.setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case LEFT:  Globals.leftKeyDown  = false; break;
                case RIGHT: Globals.rightKeyDown  = false; break;
            }
        });
        initButton();
        Globals.gameLoop = new GameLoop();
        Globals.gameLoop.start();
    }


    public static void gameOver(int score) {
        final Stage gameOverModal = new Stage();
        gameOverModal.initModality(Modality.APPLICATION_MODAL);
        Button okButton = new Button("I know, dude :(");
        gameOverModal.setOnCloseRequest((WindowEvent e) ->{
            Globals.game.endGameButtonsInit();
        });
        okButton.setOnAction(new EventHandler<ActionEvent>(){

            @Override
            public void handle(ActionEvent arg0) {
                Globals.game.endGameButtonsInit();
                gameOverModal.close();
            }

        });
        Scene myDialogScene = new Scene(VBoxBuilder.create()
                .children(new Text(String.format("Nah, the Game is Over mate!\nBut your score is %d.", score)), okButton)
                .alignment(Pos.CENTER)
                .padding(new Insets(10))
                .build());
        gameOverModal.setScene(myDialogScene);
        gameOverModal.show();
    }


    // Event handler for the restart button
    private EventHandler<MouseEvent> onRestartHandler = e -> {
        Globals.gameLoop.stop();
        Globals.gameObjects.clear();
        getChildren().clear();
        gameInit(Globals.primaryStage);
        start();
    };


    // puts the eventhandler onto the restart button
    public void addMouseEventHandler() {
        restartButton.setOnMouseClicked(onRestartHandler);
    }


    public void initButton() {
        if(getChildren().contains(restartButton))
            getChildren().remove(restartButton);
        restartButton.setLayoutX(40);
        restartButton.setLayoutY(40);

        restartButton.setMaxSize(10, 10);
        restartButton.setMinSize(10, 10);

        restartButton.setStyle("-fx-background-color: transparent; ");
        getChildren().add(restartButton);
        addMouseEventHandler();
    }
}
