import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigApp {

    @Bean
    public Game game(){
        return new Game(player1(), player2());
    }
    @Bean
    public Ship ship1(){
        return new Ship();
    }
    @Bean
    public Ship ship2(){
        return new Ship();
    }
    @Bean
    public Board board1(){
        return new Board();
    }

    @Bean
    public Board board2(){
        return new Board();
    }


    @Bean
    public Player player1(){
        return new Player(board1(), boardShots1(), ship1());
    }

    @Bean
    public Board boardShots1() {
        return new Board();
    }

    @Bean
    public Player player2(){
        return new Player(board2(), boardShots2(), ship2());
    }

    @Bean
    public Board boardShots2() {
        return new Board();
    }

}
