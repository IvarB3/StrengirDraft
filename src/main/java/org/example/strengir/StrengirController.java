package org.example.strengir;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import vinnsla.Strengir;

public class StrengirController {
    @FXML
    private Label fxLblCount;

    @FXML
    private TextField fxTxtSearch;

    @FXML
    private TextArea fxTextArea1;

    @FXML
    private Label fxLabelFjoldi;

    private Strengir strengir = new Strengir();

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


}
