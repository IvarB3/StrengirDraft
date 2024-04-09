package org.example.strengir;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class InfoController {
    @FXML
    private void handleCloseButtonAction(ActionEvent event) {
        try {
            // Hleður upp strengir-view
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/strengir/strengir-view.fxml"));
            Parent strengirViewRoot = loader.load();

            // Fer aftur á forsíðu
            Scene scene = new Scene(strengirViewRoot);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
