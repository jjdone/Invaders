package screen;

import engine.Cooldown;

public class PauseScreen extends Screen{
    /** time that the game pause was started */
    private long startTime;
    /** how long the game was paused */
    private long pauseTime;
    /**
     * Constructor, establishes the properties of the screen.
     *
     * @param width
     *            Screen width.
     * @param height
     *            Screen height.
     * @param fps
     *            Frames per second, frame rate at which the game is run.
     */
    public PauseScreen(final int width, final int height, final int fps) {
        super(width, height, fps);
        this.isRunning = false;
        /** GameScreen's return code */
        this.returnCode = 2;
    }

    /**
     * Starts the action.
     *
     * @return Next screen code.
     */
    public final int run() {
        super.run();
        return this.returnCode;
    }

    /**
     * Updates the elements on screen and checks for events.
     */
    protected final void update() {
        super.update();
        pauseTime = System.currentTimeMillis() - startTime;
        draw();
    }

    /**
     * Draws the elements associated with the screen.
     */
    private void draw() {
        drawManager.initDrawing(this);

        drawManager.drawPause(this);

        drawManager.completeDrawing(this);
    }

    /**
     * Set start time when game is paused.
     */
    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }
    public void applyPauseTime(){
        Cooldown.addPauseMilli(pauseTime);
    }
}
