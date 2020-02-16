package algorithm.string.sample;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ReverseVowels345 {

    @Test
    public void tesTreverseVowels(){
        String s = "hello";
        s = reverseVowels(s);
        Assert.assertEquals("holle",s);
//        System.out.println(s);

        String s2 ="leetcode";
        s2 = reverseVowels(s2);
//        System.out.println(s2);
        Assert.assertEquals("leotcede",s2);
//        Assert.assertEquals(58,number);
    }

    private String reverseVowels(String s) {
        //原因字母map
        List<Character> list = new ArrayList<Character>();
        list.add('a');
        list.add('e');
        list.add('i');
        list.add('o');
        list.add('u');
        list.add('A');
        list.add('E');
        list.add('I');
        list.add('O');
        list.add('U');
        int left = 0;
        int right = s.length()-1;
        char[] chars = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        while(left<=right){

            boolean isLeft=false;
            boolean isRight = true;
            //左指针由左往右移动，找到第一个原因字母
            for(int i=left;i<chars.length;i++){
                if(list.contains(chars[i]) && right>=i){
                    left = i;
                    isLeft=true;
                    break;
                }
            }

            //右指针由左往右移动，找到第一个原因字母
            for(int i=right;i>=0;i--){
                if(list.contains(chars[i]) && i>=left){
                    right = i;
                    isRight = true;
                    break;
                }
            }
            if(isLeft && isRight){
                //连边替换
                char leftChar = chars[left];
                char rightChar = chars[right];
                chars[left] = rightChar;
                chars[right] = leftChar;
            }

            left++;
            right--;
        }
        for(char c:chars){
            sb.append(c);
        }

        return sb.toString();
    }
}
