
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
public class App {
    public static void main(String[] args) {
       ApplicationContext context = new AnnotationConfigApplicationContext(ConfigApp.class);
       context.getBean(Game.class).start();

    }
}
