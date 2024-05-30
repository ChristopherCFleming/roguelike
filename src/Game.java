import java.util.Random;
import java.util.Scanner;

public class Game {

    private final static int WIDTH = 10;
    private final static String WALL_CHARACTER = "#";
    private final static String EMPTY_CHARACTER = " ";

    private final Scanner console = new Scanner(System.in);
    private Hero hero;
    private boolean isOver = false;

    public void run() {
        setUp();
        while (!isOver) {
            printWorld();
            move();
        }
    }

    private void setUp() {
        System.out.print("What is the name of your hero? ");
        String name = console.nextLine();

        Random rand = new Random();
        int x = rand.nextInt(WIDTH);
        int y = rand.nextInt(WIDTH);

        hero = new Hero(name, x, y);
    }

    private void printWorld() {
        System.out.println(WALL_CHARACTER.repeat(WIDTH + 2));

        for (int row = 0; row < WIDTH; row++) {
            System.out.print(WALL_CHARACTER);
            for (int col = 0; col < WIDTH; col++) {
                if (row == hero.getY() && col == hero.getX()) {
                    System.out.print(hero.getSymbol());
                } else {
                    System.out.print(EMPTY_CHARACTER);
                }
            }

            System.out.println(WALL_CHARACTER);
        }

        System.out.println(WALL_CHARACTER.repeat(WIDTH +2));
    }

    private void move() {
        System.out.print(hero.getName() + ", move [WASD]: ");
        String move = console.nextLine().trim().toUpperCase();
        String acceptableMoves = "WASD";

        if (move.length() != 1) {
            return;
        }

        switch (move.charAt(0)) {
            case 'W':
                if (hero.getY() - 1 >=0) {
                    hero.moveUp();
                }
                break;
            case 'A':
                if (hero.getY() + 1 < WIDTH) {
                    hero.moveLeft();
                }
                break;
            case 'S':
                if (hero.getY() + 1 >= 0) {
                    hero.moveDown();
                }
                break;
            case 'D':
                if (hero.getX() + 1 < WIDTH) {
                    hero.moveRight();
                }
                break;
            case 'X':
                isOver = true;
                break;
            default:
                break;
        }
    }

}
