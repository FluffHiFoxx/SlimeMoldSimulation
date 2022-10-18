package com.slimemold;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
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
/*
        GridPane root = new GridPane();
        root.setGridLinesVisible(true);
        final double numCols = canvas.getWidth();
        final double numRows =  canvas.getHeight();
        for (int i = 0; i < numCols; i++) {
            ColumnConstraints colConst = new ColumnConstraints();
          //  colConst.setPercentWidth(100.0 / numCols);
            colConst.setPrefWidth(numCols);
            root.getColumnConstraints().add(colConst);
        }
        for (int i = 0; i < numRows; i++) {
            RowConstraints rowConst = new RowConstraints();
            //rowConst.setPercentHeight(100.0 / numRows);
            rowConst.setPrefHeight(numRows);
            root.getRowConstraints().add(rowConst);
        }
        stage.setScene(new Scene(root, 800, 600));
        stage.show();
        */

    }
}
