package types;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
public class Code<Colour> implements Cloneable, Iterable<Colour> {

    // lista que contém as cores do código.
	private List<? extends Colour> code;

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
		List<Colour> clonedCode = new ArrayList<Colour>();
		for (Colour currentColour : this.code) {
			clonedCode.add(currentColour);
		}
			
		return clonedCode;
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
	public int[] howManyCorrect(Code<Colour> other) {
		
		// código secreto
		List<? extends Colour> clonedSecret = this.clone().code;
		
		// mapa para armazenar as cores certas nas posições erradas
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
					else if (turn == 1 && !correctPositionMap.containsKey(i)  && !wrongPositionMap.containsValue(currentColour)) 
						wrongPositionMap.put(clonedSecret.indexOf(currentColour), currentColour);	
				}	
				i++;
				
			}
			turn++;
		}
		
		// retorna o número de cores na posição certa e cores certas na posição errada
		int[] result = {correctPositionMap.size(), wrongPositionMap.size()};
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
	public Code<Colour> clone() {
		
		try {
			List<Colour> clonedCode = new ArrayList<Colour>();
			
			for (Colour colour : this.code) 
				clonedCode.add(colour);
			
			Code<Colour> cloned = (Code<Colour>) super.clone();
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
			Code<Colour> castObj = (Code<Colour>) obj;
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

	@Override
	public Iterator<Colour> iterator() {
		return new CodeIterator();
	}
	
	
	/**
	 * Classe que implementa um iterador de Code
	 */
	private class CodeIterator implements Iterator<Colour>{

		private int currentIndex;
		
		private CodeIterator() {
			this.currentIndex = 0;
		}
		
		@Override
		public boolean hasNext() {
			return this.currentIndex < getLength();
		}

		@Override
		public Colour next() {
			return code.get(currentIndex++);
		}
		
	}
}
