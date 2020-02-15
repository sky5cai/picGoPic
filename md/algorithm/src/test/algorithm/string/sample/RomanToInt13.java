package algorithm.string.sample;

import org.junit.Assert;
import org.junit.Test;

public class RomanToInt13 {

    @Test
    public void testRomanToInt(){
        String s = "LVIII";
        int number = romanToInt(s);
        Assert.assertEquals(58,number);

//        int number2 = largestRectangleArea2(heights);
//        Assert.assertEquals(10,number2);

    }

    private int romanToInt(String s) {
        int sum=0;
        if(s.indexOf("IV")!=-1){sum-=2;}
        if(s.indexOf("IX")!=-1){sum-=2;}
        if(s.indexOf("XL")!=-1){sum-=20;}
        if(s.indexOf("XC")!=-1){sum-=20;}
        if(s.indexOf("CD")!=-1){sum-=200;}
        if(s.indexOf("CM")!=-1){sum-=200;}

        char c[]=s.toCharArray();
        int count=0;

        for(;count<=s.length()-1;count++){
            if(c[count]=='M') sum+=1000;
            if(c[count]=='D') sum+=500;
            if(c[count]=='C') sum+=100;
            if(c[count]=='L') sum+=50;
            if(c[count]=='X') sum+=10;
            if(c[count]=='V') sum+=5;
            if(c[count]=='I') sum+=1;

        }

        return sum;
    }

}
