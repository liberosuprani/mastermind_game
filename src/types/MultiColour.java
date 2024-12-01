// Grupo 2
// Libero Suprani - 62220
// Ravi Mughal - 62504
// Ricardo Avelãs - 62257

package types;

/**
 * Uma enumeração que representa múltiplas cores com constantes de cores predefinidas.
 * Esta enumeração fornece uma representação em string para cada cor e implementa a interface Colour,
 * que exige um método para recuperar todos os valores de cor.
 * 
 * @author PCO Team
 */
public enum MultiColour implements Colour {

    // definição das cores com suas representações em string
    BLUE("B"),
    RED("R"),
    YELLOW("Y"),
    GREEN("G"),
    PINK("P"),
    ORANGE("O");

    // a representação em string da cor
    private String rep;

    /**
     * Construtor para a enumeração MultiColour.
     * Inicializa a representação da cor com o valor fornecido.
     * 
     * @param s A representação em string da cor.
     */
    MultiColour(String s) {
        this.rep = s;
    }

    /**
     * Retorna a representação em string da cor.
     * 
     * @return A representação em string da cor (ex: "B" para BLUE).
     */
    @Override
    public String toString() {
        return this.rep; // retorna a representação em string da cor
    }

    /**
     * Retorna um array contendo todos os valores da enumeração MultiColour.
     * Este método é utilizado para obter todos os valores possíveis da enumeração de cores.
     * 
     * @return Um array contendo todos os valores da enumeração MultiColour, 
     *         cast para o tipo Colour.
     */
    @Override
    public Colour[] colours() {
        return MultiColour.values(); // retorna todos os valores da enumeração MultiColour
    }

    /**
     * Converte um caractere para o tipo de cor correspondente na enumeração MultiColour.
     * 
     * @param c O caractere a ser convertido para uma cor.
     * @return O valor correspondente da enumeração MultiColour, ou null caso o caractere
     *         não corresponda a nenhuma cor definida.
     */
    public static Colour fromChar(char c) {
        String charGiven = "" + c; // converte o caractere em uma string
        
        // itera sobre os valores da enumeração e compara com o caractere fornecido
        for (Colour colour : MultiColour.values()) {
            if (charGiven.equals(colour.toString())) 
                return colour; // retorna a cor correspondente se encontrada
        }
        
        return null; // retorna null se o caractere não corresponder a nenhuma cor
    }
}
