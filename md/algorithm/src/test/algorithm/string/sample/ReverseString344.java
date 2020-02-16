package algorithm.string.sample;

import org.junit.Assert;
import org.junit.Test;

public class ReverseString344 {

    @Test
    public void testRomanToInt(){
        char[] s = new char[]{'h','e','l','l','o'};
        s = reverseString(s);
        System.out.println(s[0]);

        char[] s2 = new char[]{'H','a','n','n','a','h'};
        s2 = reverseString(s2);
        System.out.println(s2[0]);
//        Assert.assertEquals(58,number);
    }

    private char[] reverseString(char[] s) {
        int left = 0;
        int right = s.length-1;
        while(left<=right){
            char leftChar = s[left];
            char rightChar = s[right];
            s[left] = rightChar;
            s[right] = leftChar;
            left++;
            right--;
        }

        return s;
    }
}
