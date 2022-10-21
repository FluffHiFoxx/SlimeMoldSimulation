package com.slimemold;

import com.slimemold.board.Board;
import com.slimemold.board.Cell;
import com.slimemold.board.LiveCell;
import com.slimemold.board.Trail;
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

import java.util.Random;

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
        stage.show();
        putCellsOnBoard(100);
        render();
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.05), e -> {
            handleContent();
            render();
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.playFromStart();
    }

    private void handleContent() {
        board.moveCells();
        board.fadeTrails();
        board.fillBoard();
    }

    private void putCellsOnBoard(int amount) {
        Random rand = new Random();
        int halfWidth = boardWidth / 2;
        int halfHeight = boardHeight / 2;

        for (int i = 0; i < amount; i++) {
            switch (rand.nextInt(4)) {
                case 0 -> {
                    int x = halfWidth + i < boardWidth && halfWidth + i > 0 ? halfWidth + i : halfWidth - i;
                    board.addCell(new LiveCell(Color.WHITE, x, halfHeight));
                }
                case 2 -> {
                    int x = halfWidth - i > 0 && halfWidth - i < boardWidth ? halfWidth - i : halfWidth + i;
                    board.addCell(new LiveCell(Color.WHITE, x, halfHeight));
                }
                case 1 -> {
                    int y = halfHeight + i < boardHeight && halfHeight + i > 0 ? halfHeight + i : halfHeight - i;
                    board.addCell(new LiveCell(Color.WHITE, halfWidth, y));
                }
                case 3 -> {
                    int y = halfHeight - i > 0 && halfHeight - i < boardHeight? halfHeight - i : halfHeight + i;
                    board.addCell(new LiveCell(Color.WHITE, halfWidth, y));
                }
            }
        }
    }

    private void render() {
        this.graphics.setFill(Color.BLACK);
        this.graphics.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        PixelWriter pixelWriter = canvas.getGraphicsContext2D().getPixelWriter();
        for (int y = 0; y < boardHeight; y++) {
            for (int x = 0; x < boardWidth; x++) {
                Cell cell = board.getCell(y, x);
                pixelWriter.setColor(x, y, cell instanceof LiveCell liveCell ? liveCell.getColor() :
                        (cell instanceof Trail trail ? trail.getColor() : (Color) graphics.getFill()));
            }
        }
    }
}
