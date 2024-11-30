package types;

/**
 * Representa cores binárias com dois valores possíveis: PRETO ou BRANCO.
 * Cada constante da enumeração está associada a uma representação em string.
 * 
 * @author PCO Team
 */
public enum BinaryColour implements Colour {

    // constantes da enumeração representando as cores binárias
	BLACK("B"),
	WHITE("W");

    // representação em string da cor
	private String rep;

    /**
     * Construtor da enumeração BinaryColour.
     * 
     * @param s A representação em string da cor.
     */
	BinaryColour(String s) {
		this.rep = s;
	}

    /**
     * Retorna a representação em string da cor.
     * 
     * @return A representação em string da cor.
     */
	@Override
	public String toString() {
		return this.rep;
	}

    /**
     * Retorna um array contendo todos os valores do tipo enum BinaryColour.
     * Este método pode ser usado para iterar sobre os valores de BinaryColour.
     * 
     * @return Um array com todas as constantes enum de BinaryColour.
     */
	@Override
	public BinaryColour[] colours() {
		return BinaryColour.values();
	}
	
    /**
     * Converte um caractere fornecido para sua constante enum BinaryColour correspondente.
     * Se o caractere não corresponder a nenhuma cor binária, será retornado null.
     * 
     * @param c O caractere representando a cor ('B' para PRETO, 'W' para BRANCO).
     * @return A cor binária correspondente ou null se não houver correspondência.
     */
	public static Colour fromChar(char c) {
		String charGiven = "" + c;  // Converte o caractere para uma String
		for (Colour colour : BinaryColour.values()) {
			// Verifica se o caractere fornecido corresponde à representação em string da cor
			if (charGiven.equals(colour.toString())) 
				return colour;
		}
		// Retorna null caso não haja correspondência
		return null;
	}
}
