import java.sql.SQLOutput;

public class FieldGame {

    private static final int FIELD_LENGTH = 16;
    private int countShips;
    private final char[][] fieldSize;

    private final char[] fieldPlayres = new char[2];

    public FieldGame(int fieldLength, int countShips) {
        this.fieldSize = new char[FIELD_LENGTH][FIELD_LENGTH];
        this.countShips = countShips;
    }


    public void print(){


        for (int p = 0; p < fieldPlayres.length; p++) {
            int nameRom = 1;
            System.out.print("  ");
            for (char c = 'A'; c <= 'P'; c++) {
                System.out.print(c);
            }
            System.out.println();
            for (int i = 0; i < fieldSize.length; i++) {

                System.out.print(nameRom++ + " ");
                for (int j = 0; j < fieldSize.length; j++) {
                    System.out.print('-');
                }
                System.out.println();

            }
            System.out.println();
        }

    }

}
