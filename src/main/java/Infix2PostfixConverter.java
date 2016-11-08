import calculator.Calculator;
import command.impl.CommandImpl;
import invoker.Invoker;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Infix2PostfixConverter {

    public static List<String> infixToPostfixConvert(String input) {

        int priority = 0;
        String postfixBuffer = "";
        Stack<Character> stack = new Stack<Character>();
        List<String> postfixArray = new ArrayList<String>();

        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {

                if (postfixBuffer.length() > 0) {
                    postfixArray.add(postfixBuffer);
                }
                postfixBuffer = "";
                // check the precedence
                if (stack.size() <= 0)
                    stack.push(ch);
                else {
                    Character chTop = stack.peek();
                    if (chTop == '*' || chTop == '/')
                        priority = 1;
                    else
                        priority = 0;
                    if (priority == 1) {
                        if (ch == '+' || ch == '-') {
                            postfixArray.add(String.valueOf(stack.pop()));
                            i--;
                        } else { // Same
                            postfixArray.add(String.valueOf(stack.pop()));
                            i--;
                        }
                    } else {
                        if (ch == '+' || ch == '-') {
                            postfixArray.add(String.valueOf(stack.pop()));
                            stack.push(ch);
                        } else
                            stack.push(ch);
                    }
                }
            } else {
                postfixBuffer += ch;
            }
        }
        postfixArray.add(postfixBuffer);
        int len = stack.size();
        for (int j = 0; j < len; j++)
            postfixArray.add(stack.pop().toString());

        return postfixArray;

    }

    public void calculate(Calculator cal, List<String> postFix) {

        Stack<BigDecimal> stack = new Stack<BigDecimal>();

        for (int i = 0; i < postFix.size(); i++) {

            String next = postFix.get(i);

            if (next.equals("+") || next.equals("-") || next.equals("*")
                    || next.equals("/")) {
                CommandImpl cmd = new CommandImpl(
                        next.charAt(0), stack.pop(), stack.pop(), cal);
                Invoker invoker = new Invoker();
                invoker.compute(cmd);
                stack.push(cal.getCurrent());
            } else if (false) {

            } else {
                stack.push(new BigDecimal(next.trim()));
            }
        }
    }

}
