import java.util.Random;

public class H2O {
	// instance variables
	int bPosX, bPosY;
	EZImage bottle;
	Random randomGenerator = new Random();

	// constructor
	public H2O() {
		bPosX = randomGenerator.nextInt(1000);
		bPosY = randomGenerator.nextInt(750);
		bottle = EZ.addImage("water.jpg", bPosX, bPosY);
		bottle.scaleBy(0.02);
	}

	// randomize bottle location Method
	public void reRandom() {
		bPosX = randomGenerator.nextInt(1000);
		bPosY = randomGenerator.nextInt(750);
		bottle.translateTo(bPosX, bPosY);
	}

	// return X Position Method
	public int getPosX() {
		return bottle.getXCenter();
	}

	// return Y Position Method
	public int getPosY() {
		return bottle.getYCenter();
	}
}
