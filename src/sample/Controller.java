package sample;

import com.jfoenix.controls.JFXTreeCell;
import com.jfoenix.controls.JFXTreeView;
import com.sun.corba.se.pept.transport.ContactInfoList;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTreeCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;


import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public TreeView<Child> treeView;
    public AnchorPane pane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        CheckBoxTreeItem<CheckBox> root = new CheckBoxTreeItem();
//        root.setValue(new CheckBox("root"));
//
//        root.setExpanded(true);
//        CheckBoxTreeItem<CheckBox> child1 = new CheckBoxTreeItem<>();
//        child1.setValue(new CheckBox("hi"));
//        CheckBoxTreeItem<CheckBox> child2 = new CheckBoxTreeItem<>();
//        child1.setValue(new CheckBox("hi2"));
//        root.getChildren().add(child1);
//        root.getChildren().add(child2);
//        treeView.setRoot(root);
//        treeView.setOnMouseClicked(event -> {
//
//        });
        CheckBoxTreeItem<Child> jonathanGiles = null;
        try {
//            jonathanGiles = new CheckBoxTreeItem<Child>(new Child("root"));
//            CheckBoxTreeItem<Child> juliaGiles = new CheckBoxTreeItem<Child>(new Child("Julia"));
//            CheckBoxTreeItem<Child> mattGiles = new CheckBoxTreeItem<Child>(new Child("Matt"));
//            CheckBoxTreeItem<Child> sueGiles = new CheckBoxTreeItem<Child>(new Child("Sue"));
//            CheckBoxTreeItem<Child> ianGiles = new CheckBoxTreeItem<Child>(new Child("Ian"));

            CheckBoxTreeItem<Child> gilesFamily = new CheckBoxTreeItem<Child>(new Child("https://www.google.com"));


        gilesFamily.setExpanded(true);
//        gilesFamily.getChildren().addAll(jonathanGiles, juliaGiles, mattGiles, sueGiles, ianGiles);

        // create the treeView

        treeView.setRoot(gilesFamily);
        treeView.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Node node = event.getPickResult().getIntersectedNode();
                if (node instanceof Text || (node instanceof TreeCell && ((TreeCell) node).getText() != null)) {
                    String name = ((Child) ((TreeItem)treeView.getSelectionModel().getSelectedItem()).getValue()).getTitle();
                    System.out.println("Node click: " + name);
                }
            }
        });

        // set the cell factory
        treeView.setCellFactory(CheckBoxTreeCell.<Child>forTreeView());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
