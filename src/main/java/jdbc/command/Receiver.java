package jdbc.command;

public class Receiver {
    private Command command;

    public Receiver(Command command) {
        this.command = command;
    }

    public Object runCommand() {
        return command.execute();
    }
}
