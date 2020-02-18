package jdbc.command;

public class Receiver {
    private Command command;

    public Receiver(Command command) {
        this.command=command;
    }

   public void runCommand(){
        command.execute();
    }
}
