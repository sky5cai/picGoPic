package algorithm.string.sample;

import org.junit.Assert;
import org.junit.Test;

public class LongestCommonPrefix14 {

    @Test
    public void testRomanToInt(){
        String[] s = new String[]{"flower","flow","flight"};
        String tmp = longestCommonPrefix(s);
        Assert.assertEquals("fl",tmp);

    }

    private String longestCommonPrefix(String[] strs) {

        if (strs.length == 0) return "";
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++)
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.length()==0) return "";
            }
        return prefix;

    }

}
