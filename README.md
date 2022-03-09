# DesignPattern_Study_Mediator

# Notion Link
https://five-cosmos-fb9.notion.site/Mediator-7450730267f64331a18e6dcc17486cc1


# 중재자 (Mediator)

### 의도

한 집합에 속해있는 객체의 상호작용을 캡슐화 하는 객체를 정의한다.

객체들이 직접 서로가 서로를 참조하지 않도록 하여 객체 사이의 소결합(loose coupling)을 촉진시키며, 개발자가 객체의 상호작용을 독립적으로 다양화시킬 수 있게 만든다.

### 활용성

- 여러 객체가 잘 정의된 형태이기는 하지만 복잡한 상호작용을 가질 때, 객체 간의 의존성이 구조화되지 않으며, 잘 이해하기 어려울 때
- 한 객체가 다른 객체를 너무 많이 참조하고, 너무 많은 의사소통을 수행해서 그 객체를 재사용하기 힘들 때
- 여러 클래스에 분산된 행동들이 상속 없이 상황에 맞게 수정되어야 할 때

### 구조 (in WiKi)

![image](https://user-images.githubusercontent.com/18654358/157388749-243ec684-31df-48e7-b504-0b0a5377c783.png)


### 참여자

**Mediator**

- 객체와 교류하는데 필요한 인터페이스를 정의

**ConcreteMediator**

- Colleague 객체와 조화를 이뤄서 협력 행동을 구현하며
- 자신이 맡을 동료(Colleague)를 파악하고 관리한다.

**Colleague 클래스들**

- 자신의 중재자 객체가 무엇인지 파악한다.
- 다른 객체와 통신이 필요하면 그 중재자를 통해 통신되도록 하는 동료 객체를 나타내는 클래스이다.

---

### 다시 살펴보는 중재자(Mediator) 패턴

Object 간의 다이렉트한 커뮤니케이션들을 없애기 위해 사용할 수 있다.

Object 간 의존성, 커플링도 줄어주게 된다.

Dependency가 복잡해지고 다른 곳에서 사용하기도 어렵다. 

이렇게 Object간 의존성을 가지도록 하는 것이 아니라 중간에 Mediator를 둠으로써 이와 같은 이슈를 해결해낼 수 있다.

![image](https://user-images.githubusercontent.com/18654358/157388775-d3c47e90-3dca-4e19-8fa2-54ba6e463bcf.png)

**조금 더 실 사용 예제로 바라보자. (스마트 홈 시스템)**

![image](https://user-images.githubusercontent.com/18654358/157388814-71cd9f7d-790f-489e-8b82-d930916372d4.png)

![image](https://user-images.githubusercontent.com/18654358/157388833-933cfa5d-9c50-41ba-9ce4-8c82fefb6353.png)

<aside>
🎈 Mediator 가 원하는 CASE 1, 2  요소를 해결해내는 것이다.

</aside>

**아래와 같이 클래스 구조를 생각해낼 수 있다.**

![image](https://user-images.githubusercontent.com/18654358/157388860-f1f918ba-16ca-41dc-a7c2-53fde53fd627.png)

**Signal.java**

```java
public enum Signal {
    ALARM_ON,
    LIGHT_OFF
}
```

**Mediator.java**

```java
public interface Mediator {
    void noti(Signal signal);
}
```

**Clock, Light, Speaker**

```java
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
```

```java
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
```

```java

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
```

[**HomeMediator.java](http://HomeMediator.java) (implements Mediator)**

```java
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
```

**테스트**
