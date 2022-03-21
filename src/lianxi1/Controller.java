/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lianxi1;

import java.util.Arrays;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 19852
 */
public class Controller extends Thread {

    Snake snake;
    NewJPanel jp;

    public Controller(Snake snake, NewJPanel jp) {
        this.snake = snake;
        this.jp = jp;
    }

    @Override
    public void run() {
        while (!isOver()) {
            try {
                this.Move();
                Eat();
                sleep(200);
            } catch (InterruptedException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void Move() {
        for (int i = snake.length - 1; i > 1; i--) {
            snake.coordinate.put(i, new int[]{snake.coordinate.get(i - 1)[0], snake.coordinate.get(i - 1)[1]});
        }
        snake.coordinate.put(1, new int[]{snake.headCoordinate[0], snake.headCoordinate[1]});
        switch (snake.direction) {
            case 1:
                snake.headCoordinate[1] -= 20;
                break;
            case 2:
                snake.headCoordinate[1] += 20;
                break;
            case 3:
                snake.headCoordinate[0] -= 20;
                break;
            case 4:
                snake.headCoordinate[0] += 20;
                break;
        }
        jp.repaint();
    }

    public boolean isContains(int n[]) {
        for (int i = 1; i < snake.length; i++) {
            if (Arrays.equals(n, snake.coordinate.get(i))) {
                return true;
            }
        }
        return false;
    }

    public void newFood() {
        Random r = new Random();
        int x = r.nextInt(20);
        int y = r.nextInt(20);
        snake.foodCoordinate = new int[]{x * 20, y * 20};
        while (Arrays.equals(snake.headCoordinate, snake.foodCoordinate)
                || isContains(snake.foodCoordinate)) {
            x = r.nextInt(20);
            y = r.nextInt(20);
            snake.foodCoordinate = new int[]{x * 20, y * 20};
        }
        jp.repaint();
    }

    public void Eat() {
        if (Arrays.equals(snake.headCoordinate, snake.foodCoordinate)) {
            newFood();
            newSanke();
            snake.length++;
            newFood();
        }
    }

    public void newSanke() {
        int x = 2 * snake.coordinate.get(snake.length - 1)[0] - snake.coordinate.get(snake.length - 2)[0];
        int y = 2 * snake.coordinate.get(snake.length - 1)[1] - snake.coordinate.get(snake.length - 2)[1];
        int n[] = {x, y};
        snake.coordinate.put(snake.length, new int[]{x, y});
    }

    public boolean isOver() {
        boolean flag = false;
        if (snake.headCoordinate[0] < 0 || snake.headCoordinate[0] >= 400
                || snake.headCoordinate[1] < 0 || snake.headCoordinate[1] >= 400
                || isContains(snake.headCoordinate)) {
            return true;
        }
        return false;
    }

}
