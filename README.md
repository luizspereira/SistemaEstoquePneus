# Sistema de Estoque de Pneus

Aplicação de console em Java para cadastrar pneus e controlar as quantidades disponíveis em estoque.

## Funcionalidades

- Cadastrar pneus informando marca, aro, quantidade inicial e preço.
- Listar os pneus cadastrados e seus dados.
- Vender uma quantidade de um pneu selecionado, respeitando o estoque disponível.
- Adicionar unidades ao estoque de um pneu.
- Validar entradas no console, incluindo valores numéricos e campos obrigatórios.

Os dados são mantidos em memória durante a execução e não são salvos após o encerramento do programa.

## Estrutura do projeto

```text
src/
├── aplicacao/
│   └── Main.java       # Ponto de entrada e menu de console
└── modelo/
    ├── Estoque.java    # Cadastro e operações sobre os pneus
    └── Pneu.java       # Dados e comportamento de um pneu
```

## Tecnologias utilizadas

- Java 21
- `ArrayList` e `List` da biblioteca padrão do Java
- `Scanner` para leitura de dados pelo console
- IntelliJ IDEA (configuração de projeto incluída)

Não são necessárias bibliotecas externas.

## Execução

### IntelliJ IDEA

1. Abra o projeto no IntelliJ IDEA.
2. Configure um JDK 21 para o projeto.
3. Execute a classe `aplicacao.Main`, localizada em `src/aplicacao/Main.java`.

### Terminal

Na raiz do projeto, compile os arquivos Java:

```bash
javac -encoding UTF-8 -d out src/modelo/Pneu.java src/modelo/Estoque.java src/aplicacao/Main.java
```

Em seguida, inicie a aplicação:

```bash
java -cp out aplicacao.Main
```

O menu oferece as opções para cadastrar, listar, vender, adicionar estoque e sair. Para vender ou repor, escolha o pneu pelo número exibido na lista.
