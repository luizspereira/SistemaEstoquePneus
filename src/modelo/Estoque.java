package modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Estoque {
    private final ArrayList<Pneu> pneus = new ArrayList<>();

    public void cadastrar(Pneu pneu) {
        if (pneu == null) {
            throw new IllegalArgumentException("O pneu não pode ser nulo.");
        }

        pneus.add(pneu);
    }

    public List<Pneu> listar() {
        return Collections.unmodifiableList(new ArrayList<>(pneus));
    }

    public List<Pneu> buscarPorMarca(String marca) {
        if (marca == null || marca.trim().isEmpty()) {
            throw new IllegalArgumentException("A marca deve ser informada.");
        }

        List<Pneu> encontrados = new ArrayList<>();
        for (Pneu pneu : pneus) {
            if (marca.trim().equalsIgnoreCase(pneu.getMarca())) {
                encontrados.add(pneu);
            }
        }

        return Collections.unmodifiableList(encontrados);
    }

    public void vender(Pneu pneu, int quantidade) {
        validarCadastro(pneu);
        pneu.vender(quantidade);
    }

    public void adicionarEstoque(Pneu pneu, int quantidade) {
        validarCadastro(pneu);
        pneu.adicionarEstoque(quantidade);
    }

    private void validarCadastro(Pneu pneu) {
        if (pneu == null || !pneus.contains(pneu)) {
            throw new IllegalArgumentException("O pneu informado não está cadastrado neste estoque.");
        }
    }
}
