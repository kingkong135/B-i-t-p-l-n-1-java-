package Dictionary;


import java.util.ArrayList;
import java.util.List;

/**
 * Method:
 *  - addWord(w)
 *  - removeWord(w)
 *  - getSize()
 *  - getWord()
 *
 */
public class Dictionary {
    public static List<Word> myDic = new ArrayList<Word>();

    public static boolean addWord( Word w)
    {
        int l = 0, r = myDic.size();
        String t = w.getWord_target();
        while (l<r)
        {
            int mid = (l +r)/2;
            String wmt = myDic.get(mid).getWord_target();
            if (wmt.compareToIgnoreCase(t)==0) {
               return false; }
            if (wmt.compareToIgnoreCase(t)<0) l = mid+1 ;
            else  r = mid;
        }

        if (myDic.size() == 0 || l == myDic.size())
            myDic.add(w);
        else
            myDic.add(l,w);
        return true;
    }

    public static int find(String w)
    {
        int l = 0, r = myDic.size();
        while (l<r)
        {
            int mid = (l +r)/2;
            String wmt = myDic.get(mid).getWord_target();
            if (wmt.compareToIgnoreCase(w)==0) {
                return mid; }
            if (wmt.compareToIgnoreCase(w)<0) l = mid+1 ;
            else  r = mid;
        }
        return -1;
    }

    public static  int getSize()
    {
        return myDic.size();
    }

    public static Word getWord(String t)
    {
        for (int i = 0; i<getSize(); i++)
        {
            Word w = myDic.get(i);
            if (w.getWord_target().equalsIgnoreCase(t))
                return w;
        }
        return null;
    }





}
