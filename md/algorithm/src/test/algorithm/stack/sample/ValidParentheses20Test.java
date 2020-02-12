package algorithm.stack.sample;

import org.junit.Assert;
import org.junit.Test;

public class ValidParentheses20Test {
    @Test
    public void isValidParenthese(){
        String string="[[{{}}]]";
        ValidParentheses20 validParenthese = new ValidParentheses20();
        boolean valid = validParenthese.isValid(string);
        Assert.assertTrue(valid);

        String string2="[[{{}]]";
        ValidParentheses20 validParenthese2 = new ValidParentheses20();
        boolean valid2 = validParenthese.isValid(string2);
        Assert.assertFalse(valid2);
    }
}
