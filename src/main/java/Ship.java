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

    private static int[] initShip(){
       int[] ships = new int[2];
        for (int i = 0; i < ships.length; i++) {
            ships[i] = ships.length - i;
        }
        return ships;
    }

}
