import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import java.util.ArrayList;

import java.util.List;

@Data
@Component("prototype")
public class Player  {
    private String name;
    private final  Board board;

    private final Board boardShots;
    private final Ship ship;

    private List<Ship> ships = new ArrayList<>();

    @Autowired
    public Player(Board board, Board boardShots, Ship ship) {
        this.board = board;
        this.boardShots = boardShots;
        this.ship = ship;
    }
    @Override
    public String toString() {
        return  name;
    }


}
