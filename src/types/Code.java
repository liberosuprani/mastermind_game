package types;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Representa um código no contexto de um jogo de Mastermind.
 * Este código consiste em uma lista de cores, e oferece funcionalidades
 * como verificar a quantidade de cores corretas, criar uma representação
 * em string do código, clonar o código e comparar códigos para verificar
 * se são iguais.
 * 
 * @author PCO Team
 */
public class Code implements Cloneable {

    // lista que contém as cores do código.
	protected List<? extends Colour> code;

    /**
     * Construtor da classe Code.
     * Inicializa o código com a lista de cores fornecida.
     * 
     * @param code A lista de cores que compõe o código.
     */
	public Code(List<? extends Colour> code) {
		this.code = new ArrayList<Colour>();
		
		// copia as cores do código fornecido para a lista interna
		for (int i = 0; i < code.size(); i++)
			((List<Colour>)this.code).add(code.get(i));
	}
	
    /**
     * @return A lista de cores do código.
     */
	public List<Colour> getCode() {
		return (List<Colour>)this.code;
	}

    /**
     * @return O tamanho do código.
     */
	public int getLength() {
		return this.code.size();
	}

    /**
     * Compara o código atual com outro código e retorna o número de cores corretas na posição certa
     * e cores corretas na posição errada
     * 
     * @param other O código a ser comparado com o código atual.
     * @return Um array contendo o número de cores na posição certa e cores certas na posição errada.
     */
	public int[] howManyCorrect(Code other) {
		
		// tentativa do usuário
		List<Colour> userTry = other.getCode();
		
		// código secreto
		List<? extends Colour> secret = this.code;
		
		// mapas para armazenar as cores nas posições corretas e erradas
		Map<Integer, Colour> wrongPositionMap = new HashMap<Integer, Colour>();
		Map<Integer, Colour> rightPositionMap = new HashMap<Integer, Colour>();
		
		// itera sobre cada posição do código de tentativa
		for (int i = 0; i < other.getLength(); i++) {
			Colour currentColour = userTry.get(i);
		
			// verifica se a cor da tentativa está no código secreto
			if (secret.contains(currentColour)) {
					
				// se a cor está na posição correta 
				if (currentColour == secret.get(i)) {
					rightPositionMap.put(i, currentColour);
					
					// remove do mapa de posições erradas caso tenha sido adicionado anteriormente
					if (wrongPositionMap.containsKey(i))
						wrongPositionMap.remove(i);
				} 

				// se a cor não está na posição correta, mas está no código secreto 
				else if (!wrongPositionMap.containsValue(currentColour)) {
					
					boolean foundWrongIndex = false;

					int j = 0;
					while(!foundWrongIndex && j < secret.size()) {
						// verifica se a cor ainda não foi adicionada ao mapa
						if (!rightPositionMap.containsKey(j) && secret.get(j).equals(currentColour)) {
							foundWrongIndex = true;
							wrongPositionMap.put(j, currentColour);
						}
						j++;
					}
				}
			}
		}
		
		// retorna o número de cores na posição certa e cores certas na posição errada
		int[] result = {rightPositionMap.size(), wrongPositionMap.size()};
		return result;
	}

    /**
     * Retorna uma representação em string do código, formatada com colchetes e separada por vírgulas.
     * 
     * @return A representação em string do código.
     */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		
		// Concatena as cores do código na representação
		for (Colour colour : this.code)
			sb.append(colour + ", ");
		
		// Remove a última vírgula e espaço
		sb.delete(sb.length()-2, sb.length());
		sb.append("]");
		return sb.toString();
	}

    /**
     * Clona o código atual, criando uma nova instância com as mesmas cores.
     * 
     * @return Uma cópia do código atual.
     */
	@Override
	public Code clone() {
		
		try {
			System.out.println(this.getClass());
			List<Colour> clonedCode = new ArrayList<Colour>();
			
			for (Colour colour : this.code) 
				clonedCode.add(colour);
			
			Code cloned = (Code) super.clone();
			cloned.code = clonedCode;
			
			return cloned;
		} catch (CloneNotSupportedException e) {
			throw new AssertionError();
		}
		
	}

    /**
     * Dois códigos são considerados iguais se possuem a mesma quantidade de cores nas mesmas posições,
     * com as mesmas cores.
     * 
     * @param obj O objeto a ser comparado com o código atual.
     * @return true se os códigos forem iguais; false caso contrário.
     */
	@Override
	public boolean equals(Object obj) {
		// verifica se o objeto é uma instância de Code
		if (!(obj instanceof Code)) 
			return false;
		else {
			Code castObj = (Code) obj;
			List<Colour> objCode = castObj.getCode(); 
	
			// verifica se o código tem o mesmo tamanho
			if (this.code.size() != objCode.size())
				return false;
	
			// compara cada cor do código
			for (int i = 0; i < objCode.size(); i++) {
				// se qualquer cor for diferente, retorna falso
				if (objCode.get(i) != this.code.get(i))
					return false;
			}	
		}
		return true;
	}
}
