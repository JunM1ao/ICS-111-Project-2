public class playerData {
	// instance variables
	EZImage player;
	int xPos, yPos;
	float water = 100;
	float food = 100;
	int score = 0;
	EZSound surf = EZ.addSound("surf.wav");

	// constructor
	public playerData(String f, int x, int y, double n) {
		player = EZ.addImage(f, x, y);
		player.scaleBy(n);
		player.pullToFront();
	}

	// movement method
	public void movement() {
		if (EZInteraction.isKeyDown("w")) {
			player.translateBy(0, -6);
			if (surf.isPlaying() == false)
				surf.play();
		}
		if (EZInteraction.isKeyDown("s")) {
			player.translateBy(0, 6);
			if (surf.isPlaying() == false)
				surf.play();
		}
		if (EZInteraction.isKeyDown("a")) {
			player.translateBy(-6, 0);
			if (surf.isPlaying() == false)
				surf.play();
		}
		if (EZInteraction.isKeyDown("d")) {
			player.translateBy(6, 0);
			if (surf.isPlaying() == false)
				surf.play();
		}
		if (player.getXCenter() > 1024) {
			player.translateBy(-6, 0);
		}
		if (player.getXCenter() < 0) {
			player.translateBy(6, 0);
		}
		if (player.getYCenter() > 768) {
			player.translateBy(0, -6);
		}
		if (player.getYCenter() < 0) {
			player.translateBy(0, 6);
		}
	}

	// get X position Method
	public int getXP() {
		return player.getXCenter();
	}

	// get Y position Method
	public int getYP() {
		return player.getYCenter();
	}

	// give score Method
	public int getScore() {
		return score;
	}

	// add score Method
	public void addScore(int g) {
		score += g;
	}

	// set score Method
	public void setScore(int g) {
		score = g;
	}

	// get water points method
	public float getWaterP() {
		return water;
	}

	// add water points method
	public void addWaterP(float p) {
		water += p;
		if (water > 100) {
			water = 100;
		}
	}

	// get food points methods
	public float getFoodP() {
		return food;
	}

	// add water points method
	public void addFoodP(float p) {
		food += p;
		if (food > 100) {
			food = 100;
		}
	}

	// set water points method
	public void setWaterP(float p) {
		water = p;
	}

	// set food points method
	public void setFoodP(float p) {
		food = p;
	}
}