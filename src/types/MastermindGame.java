package types;

/**
 * Interface que define os métodos necessários para um jogo de Mastermind.
 * A interface especifica os comportamentos que um jogo de Mastermind deve ter, como
 * jogar uma tentativa, verificar o fim de uma rodada, dar dicas, e obter a pontuação.
 * 
 * Constantes e métodos obrigatórios que devem ser implementados por qualquer classe
 * que implemente esta interface são definidos aqui.
 * 
 * @author PCO Team
 */
public interface MastermindGame {
	
    // número máximo de tentativas permitido no jogo
	public static final int MAX_TRIALS = 100;
	
    /**
     * Realiza uma tentativa no jogo de Mastermind, verificando se a tentativa
     * foi correta ou não e atualizando o estado do jogo conforme necessário.
     * 
     * @param trial O código (tentativa) que o jogador está a tentar adivinhar.
     */
	public void play(Code<Colour> trial);
	
    /**
     * Verifica se a rodada atual foi finalizada.
     * Uma rodada é considerada finalizada quando o código secreto foi revelado
     * ou quando o número máximo de tentativas foi atingido.
     * 
     * @return true se a rodada foi finalizada; false caso contrário.
     */
	public boolean isRoundFinished();

    /**
     * Inicia uma nova rodada do jogo de Mastermind.
     * Reinicia as tentativas e o estado do jogo para começar uma nova rodada.
     */
	public void startNewRound();
	
    /**
     * Fornece uma dica para o jogador. 
     * 
     * @return A cor fornecida como dica para o jogador.
     */
	public Colour hint();
	
    /**
     * Retorna o número de tentativas realizadas até o momento no jogo.
     * 
     * @return O número de tentativas realizadas.
     */
	public int getNumberOfTrials();
	
    /**
     * Retorna a melhor tentativa realizada até o momento.
     * 
     * @return O código da melhor tentativa.
     */
	public Code<Colour> bestTrial();
	
    /**
     * @return A pontuação atual do jogo.
     */
	public int score();
	
    /**
     * Verifica se o código secreto foi revelado durante o jogo.
     * 
     * @return true se o código secreto foi revelado; false caso contrário.
     */
	public boolean wasSecretRevealed();

}
