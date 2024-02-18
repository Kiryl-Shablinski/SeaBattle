package players;

import lombok.Data;

@Data
public class Player {
    private String name;

    private Board board;

    public Player(){}

    public Player(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return  name;
    }
}
