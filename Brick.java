
// Aufgabe 8: Klasse Brick

class Brick extends GameItem {
    public Brick(int x, int y) {
        super(x, y);
    }

    @Override
    public void paint(AudGraphics g) {
        g.setColor(AudColor.GRAY);
        g.fillRect(getPosition().getX() * SnakeGame.SQUARE_SIZE,
                getPosition().getY() * SnakeGame.SQUARE_SIZE,
                SnakeGame.SQUARE_SIZE, SnakeGame.SQUARE_SIZE);
    }
}

