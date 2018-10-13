package Dictionary;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class DictionaryCommandline {
    public  void showAllWords()
    {
        System.out.println("No | English | Vietnamese");
        int no = 0;
        for (Word word: Dictionary.myDic)
        {
            no = no + 1;
            System.out.println(no +" | "+word.getWord_target()+ " | "+ word.getWord_explain());
        }
    }

    public  void dictionaryBasic()
    {
        DictionaryManagement.insertFromCommandline();
        showAllWords();
    }

    public void dictionaryAdvanced()
    {
        DictionaryManagement.insertFromFile();
       // showAllWords();

        Scanner sc = new Scanner(System.in);
        System.out.println("the operations you can do");
        System.out.println("1: lookup word");
        System.out.println("2: delete word");
        System.out.println("3: searcher word");
        System.out.println();

        System.out.print("Enter n operators: ");
        int n = sc.nextInt();
        sc.nextLine();


        for (int i = 0;i< n; i++)
        {
            System.out.print("Your operator: ");
            String k = sc.nextLine();
            if (k.equals("1"))
            {
                System.out.print("the word you want to lookup: ");
                String lookup =  sc.nextLine();
                System.out.println("The meaning of " + lookup +" is " + DictionaryManagement.dictionaryLookup(lookup) );
            } else  if (k.equals("2") )
              {
                System.out.print("the word you want to delete: " );
                String delete =  sc.nextLine();
                DictionaryManagement.deleteWord(delete);
              } else if (k.equals("3") )
                {
                    System.out.print("the word you want to searcher: ");
                    String sr =  sc.nextLine();
                    ArrayList<Word> res =DictionaryManagement.dictionarySearcher(sr.trim());
                    for (Word j:res)
                    {
                        System.out.println(j.getWord_target()+" ");
                    }
                }

        }
        DictionaryManagement.dictionaryExportToFile();
    }
}
