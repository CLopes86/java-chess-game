# ♟️ Java Chess Game

[![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.java.com/)
[![Swing](https://img.shields.io/badge/Swing-GUI-blue?style=for-the-badge)](https://docs.oracle.com/javase/tutorial/uiswing/)
[![License](https://img.shields.io/badge/License-MIT-green.svg?style=for-the-badge)](LICENSE)

> 🎓 Projeto académico desenvolvido no âmbito da disciplina de **Padrões e Desenho de Software** do curso de Engenharia Informática no Instituto Politécnico de Castelo Branco (IPCB).

## 📋 Sobre o Projeto

Implementação do jogo de **Xadrez** em Java utilizando princípios de **Programação Orientada a Objetos (OOP)** e **Design Patterns**. O projeto demonstra a aplicação de conceitos fundamentais como herança, polimorfismo, interfaces e padrões de design para criar uma aplicação robusta e extensível.

### ✨ Características

- 🎮 **Interface Gráfica Intuitiva**: Desenvolvida com Java Swing
- ♟️ **Regras de Xadrez Implementadas**: Movimentos específicos para cada peça
- 🎨 **Feedback Visual**: Indicadores de jogadas válidas/inválidas
- 🔄 **Sistema de Turnos**: Alternância automática entre jogadores
- 👑 **Promoção de Peões**: Conversão automática ao atingir o fim do tabuleiro
- 🎯 **Validação de Jogadas**: Verificação em tempo real da validade dos movimentos

## 🎯 Funcionalidades Implementadas

### Peças e Movimentos

| Peça | Movimento | Status |
|------|-----------|--------|
| ♙ Peão | Movimento vertical (1 casa), primeira jogada (2 casas), captura diagonal | ✅ Implementado |
| 🏰 Torre | Movimento horizontal e vertical ilimitado | 🔨 Em desenvolvimento |
| 🐴 Cavalo | Movimento em "L" (2+1 casas) | 🔨 Em desenvolvimento |
| ⛪ Bispo | Movimento diagonal ilimitado | 🔨 Em desenvolvimento |
| 👸 Rainha | Combinação de Torre + Bispo | 🔨 Em desenvolvimento |
| 👑 Rei | Uma casa em qualquer direção | 🔨 Em desenvolvimento |

### Mecânicas de Jogo

- ✅ Seleção de peças por drag-and-drop
- ✅ Validação de movimento em tempo real
- ✅ Indicador visual do turno atual
- ✅ Sistema de promoção de peões
- ✅ Menu com opções "Novo Jogo" e "Sair"

## 🏗️ Arquitetura e Design Patterns

O projeto foi desenvolvido seguindo princípios **SOLID** e utiliza os seguintes padrões:

### Padrões Utilizados

- **Template Method**: `PecaDefault` define o algoritmo base para movimento de peças
- **Strategy Pattern**: Cada peça implementa sua própria estratégia de movimento através do método `podeMover()`
- **Composite Pattern**: Hierarquia de peças com comportamentos específicos

### Estrutura de Classes

```
📦 pds
 ┣ 📂 peca
 ┃ ┣ 📜 Peca.java (Interface)
 ┃ ┣ 📜 PecaDefault.java (Classe Abstrata)
 ┃ ┗ 📜 Tabuleiro.java
 ┗ 📂 xadrez
   ┣ 📜 Peao.java
   ┣ 📜 Torre.java (Em desenvolvimento)
   ┣ 📜 Cavalo.java (Em desenvolvimento)
   ┣ 📜 Bispo.java (Em desenvolvimento)
   ┣ 📜 Rainha.java (Em desenvolvimento)
   ┣ 📜 Rei.java (Em desenvolvimento)
   ┗ 📜 Xadrez.java (Classe Principal)
```

## 🚀 Como Executar

### Pré-requisitos

- **Java JDK 8+** instalado
- IDE Java (recomendado: IntelliJ IDEA, Eclipse ou NetBeans)

### Passos para Execução

1. **Clone o repositório**
   ```bash
   git clone https://github.com/seu-usuario/java-chess-game.git
   cd java-chess-game
   ```

2. **Compile o projeto**
   ```bash
   javac -d bin src/pds/peca/*.java src/pds/xadrez/*.java
   ```

3. **Execute a aplicação**
   ```bash
   java -cp bin pds.xadrez.Xadrez
   ```

### Usando uma IDE

1. Abra o projeto na sua IDE favorita
2. Configure o JDK (Java 8 ou superior)
3. Execute a classe `Xadrez.java` (método `main`)

## 🎮 Como Jogar

1. **Iniciar o Jogo**: Execute a aplicação
2. **Mover Peças**: 
   - Clique e arraste uma peça
   - Um retângulo **verde** indica movimento válido
   - Um retângulo **vermelho** indica movimento inválido
3. **Soltar Peça**: A peça será movida se a jogada for válida
4. **Turnos**: Os jogadores alternam automaticamente (Brancas → Pretas)
5. **Novo Jogo**: Menu → Jogo → Novo Jogo

## 📚 Conceitos de OOP Aplicados

### 1. **Encapsulamento**
- Atributos privados com getters e setters
- Validação de dados através de métodos

### 2. **Herança**
- `PecaDefault` serve como base para todas as peças
- Reutilização de código para funcionalidades comuns

### 3. **Polimorfismo**
- Método `podeMover()` com implementações específicas
- Interface `Peca` permite tratamento uniforme

### 4. **Abstração**
- Interface `Peca` define o contrato
- Classe abstrata `PecaDefault` implementa comportamento comum

## 🔧 Estrutura Técnica

### Sistema de Coordenadas

O tabuleiro utiliza um sistema de coordenadas (1-8, 1-8):
- **(1,1)** = Casa inferior esquerda (a1 no xadrez tradicional)
- **(8,8)** = Casa superior direita (h8 no xadrez tradicional)

### Validação de Movimentos

```java
// Exemplo: Validação do movimento do Peão
public boolean podeMover(Point dest) {
    // 1. Validar se o destino está no tabuleiro
    if (!getTabuleiro().eCasaValida(dest))
        return false;
    
    // 2. Verificar direção do movimento
    int dy = dest.y - getPosicao().y;
    if (dy < 0 && getCor() == Peca.BRANCAS)
        return false;
    
    // 3. Validar distância e capturas
    // ... lógica específica do peão
}
```

## 📖 Documentação

### Métodos Principais

#### Interface `Peca`
- `boolean podeMover(Point dest)` - Verifica se o movimento é válido
- `boolean mover(Point dest)` - Move a peça para o destino
- `boolean ePromovivel()` - Indica se a peça pode ser promovida

#### Classe `Tabuleiro`
- `void colocarPeca(Point casa, Peca umaPeca)` - Coloca uma peça no tabuleiro
- `boolean moverPeca(Point origem, Point destino)` - Move peça de origem para destino
- `Peca getPeca(Point casa)` - Retorna a peça numa determinada casa

## 🎨 Assets

O projeto inclui dois conjuntos de peças visuais:
- **Set 1**: Dimensão 72x72px (default)
- **Set 2**: Dimensão 80x80px

Para trocar de set, modifique a constante na classe `Xadrez.java`:
```java
private static String set = "art/set1/"; // ou "art/set2/"
```

## 🛠️ Tecnologias Utilizadas

- **Linguagem**: Java
- **GUI Framework**: Java Swing
- **Paradigma**: Programação Orientada a Objetos
- **Build**: Compilação manual / IDE

## 📝 Simplificações Implementadas

Para fins académicos, foram aplicadas as seguintes simplificações:

- ❌ Não há deteção de xeque ou xeque-mate
- ❌ Movimentos especiais não implementados (roque, en passant)
- ❌ Sem controlo de empate ou repetição
- ✅ Foco no movimento correto das peças
- ✅ Sistema de turnos funcional
- ✅ Promoção de peões

## 🔮 Próximos Passos

- [ ] Implementar todas as peças (Torre, Cavalo, Bispo, Rainha, Rei)
- [ ] Adicionar deteção de xeque
- [ ] Implementar xeque-mate
- [ ] Adicionar histórico de jogadas
- [ ] Sistema de salvamento/carregamento de partidas
- [ ] Temporizador de jogadas
- [ ] Modo de jogo contra IA

## 👨‍💻 Autor

<div align="center">
  <img src="https://github.com/CLopes86.png" width="150" style="border-radius: 50%;" />
  
  **Cesaltino Lopes**
  
  🎓 Estudante de Engenharia Informática - Instituto Politécnico de Castelo Branco (IPCB)
  
  [![LinkedIn](https://img.shields.io/badge/LinkedIn-Cesaltino_Lopes-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/cesaltino-lopes-55274b176/)
  [![GitHub](https://img.shields.io/badge/GitHub-CLopes86-181717?style=for-the-badge&logo=github&logoColor=white)](https://github.com/CLopes86)
  [![Email](https://img.shields.io/badge/Email-Contacto-D14836?style=for-the-badge&logo=gmail&logoColor=white)](mailto:c.lopes46cv@gmail.com)
</div>

## 📄 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

## 🙏 Agradecimentos

- Instituto Politécnico de Castelo Branco (IPCB)
- Professor da disciplina de Padrões e Desenho de Software
- Comunidade Java

---

⭐ Se este projeto foi útil para você, considere dar uma estrela no repositório!

**Desenvolvido com ☕ e 💙 em Portugal**