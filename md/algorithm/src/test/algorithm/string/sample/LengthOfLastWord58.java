package algorithm.string.sample;

import org.junit.Assert;
import org.junit.Test;

public class LengthOfLastWord58 {

    @Test
    public void testLengthOfLastWord(){
        String s = "Hello World";

        int tmp = lengthOfLastWord(s);
        Assert.assertEquals(5,tmp);

        String s2 = "a ";
        int tmp2 = lengthOfLastWord(s2);
        Assert.assertEquals(1,tmp2);
    }

    private int lengthOfLastWord(String s) {


        int count = 0;
        char[] chars = s.toCharArray();
        int index = s.length()-1;
        //去除最右边的空格
        for(int i=chars.length-1;i>=0;i--){
            if(chars[i] != ' '){
                index=i;
                break;
            }
        }
        for(int i=index;i>=0;i--){
            if(chars[i] == ' '){
                break;
            }
            count ++;
        }
        return count;
    }
}
