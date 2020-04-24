package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeView;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public JFXTreeView<Parser> treeView;
    public JFXTextField url;
    public JFXButton btnLoad;
    public JFXButton btnBrows;
    public JFXButton btnExport;
    public JFXTextField txtExport;
    public Label lbl;
    private File exportFile;
    Parser parser;
    TreeItem<Parser> URLRoot;
    ArrayList<Parser> list;


    public void onClickExport() {
        list = new ArrayList<>();
        list.add(parser);
        for (TreeItem<Parser> item :
                treeView.getRoot().getChildren()) {
            list.add(item.getValue());
        }

        writeToFile();
        try{
        Runtime.getRuntime().exec("explorer.exe /select," + exportFile.getAbsolutePath()+"\\");
    }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void writeToFile() {

        File file;
        FileWriter fileWriter;
        try {
            for (Parser parser : list
            ) {
                file = new File(exportFile.getAbsolutePath() + "\\" + parser.getTitle() + ".txt");
                fileWriter = new FileWriter(file);
                fileWriter.write(parser.getURLString());
                fileWriter.flush();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void onClickBrows() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Export to ...");
        exportFile = directoryChooser.showDialog(new Stage());

        if (exportFile != null) {
            txtExport.setText(exportFile.getAbsolutePath());
            btnExport.setDisable(false);
        }
        txtExport.requestFocus();

    }

    public void onClickLoad() {
        String URL = url.getText();
        lbl.setText("");
        if (!isValid(URL)) {
            lbl.setText("URL not Valid");
            return;
        }
        parser = new Parser(URL);
        setupTreeView(parser);
    }

    private void setupTreeView(Parser parser) {
        URLRoot = new TreeItem<>(parser);
        URLRoot.setExpanded(true);
        HashMap<String, String> subURLs = parser.getSubURL();
        subURLs.forEach((url, title) -> {
            if (isValid(url)) {
                URLRoot.getChildren().add(new TreeItem<>(new Parser(url)));
            }
        });

        treeView.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            Node node = event.getPickResult().getIntersectedNode();
            if (node instanceof Text || (node instanceof TreeCell && ((TreeCell) node).getText() != null)) {
                String text = ((treeView.getSelectionModel().getSelectedItem()).getValue()).getURLString();
                lbl.setText("");
                lbl.setText(text);
            }
        });
        treeView.setRoot(URLRoot);
    }

    private boolean isValid(String url) {
        try {
            new URL(url).toURI();
            return true;
        } catch (Exception e) {
            return false;

        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnExport.setDisable(true);
//        lbl.setTextFill(Color.rgb(255, 255, 255));
    }

}
