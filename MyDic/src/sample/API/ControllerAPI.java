package sample.API;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ControllerAPI {

    @FXML
    TextField targetAPI;
    @FXML
    TextArea explainAPI;

    public void APITranslate() {

            String explain = "";
            String Word_find = targetAPI.getText();
             Translate translate = new Translate();
        try {
            explain = translate.callTranslate("en", "vi", Word_find);
        } catch (Exception ex) {
            System.out.println("Loi");
        }
            explainAPI.setText(explain);

    }
}
