
// Aufgabe 10: Klasse Apple

public class Apple extends GameItem {
    private static int nextValue = 1;
    private final int value;

    public Apple(int x, int y) {
        super(x, y);
        this.value = nextValue++;
    }
    // Gibt den Punktwert des Apfels zurueck
    public int getValue() {
        return value;
    }

    //Zeichnet den Apfel an seiner aktuellen Position
    @Override
    public void paint(AudGraphics g) {
        g.setColor(AudColor.RED);
        g.fillOval(getPosition().getX() * SnakeGame.SQUARE_SIZE,
                getPosition().getY() * SnakeGame.SQUARE_SIZE,
                SnakeGame.SQUARE_SIZE, SnakeGame.SQUARE_SIZE);
    }
}