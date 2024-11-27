package types;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Code implements Cloneable {

    // lista que armazena a sequência de cores deste código.
    private List<? extends Colour> code;

    /**
     * Construtor da classe `Code`.
     * 
     * @param code Uma lista de cores que compõe o código. A lista é copiada para garantir imutabilidade.
     */
    public Code(List<? extends Colour> code) {
        // Inicializa uma nova lista para armazenar as cores
        this.code = new ArrayList<Colour>();

        // Copia os elementos da lista fornecida para a lista interna
        for (int i = 0; i < code.size(); i++)
            ((List<Colour>) this.code).add(code.get(i));
    }

    /**
     * Retorna a lista de cores que compõe o código.
     * 
     * @return Uma lista de objetos do tipo `Colour`.
     */
    public List<Colour> getCode() {
        return (List<Colour>) this.code;
    }

    /**
     * Retorna o tamanho do código, ou seja, a quantidade de cores presentes.
     * 
     * @return Um inteiro representando o comprimento do código.
     */
    public int getLength() {
        return this.code.size();
    }

    /**
     * Compara o código atual com outro código fornecido e determina:
     * 1. Quantos elementos estão na posição correta.
     * 2. Quantos elementos estão presentes no código, mas em posições erradas.
     * 
     * @param other Um objeto do tipo `Code` que será comparado com o código atual.
     * @return Um array de inteiros onde:
     *         - O índice 0 contém o número de cores na posição correta.
     *         - O índice 1 contém o número de cores presentes, mas em posições erradas.
     */
    public int[] howManyCorrect(Code other) {
        int correctPosition = 0; // Contador para elementos na posição correta

        // Obtém a lista de cores do código fornecido pelo usuário
        List<Colour> userTry = other.getCode();

        // Clona a lista de cores do código atual para manipulação
        List<? extends Colour>
