package sample;

import Dictionary.Dictionary;
import Dictionary.DictionaryManagement;
import Dictionary.Word;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;


public  class Controller implements Initializable {
    @FXML ListView<String> list;
    @FXML  TextField search;
    @FXML TextArea text;
    @FXML  Text text1;
    @FXML ObservableList<String> obList = FXCollections.observableArrayList();
    @FXML FilteredList<String> filteredList = new FilteredList<>(obList,s -> true);
    @FXML Button speech_Button;
    @FXML Button API;
    @FXML  Button add;
    @FXML  Button delete;
    @FXML  Button edit;

    /**
     * API fxml
     */

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DictionaryManagement.insertFromFile();
        list.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        for (Word word: Dictionary.myDic)
        {
            obList.add( word.getWord_target());
        }
        filterListWord();
        showMeaning();
        ActionSpeech();
        API();// API google
        ADD();
    }


    public void showMeaning()
    {
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
        });
    }

    /**
     * sap xep list
     */
    public void filterListWord()
    {
        list.setItems(filteredList);
        search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(element -> {
                if (newValue == null || newValue.trim().isEmpty())
                {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();
                if (element.toLowerCase().startsWith(lowerCaseFilter))
                { // filter with list
                    return true;
                }

                return false; // Does not match
            });
            list.setItems(filteredList);
            list.getSelectionModel().selectFirst();
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
                Parent root2 = FXMLLoader.load(this.getClass().getResource("ADD.fxml"));
                Scene scene = new Scene(root2);
                Stage primaryStage2 = new Stage();
                primaryStage2.setScene(scene);
                primaryStage2.setTitle("Add word");
                primaryStage2.initModality(Modality.APPLICATION_MODAL);
                primaryStage2.setResizable(false);
                primaryStage2.show();
            }catch (Exception e){
                System.out.println("Cant lead new window ");
            }
        });

    }


 }




































