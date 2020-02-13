package algorithm.stack.medium;

import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;

/**
 * [880. 索引处的解码字符串](https://leetcode-cn.com/problems/decoded-string-at-index/)
 */
public class DecodeAtIndex880 {
    @Test
    public void testDecodeAtIndex(){
        String S="leet2code3";
        int K=10;
        String t = decodeAtIndex(S,K);
        Assert.assertEquals("o",t);

        String S1="ha22";
        int K1=5;
        String t1 = decodeAtIndex(S1,K1);
        Assert.assertEquals("h",t1);
    }

/*    private String decodeAtIndex(String S, int K) {
        StringBuilder sb = new StringBuilder();
        StringBuilder temp = new StringBuilder();
        for(char c:S.toCharArray()){
            if(Character.isDigit(c)){
                for(int i=1;i<=Integer.valueOf(String.valueOf(c));i++){
                    sb.append(temp);
                }
                temp = new StringBuilder();
            }else{
                temp.append(c);
            }
        }
        return Character.toString(sb.charAt(K-1));
    }*/

    private String decodeAtIndex(String S, int K) {
        long size = 0;
        int N = S.length();

        // Find size = length of decoded string
        for (int i = 0; i < N; ++i) {
            char c = S.charAt(i);
            if (Character.isDigit(c))
                size *= c - '0';
            else
                size++;
        }

        for (int i = N-1; i >= 0; --i) {
            char c = S.charAt(i);
            K %= size;
            if (K == 0 && Character.isLetter(c))
                return Character.toString(c);

            if (Character.isDigit(c))
                size /= c - '0';
            else
                size--;
        }

        throw null;
    }

}
