package types;

public abstract class AbstractMastermindGame implements MastermindGame{
    public AbstractMastermindGame(int seed, int size, Colour[] colours) {

    }

    public abstract int score();


}
