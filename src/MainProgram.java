import java.awt.Color;

public class MainProgram {

	public static void main(String[] args) {
		EZ.initialize(1024, 768);
		// font setup
		int fontsize1 = 50;
		Color c1 = new Color(44, 242, 228);
		EZText thirst = EZ.addText(830, 730, "", c1, fontsize1);
		thirst.setFont("1.ttf");
		Color c2 = new Color(255, 87, 51);
		EZText hunger = EZ.addText(850, 680, "", c2, fontsize1);
		hunger.setFont("1.ttf");
		Color c3 = new Color(102, 255, 153);
		EZText score = EZ.addText(195, 50, "", c3, fontsize1);
		score.setFont("1.ttf");

		// setup
		double game = 0; // game state
		int amountofbottles = 5;
		int amountofhams = 5;
		int amountofstorms = 3;
		String GameOverMessage = "";
		EZImage theOcean = EZ.addImage("background.jpg", 1024 / 2, 768 / 2);
		theOcean.pushToBack();
		H2O[] waterBottle = new H2O[amountofbottles];
		for (int i = 0; i < amountofbottles; i++) {
			waterBottle[i] = new H2O();
		}
		f00d[] hams = new f00d[amountofhams];
		for (int i = 0; i < amountofhams; i++) {
			hams[i] = new f00d();
		}
		playerData pc = new playerData("kirby.png", 512, 384, 0.07);
		Hurricane[] storm = new Hurricane[amountofstorms];
		for (int i = 0; i < amountofstorms; i++) {
			storm[i] = new Hurricane();
		}
		EZSound titleMusic = EZ.addSound("RMI.wav");
		EZSound bite = EZ.addSound("bite.wav");
		EZSound sip = EZ.addSound("sip.wav");
		EZSound Hg = EZ.addSound("gone.wav");
		EZSound hit = EZ.addSound("wind.wav");
		EZSound over = EZ.addSound("go.wav");
		EZSound encourage = EZ.addSound("havesound.wav");
		// title screen setup
		EZImage titleB = EZ.addImage("title.jpg", 0, 0);
		EZImage playB = EZ.addImage("play.png", 512, 384);
		Color c4 = new Color(0, 0, 0);
		EZText title = EZ.addText(512, 80, "Hurricane Survival", c4, 80);
		title.setFont("1.ttf");
		Color c5 = new Color(225, 225, 225);
		EZText instruction = EZ.addText(512, 584, "Press any 'wasd' keys to start", c5, 40);
		instruction.setFont("1.ttf");
		// game over screen setup
		EZImage go = EZ.addImage("go.jpg", 512, 384);
		go.hide();
		EZText result = EZ.addText(512, 225, "", c5, 35);
		result.hide();
		result.setFont("1.ttf");
		EZText tryagain = EZ.addText(512, 700, "Press 'R' to retry", c5, 30);
		tryagain.hide();
		tryagain.setFont("1.ttf");
		// game functions
		while (true) {
			if (game == 0) {
				titleB.pullToFront();
				playB.pullToFront();
				title.pullToFront();
				instruction.pullToFront();
				titleMusic.loop();
				game = 0.1;
			}
			if (game == 0.1) {
				if (EZInteraction.isKeyDown("w") || EZInteraction.isKeyDown("a") || EZInteraction.isKeyDown("s")
						|| EZInteraction.isKeyDown("d")) {
					game = 0.2;
					titleMusic.stop();
				}
			}
			if (game == 0.2) {
				playB.hide();
				titleB.hide();
				title.hide();
				instruction.hide();
				encourage.loop();
				game = 1;
			}
			if (game == 1) {
				pc.movement();
				// hurricane movements and detector
				for (int s = 0; s < amountofstorms; s++) {
					storm[s].movement();
					for (int w = 0; w < amountofbottles; w++) {
						if (waterBottle[w].bottle.isPointInElement(storm[s].getX(), storm[s].getY())) {
							Hg.play();
							waterBottle[w].reRandom();
						}
						if (waterBottle[w].bottle.isPointInElement(pc.getXP(), pc.getYP())) {
							pc.addScore(10);
							pc.addWaterP(8);
							sip.play();
							waterBottle[w].reRandom();
						}
					}
					for (int h = 0; h < amountofhams; h++) {
						if (hams[h].food.isPointInElement(storm[s].getX(), storm[s].getY())) {
							Hg.play();
							hams[h].reRandom();
						}
						if (hams[h].food.isPointInElement(pc.getXP(), pc.getYP())) {
							pc.addScore(10);
							pc.addFoodP(6);
							bite.play();
							hams[h].reRandom();
						}
					}
					if (EZInteraction.isKeyDown("w") || EZInteraction.isKeyDown("s") || EZInteraction.isKeyDown("a")
							|| EZInteraction.isKeyDown("d")) {
						int multiplier = 1;
						if (storm[s].Typhoon.isPointInElement(pc.getXP(), pc.getYP())) {
							if (hit.isPlaying() == false) {
								hit.play();
							}
							multiplier = 2;
						}
						float W = multiplier * -0.05f;
						float F = multiplier * -0.03f;
						pc.addWaterP(W);
						pc.addFoodP(F);
					}

				}

				// hamburger detector
				thirst.setMsg("Water: " + (int) pc.getWaterP());
				thirst.pullToFront();
				hunger.setMsg("Food: " + (int) pc.getFoodP());
				hunger.pullToFront();
				score.setMsg("Score: " + pc.getScore());
				score.pullToFront();
				EZ.refreshScreen();
				if (pc.getWaterP() <= 0) {
					game = 2;
					GameOverMessage = "You ran out of Water...";
					System.out.println(GameOverMessage);
				} else if (pc.getFoodP() <= 0) {
					game = 2;
					GameOverMessage = "You ran out of food...";
					System.out.println(GameOverMessage);
				}
			}
			if (game == 2) {// game over screen
				encourage.stop();
				go.show();
				go.pullToFront();
				result.setMsg(GameOverMessage);
				result.show();
				result.pullToFront();
				score.translateTo(512, 600);
				score.pullToFront();
				tryagain.show();
				tryagain.pullToFront();
				over.loop();

				game = 2.1;
			}
			if (game == 2.1) {
				if (EZInteraction.isKeyDown("r")) {
					game = 2.2;
					pc.setScore(0);
				}
			}
			if (game == 2.2) {
				over.stop();
				pc.setFoodP(100);
				pc.setWaterP(100);
				result.hide();
				tryagain.hide();
				go.hide();
				score.translateTo(150, 50);
				pc.player.translateTo(512, 384);

				game = 1;
			}
		}

	}

}
