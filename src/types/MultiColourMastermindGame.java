package types;


public class MultiColourMastermindGame extends AbstractMastermindGame {

    private int givenHints;

    public MultiColourMastermindGame(int seed, int size, Colour[] colours) {
        super(seed, size, colours);
        this.givenHints = 0; 
    }

    /**
     * Retorna a pontuação atual do jogador.
     *
     * @return A pontuação acumulada do jogador.
     */
    @Override
    public int score() {
        return this.score;
    }

    /**
     * Verifica se a rodada do jogo foi concluída.
     * A rodada termina se a sequência secreta for revelada ou se o número máximo de tentativas for alcançado.
     *
     * @return {@code true} se a rodada foi concluída; caso contrário, {@code false}.
     */
    @Override
    public boolean isRoundFinished() {
        if (this.wasSecretRevealed() || this.getNumberOfTrials() >= MastermindGame.MAX_TRIALS)
            return true;
        return false;
    }

    /**
     * Atualiza a pontuação do jogador com base no número de tentativas e dicas utilizadas.
     *
     * 
     * A pontuação adicional é calculada conforme a seguinte lógica:
     * 
     *   Se o número de tentativas for 2 ou menos: +100 pontos.
     *   Se o número de tentativas estiver entre 3 e 5 (inclusive): +50 pontos.
     *   Se o número de tentativas for maior que 5: +20 pontos.
     * 
     * Essa pontuação adicional é dividida pelo número de dicas utilizadas + 1.
     * 
     *
     * @return {@code true} sempre, indicando que a pontuação foi atualizada.
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

        // divide a pontuação adicional pelo número de dicas fornecidas + 1
        this.score += (add / (this.givenHints + 1));

        return true;
    }

    /**
     * fornece uma dica ao jogador e atualiza o número de dicas fornecidas
     *
     * @return uma cor da sequência secreta como dica
     */
    @Override
    public Colour hint() {
        this.givenHints++; // incrementa o contador de dicas
        return super.hint();
    }

}
