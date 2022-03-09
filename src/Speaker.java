import java.util.Objects;

public class Speaker {
    private Mediator mediator;

    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }

    public void on(){
        System.out.println("speaker on");
    }

    public void off(){
        System.out.println("speaker off");
    }
}
