package com.slimemold;

import com.slimemold.board.Board;
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

import java.util.Objects;
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
        putCellsOnBoard(10);
        render();
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.025), e -> {
            handleContent();
            render();
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.playFromStart();
    }

    private void handleContent() {
        board.moveCells();
//        board.fadeTrails();
    }

    private void putCellsOnBoard(int amount) {
        Random rand = new Random();
        for (int i = 0; i < amount; i++) {
            switch (rand.nextInt(4)) {
                case 0 -> {
                    int x = 320 + i < boardWidth ? 320 + i : 320 - i;
                    board.addCell(new LiveCell(Color.WHITE, x, 160));
                }
                case 2 -> {
                    int x = 320 - i < boardWidth ? 320 - i : 320 + i;
                    board.addCell(new LiveCell(Color.WHITE, x, 160));
                }
                case 1 -> {
                    int y = 160 + i < boardHeight ? 160 + i : 160 - i;
                    board.addCell(new LiveCell(Color.WHITE, 320, y));
                }
                case 3 -> {
                    int y = 160 - i < boardHeight ? 160 - i : 160 + i;
                    board.addCell(new LiveCell(Color.WHITE, 320, y));
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
                LiveCell liveCell = board.getCell(x, y);
                Trail trail = board.getTrail(x, y);
                if (trail == null && liveCell == null) {
                    pixelWriter.setColor(x, y, (Color) this.graphics.getFill());
                } else pixelWriter.setColor(x, y, Objects.requireNonNullElse(liveCell, trail).getColor());
            }
        }
    }
}
