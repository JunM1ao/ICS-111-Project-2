import java.util.Random;

public class Hurricane {
	// instance variables
	int HPosX, HPosY;
	Random randomGen = new Random();
	EZImage Typhoon;
	int movementX, movementY;
	int hit = 150;

	// constructor
	public Hurricane() {
		Typhoon = new EZImage("foo.png", 0, 0);
		movementX = 5;
		movementY = 5;
		HPosX = randomGen.nextInt(1000);
		HPosY = randomGen.nextInt(750);
		Typhoon = EZ.addImage("foo.png", HPosX, HPosY);
		Typhoon.scaleBy(0.4);
		Typhoon.pullToFront();

	}

	// Hurricane movement method
	public void movement() {

		if (Typhoon.getXCenter() > 1000) {
			movementX = -randomGen.nextInt(8);
		}
		if (Typhoon.getXCenter() < 0) {
			movementX = randomGen.nextInt(8);
		}
		if (Typhoon.getYCenter() < 0) {
			movementY = randomGen.nextInt(8);
		}
		if (Typhoon.getYCenter() > 750) {
			movementY = -randomGen.nextInt(8);
		}
		Typhoon.translateTo(Typhoon.getXCenter() + movementX, Typhoon.getYCenter() + movementY);

	}

	// Get Hurricane X location method
	public int getX() {
		return Typhoon.getXCenter();
	}

	// Get Hurricane Y location method
	public int getY() {
		return Typhoon.getYCenter();
	}

}
