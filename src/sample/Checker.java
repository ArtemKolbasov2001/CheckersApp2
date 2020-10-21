package sample;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import static sample.Main.BOARD_SIZE;

public class Checker extends StackPane {
    private CheckerType type;

    public CheckerType getType() {
        return type;
    }


    public Checker(CheckerType type, int x, int y) {
        this.type = type;
        relocate(x* BOARD_SIZE, y * BOARD_SIZE);

        Ellipse bg = new Ellipse(BOARD_SIZE * 0.3125, BOARD_SIZE * 0.26);
        bg.setFill(Color.BLACK);

        bg.setStroke(Color.BLACK);
        bg.setStrokeWidth(BOARD_SIZE * 0.03);

        bg.setTranslateX((BOARD_SIZE - BOARD_SIZE * 0.3125 * 2) / 2);
        bg.setTranslateY((BOARD_SIZE - BOARD_SIZE * 0.26 * 2) / 2 + BOARD_SIZE * 0.07);

        Ellipse ellipse = new Ellipse(BOARD_SIZE * 0.3125, BOARD_SIZE * 0.26);
        bg.setFill(type == CheckerType.RED ? Color.valueOf("c40003") : Color.valueOf("#fff9f4"));

        bg.setStroke(Color.BLACK);
        bg.setStrokeWidth(BOARD_SIZE * 0.03);

        bg.setTranslateX((BOARD_SIZE - BOARD_SIZE * 0.3125 * 2) / 2);
        bg.setTranslateY((BOARD_SIZE - BOARD_SIZE * 0.26 * 2) / 2);


        getChildren().addAll(bg, ellipse);


    }
}
