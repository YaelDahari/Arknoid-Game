import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.util.Random;

public class SimpleMain {
    public static void main(String[] args) {
        GUI gui = new GUI("two frames bouncing balls", 600, 600);
        Ball ball = new Ball(500, 200, 10, java.awt.Color.BLACK);
        GameEnvironment game = new GameEnvironment();
        ball.setGame(game);
        ball.setVelocity(10, 10);
        Block[] blocks = new Block[9];
        for (int i = 0; i < 4; i++) {
            blocks[i] = new Block(new Rectangle(randomPoint(),
                    randomSize(100), randomSize(100)));
            game.addCollidable(blocks[i]);
        }
        blocks[4] = new Block(new Rectangle(new Point(100, 300), 300, 200));
        blocks[5] = new Block(new Rectangle(new Point(0, 0), 600, 10));
        blocks[6] = new Block(new Rectangle(new Point(0, 10), 10, 600));
        blocks[7] = new Block(new Rectangle(new Point(590, 10), 10, 590));
        blocks[8] = new Block(new Rectangle(new Point(10, 590), 580, 10));
        game.addCollidable(blocks[4]);
        game.addCollidable(blocks[5]);
        game.addCollidable(blocks[6]);
        game.addCollidable(blocks[7]);
        game.addCollidable(blocks[8]);
        drawAnimation(blocks, ball, gui);
    }
    private static Point randomPoint() {
        Random rand = new Random();
        double x = rand.nextDouble() * 600;
        double y = rand.nextDouble() * 600;
        return new Point(x, y);
    }
    private static double randomSize(int maxSize) {
        Random rand = new Random();
        return rand.nextDouble() * maxSize;
    }
    public static void drawAnimation(Block[] blocks, Ball ball, GUI gui) {
        Sleeper sleeper = new Sleeper();
        DrawSurface d;
        while (true) {
            ball.moveOneStep();
            d = gui.getDrawSurface();
            ball.drawOn(d);
            for (Block block : blocks) {
                block.drawOn(d);
            }
            gui.show(d);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
        }
    }
}
