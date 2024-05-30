import java.util.Random;
import java.util.Scanner;

public class Game {

    private final static int WIDTH = 10;
    private final static String WALL_CHARACTER = "#";
    private final static String EMPTY_CHARACTER = " ";

    private final Scanner console = new Scanner(System.in);
    private Hero hero;
    private Treasure treasure;
    private boolean isOver = false;


    public void run() {
        setUp();
        while (!isOver) {
            printWorld();
            move();
        }
        printWorld(); //final render to see the move you made which caused you to win or lose the game.
    }

    private void setUp() {
        System.out.print("What is the name of your hero? ");
        String name = console.nextLine();

        Random rand = new Random();
        int x = rand.nextInt(WIDTH);
        int y = rand.nextInt(WIDTH);

        hero = new Hero(name, x, y);

        do {
            x = rand.nextInt(WIDTH);
            y = rand.nextInt(WIDTH);
        } while (x == hero.getX() && y == hero.getY());

        treasure = new Treasure(x, y);
    }

    private void printWorld() {
        System.out.println(WALL_CHARACTER.repeat(WIDTH + 2));

        for (int row = 0; row < WIDTH; row++) {
            System.out.print(WALL_CHARACTER);
            for (int col = 0; col < WIDTH; col++) {
                if (row == hero.getY() && col == hero.getX()) {
                    System.out.print(hero.getSymbol());
                } else if (row == treasure.getY() && col == treasure.getX()) {
                    System.out.print("T");
                }else {
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
                hero.moveUp();
                break;
            case 'A':
                hero.moveLeft();
                break;
            case 'S':
                hero.moveDown();
                break;
            case 'D':
                hero.moveRight();
                break;
            default:
                break;
        }

        if (hero.getX() < 0 || hero.getX() >= WIDTH || hero.getY() >= WIDTH || hero.getY() < 0) {
            System.out.println(hero.getName() + "touched lava! You lose.");
            isOver = true;
        } else if (hero.getX() == treasure.getX() && hero.getY() == treasure.getY()) {
            System.out.println("You found the treasure! You win.");
            isOver = true;
        }
    }

}
