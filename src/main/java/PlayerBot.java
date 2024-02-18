public class PlayerBot extends Player {

    private final  Board board;
    private final Ship ship;

    public PlayerBot(Board board, Board boardShots, Ship ship) {
       super(board, boardShots, ship);
       this.board = board;
       this.ship = ship;
    }
}
