public class HomeMediator implements Mediator{

    private Clock clock;
    private Light light;
    private Speaker speaker;

    public HomeMediator(Clock clock, Light light, Speaker speaker) {
        this.clock = clock;
        this.light = light;
        this.speaker = speaker;
    }

    @Override
    public void noti(Signal signal) {
        switch (signal){
            case ALARM_ON :
                // CASE 1. 알람이 켜지면 Light와 Speaker 를 On 한다.
                this.light.on();
                this.speaker.on();

                break;
            case LIGHT_OFF:
                // CASE 2. Light가 Off 되면 Speaker를 Off 한다.
                this.speaker.off();
                break;
        }
    }
}
