package types;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Representa o código específico para o jogo Bulls and Cows.
 * Esta classe estende a classe `Code` e implementa o método `howManyCorrect` para verificar
 * quantos "Bulls" (cores corretas na posição certa) e "Cows" (cores corretas na posição errada)
 * existem entre uma tentativa e o código secreto.
 * 
 * @author PCO Team
 */
public class BullsAndCowsCode extends Code<Colour> {
	
    /**
     * Construtor da classe BullsAndCowsCode.
     * Inicializa o código secreto para o jogo Bulls and Cows.
     * 
     * @param code A lista de cores que representa o código secreto.
     */
	public BullsAndCowsCode(List<? extends Colour> code) {
		super(code);
	}

    /**
     * Compara uma tentativa de código com o código secreto e retorna a quantidade de "Bulls"
     * (cores corretas na posição certa) e "Cows" (nº máximo de cores corretas na posição errada).
     * 
     * @param other O código a ser comparado com o código secreto.
     * @return Um array contendo o número de "Bulls" e "Cows", respetivamente.
     */
	@Override
	public int[] howManyCorrect(Code<Colour> other) {
		
		// o código secreto
		List<? extends Colour> secret = this.code;

		// mapas para armazenar as cores nas posições corretas e erradas
		Map<Integer, Colour> rightPositionMap = new HashMap<Integer, Colour>();
		Map<Integer, Colour> wrongPositionMap = new HashMap<Integer, Colour>();
		
		// itera sobre cada posição do código de tentativa
		int i = 0;
		for (Colour currentColour : other) {
			
			// verifica se a cor da tentativa está no código secreto
			if (secret.contains(currentColour)) {
				
				// se a cor está na posição correta (Bulls)
				if (currentColour == secret.get(i)) {
					rightPositionMap.put(i, currentColour);
					
					// remove do mapa de posições erradas caso tenha sido adicionado anteriormente
					if (wrongPositionMap.containsKey(i))
						wrongPositionMap.remove(i);
				} 

				// caso contrário, se a cor está na posição errada (Cows)
				else {
					boolean foundWrongIndex = false;

					int j = 0;
					// busca por uma posição errada para a cor (Cows)
					while(!foundWrongIndex && j < secret.size()) {
						// verifica se a cor naquela posição ainda não foi atribuída como Bull ou Cow
						if (!rightPositionMap.containsKey(j) && !wrongPositionMap.containsKey(j) && secret.get(j).equals(currentColour)) {
							foundWrongIndex = true;
							wrongPositionMap.put(j, currentColour);
						}
						j++;
					}
				}
			}
			i++;
		}
		
		// retorna o número de Bulls (certo na posição certa) e Cows (certo na posição errada)
		int[] result = {rightPositionMap.size(), wrongPositionMap.size()};
		return result;
	}
	
	@Override
	public BullsAndCowsCode clone() {
		return (BullsAndCowsCode) super.clone();
	}

}
