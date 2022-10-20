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
        board.setCell(160, 320, new LiveCell(Color.WHITE));
        render();
        stage.show();

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.025), e -> {
            board.moveTileContent();
            render();
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.playFromStart();
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
