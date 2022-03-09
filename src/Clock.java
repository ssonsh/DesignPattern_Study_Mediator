import java.util.Objects;

public class Clock {
    private Mediator mediator;

    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }

    public void alarm(){
        System.out.println("alarm on");

        if(!Objects.isNull(this.mediator)){
            this.mediator.noti(Signal.ALARM_ON);
        }
    }
}
