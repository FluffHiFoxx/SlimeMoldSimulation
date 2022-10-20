package com.slimemold;

import com.slimemold.board.Board;
import com.slimemold.board.cell.Cell;
import com.slimemold.board.cell.LiveCell;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {
    BorderPane window;
    Canvas canvas;
    GraphicsContext graphics;

    int boardWidth = 640;
    int boardHeight = 320;
    Board board = new Board(boardWidth, boardHeight);

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.window = new BorderPane();
        this.canvas = new Canvas(boardWidth, boardHeight);
        this.graphics = canvas.getGraphicsContext2D();

        Scene scene = new Scene(this.window);
        this.window.setCenter(this.canvas);
        stage.setScene(scene);
        createCells();
        render();
        scene.setOnKeyPressed(e -> {
            for (int i = 0; i < 2; i++) {
                board.moveContent();
                render();
            }
        });
        stage.show();

//        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
////            board.moveContent();
////            render();
//        }));
//        timeline.setCycleCount(1);
//        timeline.playFromStart();
    }

    private void createCells() {
        board.setCell(160, 320, new LiveCell(Color.RED));
        board.setCell(161, 320, new LiveCell(Color.VIOLET));
        board.setCell(162, 320, new LiveCell(Color.WHITE));
        board.setCell(163, 320, new LiveCell(Color.GOLD));
        board.setCell(164, 320, new LiveCell(Color.GREEN));
        board.setCell(165, 320, new LiveCell(Color.CRIMSON));
        board.setCell(166, 320, new LiveCell(Color.AQUA));
        board.setCell(167, 320, new LiveCell(Color.CYAN));
        board.setCell(168, 320, new LiveCell(Color.PURPLE));
        board.setCell(169, 320, new LiveCell(Color.PEACHPUFF));
        board.setCell(170, 320, new LiveCell(Color.WHITE));
        board.setCell(160, 321, new LiveCell(Color.WHITE));
        board.setCell(160, 322, new LiveCell(Color.BLUE));
        board.setCell(160, 323, new LiveCell(Color.WHITE));
        board.setCell(160, 324, new LiveCell(Color.WHITE));
        board.setCell(160, 325, new LiveCell(Color.WHITE));
        board.setCell(160, 326, new LiveCell(Color.WHITE));
        board.setCell(160, 327, new LiveCell(Color.WHITE));
        board.setCell(160, 328, new LiveCell(Color.WHITE));
        board.setCell(160, 329, new LiveCell(Color.WHITE));
        board.setCell(160, 330, new LiveCell(Color.WHITE));
        board.setCell(160, 331, new LiveCell(Color.WHITE));
        board.setCell(160, 332, new LiveCell(Color.WHITE));
        board.setCell(160, 333, new LiveCell(Color.WHITE));
        board.setCell(160, 334, new LiveCell(Color.WHITE));
        board.setCell(160, 335, new LiveCell(Color.WHITE));
    }

    private void render() {
        this.graphics.setFill(Color.BLACK);
        this.graphics.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        PixelWriter pixelWriter = canvas.getGraphicsContext2D().getPixelWriter();

        for (int col = 0; col < boardWidth; col++) {
            for (int row = 0; row < boardHeight; row++) {
                Cell cell = board.getCell(row, col);
                if (cell != null) {
                    pixelWriter.setColor(col, row, cell.getColor());
                } else {
                    pixelWriter.setColor(col, row, (Color) this.graphics.getFill());
                }
            }
        }
    }
}
