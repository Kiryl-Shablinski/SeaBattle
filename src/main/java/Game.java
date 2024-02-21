import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Data
@Component
public class Game {
    private static Scanner sc = new Scanner(System.in);

    private final Player player1;
    private final Player player2;

    @Autowired
    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }


    private void initPlayer() {
        System.out.println("Выберите режим игры: 1-одиночная, 2- с напарником");
        int modeGame = sc.nextInt();
        sc.nextLine();
        if (modeGame == 1) {
            System.out.println("Введите имя игрока: ");
            String playerName = sc.nextLine();
            player1.setName(playerName);
            player2.setName("Bot");
        }
        if (modeGame == 2) {
            System.out.println("Введите имя 1-го игрока: ");
            String playerName = sc.nextLine();
            player1.setName(playerName);
            System.out.println("Введите имя 2-го игрока: ");
            playerName = sc.nextLine();
            player2.setName(playerName);
        }
    }

    public void displayBoard(char[][] board) {
        System.out.print("   ");
        for (char c = 'A'; c <= 'P'; c++) {
            System.out.print(c + " ");
        }
        System.out.println();
        for (int i = 0; i < board.length; i++) {
            if (i >= 9) {
                System.out.print((i + 1) + " ");
            } else System.out.print((i + 1) + "  ");
            for (int j = 0; j < board.length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void placeShips(char[][] board, Player player) {
        System.out.printf("Ход %s игрока: разместите свои корабли.\n", player.getName());
        displayBoard(board);
        int[] ships = player.getShip().getShips();
        Ship ship = new Ship();
        int direction = 0;
        for (int i = 0; i < ships.length; i++) {
            System.out.println("Разместите " + (i + 1) + " корабль(ля) размером " + (ships.length - i) + " палуб.");
            int shipSize = ships[i];

            for (int j = 0; j <= i; j++) {
                boolean isValidPlacement = false;
                int x = 0, y = 0;

                // Проверка на корректность размещения корабля
                while (!isValidPlacement) {
                    System.out.print("Введите координаты X и Y (Например, A 1): ");
                    String input = sc.nextLine();

                    if (shipSize > 1) {
                        System.out.print("Укажите направление корабля (1 - горизонтальное, 2 - вертикальное): ");
                        direction = sc.nextInt();
                        sc.nextLine();
                    }

                    char xChar = input.charAt(0);
                    char yChar = input.charAt(1);

                    y = Character.toUpperCase(xChar) - 'A';
                    x = Character.getNumericValue(yChar) - 1;
                    isValidPlacement = Validator.inRange(board, x, y, direction, shipSize);

                    if (x >= 0 && x < board.length && y >= 0 && y < board.length
                            && isValidPlacement && board[x][y] == '~') {
                        System.out.println("Можно разместить корабль");
                    }else{
                        System.out.println("Координаты " + input + " уже заняты, или корабль " + i +" вне диапазона поля");
                        isValidPlacement = false;
                    }
                }
                if (direction == 1) {
                            for (int k = 0; k < shipSize; k++) {
                                board[x][y + k] = 'S';
                            }
                            ship.setX(x);
                            ship.setY(y);
                            ship.setSize(shipSize);
                            ship.setDirection(direction);
                            player.getShips().add(ship);
                            ship.borderShip(board);
                            ship.borderShip(board);
                        }
                     else if (direction == 2) {
                            for (int k = 0; k < shipSize; k++) {
                                board[x + k][y] = 'S';
                            }
                            ship.setX(x);
                            ship.setY(y);
                            ship.setSize(shipSize);
                            ship.setDirection(direction);
                            player.getShips().add(ship);
                            ship.borderShip(board);
                        }

                displayBoard(board);
            }
        }
    }

    public char[][] initializeBoard(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                board[i][j] = '~'; // Море
            }
        }
        return board;
    }


    public boolean playTurn(Player playerAttack, Player playerDefeat) {

        char[][] boardAttack = playerDefeat.getBoard().getBattleBoard();
        char[][] boardShot = playerAttack.getBoardShots().getBattleBoard();

        System.out.print("Введите координаты X и Y (Например, A 1): ");
        String input = sc.nextLine();
        char xChar = input.charAt(0);
        char yChar = input.charAt(1);

        int y = Character.toUpperCase(xChar) - 'A';
        int x = Character.getNumericValue(yChar) - 1;

        if (boardAttack [x][y] == 'S') {
            for (Ship ship : playerDefeat.getShips()) {
                if (ship.getSize() > 1 && ship.getX() == x && ship.getY() == y) {
                    System.out.println("Ранил");
                } else {
                    System.out.println("Убил");
                }
                break;
            }
            boardAttack [x][y] = 'X';
            boardShot [x][y] = 'X';

        } else {
            System.out.println("Мимо!");
            boardAttack[x][y] = '*';
            boardShot [x][y] = '*';
            return false;
        }
        return true;
    }

    public boolean isGameOver(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == 'S')
                    return false;
            }
        }
        return true;
    }

    public void start() {
        System.out.println("Добро пожаловать в игру морской бой.");
        //инициализация игроков
        initPlayer();
        // Инициализация игровых полей
       initializeBoard(player1.getBoard().getBattleBoard());
       initializeBoard(player1.getBoardShots().getBattleBoard());
       initializeBoard(player2.getBoard().getBattleBoard());
       initializeBoard(player2.getBoardShots().getBattleBoard());

        // Расстановка кораблей для обоих игроков
        placeShips(player1.getBoard().getBattleBoard(), player1);
        placeShips(player2.getBoard().getBattleBoard(), player2);

        // Игровой цикл
        boolean isMiss;
        int currentPlayer = 1;

        System.out.println("Игра началась!");

        while (!isGameOver(player1.getBoard().getBattleBoard()) && !isGameOver(player2.getBoard().getBattleBoard())) {
            // Отобразить игровое поле текущего игрока
            if (currentPlayer == 1) {
                System.out.println("Ход игрока " + player1.getName());
                displayBoard(player1.getBoard().getBattleBoard());
                displayBoard(player1.getBoardShots().getBattleBoard());
                isMiss = playTurn(player1, player2);

                if (!isMiss) currentPlayer = 2;
            } else {
                System.out.println("Ход игрока " + player2.getName());
                displayBoard(player2.getBoard().getBattleBoard());
                displayBoard(player2.getBoardShots().getBattleBoard());
                isMiss = playTurn(player2, player1);
                if (!isMiss) currentPlayer = 1;
            }
        }
        String playerWinner;
        if(currentPlayer == 1){
            playerWinner = player1.getName();
        }else{
            playerWinner = player2.getName();
        }
        System.out.printf("Игра окончена. Победил игрок %s", playerWinner);
    }
}
