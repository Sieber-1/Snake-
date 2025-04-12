// Aufgabe 4: Klasse Snake

class Snake {
    private Point[] points;
    private AudColor color;
    private Direction nextDirection;
    private Direction lastDirection;

    public void grow(int growAmount) {    // Schlangenwachstum
    Point[] newPoints = new Point[points.length + growAmount];
    System.arraycopy(points, 0, newPoints, 0, points.length);
        points = newPoints;
    }

    // Definition der "Enum Direction" innerhalb der Snake-Klasse

    public enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    public Snake(int x, int y) {
        this(x, y, 5); // meine Standardlaenge: 5
    }

    public Snake(int x, int y, int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("Length must be positive.");
        }

        points = new Point[length];
        points[0] = new Point(x, y);
        color = AudColor.GREEN;
        nextDirection = Direction.RIGHT;
    }

    // fuer die richtige Richtung und Eingabe

    public void setNextDirection(Direction direction) {
        if ((direction == Direction.LEFT && lastDirection != Direction.RIGHT) ||
                (direction == Direction.RIGHT && lastDirection != Direction.LEFT) ||
                (direction == Direction.UP && lastDirection != Direction.DOWN) ||
                (direction == Direction.DOWN && lastDirection != Direction.UP)) {
            nextDirection = direction;
        }
    }

    // steps Deklarierung
    public void step() {
        System.arraycopy(points, 0, points, 1, points.length - 1);

        Point head = points[0];
        int x = head.getX();
        int y = head.getY();

        switch (nextDirection) {
            case UP -> y--;
            case DOWN -> y++;
            case LEFT -> x--;
            case RIGHT -> x++;
        }

        points[0] = new Point(x, y);
        lastDirection = nextDirection;
    }

    // farben

    public void paint(AudGraphics g) {
        g.setColor(color);
        for (Point p : points) {
            if (p != null) {
                g.fillRect(p.getX() * SnakeGame.SQUARE_SIZE, p.getY() * SnakeGame.SQUARE_SIZE,
                        SnakeGame.SQUARE_SIZE, SnakeGame.SQUARE_SIZE);
            }
        }
    }


    // Pruefung der Kollisionen

    public boolean collidesWith(GameItem item) {
        return points[0].getX() == item.getPosition().getX() &&
                points[0].getY() == item.getPosition().getY();
    }

    public boolean collidesWith(int x, int y) {
        for (Point p : points) {
            if (p != null && p.getX() == x && p.getY() == y) {
                return true;
            }
        }
        return false;
    }

    public boolean collidesWithSelf() {
        for (int i = 1; i < points.length; i++) {
            if (points[i] != null && points[i].getX() == points[0].getX() &&
                    points[i].getY() == points[0].getY()) {
                return true;
            }
        }
        return false;
    }
}