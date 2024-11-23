package types;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int opcao = 0;
        System.out.println("Bem vindo ao jogo Mastermind!\n\nPara jogar, selecione uma opção.\n");

        while (opcao != 1 && opcao != 2 && opcao != 3) {
	        System.out.print("1 - Mastermind original\n2 - Bulls and cows\n3: Sair do jogo\nOpção: ");
	        opcao = sc.nextInt();
	        
	        
	        if (opcao != 1 && opcao != 2 && opcao != 3) {
	        	System.out.printf("\n--------------------\nOpção inválida!\n\n");
	        }
        }
        
        if (opcao != 3) {
     	
        }
            
    }

}
