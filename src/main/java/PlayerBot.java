import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


public class PlayerBot extends Player {

    private final  Board board;
    private final Ship ship;

    public PlayerBot(Board board, Ship ship) {
       super(board, ship);
       this.board = board;
       this.ship = ship;
    }
}
