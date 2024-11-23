package types;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class AbstractMastermindGame implements MastermindGame {
    
	private int seed;
	
	private int score;
	
	private int numberOfTrials;
	private List<Code> lastTrials;
	private Code bestTrial;
	
	private Colour[] colours;
	
	private Code code;
	private int codeSize;
	
	private Random random;
	
	public AbstractMastermindGame(int seed, int size, Colour[] colours) {
		this.score = 0;
		this.seed = seed;
		this.colours = colours;
		this.codeSize = size;
		this.lastTrials = new ArrayList<Code>(10);
		this.numberOfTrials = 0;
		this.bestTrial = null;
		this.random = new Random(seed);
	
		List<Colour> generatedCode = new ArrayList<Colour>();
		for (int i = 0; i < size; i++) 
			generatedCode.add(colours[random.nextInt(colours.length)]);
			
		this.code = new Code(generatedCode);
	}

    public abstract int score();

    public abstract boolean isRoundFinished();
    
    public abstract boolean updateScore();
    
    
    // IMPLEMENTATIONS
   
    public void play(Code trial) {	
    	
    	if (!this.lastTrials.contains(trial)) {
    		
    		int i = 1;
    		for (; i < this.lastTrials.size()-1; i++) 
    			this.lastTrials.set(i-1, this.lastTrials.get(i));
    		this.lastTrials.set(i, trial);

    		this.numberOfTrials++;
    		
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
    	//TODO
    	return null;
    }
    
    public int getNumberOfTrials() {
    	return this.numberOfTrials;
    }
    
    public Code bestTrial() {
    	if (this.numberOfTrials == 0) {
    		this.bestTrial = this.lastTrials.get(0);
    	}
    		
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
    	Code lastTrial = this.lastTrials.getLast();
    	int correctPositions = this.code.howManyCorrect(lastTrial)[0];
    	
       	if (correctPositions == this.codeSize) 
    		return true;
    	
    	return false;
    }
    
    @Override
    public String toString() {
    	//TODO
    	return null;
    }
    
}
