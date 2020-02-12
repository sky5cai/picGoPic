package algorithm.stack.sample;

import org.junit.Assert;
import org.junit.Test;


public class RemoveDuplicates1047 {

    @Test
    public void testRemoveDuplicates(){

        RemoveDuplicates1047 remove = new RemoveDuplicates1047();
        String s="abbaca";
        Assert.assertEquals("ca",remove.removeDuplicates(s));
    }

    private String removeDuplicates(String s) {
        StringBuilder sb = new StringBuilder();
        for(char c:s.toCharArray()){
            if(sb.length()!=0 && c==sb.charAt(sb.length()-1)){
                sb.deleteCharAt(sb.length()-1);
            }else{
                sb.append(c);
//                sb.setLength(sb.length()+1);
            }
        }


        return  sb.toString();
    }
}
