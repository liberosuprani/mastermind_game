package types;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Classe abstrata que implementa parte da lógica de um jogo Mastermind.
 * Deve ser estendida por classes concretas que implementam os métodos abstratos.
 */
public abstract class AbstractMastermindGame implements MastermindGame {
  
    // pontuação do jogador
    protected int score;
    
    // o código secreto 
    protected Code<Colour> code; 

    // número total de tentativas feitas até o momento
    private int numberOfTrials;
    
    // lista com as últimas tentativas realizadas
    private List<Code<Colour>> lastTrials;
    
    // melhor tentativa registada até o momento
    private Code<Colour> bestTrial;
    
    // array de cores possíveis no código secreto
    private Colour[] colours;
    
    // tamanho do código secreto (número de posições)
    private int codeSize;
    
    // gerador de números aleatórios usado para criar o código secreto e dicas
    private Random random;
    
    // string que representa o estado atual do tabuleiro do jogo
    private StringBuilder boardString;

    /**
     * Construtor da classe AbstractMastermindGame.
     * 
     * @param seed Valor da semente para o gerador aleatório, garantindo repetibilidade.
     * @param size Tamanho do código secreto.
     * @param colours Array de cores possíveis para o código secreto.
     */
    public AbstractMastermindGame(int seed, int size, Colour[] colours) {
        this.score = 0;
        this.colours = colours;
        this.codeSize = size;
        this.lastTrials = new ArrayList<Code<Colour>>(10);
        this.numberOfTrials = 0;
        this.bestTrial = null;
        this.random = new Random(seed);
        
        // geração do código secreto
        StringBuilder hidden = new StringBuilder("[");
        List<Colour> generatedCode = new ArrayList<Colour>();
        for (int i = 0; i < size; i++) {
            generatedCode.add(colours[random.nextInt(colours.length)]);
            hidden.append("?, ");
        }

        this.code = new Code<Colour>(generatedCode);
        hidden.delete(hidden.length() - 2, hidden.length()).append(']');
        
        // configuração inicial do tabuleiro
        this.boardString = new StringBuilder(
            "Number of Trials = " + this.numberOfTrials + "\n" +
            "Score = " + this.score + "\n" +
            hidden.toString() + "\n" + "\n"
        );
    }

    // métodos abstratos que devem ser implementados pelas classes concretas
    public abstract int score();
    public abstract boolean isRoundFinished();
    public abstract boolean updateScore();
    
    /**
     * Regista uma tentativa (jogada) no jogo.
     * 
     * @param trial tentativa feita pelo jogador.
     */
    public void play(Code<Colour> trial) {
        if (trial.getCode().size() == this.codeSize) {
            this.numberOfTrials++;
            
            if (!this.lastTrials.contains(trial)) {
                // Atualiza a lista de últimas tentativas
                int i = 1;
                if (lastTrials.size() == 10) {
                    for (; i < this.lastTrials.size(); i++) 
                        this.lastTrials.set(i - 1, this.lastTrials.get(i));
                    this.lastTrials.set(i - 1, trial);
                } else {
                    lastTrials.add(trial);
                }
                
                // atualiza a pontuação se a rodada terminou
                if (isRoundFinished())
                    updateScore();       
            }
        }
    }
    
    /**
     * Inicia uma nova rodada do jogo, reiniciando o estado.
     */
    public void startNewRound() {
        this.numberOfTrials = 0;
        this.lastTrials.clear();
        this.bestTrial = null;
        
        // Geração de um novo código secreto
        List<Colour> generatedCode = new ArrayList<Colour>();
        for (int i = 0; i < this.codeSize; i++) 
            generatedCode.add(this.colours[this.random.nextInt(this.colours.length)]);
            
        this.code = new Code<Colour>(generatedCode);
    }
    
    /**
     * Fornece uma dica ao jogador, retornando uma cor aleatória do código secreto.
     * 
     * @return Uma cor aleatória presente no código secreto.
     */
    public Colour hint() {
        int position = random.nextInt(this.codeSize);
        return (Colour) this.code.getCode().get(position);
    }
    
    /**
     * @return O número de tentativas realizadas na rodada atual.
     */
    public int getNumberOfTrials() {
        return this.numberOfTrials;
    }
    
    /**
     * Determina a melhor tentativa feita até o momento com base na proximidade ao código secreto.
     * 
     * @return A melhor tentativa ou null se nenhuma tentativa foi realizada.
     */
    public Code<Colour> bestTrial() {
        if (this.numberOfTrials == 0) {
            return null;
        }
        
        this.bestTrial = this.lastTrials.get(0);
        int bestCorrectPositions = this.code.howManyCorrect(bestTrial)[0];
        int bestWrongPositions = this.code.howManyCorrect(bestTrial)[1];
        int bestTrialIndex = 0;
        
        for (Code<Colour> trial : this.lastTrials) {
            int currentBestCorrectPositions = this.code.howManyCorrect(trial)[0];
            int currentBestWrongPositions = this.code.howManyCorrect(trial)[1];
            
            if (currentBestCorrectPositions > bestCorrectPositions) {
                bestCorrectPositions = currentBestCorrectPositions;
                bestTrialIndex = this.lastTrials.indexOf(trial);
            } else if (currentBestCorrectPositions == bestCorrectPositions) {
                if (currentBestWrongPositions > bestWrongPositions) {
                    bestWrongPositions = currentBestWrongPositions;
                    bestTrialIndex = this.lastTrials.indexOf(trial);
                } else if (currentBestWrongPositions == bestWrongPositions) {
                    if (trial.toString().compareTo(this.bestTrial.toString()) < 0)
                        bestTrialIndex = this.lastTrials.indexOf(trial);
                }
            }
        }
        
        this.bestTrial = this.lastTrials.get(bestTrialIndex);
        return this.bestTrial;
    }
    
    /**
     * Verifica se o código secreto foi descoberto.
     * 
     * @return true se a última tentativa revelou o código secreto; false caso contrário.
     */
    public boolean wasSecretRevealed() {
        if (!this.lastTrials.isEmpty()) {
        	Code<Colour> lastTrial = this.lastTrials.get(this.lastTrials.size() - 1);
            int correctPositions = this.code.howManyCorrect(lastTrial)[0];
            
            if (correctPositions == this.codeSize) 
                return true;
        }
        return false;
    }
    
    /**
     * @return Representação textual do estado atual do jogo, incluindo tentativas e pontuação.
     */
    @Override
    public String toString() {
        StringBuilder trialsString = new StringBuilder();
        
        this.boardString.delete(0, this.boardString.length());
        StringBuilder hidden = new StringBuilder("[");     
        for (int i = 0; i < this.codeSize; i++) {
            hidden.append("?, ");
        }
        hidden.delete(hidden.length() - 2, hidden.length()).append(']');
        this.boardString.append(
            "Number of Trials = " + this.numberOfTrials + "\n" +
            "Score = " + this.score + "\n" +
            hidden.toString() + "\n" + "\n"
        );
        
        int correctPositions = 0, wrongPositions = 0;
        
        for (Code<Colour> trial : this.lastTrials) {
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
