package com.slimemold;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
    BorderPane window;
    Canvas canvas;
    GraphicsContext graphics;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.window = new BorderPane();
        this.canvas = new Canvas(320,180);
        this.graphics = canvas.getGraphicsContext2D();
        this.graphics.setFill(Color.BLACK);
        this.graphics.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        Scene scene = new Scene(this.window);
        this.window.setCenter(this.canvas);
        stage.setScene(scene);
        stage.setTitle("Simulation");
        stage.show();
    }
}
