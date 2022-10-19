package com.slimemold;

import com.slimemold.board.Board;
import com.slimemold.board.Cell;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

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
        board.setCell(160, 320, new Cell(Color.AQUA));
        board.setCell(319, 0, new Cell(Color.AQUA));
        board.setCell(0, 639, new Cell(Color.AQUA));
        board.setCell(0, 0, new Cell(Color.AQUA));
        board.setCell(319, 639, new Cell(Color.AQUA));
        render();
        stage.show();
    }

    private void render() {
        this.graphics.setFill(Color.BLACK);
        this.graphics.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        PixelWriter pixelWriter = canvas.getGraphicsContext2D().getPixelWriter();

        for (int col = 0; col < boardWidth; col++) {
            for (int row = 0; row < boardHeight; row++) {
                Cell cell = board.getCell(row, col);
                if (cell == null) {
                    pixelWriter.setColor(col, row, (Color) this.graphics.getFill());
                } else {
                    pixelWriter.setColor(col, row, cell.getColor());
                }
            }
        }
    }
}
