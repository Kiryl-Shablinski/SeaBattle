import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigApp {

    @Bean
    public Game game(){
        return new Game(player1(), player2());
    }
    @Bean
    public Ship ship(){
        return new Ship();
    }
    @Bean
    public Board board(){
        return new Board();
    }

    @Bean
    public Player player1(){
        return new Player(board(),ship());
    }

    @Bean
    public Player player2(){
        return new Player(board(),ship());
    }

}
