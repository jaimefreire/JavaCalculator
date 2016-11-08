package command.impl;

import calculator.Calculator;
import command.Command;

import java.math.BigDecimal;

public class CommandImpl implements Command {

    private char operator;
    private BigDecimal leftOperand;
    private BigDecimal rightOperand;
    private Calculator calculator;

    public CommandImpl(char operator, BigDecimal leftOperand, BigDecimal rightOperand, Calculator calculator) {
        this.leftOperand = leftOperand;
        this.rightOperand = rightOperand;
        this.operator = operator;
        this.calculator = calculator;
    }

    /*
     * This method invoke the three argument operation method that is only used for arithmetic calculations.
     * @param operator
     * @param leftOperand
     * @param rightOperand   *
     * @see org.sanjaya.Command#calculate()
     */
    public void calculate() {
        calculator.operation(operator, leftOperand, rightOperand);
    }
}