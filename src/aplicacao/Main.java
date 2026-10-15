package aplicacao;
import modelo.Pneu;

public class Main {
    public static void main(String [] args) {
        Pneu pneu1 = new Pneu("Pirelli", 15, 8, 350.50);

        System.out.println("Estoque inicial:" + pneu1.getQuantidade());



        System.out.println("\n---Chegou um caminhao com 5 pneus novos ---");
        pneu1.vender(3);

        System.out.println("Estoque final:" + pneu1.getQuantidade());
    }

}
