package types;

public class BullsAndCows extends AbstractMastermindGame {


	public BullsAndCows(int seed, int size, Colour[] colours) {
		super(seed, size, colours);
		this.score = 0;
		this.code = new BullsAndCowsCode(this.code.getCode());
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
		this.score += 2000;
		
		return true;
	}
	
	@Override 
	public Colour hint() {
		this.score /= 2;
		return super.hint();
	}
	
}
