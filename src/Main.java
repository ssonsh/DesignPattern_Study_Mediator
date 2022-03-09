public class Main {
    public static void main(String[] args) {

        Clock clock = new Clock();
        Light light = new Light();
        Speaker speaker = new Speaker();

        Mediator mediator = new HomeMediator(clock, light, speaker);

        clock.setMediator(mediator);
        light.setMediator(mediator);
        speaker.setMediator(mediator);

        // CASE 1 수행
        System.out.println("--- CASE 1 수행 ---");
        clock.alarm();


        // CASE 2 수행
        System.out.println("--- CASE 2 수행 ---");
        light.off();
    }
}
