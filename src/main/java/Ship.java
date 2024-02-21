import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Data
@Component
@Scope("prototype")
public class Ship {

    private final int[] ships = initShip();

    private int size;
    private int x;
    private int y;
    private int direction;

    private int[] initShip() {
        int[] ships = new int[2];
        for (int i = 0; i < ships.length; i++) {
            ships[i] = ships.length - i;
        }
        return ships;
    }

    public void borderShip(char[][] matrix) {
        int[][] neighbours = {{-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}};
           for (int [] neighbor : neighbours) {
               for (int i = 0; i < size; i++) {
                   int row;
                   int col;
                   if (direction == 1) {
                       row = x + neighbor[0];
                       col = y + neighbor[1] + i;
                   } else {
                       row = x + neighbor[0] + i;
                       col = y + neighbor[1];
                   }
                   if (isValidCell(matrix, row, col) && matrix[row][col] != 'S')
                       matrix[row][col] = '^';

               }
           }
    }

    private boolean isValidCell(char[][] matrix, int row, int col){
      return row >= 0 && row < matrix.length
              && col >= 0 && col < matrix.length;

    }
}
