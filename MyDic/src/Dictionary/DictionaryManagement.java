package Dictionary;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;

import static Dictionary.Dictionary.addWord;
import static Dictionary.Dictionary.myDic;

public class DictionaryManagement {


    public  static void insertFromFile()
    {
        try {
            File fileDir = new File("src\\Dictionary\\dict.txt");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(fileDir), "UTF8"));
            String line = null;
            while ((line = in.readLine())!=null)
            {
                String [] read_word = line.split("\t",2);
                String english = "";
                String vietnamese = "";
                if (read_word.length ==1)
                {   english = read_word[0];}
                else
                {  english = read_word[0];
                   vietnamese = read_word[1];
                   Word k = new Word(english,vietnamese);
                   addWord(k);
                }
            }

            in.close();
        } catch (FileNotFoundException ex)
        {
            System.out.println("File not found!");
        } catch (IOException ex)
        {
            System.out.println("Unexpected error reading file");
        }
    }
    public static String dictionaryLookup(String english_word)
    {
        String ans = "word not found";
        for (Word word: Dictionary.myDic)
        {
            if (word.getWord_target().equals(english_word))
            {
                return word.getWord_explain();
            }
        }
        return ans;
    }


    public static void deleteWord (String english_word)
    {
        for (Word word: Dictionary.myDic)
        {
            if (word.getWord_target().equals(english_word))
            {
                Dictionary.myDic.remove(word);
                break;
            }
        }
    }

    public static ObservableList<String> listTarget (String t)
    {
        ObservableList<String> listTarget = FXCollections.observableArrayList();
        if (t!=null)
        {
            for(Word w: myDic)
                if (w.getWord_target().toUpperCase().startsWith(t.toUpperCase()))
                    listTarget.add(w.getWord_target());
        }
        else
        {
            for (Word w :myDic)
                listTarget.add(w.getWord_target());
        }
        return listTarget;
    }
}


