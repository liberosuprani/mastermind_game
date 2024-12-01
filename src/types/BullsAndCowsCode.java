// Grupo 2
// Libero Suprani - 62220
// Ravi Mughal - 62504
// Ricardo Avelãs - 62257

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
		List<? extends Colour> clonedSecret = this.getCode();

		// mapas para armazenar as cores corretas nas posições erradas
		Map<Integer, Colour> correctPositionMap = new HashMap<Integer, Colour>();
		Map<Integer, Colour> wrongPositionMap = new HashMap<Integer, Colour>();
		
		// variavel para marcar turno do loop, i.e se estão a ser procuradas cores na posicao certa (turn == 0)
		// ou cores na posição errada (turn == 1)
		int turn = 0; 
		
		int i = 0;
		
		// itera sobre a quantidade de parâmetros que o retorno do método possui
		while (turn < 2) {
			i = 0;
			for (Colour currentColour : other) {
				
				if (clonedSecret.contains(currentColour)) {
					if (turn == 0 && currentColour == clonedSecret.get(i)) {
						correctPositionMap.put(i, currentColour);
						clonedSecret.set(i, null);
					}
					else if (turn == 1 && !correctPositionMap.containsKey(i)) {
						wrongPositionMap.put(clonedSecret.indexOf(currentColour), currentColour);
						
						// muda a posição no segredo para null, assim as proximas comparacoes nao
						// irao levar mais esta posiçao em conta
						clonedSecret.set(clonedSecret.indexOf(currentColour), null);	
					}
				}	
				i++;
				
			}
			turn++;
		}
		
		
		// retorna o número de cores na posição certa e cores certas na posição errada
		int[] result = {correctPositionMap.size(), wrongPositionMap.size()};
		return result;
		
	}
	
	@Override
	public BullsAndCowsCode clone() {
		return (BullsAndCowsCode) super.clone();
	}

}
