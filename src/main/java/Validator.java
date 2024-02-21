public class Validator {
    public static boolean inRange(char[][] range, int x, int y ,int direction, int size){
        return direction == 1 ? range[x][y + size] >range.length : range[x + size][y] >range.length;
    }
}
