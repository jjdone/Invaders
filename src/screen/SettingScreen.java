package screen;

import engine.Cooldown;
import engine.Core;

import java.awt.event.KeyEvent;

public class SettingScreen extends Screen {
	
	private static final int SELECTION_TIME = 200;
	
	private static final int RightKey = 1;
	private static final int LeftKey = 2;
	private static final int Exit = 3;
	
	private Cooldown selectionCooldown;

	public SettingScreen(final int width, final int height, final int fps) {
		super(width, height, fps);
		
		this.returnCode = 1;
		this.selectionCooldown = Core.getCooldown(SELECTION_TIME);
		this.selectionCooldown.reset();
		this.functionCode = 1;
	}

	public final int run() {
		super.run();
		
		return this.returnCode;
	}
	
	private void draw() {
		drawManager.initDrawing(this);
		drawManager.drawSetting(this);
		drawManager.drawSelection(this, this.functionCode);
		drawManager.completeDrawing(this);
	}
	
	protected final void update() {
		super.update();
		
		draw();
		if (this.selectionCooldown.checkFinished()
				&& this.inputDelay.checkFinished()) {
			if (inputManager.isKeyDown(KeyEvent.VK_UP)
					|| inputManager.isKeyDown(KeyEvent.VK_W)) {
				previousFunction();
				this.selectionCooldown.reset();
			}
			if (inputManager.isKeyDown(KeyEvent.VK_DOWN)
					|| inputManager.isKeyDown(KeyEvent.VK_S)) {
				nextFunction();
				this.selectionCooldown.reset();
			}
			if (inputManager.isKeyDown(KeyEvent.VK_SPACE) && this.functionCode == RightKey)
				SwitchingKey();
			else if (inputManager.isKeyDown(KeyEvent.VK_SPACE) && this.functionCode == LeftKey)
				SwitchingKey();
			else if (inputManager.isKeyDown(KeyEvent.VK_SPACE) && this.functionCode == Exit)
				this.isRunning = false;
				
		}
	}
	/** next selection(아랫 방향)
	 * */
	private void nextFunction() {
		if (this.functionCode == Exit)
			this.functionCode = RightKey;
		else
			this.functionCode++;
	}

	/** previous selection(윗 방향)
	 * */
	private void previousFunction() {
		if (this.functionCode == RightKey)
			this.functionCode = Exit;
		else
			this.functionCode--;
	}
	
	private void SwitchingKey() {
		if (this.functionCode == RightKey) {//
			key_R = inputManager.getKeyCode();
			this.logger.info("Right Key change " + key_R);
		}
		else if (this.functionCode == LeftKey) {
			key_L = inputManager.getKeyCode();
			this.logger.info("Left Key change " + key_L);
		}
	}
}
