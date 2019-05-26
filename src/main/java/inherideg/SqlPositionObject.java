package inherideg;

public abstract class SqlPositionObject extends SqlObject {

    private int x;
    private int y;

    protected SqlPositionObject(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void move(int x, int y) {
        this.x = this.x + x;
        this.y = this.y + y;
    }

    public void moveUp(int y) {
        move(0, -y);
    }

    public void moveDown(int y) {
        move(0, y);
    }

    public void moveLeft(int x) {
        move(-x, 0);
    }

    public void moveRight(int x) {
        move(x, 0);
    }

    public double distanceTo(int otherX, int otherY) {
        int horiz = Math.abs(this.x - otherX);
        int vert = Math.abs(this.y - otherY);

        return Math.hypot(vert, horiz);
    }

}
