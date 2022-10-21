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

    int boardWidth = 440;
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
//        scene.setOnKeyPressed(e -> {
//                board.moveContent();
//                render();
//
//        });
        stage.show();

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(70), e -> {
            board.moveContent();
            render();
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.playFromStart();
    }

    private void createCells() {
        board.setCell(160, 225, Color.RED);
        board.setCell(131, 220, Color.VIOLET);
        board.setCell(162, 328, Color.WHITE);
        board.setCell(163, 120, Color.GOLD);
        board.setCell(164, 320, Color.GREEN);
        board.setCell(125, 227, Color.CRIMSON);
        board.setCell(166, 129, Color.AQUA);
        board.setCell(167, 120, Color.CYAN);
        board.setCell(168, 320, Color.PURPLE);
        board.setCell(169, 320, Color.PEACHPUFF);

        board.setCell(170, 320, Color.DARKSEAGREEN);
        board.setCell(150, 121, Color.NAVY);
        board.setCell(160, 322, Color.BLUE);
        board.setCell(140, 423, Color.HONEYDEW);
        board.setCell(160, 324, Color.HOTPINK);
        board.setCell(160, 325, Color.LIGHTSALMON);
        board.setCell(190, 226, Color.YELLOW);
        board.setCell(130, 129, Color.ORANGE);
        board.setCell(110, 330, Color.DARKCYAN);
        board.setCell(120, 331, Color.WHITE);

        board.setCell(160, 332, Color.DARKMAGENTA);
        board.setCell(160, 333, Color.WHITE);
        board.setCell(160, 334, Color.DARKORANGE);
        board.setCell(160, 335, Color.DARKVIOLET);
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
