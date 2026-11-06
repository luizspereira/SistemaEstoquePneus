package modelo;

public class Pneu {
    private String marca;
    private int aro;
    private int quantidade;
    private double preco;

    public Pneu(String marca, int aro, int quantidade, double preco) {
        this.marca = marca;
        this.aro = aro;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    public String getMarca() {
        return this.marca;
    }

    public int getAro() {
        return this.aro;
    }

    public int getQuantidade() {
        return this.quantidade;
    }

    public double getPreco() {
        return this.preco;
    }

    public void vender(int quantidadeVendida) {
        if (quantidadeVendida < 0) {
            throw new IllegalArgumentException("A quantidade vendida não pode ser negativa.");
        }

        if (quantidadeVendida <= this.quantidade) {
            this.quantidade = this.quantidade - quantidadeVendida;
            System.out.println("Venda realizada com sucesso!");
        } else {
            System.out.println("Ops! Estoque insuficiente. Você tentou vender " + quantidadeVendida
                    + ", mas só temos " + this.quantidade + " no estoque.");
        }
    }

    public void adicionarEstoque(int quantidadeChegando) {
        if (quantidadeChegando < 0) {
            throw new IllegalArgumentException("A quantidade adicionada não pode ser negativa.");
        }

        if (quantidadeChegando > 0) {
            this.quantidade = this.quantidade + quantidadeChegando;
            System.out.println("Chegaram novos pneus! Estoque atualizado.");
        }
    }
}
