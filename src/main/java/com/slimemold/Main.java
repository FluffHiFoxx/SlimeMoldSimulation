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
    private final int BOARD_WIDTH = 640;
    private final int BOARD_HEIGHT = 320;
    private final Board BOARD = new Board(BOARD_WIDTH, BOARD_HEIGHT);
    private final BorderPane WINDOW = new BorderPane();
    private final Canvas CANVAS = new Canvas(BOARD_WIDTH, BOARD_HEIGHT);
    private final GraphicsContext GRAPHICS = CANVAS.getGraphicsContext2D();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(this.WINDOW);
        this.WINDOW.setCenter(this.CANVAS);
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
        BOARD.handleCells();
        BOARD.fillBoard();
    }

    private void putCellsOnBoard(int amount) {
        Random rand = new Random();
        int halfWidth = BOARD_WIDTH / 2;
        int halfHeight = BOARD_HEIGHT / 2;

        for (int i = 0; i < amount; i++) {
            switch (rand.nextInt(4)) {
                case 0 -> {
                    int x = halfWidth + i < BOARD_WIDTH && halfWidth + i > 0 ? halfWidth + i : halfWidth - i;
                    BOARD.addCell(new LiveCell(Color.BLUE, x, halfHeight));
                }
                case 2 -> {
                    int x = halfWidth - i > 0 ? halfWidth - i : halfWidth + i;
                    BOARD.addCell(new LiveCell(Color.RED, x, halfHeight));
                }
                case 1 -> {
                    int y = halfHeight + i < BOARD_HEIGHT && halfHeight + i > 0 ? halfHeight + i : halfHeight - i;
                    BOARD.addCell(new LiveCell(Color.LIMEGREEN, halfWidth, y));
                }
                case 3 -> {
                    int y = halfHeight - i > 0 ? halfHeight - i : halfHeight + i;
                    BOARD.addCell(new LiveCell(Color.WHITE, halfWidth, y));
                }
            }
        }
    }

    private void render() {
        this.GRAPHICS.clearRect(0, 0, CANVAS.getWidth(), CANVAS.getHeight());
        this.GRAPHICS.setFill(Color.BLACK);
        this.GRAPHICS.fillRect(0, 0, CANVAS.getWidth(), CANVAS.getHeight());
        PixelWriter pixelWriter = CANVAS.getGraphicsContext2D().getPixelWriter();
        for (Cell cell : BOARD.getCells()) {
            pixelWriter.setColor(cell.getxCoordinate(), cell.getyCoordinate(), cell.getColor());
        }
    }
}
