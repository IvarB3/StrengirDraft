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
import java.util.regex.Pattern;


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
    public void onLeit() {
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
    public void onTeljaOrd() {
        String stuttiText = fxTxtSearch.getText();
        int fjoldi = strengir.fjoldiOrda(stuttiText);
        fxLabelFjoldi.setText(String.valueOf(fjoldi));
    }

    //Vista texta
    @FXML
    public void onVistaTexta() {
        String langiText = fxTextArea1.getText();
        strengir.setTexti(langiText);
    }

    //Refactora orð
    @FXML
    public void onRefactor() {
        String searchWord = fxTxtSearch.getText();
        String replaceWord = fxTxtRefactor.getText();

        if (!searchWord.isEmpty() && !replaceWord.isEmpty()) {
            String currentText = fxTextArea1.getText();
            String updatedText = currentText.replace(searchWord, replaceWord);
            fxTextArea1.setText(updatedText);
        }
    }
    public void onLogin() {
        AskrifandiDialog dialog = new AskrifandiDialog(new Askrifandi(ASKRIFANDI));
        Optional<Askrifandi> utkoma = dialog.showAndWait();
        utkoma.ifPresent (a -> {
            fxAskrifandi.setText(a.getNafn());});
    }

    //Highlighta orð
    @FXML
    public void onHighlightSearchResults() {
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
        final String regex = "\\*\\*" + Pattern.quote(searchWord) + "\\*\\*";

        // Check if the word is already highlighted
        if (Pattern.compile(regex).matcher(text).find()) {
            return text;
        }

        String highlightedText = text.replaceAll(Pattern.quote(searchWord), highlightStart + searchWord + highlightEnd);

        return highlightedText;
    }


    @FXML
    private Label fxLblWordCount;

    //Word Count
    @FXML
    public void onWordCount() {
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
            // Hleður inn umforrit-view
            FXMLLoader loader = new FXMLLoader(getClass().getResource("umforrit-view.fxml"));
            Parent umforritViewRoot = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();



            // Breytir um senu
            Scene scene = new Scene(umforritViewRoot);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
