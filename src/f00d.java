import java.util.Random;

public class f00d {
	// instance variables
	int fPosX, fPosY;
	EZImage food;
	Random randomGenerator = new Random();

	// constructor
	public f00d() {
		fPosX = randomGenerator.nextInt(1000);
		fPosY = randomGenerator.nextInt(750);
		food = EZ.addImage("ham.png", fPosX, fPosY);
		food.scaleBy(0.12);
	}

	// randomize bottle location Method
	public void reRandom() {
		fPosX = randomGenerator.nextInt(1000);
		fPosY = randomGenerator.nextInt(750);
		food.translateTo(fPosX, fPosY);
	}

	// return X Position Method
	public int getPosX() {
		return food.getXCenter();
	}

	// return Y Position Method
	public int getPosY(int foodNumber) {
		return food.getYCenter();
	}

}
