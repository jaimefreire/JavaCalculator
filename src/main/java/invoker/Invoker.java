package invoker;

import command.Command;

public class Invoker {

    public void compute(Command command) {
        command.calculate();
    }
}