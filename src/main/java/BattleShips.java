import java.util.Scanner;

public class BattleShips {


    public class BattleshipGame {
        // Размер игрового поля
        public static final int BOARD_SIZE = 10;

        public static void main(String[] args) {
          char[][] player1Board = new char[BOARD_SIZE][BOARD_SIZE];
            char[][] player2Board = new char[BOARD_SIZE][BOARD_SIZE];

            // Инициализация игровых полей
            initializeBoard(player1Board);
            initializeBoard(player2Board);

            // Расстановка кораблей для обоих игроков
            placeShips(player1Board);
            placeShips(player2Board);

            // Игровой цикл
            boolean isGameOver = false;
            int currentPlayer = 1;
            while (!isGameOver) {
                System.out.println("Ход игрока " + currentPlayer);

                // Отобразить игровое поле текущего игрока
                if (currentPlayer == 1) {
                    displayBoard(player1Board);
                } else {
                    displayBoard(player2Board);
                }

                // Попытка выстрелить
                if (currentPlayer == 1) {
                    isGameOver = playTurn(player2Board);
                    currentPlayer = 2;
                } else {
                    isGameOver = playTurn(player1Board);
                    currentPlayer = 1;
                }
            }

            System.out.println("Игра окончена. Победил игрок " + currentPlayer);
        }

        // Инициализация игрового поля
        public static void initializeBoard(char[][] board) {
            for (int i = 0; i < BOARD_SIZE; i++) {
                for (int j = 0; j < BOARD_SIZE; j++) {
                    board[i][j] = '~'; // Море
                }
            }
        }

        // Отобразить игровое поле
        public static void displayBoard(char[][] board) {
            System.out.println("  A B C D E F G H I J");
            for (int i = 0; i < BOARD_SIZE; i++) {
                System.out.print((i + 1) + " ");
                for (int j = 0; j < BOARD_SIZE; j++) {
                    System.out.print(board[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println();
        }

        // Расстановка кораблей
        public static void placeShips(char[][] board) {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Ход игрока: разместите свои корабли.");
            for (int i = 1; i <= 5; i++) {
                System.out.println("Разместите корабль " + i + " размером " + (6 - i) + ".");
                int size = 6 - i;

                for (int j = 0; j < i; j++) {
                    boolean isValidPlacement = false;
                    int x = 0, y = 0;

                    // Проверка на корректность размещения корабля
                    while (!isValidPlacement) {
                        System.out.print("Введите координаты X и Y (Например, A 1): ");
                        String input = scanner.nextLine();

                        char xChar = input.charAt(0);
                        char yChar = input.charAt(2);

                        x = xChar - 'A';
                        y = Character.getNumericValue(yChar) - 1;

                        if (x >= 0 && x < BOARD_SIZE && y >= 0 && y < BOARD_SIZE) {
                            if (board[x][y] == '~') { // Можно разместить корабль
                                isValidPlacement = true;
                            } else {
                                System.out.println("Координаты " + input + " уже заняты.");
                            }
                        } else {
                            System.out.println("Координаты " + input + " вне диапазона.");
                        }
                    }

                    // Размещение корабля
                    board[x][y] = Character.forDigit(size, 10);
                }

                displayBoard(board);
            }
        }

        // Ход игрока
        public static boolean playTurn(char[][] board) {
            Scanner scanner = new Scanner(System.in);

            boolean isHit = false;
            while (!isHit) {
                System.out.print("Введите координаты X и Y (Например, A 1): ");
                String input = scanner.nextLine();

                char xChar = input.charAt(' ');
            }
            return true;
        }

    }
}
