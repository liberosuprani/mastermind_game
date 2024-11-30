package types;

/**
 * Representa o jogo Bulls and Cows, uma variação do jogo Mastermind.
 * Esta classe estende a classe abstrata AbstractMastermindGame e implementa as regras específicas do jogo Bulls and Cows.
 * 
 * @author PCO Team
 */
public class BullsAndCows extends AbstractMastermindGame {

    /**
     * Construtor da classe BullsAndCows.
     * Inicializa o jogo com uma semente aleatória, o tamanho do código e as cores possíveis.
     * 
     * @param seed A semente para gerar números aleatórios.
     * @param size O tamanho do código a ser gerado.
     * @param colours O array de cores disponíveis para formar o código.
     */
	public BullsAndCows(int seed, int size, Colour[] colours) {
		super(seed, size, colours);
		// substitui o código gerado pela implementação específica de Bulls and Cows
		this.code = new BullsAndCowsCode(this.code.getCode());
	}

    /**
     * Retorna a pontuação atual do jogador.
     * 
     * @return A pontuação atual do jogador.
     */
	@Override
	public int score() {
		return this.score;
	}

    /**
     * Verifica se a rodada terminou. A rodada é considerada terminada se o segredo foi revelado
     * ou se o número máximo de tentativas foi alcançado.
     * 
     * @return true se a rodada terminou; false caso contrário.
     */
	@Override
	public boolean isRoundFinished() {
		if (this.wasSecretRevealed() || this.getNumberOfTrials() >= MastermindGame.MAX_TRIALS)
			return true;
		return false;
	}

    /**
     * Atualiza a pontuação do jogador. Neste jogo, a pontuação é aumentada em 2000.
     * 
     * @return true se a pontuação foi atualizada com sucesso.
     */
	@Override
	public boolean updateScore() {
		this.score += 2000;
		return true;
	}
	
    /**
     * Fornece uma dica ao jogador. A pontuação é reduzida pela metade antes de fornecer a dica.
     * 
     * @return A cor correspondente a uma dica para o jogador.
     */
	@Override 
	public Colour hint() {
		// reduz a pontuação pela metade antes de fornecer a dica
		this.score /= 2;
		return super.hint();
	}
}
