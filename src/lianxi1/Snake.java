package lianxi1;

import java.util.Arrays;
import java.util.HashMap;

/**
 *
 * @author 19852
 */
public class Snake {

    HashMap<Integer, int[]> coordinate = new HashMap<Integer, int[]>();
    int foodCoordinate[] = {0, 0};
    int headCoordinate[] = {60, 20};
    int direction = 4;
    int length = 3;

    public Snake() {
        coordinate.put(1, new int[]{40, 20});
        coordinate.put(2, new int[]{20, 20});
    }

}
