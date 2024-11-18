package com.fileorganizer;

import javafx.application.Application;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;

public class FileOrganizerGUI extends Application {
    @Override
    public void start(Stage stage) {
        VBox root = new VBox();
        Button selectFolderButton = new Button("Select Folder to Organize");
        root.getChildren().add(selectFolderButton);

        selectFolderButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Directories", "*.dir"));
            fileChooser.showOpenDialog(stage);
        });

        Scene scene = new Scene(root, 300, 200);
        stage.setTitle("File Organizer");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
