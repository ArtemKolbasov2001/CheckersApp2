package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

public class Main extends Application {

    public static final int BOARD_SIZE = 100;
    public static final int WIDTH = 8;
    public static final int HEIGHT = 8;

    private Board[][] desk = new Board[WIDTH][HEIGHT];

    private Group boardGroup = new Group();
    private Group checkerGroup = new Group();

    private Parent createContent() {
        Pane root = new Pane();
        root.setPrefSize(WIDTH * BOARD_SIZE, HEIGHT * BOARD_SIZE);
        root.getChildren().addAll(boardGroup, checkerGroup);

         for (int y=0; y < HEIGHT; y++){
             for (int x = 0; x < WIDTH; x++){
                 Board board = new Board((x+y) % 2== 0, x, y);
                 desk[x][y] = board;

                 boardGroup.getChildren().add(board);
                 Checker checker = null;

                 if(y <= 2 && (x + y) % 2 != 0 ) {
                     checker = makeChecker(CheckerType.RED, x, y);
                 }
                 if(y >=5 && (x + y) % 2 != 0 ) {
                     checker = makeChecker(CheckerType.WHITE, x, y);
                 }

                 if(checker != null) {
                     board.setChecker(checker);
                     checkerGroup.getChildren().add(checker);
                 }
                 board.setChecker(checker);


             }

         }
        return root;
    }


    private  MoveResult tryMove(Checker checker, int newX, int newY){

        if(desk[newX][newY].hasChecker() || (newX + newY) % 2 == 0) {
            return new MoveResult(MoveType.NONE);
        }
        int x0 = toDesk(checker.getOldX());
        int y0 = toDesk(checker.getOldY());
        if(Math.abs(newX- x0) == 1 && newY - y0 == checker.getType().moveDir){
            return new  MoveResult(MoveType.NORMAL);
        } else  if(Math.abs(newX- x0) == 2 && newY - y0 == checker.getType().moveDir * 2) {
            int x1 = x0 + (newX - x0) / 2;
            int y1 = y0 + (newY - y0) / 2;

            if(desk[x1][y1].hasChecker() && desk[x1][y1].getChecker().getType() != checker.getType()){
                return new MoveResult(MoveType.KILL, desk[x1][y1].getChecker());
            }

        }
        return new MoveResult(MoveType.NONE);


}
     private int toDesk(double pixel){
        return(int)(pixel + BOARD_SIZE / 2) / BOARD_SIZE;

         }

    @Override
    public void start(@NotNull Stage primaryStage) throws Exception{

    Scene scene = new Scene(createContent());
    primaryStage.setTitle("Checkers");
    primaryStage.setScene(scene);
    primaryStage.show();
    }

    public Checker makeChecker(CheckerType type, int x, int y){
        Checker checker = new Checker(type, x, y);
        checker.setOnMouseReleased(e ->{
            int newX = toDesk(checker.getLayoutX());
            int newY = toDesk(checker.getLayoutY());

            MoveResult result = tryMove(checker, newX, newY);
            int x0 = toDesk(checker.getOldX());
            int y0 = toDesk(checker.getOldY());
            switch (result.getType()){
                case NONE:
                    checker.abortMove();
                    break;
                case NORMAL:
                    checker.move(newX, newY);
                    desk[x0][y0].setChecker(null);
                    desk[newX][newY].setChecker(checker);
                    break;
                case KILL:
                    checker.move(newX, newY);
                    desk[x0][y0].setChecker(null);
                    desk[newX][newY].setChecker(checker);
                    Checker otherChecker = result.getChecker();
                    desk[toDesk(otherChecker.getOldX())][toDesk(otherChecker.getOldY())].setChecker(null);
                    checkerGroup.getChildren().remove(otherChecker);
                    break;
            }

        });
        return checker;

    }

    public static void main(String[] args) {
        launch(args);
    }
}
