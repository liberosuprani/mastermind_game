package types;

public class MultiColourMastermindGame extends AbstractMastermindGame {

	private int givenHints;
	
	public MultiColourMastermindGame(int seed, int size, Colour[] colours) {
		super(seed, size, colours);
		this.givenHints = 0;
	}
	
	@Override
	public int score() {
		return this.score;
	}


	@Override
	public boolean isRoundFinished() {
		if (this.wasSecretRevealed() || this.getNumberOfTrials() >= MastermindGame.MAX_TRIALS)
			return true;
		return false;
	}


	@Override
	public boolean updateScore() {
		int add;
		if (this.getNumberOfTrials() <= 2)
			add = 100;
		else if (this.getNumberOfTrials() <= 5)
			add = 50;
		else
			add = 20;
		
		this.score += (add / (this.givenHints + 1));
		
		return true;
	}
	
	@Override 
	public Colour hint() {
		this.givenHints++;
		return super.hint();
	}
	
}
