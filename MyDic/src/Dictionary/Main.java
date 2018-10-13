package Dictionary;

public class Main {
    public static void main(String[] args)
    {
        DictionaryCommandline commadline = new DictionaryCommandline();
        DictionaryManagement.insertFromFile();
        DictionaryManagement.dictionaryExportToFile();
       // commadline.dictionaryBasic();
       // commadline.dictionaryAdvanced();
    }
}
