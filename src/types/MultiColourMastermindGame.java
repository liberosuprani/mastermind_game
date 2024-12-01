// Grupo 2
// Libero Suprani - 62220
// Ravi Mughal - 62504
// Ricardo Avelãs - 62257

package types;

/**
 * Classe que representa um jogo de Mastermind com múltiplas cores. 
 * Essa classe herda de AbstractMastermindGame e implementa a lógica de pontuação
 * e dicas de um jogo de Mastermind com cores variadas.
 * 
 * Além disso, essa classe gerencia a quantidade de dicas fornecidas e altera a pontuação
 * dependendo do número de tentativas realizadas e das dicas fornecidas.
 * 
 * @author PCO Team
 */
public class MultiColourMastermindGame extends AbstractMastermindGame {

    // a quantidade de dicas fornecidas até o momento no jogo
    private int givenHints;
	
    /**
     * Construtor da classe MultiColourMastermindGame. Inicializa o jogo com um código secreto
     * gerado aleatoriamente e configura o número de dicas fornecidas.
     * 
     * @param seed O valor de semente para a geração aleatória do código secreto.
     * @param size O tamanho do código secreto.
     * @param colours O conjunto de cores disponíveis para formar o código secreto.
     */
	public MultiColourMastermindGame(int seed, int size, Colour[] colours) {
		super(seed, size, colours);
		this.givenHints = 0; 
	}
	
    /**
     * @return A pontuação atual do jogo.
     */
	@Override
	public int score() {
		return this.score; 
	}

    /**
     * Verifica se a rodada do jogo foi finalizada.
     * 
     * @return true se a rodada foi finalizada; false caso contrário
     */
	@Override
	public boolean isRoundFinished() {
		if (this.wasSecretRevealed() || this.getNumberOfTrials() >= MastermindGame.MAX_TRIALS)
			return true;
		return false;
	}

    /**
     * Atualiza a pontuação do jogo com base no número de tentativas realizadas e na quantidade
     * de dicas fornecidas.
     * A pontuação é incrementada de acordo com o número de tentativas e dividida pelo número de dicas fornecidas.
     * 
     * @return true se a pontuação foi atualizada corretamente.
     */
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
	
    /**
     * Fornece uma dica ao jogador. A cada dica fornecida, o contador de dicas fornecidas
     * é incrementado. A lógica para fornecer a dica é herdada da classe AbstractMastermindGame.
     * 
     * @return A cor fornecida como dica ao jogador.
     */
	@Override 
	public Colour hint() {
		this.givenHints++; 
		return super.hint(); 
	}
}
