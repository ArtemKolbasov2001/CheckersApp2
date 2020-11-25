package sample;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import static sample.Main.BOARD_SIZE;

public class Checker extends StackPane {
    private CheckerType type;

    private double mouseX, mouseY;
    private double oldX, oldY;

    public CheckerType getType() {
        return type;
    }

    public double getOldX(){
        return oldX;
    }
    public double getOldY(){
        return oldY;
    }


    public Checker(CheckerType type, int x, int y) {
        this.type = type;
        move(x, y);

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

        setOnMousePressed(e -> {
            mouseX = e.getSceneX();
            mouseY = e.getSceneY();
        });

        setOnMouseDragged(e->{
            relocate(e.getSceneX() - mouseX + oldX, e.getSceneY() - mouseY + oldY);
        });


    }
    public void move(int x, int y){
        oldX = x * BOARD_SIZE;
        oldY = y * BOARD_SIZE;
        relocate(oldX, oldY);
    }
    public void abortMove(){
        relocate(oldX, oldY);
    }

}

