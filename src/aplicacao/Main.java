package aplicacao;

import modelo.Estoque;
import modelo.Pneu;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Estoque estoque = new Estoque();

        try (Scanner scanner = new Scanner(System.in)) {
            boolean executando = true;

            while (executando) {
                exibirMenu();
                int opcao = lerInteiro(scanner, "Escolha uma opção: ", 1);

                switch (opcao) {
                    case 1:
                        cadastrarPneu(scanner, estoque);
                        break;
                    case 2:
                        listarPneus(estoque);
                        break;
                    case 3:
                        buscarPorMarca(scanner, estoque);
                        break;
                    case 4:
                        buscarPorCodigo(scanner, estoque);
                        break;
                    case 5:
                        venderPneu(scanner, estoque);
                        break;
                    case 6:
                        adicionarEstoque(scanner, estoque);
                        break;
                    case 7:
                        executando = false;
                        System.out.println("Programa encerrado.");
                        break;
                    default:
                        System.out.println("Opção inválida. Escolha uma opção de 1 a 7.");
                }
            }
        }
    }

    private static void exibirMenu() {
        System.out.println("\n--- Sistema de Estoque de Pneus ---");
        System.out.println("1 - Cadastrar pneu");
        System.out.println("2 - Listar pneus");
        System.out.println("3 - Buscar pneu por marca");
        System.out.println("4 - Buscar pneu por código");
        System.out.println("5 - Vender pneu");
        System.out.println("6 - Adicionar estoque");
        System.out.println("7 - Sair");
    }

    private static void cadastrarPneu(Scanner scanner, Estoque estoque) {
        System.out.println("\n--- Cadastro de pneu ---");
        String marca = lerTexto(scanner, "Marca: ");
        int aro = lerInteiro(scanner, "Aro: ", 1);
        int quantidade = lerInteiro(scanner, "Quantidade inicial: ", 0);
        double preco = lerDecimalNaoNegativo(scanner, "Preço: ");

        estoque.cadastrar(new Pneu(marca, aro, quantidade, preco));
        System.out.println("Pneu cadastrado com sucesso.");
    }

    private static void listarPneus(Estoque estoque) {
        exibirPneus(estoque.listar(), "Estoque", "Nenhum pneu cadastrado.");
    }

    private static void buscarPorMarca(Scanner scanner, Estoque estoque) {
        String marca = lerTexto(scanner, "Marca para buscar: ");
        List<Pneu> pneus = estoque.buscarPorMarca(marca);
        exibirPneus(pneus, "Resultados para a marca \"" + marca + "\"",
                "Nenhum pneu encontrado para essa marca.");
    }

    private static void buscarPorCodigo(Scanner scanner, Estoque estoque) {
        long codigo = lerLong(scanner, "Código do pneu: ", 1);
        Optional<Pneu> resultado = estoque.buscarPorCodigo(codigo);
        if (resultado.isPresent()) {
            exibirPneus(List.of(resultado.get()), "Pneu encontrado",
                    "Nenhum pneu encontrado para esse código.");
        } else {
            System.out.println("Nenhum pneu encontrado para esse código.");
        }
    }

    private static void exibirPneus(List<Pneu> pneus, String titulo, String mensagemVazia) {
        if (pneus.isEmpty()) {
            System.out.println(mensagemVazia);
            return;
        }

        System.out.println("\n--- " + titulo + " ---");
        for (int i = 0; i < pneus.size(); i++) {
            Pneu pneu = pneus.get(i);
            System.out.printf("%d - Código: %d | Marca: %s | Aro: %d | Quantidade: %d | Preço: %.2f%n",
                    i + 1, pneu.getCodigo(), pneu.getMarca(), pneu.getAro(),
                    pneu.getQuantidade(), pneu.getPreco());
        }
    }

    private static void venderPneu(Scanner scanner, Estoque estoque) {
        Pneu pneu = selecionarPneu(scanner, estoque);
        if (pneu == null) {
            return;
        }

        int quantidade = lerInteiro(scanner, "Quantidade para venda: ", 1);
        estoque.vender(pneu, quantidade);
    }

    private static void adicionarEstoque(Scanner scanner, Estoque estoque) {
        Pneu pneu = selecionarPneu(scanner, estoque);
        if (pneu == null) {
            return;
        }

        int quantidade = lerInteiro(scanner, "Quantidade a adicionar: ", 1);
        estoque.adicionarEstoque(pneu, quantidade);
    }

    private static Pneu selecionarPneu(Scanner scanner, Estoque estoque) {
        List<Pneu> pneus = estoque.listar();
        if (pneus.isEmpty()) {
            System.out.println("Nenhum pneu cadastrado. Cadastre um pneu primeiro.");
            return null;
        }

        System.out.println("\nSelecione o pneu:");
        for (int i = 0; i < pneus.size(); i++) {
            Pneu pneu = pneus.get(i);
            System.out.printf("%d - Código: %d | %s | Aro: %d | Quantidade: %d%n",
                    i + 1, pneu.getCodigo(), pneu.getMarca(),
                    pneu.getAro(), pneu.getQuantidade());
        }

        int indice = lerInteiro(scanner, "Número do pneu: ", 1);
        while (indice > pneus.size()) {
            System.out.println("Número inválido. Escolha um pneu da lista.");
            indice = lerInteiro(scanner, "Número do pneu: ", 1);
        }

        return pneus.get(indice - 1);
    }

    private static String lerTexto(Scanner scanner, String mensagem) {
        String valor;
        do {
            System.out.print(mensagem);
            valor = scanner.nextLine().trim();
            if (valor.isEmpty()) {
                System.out.println("Este campo não pode ficar vazio.");
            }
        } while (valor.isEmpty());

        return valor;
    }

    private static int lerInteiro(Scanner scanner, String mensagem, int minimo) {
        while (true) {
            System.out.print(mensagem);
            try {
                int valor = Integer.parseInt(scanner.nextLine().trim());
                if (valor >= minimo) {
                    return valor;
                }
                System.out.println("Informe um número inteiro maior ou igual a " + minimo + ".");
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Informe um número inteiro.");
            }
        }
    }

    private static long lerLong(Scanner scanner, String mensagem, long minimo) {
        while (true) {
            System.out.print(mensagem);
            try {
                long valor = Long.parseLong(scanner.nextLine().trim());
                if (valor >= minimo) {
                    return valor;
                }
                System.out.println("Informe um número inteiro maior ou igual a " + minimo + ".");
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Informe um número inteiro.");
            }
        }
    }

    private static double lerDecimalNaoNegativo(Scanner scanner, String mensagem) {
        while (true) {
            System.out.print(mensagem);
            try {
                double valor = Double.parseDouble(scanner.nextLine().trim().replace(',', '.'));
                if (Double.isFinite(valor) && valor >= 0) {
                    return valor;
                }
                System.out.println("Informe um preço válido, maior ou igual a zero.");
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Informe um valor decimal.");
            }
        }
    }
}
