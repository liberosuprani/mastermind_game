package types;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class AbstractMastermindGame implements MastermindGame {
  
	
	protected int score;
	
	private int numberOfTrials;
	private List<Code> lastTrials;
	private Code bestTrial;
	
	private Colour[] colours;
	
	public Code code; // TODO mudar para private (tá public pq to testando no main)
	private int codeSize;
	
	private Random random;
	
	private StringBuilder boardString;
	
	public AbstractMastermindGame(int seed, int size, Colour[] colours) {
		
		this.score = 0;
		this.colours = colours;
		this.codeSize = size;
		this.lastTrials = new ArrayList<Code>(10);
		this.numberOfTrials = 0;
		this.bestTrial = null;
		this.random = new Random(seed);
	
		StringBuilder hidden = new StringBuilder("[");
		
		List<Colour> generatedCode = new ArrayList<Colour>();
		for (int i = 0; i < size; i++) {
			generatedCode.add(colours[random.nextInt(colours.length)]);
			hidden.append("?, ");
		}
		this.code = new Code(generatedCode); 
		
		hidden.delete(hidden.length()-2, hidden.length()).append(']');
		
		this.boardString = new StringBuilder(
			"Number of Trials = " + this.numberOfTrials + "\n" +
			"Score = " + this.score + "\n" +
			hidden.toString() + "\n" + "\n"
		);
	}

    public abstract int score();

    public abstract boolean isRoundFinished();
    
    public abstract boolean updateScore();
    
    
    // IMPLEMENTATIONS
   
    public void play(Code trial) {	
    	this.numberOfTrials++;
    	
    	if (!this.lastTrials.contains(trial)) {
    		
    		int i = 1;
    		if (lastTrials.size() == 10) {
	    		for (; i < this.lastTrials.size(); i++) 
    				this.lastTrials.set(i-1, this.lastTrials.get(i));
	    		this.lastTrials.set(i-1, trial);
    		}
    		else
    			lastTrials.add(trial);
    		
    		
    		if (isRoundFinished())
    			updateScore();   		
    	}
    }
    
    public void startNewRound() {
    	this.numberOfTrials = 0;
    	this.lastTrials.clear();
    	this.bestTrial = null;
    	
    	List<Colour> generatedCode = new ArrayList<Colour>();
		for (int i = 0; i < this.codeSize; i++) 
			generatedCode.add( this.colours[this.random.nextInt(this.colours.length)] );
			
		this.code = new Code(generatedCode);
    }
    
    public Colour hint() {
    	int position = random.nextInt(this.codeSize);
    	return this.code.getCode().get(position);
    }
    
    public int getNumberOfTrials() {
    	return this.numberOfTrials;
    }
    
    public Code bestTrial() {
    	if (this.numberOfTrials == 0) {
    		return null;
    	}
    		
    	this.bestTrial = this.lastTrials.get(0);
    	int bestCorrectPositions = this.code.howManyCorrect(bestTrial)[0];
    	int bestWrongPositions = this.code.howManyCorrect(bestTrial)[1];
    	int bestTrialIndex = 0;
    	
    	for (Code trial : this.lastTrials) {
    		
    		int currentBestCorrectPositions = this.code.howManyCorrect(trial)[0];
    		int currentBestWrongPositions = this.code.howManyCorrect(trial)[1];
    		
    		if (currentBestCorrectPositions > bestCorrectPositions) {
    			bestCorrectPositions = currentBestCorrectPositions;
    			bestTrialIndex = this.lastTrials.indexOf(trial);
    		}
    		
    		else if (currentBestCorrectPositions == bestCorrectPositions) 
    			
    			if (currentBestWrongPositions > bestWrongPositions) {
    				bestWrongPositions = currentBestWrongPositions;
    				bestTrialIndex = this.lastTrials.indexOf(trial);
    			}
    			
    			else if (currentBestWrongPositions == bestWrongPositions) 
    				if (trial.toString().compareTo(this.bestTrial.toString()) < 0)
    					bestTrialIndex = this.lastTrials.indexOf(trial);		  		
    	}
    	
    	this.bestTrial = this.lastTrials.get(bestTrialIndex);
    	return this.bestTrial;
    }
    
    public boolean wasSecretRevealed() {
    	if (!this.lastTrials.isEmpty()) {
	    	Code lastTrial = this.lastTrials.getLast();
	    	int correctPositions = this.code.howManyCorrect(lastTrial)[0];
	    	
	       	if (correctPositions == this.codeSize) 
	    		return true;
    	}
    	return false;
    }
    
    @Override
    public String toString() {
    	StringBuilder trialsString = new StringBuilder();
		
    	this.boardString.delete(0, this.boardString.length());
    	StringBuilder hidden = new StringBuilder("[");    	
		for (int i = 0; i < this.codeSize; i++) {
			hidden.append("?, ");
		}
		hidden.delete(hidden.length()-2, hidden.length()).append(']');
		this.boardString.append(
    			"Number of Trials = " + this.numberOfTrials + "\n" +
				"Score = " + this.score + "\n" +
				hidden.toString() + "\n" + "\n"   			
		);
		
    	int correctPositions = 0, wrongPositions = 0;
    	
    	for (Code trial : this.lastTrials) {
    		correctPositions = this.code.howManyCorrect(trial)[0];
    		wrongPositions = this.code.howManyCorrect(trial)[1];
    		
    		trialsString.append(trial.toString() + "    " + correctPositions + " " + wrongPositions + "\n");
    	}
  
    	if (this.wasSecretRevealed()) {
    		int i = 0;
    		while (this.boardString.toString().contains("?")) {
     			this.boardString.setCharAt(
    				this.boardString.indexOf("?"),
    				this.code.getCode().get(i).toString().charAt(0)
				);
     			i++;
    		}
    	}
    	
    	this.boardString.append(trialsString);
				
    	return this.boardString.toString();
    }
    
}
