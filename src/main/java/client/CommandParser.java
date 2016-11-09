package client;

import calculator.Calculator;
import command.impl.CommandImpl;
import invoker.Invoker;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import java.util.logging.Logger;

public class CommandParser {

    public static final Logger logger = Logger.getLogger("Calculator_Logger");

    public static void main(String a[]) {
        new CommandParser().start();
    }

    /*
     * This method convert the infix into postfix in order to proceed in the calculation.
     * @param input
     */
    public static List<String> infixToPostfixConvert(String input) {

        String postfixBuffer = "";
        Stack<Character> stack = new Stack<Character>();
        List<String> postfixArray = new ArrayList<String>();

        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {

                //If there's already a number in buffer, has to go to the list, put it in the list already.
                if (postfixBuffer.length() > 0) {
                    postfixArray.add(postfixBuffer);
                    postfixBuffer = "";
                }

                // check the precedence
                //Empty stack
                if (stack.size() == 0) {
                    stack.push(ch);
                } else {
                    //Already a higher prio?
                    Character chTop = stack.peek();
                    if (chTop == '*' || chTop == '/') {
                        //Add higher prio and run again same iteration.
                        postfixArray.add(String.valueOf(stack.pop()));
                        i--; //Repeat same loop.
                    } else {
                        //Push operator to stack
                        stack.push(ch);
                    }
                }
            } else {
                //Add numbers added to buffer normally
                postfixBuffer += ch;
            }
        }
        //Add all operands
        postfixArray.add(postfixBuffer);
        //All remaining operators added to array at the end and with their prio.
        while (!stack.isEmpty()) {
            postfixArray.add("" + stack.pop());
        }
        return postfixArray;
    }

    /*
     * This is the starting point of the program. It receives input from the command line
     * and process them further and sends to calculate function. At the end this method
     * displays the calculated result.
     */
    public void start() {
        Scanner scanner = new Scanner(System.in);
        logger.info("\n\t Please input expression to calculate::");

        String line = scanner.nextLine();
        List<String> postfixString = CommandParser.infixToPostfixConvert(line);
        Calculator calculator = Calculator.getInstance();
        calculator.setCurrent(new BigDecimal(0));

        calculate(calculator, postfixString);

        logger.info("Result is " + calculator.getCurrent());
    }

    /*
     * This method keeps a stack to process postfix version of the input and execute the right command implementation.
     * Currently this method supports for arithmetic command calculations only.
     * @param Cal
     * @param postFix
     */
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
            } else {
                stack.push(new BigDecimal(next.trim()));
            }
        }
    }
}