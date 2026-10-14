package modelo;

public class Pneu {
    public String marca;
    public int aro;
    public int quantidade;
    public double preco;
    public Pneu(String marca, int aro, int quantidade, double preco) {
        this.marca = marca;
        this.aro = aro;
        this.quantidade = quantidade;
        this.preco = preco;
    }
    public void vender(int quantidadeVendida) {
        if (quantidadeVendida <= this.quantidade) {
            this.quantidade = this.quantidade - quantidadeVendida;
            System.out.println("Venda realizada com sucesso!");
        } else {
            System.out.println("Ops! Estoque  insuficiente. Voce tentou vender " + quantidadeVendida + "mas so temos" + this.quantidade + " no estoque.");
        }
    }
    public void adicionarEstoque(int quantidadeChegando) {
        if (quantidadeChegando > 0){
            this.quantidade = this.quantidade + quantidadeChegando;
            System.out.println("Chegaram novos pneus! estoque atualizado.");
        }
    }
}
