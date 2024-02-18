import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
@Data
public class Board {
    private final int BOARD_LENGTH = 16;
    private int countShips;


    private  char[][] battleBoard;


    public Board() {
        this.battleBoard = new char[BOARD_LENGTH][BOARD_LENGTH];
        this.countShips = 2;
    }
}
