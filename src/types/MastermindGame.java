package types;


//Documentation needed.

public interface MastermindGame {
	
	public static final int MAX_TRIALS = 100;
	
	public void play(Code trial);
	
	public boolean isRoundFinished();

	public void startNewRound();
	
	public Colour hint();
	
	public int getNumberOfTrials();
	
	public Code bestTrial();
	
	public int score();
	
	public boolean wasSecretRevealed();

}
