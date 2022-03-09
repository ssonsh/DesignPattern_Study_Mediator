import java.util.Objects;

public class Light {
    private Mediator mediator;

    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }

    public void on(){
        System.out.println("light on");
    }

    public void off(){
        System.out.println("light off");

        if(!Objects.isNull(this.mediator)){
            this.mediator.noti(Signal.LIGHT_OFF);
        }
    }
}
