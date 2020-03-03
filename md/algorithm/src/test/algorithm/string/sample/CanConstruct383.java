package algorithm.string.sample;

import org.junit.Assert;
import org.junit.Test;

public class CanConstruct383 {

    @Test
    public void testCanConstruct(){
        String s = "aa";
        String ss = "ab";
        boolean tmp = canConstruct(s,ss);
        Assert.assertEquals(false,tmp);

        String s2 = "aa";
        String ss2 = "aab";
        boolean tmp2 = canConstruct(s2,ss2);
        Assert.assertEquals(true,tmp2);
    }

    private boolean canConstruct(String ransomNote, String magazine) {

        char[] ransomNoteChar = ransomNote.toCharArray();
        char[] magazineChar = magazine.toCharArray();
        boolean isHave = false;
        int index = 0;
//        for(int i=0;i<ransomNoteChar.length-1;i++){
            for(int j=0;j<magazineChar.length-1;j++){
                if(ransomNoteChar[index]==magazineChar[j]){

                }
            }

//        }
        return false;
    }
}
