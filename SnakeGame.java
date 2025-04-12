



// Aufgabe 2: Hauptklasse fuer das Spiel

public class SnakeGame extends AudGameWindow {
    public static final int SQUARE_SIZE = 16; // Aufgabe 2f
    public static final int STEP_TIME = 100; // Aufgabe 5d
    public static final int GROW_AMOUNT = 5; // Aufgabe 11a

    private int width;
    private int height;
    private long lastSnakeUpdate;
    private int score;

    private Snake snake;
    private Apple apple;
    private Brick[] wall;

    // Aufgabe 2e: Konstruktor
    public SnakeGame() {
        setTitle("AuD-Snake - Score: 0");
        width = getGameAreaWidth() / SQUARE_SIZE;
        height = getGameAreaHeight() / SQUARE_SIZE;
        lastSnakeUpdate = System.currentTimeMillis();

        snake = new Snake(width / 2, height / 2); // Aufgabe 4f
        createWall(); // Aufgabe 8c
        createNewApple(); // Aufgabe 10d
    }

    // Aufgabe 2c: main-Methode
    public static void main(String[] args) {
        SnakeGame game = new SnakeGame();
        game.start(); // Startet das Spiel
    }

    // Aufgabe 2d: paintGame-Methode
    @Override
    public void paintGame(AudGraphics g) {
        g.setColor(AudColor.BLACK);
        g.fillRect(0, 0, getGameAreaWidth(), getGameAreaHeight());

        for (Brick b : wall) {
            b.paint(g); // Waende zeichnen
        }

        snake.paint(g); // Schlange zeichnen
        apple.paint(g); // Apfel zeichnen
    }

    // Aufgabe 2g: Update-Logik
    @Override
    public void updateGame(long time) {
        int steps = (int) ((time - lastSnakeUpdate) / STEP_TIME);
        for (int i = 0; i < steps; i++) {
            snake.step(); // Schlange bewegen
            checkCollisions();
            lastSnakeUpdate += STEP_TIME;
        }
    }

    @Override
    public void handleInput(int keyCode) {
        //System.out.println("Eingegebener KeyCode: " + keyCode); // war fuer meine Debug-Ausgabe

        if (keyCode == AudGameWindow.KeyEvent.VK_RIGHT) {
            snake.setNextDirection(Snake.Direction.RIGHT);
        } else if (keyCode == AudGameWindow.KeyEvent.VK_LEFT) {
            snake.setNextDirection(Snake.Direction.LEFT);
        } else if (keyCode == AudGameWindow.KeyEvent.VK_UP) {
            snake.setNextDirection(Snake.Direction.UP);
        } else if (keyCode == AudGameWindow.KeyEvent.VK_DOWN) {
            snake.setNextDirection(Snake.Direction.DOWN);
        }
    }

    // Aufgabe 9: Kollisionspruefung
    private void checkCollisions() {
        for (Brick b : wall) {
            if (snake.collidesWith(b)) {
                stop();
                showDialog("Game Over! You've hit a wall.");
                return;
            }
        }

        if (snake.collidesWithSelf()) {
            stop();
            showDialog("Game Over! You've collided with yourself.");
            return;
        }

        if (snake.collidesWith(apple)) {
            snake.grow(GROW_AMOUNT); // Aufgabe 11b
            score += apple.getValue(); // Punktestand erhoehen
            updateScore();
            createNewApple();
        }
    }

    // Aufgabe 11f: Punktestand aktualisieren
    private void updateScore() {
        setTitle("AuD-Snake - Score: " + score);
    }

    // Aufgabe 10d: Apfel erzeugen
    private void createNewApple() {
        int x, y;
        do {
            // Apfel wird immer mindestens ein Feld von den Waenden entfernt erzeugt
            x = 1 + (int) (Math.random() * (width - 2));
            y = 1 + (int) (Math.random() * (height - 2));
        } while (snake.collidesWith(x, y));

        apple = new Apple(x, y);
    }

    // Aufgabe 8c: Waende erstellen
    private void createWall() {
        wall = new Brick[2 * (width + height) - 4];
        int index = 0;

        for (int x = 0; x < width; x++) {
            wall[index++] = new Brick(x, 0);
            wall[index++] = new Brick(x, height - 1);
        }

        for (int y = 1; y < height - 1; y++) {
            wall[index++] = new Brick(0, y);
            wall[index++] = new Brick(width - 1, y);
        }
    }

    // Aufgabe 5a: Enum Direction
    public enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

}

