import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * Created by jaimefreire on 07.11.16.
 */
public class JavaCalc {

    public static int getSum(String chain) {
        Deque<String> stack = new ArrayDeque();
        stack.addAll(Arrays.asList(chain.split("")));
        return Integer.parseInt(sum(stack));
    }

    private static String sum(Deque<String> stack) {
        //Result of sum  is one element.
        if (stack.size() == 1) {
            return stack.poll();
        } else {
            Integer left = Integer.parseInt(String.valueOf(stack.pop()));
            String op = stack.pop();
            Integer right = Integer.parseInt(String.valueOf(stack.pop()));
            stack.push("" + (left + right));
            return sum(stack);
        }
    }


//    public static int getSumMulti(String chain) {
//        Deque<String> stackOps = new ArrayDeque();
//        Deque<Integer> stackVals = new ArrayDeque();
//
//        Arrays.stream(chain.split("")).forEach({ x -> Character.isDigit((x.toCharArray())[0]) ? stackVals.push
//                (Integer
//                .parseInt(x)) :  stackOps.push(x)});
//
//        return Integer.parseInt(multi(stackOps, stackVals));
//    }
//
//    private static String multi(Deque<String> stackOps, Deque<Integer> stackVals) {
//        //Result of sum  is one element.
//        if (stack.size() == 1) {
//            return stack.poll();
//        } else {
//            Integer left = Integer.parseInt(String.valueOf(stack.pop()));
//            String op = stack.pop();
//            Integer right = Integer.parseInt(String.valueOf(stack.pop()));
//            stack.push("" + (left + right));
//            return sum(stack);
//        }
//    }
}

