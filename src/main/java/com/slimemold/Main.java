package com.slimemold;

import com.slimemold.board.Board;
import com.slimemold.board.Cell;
import com.slimemold.board.LiveCell;
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
        putCellsOnBoard(500);
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
        for (int i = 0; i < amount; i++) {
            Color color = switch (rand.nextInt(4)) {
                case 0 -> Color.BLUE;
                case 2 -> Color.RED;
                case 1 -> Color.LIMEGREEN;
                case 3 -> Color.WHITE;
                default -> throw new IllegalStateException("Unexpected value: " + rand.nextInt(4));
            };
            BOARD.addCell(new LiveCell(color, rand.nextInt(BOARD_WIDTH - 1), rand.nextInt(BOARD_HEIGHT - 1)));
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
