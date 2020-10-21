package sample;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.awt.*;

public class Board extends Rectangle {

   private Checker checker;
    public boolean hasChecker(){
        return checker != null;
    }
    public Checker getChecker(){
        return checker;
    }

    public void setChecker(Checker checker) {
        this.checker = checker;
    }

    public Board (boolean light, int x, int y){
        setWidth(Main.BOARD_SIZE);
        setHeight(Main.BOARD_SIZE);
        relocate(x * Main.BOARD_SIZE, y * Main.BOARD_SIZE);

        setFill(light ? Color.valueOf("#feb") : Color.valueOf("#582"));
    }
}
