// 325166510 Yael Dahari
import biuoop.DrawSurface;
/**
 * Balls have size (radius), color, and location (a Point). Balls also know how
 * to draw themselves on a DrawSurface.
 */
public class Ball implements Sprite {
    static final double NO_VELOCITY = 0.0;
    static final int CHANGE_DIR = -1;
    static final double DISTANCE = 5.0;
    private final Velocity velocity;
    private Point center;
    private final int radius;
    private final java.awt.Color color;
    private GameEnvironment environment;

    /**
     * Instantiates a new Ball.
     *
     * @param center (Point) - the location of the ball
     * @param r (int) - the ball's radius
     * @param color (java.awt.Color) - the ball's color
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.radius = r;
        this.color = color;
        this.velocity = new Velocity(NO_VELOCITY, NO_VELOCITY);
    }

    /**
     * Instantiates a new Ball.
     *
     * @param x (double) - the x value of the ball's location
     * @param y  (double) - the y value of the ball's location
     * @param r (int) - the ball's radius
     * @param color (java.awt.Color) - the ball's color
     */
    public Ball(int x, int y, int r, java.awt.Color color) {
        this.center = new Point(x, y);
        this.radius = r;
        this.color = color;
        this.velocity = new Velocity(NO_VELOCITY, NO_VELOCITY);
    }

    /**
     * The method returns the x value of the ball's location.
     *
     * @return (int) - the x value of the ball's location.
     */

    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * The method returns the y value of the ball's location.
     *
     * @return (int) - the y value of the ball's location.
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * The method returns the ball's radius.
     *
     * @return (int) - the ball's radius.
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * The method returns the ball's color.
     *
     * @return (int) - the ball's color.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * The method gets a DrawSurface draws the ball on the given DrawSurface.
     *
     * @param surface (DrawSurface) - the surface we're drawing on
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.getColor());
        surface.fillCircle(this.getX(), this.getY(), this.getSize());
    }

    @Override
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * The method gets a velocity and sets the velocity of this ball.
     *
     * @param v (Velocity) - the velocity we need to set
     */
    public void setVelocity(Velocity v) {
        this.setVelocity(v.getDx(), v.getDy());
    }

    /**
     * The method gets both parameters of velocity and sets the velocity of
     * this ball.
     *
     * @param dx (double) - the dx value of velocity
     * @param dy (double) - the dy value of velocity
     */
    public void setVelocity(double dx, double dy) {
        this.velocity.setDx(dx);
        this.velocity.setDy(dy);
    }

    /**
     * The method returns the velocity of this ball.
     *
     * @return (Velocity) - the velocity of this ball
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * The method gets the measurements of a frame and coordinates for its
     * left-down point. It calls moveOneStep() and checks if this ball collided
     * or went through the frame and if so, it returns it back to the wall and
     * changes the velocity direction accordingly.
     *
     * @param width (int) - the frame's width
     * @param height (int) - the frame's height
     * @param leftEdge (double) - the frame's left edge
     * @param downEdge (double) - the frame's down edge
     */
    public void moveInFrame(int width, int height, double leftEdge,
                            double downEdge) {
        this.moveOneStep();
        boolean collidedVertical = false, collidedHorizontal = false;
        double x = this.center.getX(), y = this.center.getY();
        double radius = this.getSize();
        if ((x - radius) <= leftEdge) {
            this.center.setX(leftEdge + radius);
            collidedVertical = true;
        } else if ((x + radius) >= (width + leftEdge)) {
            this.center.setX(width + leftEdge - radius);
            collidedVertical = true;
        }
        if (y - radius <= downEdge) {
            this.center.setY(downEdge + radius);
            collidedHorizontal = true;
        } else if ((y + radius) >= (height + downEdge)) {
            this.center.setY(height + downEdge - radius);
            collidedHorizontal = true;
        }
        if (collidedVertical) {
            this.setVelocity(CHANGE_DIR * this.getVelocity().getDx(),
                    this.getVelocity().getDy());
        }
        if (collidedHorizontal) {
            this.setVelocity(this.getVelocity().getDx(),
                    CHANGE_DIR * this.getVelocity().getDy());
        }
    }

    /**
     * The method applies the velocity to this ball's coordinates and
     * therefore causes it to move a step. It also checks if it's colliding
     * with an object and if so, moves the ball accordingly.
     */
    public void moveOneStep() {
        Point end = this.getVelocity().applyToPoint(this.center);
        Line trajectory = new Line(this.center, end);
        CollisionInfo c = this.environment.getClosestCollision(trajectory);
        if (c == null) {
            this.center = end;
            return;
        }
        this.center.setX(moveABit(c.collisionPoint().getX(),
                trajectory.start().getX()));
        this.center.setY(moveABit(c.collisionPoint().getY(),
                trajectory.start().getY()));
        this.setVelocity(c.collisionObject().hit(c.collisionPoint(),
                this.getVelocity()));
    }

    /**
     * The method returns the ball's coordinate to "almost" the hit point, but
     * just slightly before it.
     *
     * @param collision (double) - the collision coordinate
     * @param start (double) - the original coordinate
     * @return (double) - the coordinate just before the hit
     */
    private double moveABit(double collision, double start) {
        if (start > collision) {
            return collision + DISTANCE;
        }
        return collision - DISTANCE;
    }

    /**
     * The method sets the environment value of this point to a given value.
     *
     * @param environment (GameEnvironment) - the given value
     */
    public void setEnvironment(GameEnvironment environment) {
        this.environment = environment;
    }
    @Override
    public void addToGame(Game game) {
        game.addSprite(this);
    }
}