package sample;

import javafx.fxml.FXML;
import javafx.scene.canvas.*;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.event.*;
import javafx.scene.input.MouseEvent;
import javafx.beans.value.*;

public class Controller {
    final Color defaultColor = Color.valueOf("4d3399");

    private int action;
    private double curX;
    private double curY;
    private GraphicsContext gc;

    @FXML private Canvas canvas;
    @FXML private ToggleGroup actionSelect;
    @FXML private Toggle selectMove;
    @FXML private Toggle ellipse;
    @FXML private Toggle rectangle;
    @FXML private Toggle line;
    @FXML private ColorPicker colorPick;
    @FXML private Button deleteButton;
    @FXML private Button cloneButton;

    //changemant d'action
    private ChangeListener changeAction = new ChangeListener <Toggle>(){
        @Override
        public void changed(ObservableValue<? extends Toggle> val, Toggle oldT, Toggle newT){
            if(newT.equals(selectMove)){
                action = 0;
                deleteButton.setDisable(false);
                cloneButton.setDisable(false);
            }
            if(newT.equals(ellipse)){
                action = 1;
                deleteButton.setDisable(true);
                cloneButton.setDisable(true);
            }
            if(newT.equals(rectangle)){
                action = 2;
                deleteButton.setDisable(true);
                cloneButton.setDisable(true);
            }
            if(newT.equals(line)){
                action = 3;
                deleteButton.setDisable(true);
                cloneButton.setDisable(true);
            }
        }
    };

    //changement de couleur
    private EventHandler changeColor = new EventHandler(){
        @Override
        public void handle(Event e) {
            gc.setFill(colorPick.getValue());
            gc.setStroke(colorPick.getValue());
        }
    };

    //clic sur delete
    private EventHandler deleteHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent e) {

        }
    };

    //clic sur clone
    private EventHandler cloneHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent e) {
            
        }
    };

    //on commence à cliquer sur le canvas
    private EventHandler pressCanvas = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent e) {
            curX = e.getX();
            curY = e.getY();
        }
    };

    //on relache la souris
    private EventHandler releaseCanvas = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent e) {
            if(action == 0){

            }
            if(action == 1){
                double minX;
                double maxX;
                if(e.getX() > curX){
                    minX = curX;
                    maxX = e.getX();
                }else{
                    minX = e.getX();
                    maxX = curX;
                }
                double minY;
                double maxY;
                if(e.getY() > curY){
                    minY = curY;
                    maxY = e.getY();
                }else{
                    minY = e.getY();
                    maxY = curY;
                }
                gc.fillOval(minX, minY,maxX - minX,maxY - minY);
            }
            if(action == 2){
                double minX;
                double maxX;
                if(e.getX() > curX){
                    minX = curX;
                    maxX = e.getX();
                }else{
                    minX = e.getX();
                    maxX = curX;
                }
                double minY;
                double maxY;
                if(e.getY() > curY){
                    minY = curY;
                    maxY = e.getY();
                }else{
                    minY = e.getY();
                    maxY = curY;
                }
                gc.fillRect(minX, minY,maxX - minX,maxY - minY);
            }
            if(action == 3){
                gc.strokeLine(curX, curY, e.getX(), e.getY());
            }
        }
    };

    @FXML public void initialize(){
        //initialisation des variables
        action = 3;
        curX = 0;
        curY = 0;

        //préparation du canvas
        colorPick.setValue(defaultColor);
        gc = canvas.getGraphicsContext2D();
        gc.setFill(defaultColor);
        gc.setStroke(defaultColor);

        //ajout des listeners
        actionSelect.selectedToggleProperty().addListener(changeAction);
        colorPick.setOnAction(changeColor);
        deleteButton.setOnMouseClicked(deleteHandler);
        cloneButton.setOnMouseClicked(cloneHandler);
        canvas.setOnMousePressed(pressCanvas);
        canvas.setOnMouseReleased(releaseCanvas);
    }
}
