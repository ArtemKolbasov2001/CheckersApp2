package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

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
                 checkerGroup.getChildren().add(checker);

             }

         }
        return root;
    }




    @Override
    public void start(Stage primaryStage) throws Exception{

    Scene scene = new Scene(createContent());
    primaryStage.setTitle("Checkers");
    primaryStage.setScene(scene);
    primaryStage.show();
    }

    public Checker makeChecker(CheckerType type, int x, int y){
        Checker checker = new Checker(type, x, y);
        return checker;

    }

    public static void main(String[] args) {
        launch(args);
    }
}
