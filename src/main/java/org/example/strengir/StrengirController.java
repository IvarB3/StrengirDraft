package org.example.strengir;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import vinnsla.Askrifandi;
import vinnsla.Strengir;

import java.io.IOException;
import java.util.Optional;


public class StrengirController {

    @FXML
    private Label fxLblCount;

    @FXML
    private TextField fxTxtSearch, fxTxtRefactor;

    @FXML
    private TextArea fxTextArea1;

    @FXML
    private Label fxLabelFjoldi;


    @FXML
    protected Button fxAskrifandi;

    private Strengir strengir = new Strengir();
    public static final String ASKRIFANDI = "Áskrifandi";

    @FXML
    public void onLeit(ActionEvent actionEvent) {
        String leitarstrengur = fxTxtSearch.getText();

        if (leitarstrengur.isEmpty()) {
            // Sýnum rauðan ramma ef lengdin er 0
            fxTxtSearch.setStyle("-fx-border-color: red;");
            return;  // Hættum við að leit ef strengur er tómur

        } else {
            // Ef border var rauður þá taka hann af
            fxTxtSearch.setStyle("");
        }

        int index = strengir.leita(leitarstrengur);

        if (index==-1){
            fxLblCount.setText("");
        }
        else {
            fxLblCount.setText(String.valueOf(index));
        }

    }

    @FXML
    public void onTeljaOrd(ActionEvent actionEvent) {
        String stuttiText = fxTxtSearch.getText();
        int fjoldi = strengir.fjoldiOrda(stuttiText);
        fxLabelFjoldi.setText(String.valueOf(fjoldi));
    }

    @FXML
    public void onVistaTexta(ActionEvent actionEvent) {
        String langiText = fxTextArea1.getText();
        strengir.setTexti(langiText);
    }

    @FXML
    public void onRefactor(ActionEvent actionEvent) {
        String searchWord = fxTxtSearch.getText();
        String replaceWord = fxTxtRefactor.getText();

        if (!searchWord.isEmpty() && !replaceWord.isEmpty()) {
            String currentText = fxTextArea1.getText();
            String updatedText = currentText.replace(searchWord, replaceWord);
            fxTextArea1.setText(updatedText);
        }
    }
    public void onLogin(ActionEvent actionEvent) {
        AskrifandiDialog dialog = new AskrifandiDialog(new Askrifandi(ASKRIFANDI));
        Optional<Askrifandi> utkoma = dialog.showAndWait();
        utkoma.ifPresent (a -> {
            fxAskrifandi.setText(a.getNafn());});
    }

    @FXML
    private Button fxBtnHighlight;

    @FXML
    public void onHighlightSearchResults(ActionEvent actionEvent) {
        String searchWord = fxTxtSearch.getText();
        if (searchWord.isEmpty()) {
            return;
        }

        String text = fxTextArea1.getText();
        String highlightedText = highlightText(text, searchWord);
        fxTextArea1.setText(highlightedText);
    }

    private String highlightText(String text, String searchWord) {
        final String highlightStart = "**"; // Start highlight
        final String highlightEnd = "**"; // End highlight

        String highlightedText = text.replaceAll(searchWord, highlightStart + searchWord + highlightEnd);

        return highlightedText;
    }

    @FXML
    private Label fxLblWordCount;

    @FXML
    public void onWordCount(ActionEvent actionEvent) {
        String text = fxTextArea1.getText().trim();

        if (text.isEmpty()) {
            fxLblWordCount.setText("0");
            return;
        }

        String[] words = text.split("\\s+");
        int wordCount = words.length;

        fxLblWordCount.setText(String.valueOf(wordCount));
    }

    @FXML
    private void onInfo(ActionEvent event) {
        try {
            // Load the FXML file for the umforrit-view
            FXMLLoader loader = new FXMLLoader(getClass().getResource("umforrit-view.fxml"));
            Parent umforritViewRoot = loader.load();

            // Get the current stage (window) using the event's source
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Optional: If you have specific setup to do with InfoController, you can do it here
            // InfoController controller = loader.getController();

            // Set the new scene on the current stage
            Scene scene = new Scene(umforritViewRoot);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace(); // Or handle the exception as you see fit
        }
    }
}
