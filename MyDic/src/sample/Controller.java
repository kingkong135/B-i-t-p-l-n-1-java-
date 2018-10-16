package sample;

import Dictionary.Dictionary;
import Dictionary.DictionaryManagement;
import Dictionary.Word;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import static Dictionary.DictionaryManagement.listTarget;


public  class Controller implements Initializable {
    @FXML  ListView<String> list;
    @FXML  TextField search;
    @FXML  TextArea text;
    @FXML  Text text1;
    @FXML  Button speech_Button;
    @FXML  Button API;
    @FXML  Button add;
    @FXML  Button delete;
    @FXML  Button edit;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DictionaryManagement.insertFromFile();
        list.setItems(listTarget(null));
        update();
        showMeaning();
        ActionSpeech();
        API();// API google
        ADD();
        EDIT();
    }

    public void update()
    {
        search.textProperty().addListener((observable, oldValue, newValue) -> {
            String findWord = search.getText();
            list.getItems().removeAll();
            list.setItems(listTarget(findWord));
            list.getSelectionModel().selectFirst();

            if (list.getSelectionModel().getSelectedItems()==null)
            {
                speech_Button.setVisible(false);
                delete.setDisable(true);
            }
        });
    }



    public void showMeaning()
    {
        text.setText("");
        text1.setText("");
        list.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

            String lookup = newValue;
            String line = DictionaryManagement.dictionaryLookup(lookup);
            String [] ans = line.split("\t");
            String k="";
            for(int i =0; i<ans.length;i++)
            {
                k = k+ ans[i]+ "\n";
            }
            text.setText(k);
            text1.setText(lookup);
                speech_Button.setVisible(true);
                delete.setDisable(false);


        });
    }


    /**
     * edit button speech
     */
    public void ActionSpeech()
    {
      speech_Button.setOnAction(event -> {
          System.setProperty("mbrola.base","src\\sample\\res\\mbrola");
          VoiceManager vm = VoiceManager.getInstance();
          Voice voice = vm.getVoice("mbrola_us1");
          voice.allocate();
          voice.speak(text1.getText());
          voice.deallocate();
      });
    }

    /**
     * API
     */
    public void API()
    {
        API.setOnAction(event ->{
            try{
                Parent root1 = FXMLLoader.load(this.getClass().getResource("API/API.fxml"));
                Scene scene = new Scene(root1);
                Stage primaryStage1 = new Stage();
                primaryStage1.setScene(scene);
                primaryStage1.getIcons().add(new Image("sample\\image\\Translate.png"));
                primaryStage1.setTitle("API");
                primaryStage1.initModality(Modality.APPLICATION_MODAL);
                primaryStage1.setResizable(false);
                primaryStage1.show();
            }catch (Exception e){
                System.out.println("Cant lead new window ");
            }
        });

    }

    public void ADD()
    {
        add.setOnAction(event ->{
            try{
                Parent root2 = FXMLLoader.load(this.getClass().getResource("ADD/ADD.fxml"));
                Scene scene = new Scene(root2);
                Stage primaryStage2 = new Stage();
                primaryStage2.setScene(scene);
                primaryStage2.setTitle("Add word");
                primaryStage2.initModality(Modality.WINDOW_MODAL);
                primaryStage2.setResizable(false);
                primaryStage2.show();
            }catch (Exception e){
                System.out.println("Cant lead new window ");
            }
        });

    }


    public void REMOVE()
    {
        Alert confirmRemove = new Alert(Alert.AlertType.NONE);
        Image image = new Image("sample\\image\\info.png");
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(48);
        imageView.setFitWidth(48);
        confirmRemove.setGraphic(imageView);
        confirmRemove.setTitle("Thông báo");
        confirmRemove.setContentText("Bạn có chắc muốn xóa từ này không ?");
        ButtonType buttonTypeOk = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType buttonTypeCancel = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
        confirmRemove.getButtonTypes().addAll(buttonTypeOk,buttonTypeCancel);
        Optional<ButtonType> resultRemove = confirmRemove.showAndWait();
        if(resultRemove.get()==buttonTypeOk){
            int k = Dictionary.find(list.getSelectionModel().getSelectedItem());
            Dictionary.myDic.remove(k);
        }
        else{
            confirmRemove.close();
        }
        list.setItems(listTarget(search.getText()));
    }

    public void EDIT()
    {
        edit.setOnAction(event -> {
            String wordToDefine = list.getSelectionModel().getSelectedItem();
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Thay đổi nghĩa của từ");
            dialog.setHeaderText("Bạn có chắc chắn muốn thay đổi không");
            dialog.setContentText("Nhập vào nghĩa mới: ");

            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()){
                String newMeaning =  result.get();
                int k = Dictionary.find(list.getSelectionModel().getSelectedItem());
                Dictionary.myDic.remove(k);
                Dictionary.addWord(new Word(wordToDefine,newMeaning));
                list.setItems(listTarget(search.getText()));
            }

        });
    }


 }




































