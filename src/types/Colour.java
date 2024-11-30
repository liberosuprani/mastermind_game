package types;

/**
 * Uma interface que define um contrato para entidades relacionadas a cores.
 * Esta interface exige que as classes que a implementam forneçam 
 * um método que retorna todos os valores possíveis de cores.
 * 
 * @author Equipe PCO
 */
public interface Colour {

    /**
     * Recupera todos os valores possíveis de cores representados 
     * pela classe que implementa esta interface.
     * 
     * @return Um array de objetos Colour representando todas as cores possíveis.
     */
    Colour[] colours();  
}
