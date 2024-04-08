package org.example.strengir;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import vinnsla.Askrifandi;
import vinnsla.Strengir;

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
        // býr til nýjan dialog með tómum áskrifanda
        AskrifandiDialog dialog = new AskrifandiDialog(new Askrifandi(ASKRIFANDI));
        // sýndu dialoginn
        Optional<Askrifandi> utkoma = dialog.showAndWait();
        // Ef fékkst svar úr dialognum setjum við nafnið á áskrifandanum í notendaviðmótið
        utkoma.ifPresent (a -> {
            fxAskrifandi.setText(a.getNafn());});
    }



}
