package sample.ADD;

import Dictionary.Dictionary;
import Dictionary.Word;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.util.Optional;

public class ControllerADD {

    @FXML
    private TextField addtarget;
    @FXML
    private TextArea addexplain;
    @FXML
    private Button add;

   public void addWord1()
   {
       Stage stage = (Stage) add.getScene().getWindow();
       String target = addtarget.getText();
       String explaint = addexplain.getText();

        if (target.length()==0||explaint.length()==0)
        {
            Alert warn1 = new Alert(Alert.AlertType.NONE);
            Image image1 = new Image("sample\\image\\warn.png");
            ImageView imageView1 = new ImageView(image1);
            imageView1.setFitHeight(48);
            imageView1.setFitWidth(48);
            warn1.setGraphic(imageView1);
            warn1.setTitle("Thông báo:");
            warn1.setContentText("Từ và nghĩa phải có nội dung");
            ButtonType buttonTypeOk = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            warn1.getButtonTypes().addAll(buttonTypeOk);
            Optional<ButtonType> resultRemove = warn1.showAndWait();
            if(resultRemove.get()==buttonTypeOk){
                warn1.close();
            }
        }

       else {
            Word k = new Word(target,explaint);
           if (Dictionary.addWord(k)==false)
           {
               Alert warn = new Alert(Alert.AlertType.NONE);
               Image image = new Image("sample\\image\\warn.png");
               ImageView imageView = new ImageView(image);
               imageView.setFitHeight(48);
               imageView.setFitWidth(48);
               warn.setGraphic(imageView);
               warn.setTitle("Warn");
               warn.setContentText("Từ này đã có trong từ điển");
               ButtonType buttonTypeOk = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
               warn.getButtonTypes().addAll(buttonTypeOk);
               Optional<ButtonType> resultRemove = warn.showAndWait();
               if(resultRemove.get()==buttonTypeOk)
               {
                   warn.close();
               }
           }
               else {
               Dictionary.addWord(k);
               stage.close();
               }
       }
   }







}
