package Dictionary;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

public class DictionaryManagement {

    public static void insertFromCommandline() {

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of new word:");
        int n = sc.nextInt();
        sc.nextLine();
        System.out.println();

        for (int i = 1; i <= n; i++) {
            System.out.print("Input english word " + i + ": ");
            String english = sc.nextLine();
            System.out.print("Input vietnamese meaning " + i + ": ");
            String vietnamese = sc.nextLine();
            System.out.println();
            Dictionary.myDic.add(new Word(english, vietnamese));
        }
        sc.close();
    }
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
                   vietnamese = read_word[1];}
                Dictionary.myDic.add(new Word(english,vietnamese));
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

    public static void dictionaryExportToFile()
    {

        try {

            OutputStreamWriter writer =
                    new OutputStreamWriter(new FileOutputStream("src\\Dictionary\\fileWrite.txt"), StandardCharsets.UTF_8);
            writer.write("No | English | Vietnamese\n");
            int no = 0;
            for (Word word: Dictionary.myDic)
            {
                no = no + 1;
                writer.write(no +"  | "+word.getWord_target()+ " | "+ word.getWord_explain()+"\n");
            }
            writer.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }

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

    public static ArrayList<Word> dictionarySearcher(String english_word)
    {
        ArrayList<Word> res = new ArrayList<Word>();
        for (Word word: Dictionary.myDic)
        {
            if (word.getWord_target().substring(0,english_word.length()).equals(english_word))
            {
                res.add(word);

            }
        }

        return res;
    }
}


