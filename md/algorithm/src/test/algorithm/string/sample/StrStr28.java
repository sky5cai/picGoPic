package algorithm.string.sample;

import org.junit.Assert;
import org.junit.Test;

public class StrStr28 {

    @Test
    public void testStrStr(){
        String haystack="hello";
        String needle="ll";
        int number = strStr2(haystack,needle);
        Assert.assertEquals(2,number);

    }

    private int strStr(String haystack, String needle) {
        for (int i = 0; ; i++) {
            for (int j = 0; ; j++) {
                if (j == needle.length()) return i;
                if (i + j == haystack.length()) return -1;
                if (needle.charAt(j) != haystack.charAt(i + j)) break;
            }
        }
    }

    private int strStr2(String haystack, String needle) {
        if (needle.length() == 0) {
            return 0;
        }
        int j = 0;
        //遍历每个字符
        for (int i = 0; i < haystack.length(); i++) {
            //相等的话计数加 1
            if (haystack.charAt(i) == needle.charAt(j)) {
                j++;
                //长度够了就返回
                if (j == needle.length()) {
                    return i - j + 1;
                }
                // 不相等的话 j 清零，
                // 并且 i 回到最初的位置，之后就进入 for 循环中的 i++，从下个位置继续判断
            } else {
                i = i - j;
                j = 0;
            }
        }
        return -1;
    }
}
