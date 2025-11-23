# ♟️ Jogo de Xadrez em Java

[![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.java.com/)
[![Swing](https://img.shields.io/badge/Swing-GUI-blue?style=for-the-badge)](https://docs.oracle.com/javase/tutorial/uiswing/)
[![License](https://img.shields.io/badge/License-Educational-green.svg?style=for-the-badge)](LICENSE)

> 🎓 Projeto académico desenvolvido no âmbito da disciplina de **Padrões de Design de Software** do 3º ano do curso de **Informatica e Multimedia** no Instituto Politécnico de Castelo Branco (IPCB), ano letivo 2025/2026.

## 📋 Sobre o Projeto

Implementação **completa** e **funcional** do jogo de Xadrez em Java, utilizando princípios de **Programação Orientada a Objetos (OOP)** e **Design Patterns**. O projeto demonstra a aplicação prática de conceitos como herança, polimorfismo, abstração e padrões de design para criar uma aplicação robusta e jogável.

### ✨ Destaques do Projeto

- ♟️ **Todas as peças implementadas**: Peão, Torre, Bispo, Cavalo, Rainha e Rei
- 🎯 **Detecção de xeque**: Sistema completo de validação de ameaças ao Rei
- 🛡️ **Prevenção de movimentos ilegais**: Impossível deixar o próprio Rei em xeque
- 🎨 **Interface gráfica profissional**: Drag & drop com feedback visual em tempo real
- 🔄 **Sistema de turnos**: Alternância automática entre jogadores
- 👑 **Promoção de peões**: Escolha entre Rainha, Torre, Bispo ou Cavalo
- 📐 **Regras oficiais**: Movimentos conforme as regras internacionais do xadrez

## 🎯 Funcionalidades Implementadas

### ✅ Peças e Movimentos - TODAS IMPLEMENTADAS

| Peça | Movimento | Status |
|------|-----------|--------|
| ♙ **Peão** | Vertical 1 casa, primeira jogada 2 casas, captura diagonal | ✅ Completo |
| 🏰 **Torre** | Horizontal e vertical ilimitado, caminho livre | ✅ Completo |
| 🐴 **Cavalo** | Movimento em "L" (2+1 casas), salta peças | ✅ Completo |
| ⛪ **Bispo** | Diagonal ilimitado, caminho livre | ✅ Completo |
| 👸 **Rainha** | Combinação Torre + Bispo | ✅ Completo |
| 👑 **Rei** | Uma casa em qualquer direção | ✅ Completo |

### 🎮 Mecânicas de Jogo Implementadas

- ✅ **Seleção intuitiva**: Click-and-drag com feedback visual
- ✅ **Validação em tempo real**: 
  - 🟢 Verde = jogada válida
  - 🔴 Vermelho = jogada inválida
  - 🔵 Azul = peça selecionada
- ✅ **Sistema de turnos**: Alternância automática Brancas ↔ Pretas
- ✅ **Detecção de xeque**: Alerta quando o Rei está sob ataque
- ✅ **Proteção do Rei**: Bloqueio automático de jogadas que deixam o Rei em perigo
- ✅ **Promoção de peões**: Diálogo de escolha ao atingir a última linha
- ✅ **Captura de peças**: Remoção automática da peça adversária
- ✅ **Menu funcional**: Novo jogo e sair

### 🎨 Interface Gráfica

- **Indicador visual de turno**: Círculo branco indica quem joga
- **Feedback de movimento**: Quadrados coloridos mostram validade da jogada
- **Drag & drop fluido**: Arraste natural de peças
- **Tabuleiro profissional**: Gráficos de alta qualidade

## 🏗️ Arquitetura e Padrões de Design

O projeto foi desenvolvido aplicando princípios **SOLID** e padrões de design reconhecidos:

### 🎯 Padrões Implementados

#### 1. **Strategy Pattern** 🎯
Cada peça implementa sua própria estratégia de movimento através do método `podeMover()`:
```java
// Cada peça tem sua lógica única
public boolean podeMover(Point destino) {
    // Peão: movimento vertical
    // Torre: horizontal/vertical
    // Bispo: diagonal
    // etc.
}
```

#### 2. **Template Method Pattern** 📋
A classe abstrata `Peca` define a estrutura base, enquanto subclasses implementam detalhes:
```java
public abstract class Peca {
    // Método template
    public boolean mover(Point dest) {
        if (podeMover(dest)) {
            setPosicao(dest);
            return true;
        }
        return false;
    }
    
    // Método abstrato - implementado por cada peça
    public abstract boolean podeMover(Point dest);
}
```

#### 3. **Observer Pattern** 👀
Sistema de eventos do Swing para interação do utilizador:
- `MouseListener` - detecta cliques
- `MouseMotionListener` - detecta arrastos
- `ActionListener` - menu de opções

#### 4. **Composite Pattern** 🧩
Hierarquia de peças com comportamentos especializados, mas interface comum

### 📐 Princípios SOLID Aplicados

- **S**ingle Responsibility: Cada classe tem uma responsabilidade clara
- **O**pen/Closed: Fácil adicionar novas peças sem modificar código existente
- **L**iskov Substitution: Todas as peças são substituíveis pela classe base
- **I**nterface Segregation: Interfaces específicas para cada comportamento
- **D**ependency Inversion: Classes dependem de abstrações (Peca), não de implementações

## 📁 Estrutura do Projeto
```
XadrezDamas/
├── 📂 src/
│   └── 📂 pds/
│       ├── 📂 peca/                    # Pacote base
│       │   ├── 📄 Peca.java            # Classe abstrata base
│       │   └── 📄 Tabuleiro.java       # Lógica do tabuleiro e validações
│       └── 📂 xadrez/                  # Implementação das peças
│           ├── 📄 Xadrez.java          # ⭐ Classe principal + GUI
│           ├── 📄 Peao.java            # ♟️ Movimento + promoção
│           ├── 📄 Torre.java           # 🏰 Movimento linha reta
│           ├── 📄 Bispo.java           # ⛪ Movimento diagonal
│           ├── 📄 Cavalo.java          # 🐴 Movimento em L
│           ├── 📄 Rainha.java          # 👸 Torre + Bispo combinados
│           └── 📄 Rei.java             # 👑 Movimento limitado
├── 📂 art/                             # Assets gráficos
│   └── 📂 set1/                        # Conjunto de imagens (72x72px)
│       ├── 🖼️ tabuleiro.png
│       ├── 🖼️ peao_branco.png
│       ├── 🖼️ torre_preta.png
│       └── ... (mais 10 imagens)
├── 📂 bin/                             # Classes compiladas (gerado)
├── 📄 README.md                        # Este ficheiro
└── 📄 .gitignore                       # Exclusões do Git
```

### 🔗 Relações Entre Classes
```
         Peca (abstract)
            ↑
    ┌───────┼───────┬───────┬────────┬────────┐
    │       │       │       │        │        │
  Peao   Torre   Bispo   Cavalo   Rainha    Rei
```

## 🚀 Como Executar

### 📋 Pré-requisitos

- ✅ **Java JDK 8 ou superior** ([Download](https://www.oracle.com/java/technologies/downloads/))
- ✅ **Terminal/Prompt de comando**
- 💡 Opcional: IDE Java (IntelliJ IDEA, Eclipse, VS Code)

### 🎮 Execução Rápida

#### 1️⃣ **Clonar o Repositório**
```bash
git clone https://github.com/CLopes86/XadrezDamas.git
cd XadrezDamas
```

#### 2️⃣ **Compilar**
```bash
javac -d bin src/pds/peca/*.java src/pds/xadrez/*.java
```

#### 3️⃣ **Executar**
```bash
java -cp bin pds.xadrez.Xadrez
```

### 🛠️ Usando uma IDE

**IntelliJ IDEA / VS Code:**
1. Abrir pasta do projeto
2. Configurar JDK (File → Project Structure)
3. Run `Xadrez.java` (▶️)

**Eclipse:**
1. Import → Existing Projects
2. Right-click `Xadrez.java` → Run As → Java Application

## 🎮 Como Jogar

### 🎯 Controlos

1. **Selecionar peça**: 
   - Clique e **segure** numa peça da sua cor
   - Aparece um **quadrado azul** ao redor

2. **Ver movimento válido**:
   - Arraste a peça
   - **🟢 Verde** = pode mover
   - **🔴 Vermelho** = movimento inválido

3. **Confirmar movimento**:
   - Solte o botão do rato
   - A peça move-se se a jogada for válida

4. **Indicador de turno**:
   - **Círculo branco** à direita
   - Topo = Jogador Preto
   - Baixo = Jogador Branco

### 📜 Regras Importantes

#### ⚠️ Xeque
- Se o seu Rei está sob ataque, **deve** protegê-lo
- Movimentos que deixam o Rei em perigo são **bloqueados**
- Terminal mostra mensagem "XEQUE!" quando adversário está ameaçado

#### 👑 Promoção de Peão
Quando um peão chega à última linha:
1. Janela de seleção aparece
2. Escolha: **Rainha** 👸, **Torre** 🏰, **Bispo** ⛪ ou **Cavalo** 🐴
3. Peça é substituída automaticamente

#### 📋 Menu
- **Jogo → Novo Jogo**: Reiniciar partida
- **Jogo → Sair**: Fechar aplicação

## 📚 Conceitos de OOP Aplicados

### 1. **Encapsulamento** 🔒
```java
public abstract class Peca {
    private Point posicao;        // Atributo privado
    private int cor;              // Atributo privado
    
    public Point getPosicao() {   // Getter público
        return posicao;
    }
    
    protected void setPosicao(Point p) {  // Setter protegido
        this.posicao = p;
    }
}
```

### 2. **Herança** 🧬
```java
// Classe base
public abstract class Peca { ... }

// Classes derivadas
public class Torre extends Peca { ... }
public class Bispo extends Peca { ... }
```

### 3. **Polimorfismo** 🎭
```java
// Mesmo método, comportamentos diferentes
Peca torre = new Torre(...);
Peca bispo = new Bispo(...);

torre.podeMover(destino);  // Validação de torre
bispo.podeMover(destino);  // Validação de bispo
```

### 4. **Abstração** 🎨
```java
// Classe abstrata define contrato
public abstract class Peca {
    public abstract boolean podeMover(Point dest);
}

// Implementações concretas
public class Peao extends Peca {
    public boolean podeMover(Point dest) {
        // Lógica específica do peão
    }
}
```

## 🔧 Estrutura Técnica

### 📍 Sistema de Coordenadas

O tabuleiro utiliza coordenadas cartesianas (1-8, 1-8):
- **(1,1)** = Casa inferior esquerda (a1 no xadrez tradicional)
- **(8,8)** = Casa superior direita (h8 no xadrez tradicional)
```
  8  ♜ ♞ ♝ ♛ ♚ ♝ ♞ ♜
  7  ♟ ♟ ♟ ♟ ♟ ♟ ♟ ♟
  6  · · · · · · · ·
  5  · · · · · · · ·
  4  · · · · · · · ·
  3  · · · · · · · ·
  2  ♙ ♙ ♙ ♙ ♙ ♙ ♙ ♙
  1  ♖ ♘ ♗ ♕ ♔ ♗ ♘ ♖
     1 2 3 4 5 6 7 8
```

### 🎯 Validação de Movimentos

#### Exemplo: Torre
```java
public boolean podeMover(Point dest) {
    // 1. Verificar se destino é válido
    if (!getTabuleiro().eCasaValida(dest))
        return false;
    
    // 2. Torre move-se em linha reta
    int dx = dest.x - getPosicao().x;
    int dy = dest.y - getPosicao().y;
    
    if (dx != 0 && dy != 0)  // Não é linha reta
        return false;
    
    // 3. Verificar caminho livre
    if (!caminhoLivre(dest))
        return false;
    
    // 4. Verificar destino (vazio ou peça adversária)
    return destinoValido(dest);
}
```

#### Exemplo: Cavalo
```java
public boolean podeMover(Point dest) {
    int dx = Math.abs(dest.x - getPosicao().x);
    int dy = Math.abs(dest.y - getPosicao().y);
    
    // Movimento em "L": (2,1) ou (1,2)
    boolean movimentoL = (dx == 2 && dy == 1) || (dx == 1 && dy == 2);
    
    if (!movimentoL)
        return false;
    
    // Cavalo salta peças - não precisa verificar caminho
    return destinoValido(dest);
}
```

### 🧮 Algoritmo de Detecção de Xeque
```java
public boolean reiEmXeque(int cor) {
    // 1. Encontrar posição do Rei
    Point posicaoRei = encontrarRei(cor);
    
    // 2. Verificar se alguma peça adversária ataca essa posição
    int corAdversaria = (cor == BRANCAS) ? PRETAS : BRANCAS;
    
    // 3. Percorrer todas as peças adversárias
    for (Peca peca : pecasAdversarias) {
        if (peca.podeMover(posicaoRei)) {
            return true;  // Rei está em xeque!
        }
    }
    
    return false;
}
```

### 🛡️ Proteção do Rei
```java
private void pousarPeca(MouseEvent e) {
    // 1. Simular movimento
    Peca capturada = tabuleiro.simularMovimento(origem, destino);
    
    // 2. Verificar se deixa Rei em xeque
    boolean reiEmPerigo = tabuleiro.reiEmXeque(turnoAtual);
    
    // 3. Desfazer simulação
    tabuleiro.desfazerMovimento(origem, destino, capturada);
    
    // 4. Bloquear se necessário
    if (reiEmPerigo) {
        return;  // Movimento não permitido!
    }
    
    // 5. Executar movimento real
    executarMovimento(origem, destino);
}
```

## 🛠️ Tecnologias Utilizadas

| Tecnologia | Utilização |
|------------|------------|
| **Java SE 8+** | Linguagem de programação |
| **Java Swing** | Framework para GUI |
| **AWT** | Componentes gráficos e eventos |
| **Git** | Controlo de versão |
| **VS Code / IntelliJ** | Ambiente de desenvolvimento |

## 💡 Decisões de Design

### ✅ Implementado

- ✅ **Todas as peças** com movimentos corretos
- ✅ **Detecção de xeque** funcional
- ✅ **Prevenção de auto-xeque** implementada
- ✅ **Promoção de peões** com escolha de peça
- ✅ **Interface gráfica** profissional

### 📝 Simplificações (Para Foco Académico)

- ⚠️ **Xeque-mate**: Implementado mas desabilitado (questões de performance)
- ⚠️ **Roque**: Não implementado (movimento especial avançado)
- ⚠️ **En Passant**: Não implementado (captura especial de peão)
- ⚠️ **Empate**: Não há deteção de empate por repetição ou afogamento
- ⚠️ **Relógio**: Sem limite de tempo por jogada

### 💭 Razões das Simplificações

O foco do projeto foi demonstrar:
1. ✅ Aplicação de **Design Patterns**
2. ✅ Princípios de **OOP**
3. ✅ **Arquitetura** limpa e extensível
4. ✅ **Validação completa** de movimentos
5. ✅ **Interface gráfica** funcional

## 🔮 Melhorias Futuras

### 🎯 Curto Prazo
- [ ] Otimizar algoritmo de xeque-mate
- [ ] Implementar movimento de roque
- [ ] Adicionar movimento en passant
- [ ] Sistema de sons (captura, xeque, etc.)

### 🚀 Médio Prazo
- [ ] Histórico de jogadas com notação algébrica
- [ ] Sistema de desfazer/refazer jogadas
- [ ] Temporizador por jogador (relógio de xadrez)
- [ ] Salvamento/carregamento de partidas (PGN)

### 💎 Longo Prazo
- [ ] IA com algoritmo minimax
- [ ] Modo online multiplayer
- [ ] Análise de partidas
- [ ] Tabuleiro 3D
- [ ] Integração com base de dados de aberturas

## 📊 Estatísticas do Projeto

- **Linhas de código**: ~2000
- **Classes**: 9 (Xadrez, Tabuleiro, 6 peças + Peca)
- **Métodos**: ~80
- **Tempo de desenvolvimento**: 3 semanas
- **Commits**: 50+

## 🎨 Assets Gráficos

O projeto inclui um conjunto de imagens profissionais:
- **Tabuleiro**: 584x584px
- **Peças**: 72x72px cada
- **Formato**: PNG com transparência
- **Estilo**: Clássico Staunton

Para trocar de conjunto, modifique em `Xadrez.java`:
```java
private static String set = "art/set1/";  // Caminho do conjunto
private static int DIMCASA = 72;          // Tamanho das peças
```

## 🐛 Problemas Conhecidos

| Issue | Descrição | Status |
|-------|-----------|--------|
| Performance | Verificação de xeque-mate pode ser lenta em posições complexas | 🔧 Known |
| UI | Não há confirmação ao sair com jogo em andamento | 📝 Backlog |

## 👨‍💻 Autor

<div align="center">
  <img src="https://github.com/CLopes86.png" width="150" style="border-radius: 50%;" />
  
  ### ***Cesaltino Lopes **
  
  🎓 Estudante de Infoormatica e Multimedia<br>
  📍 Instituto Politécnico de Castelo Branco (IPCB)<br>
  📅 3º Ano - 2025/2026
  
  [![LinkedIn](https://img.shields.io/badge/LinkedIn-Joaquim_Lopes-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://https://www.linkedin.com/in/cesaltino-lopes-55274b176/)
  [![GitHub](https://img.shields.io/badge/GitHub-CLopes86-181717?style=for-the-badge&logo=github&logoColor=white)](https://github.com/CLopes86)
  [![Email](https://img.shields.io/badge/Email-Contacto-D14836?style=for-the-badge&logo=gmail&logoColor=white)](mailto:c.lopes46cv@gmail.com)
</div>

## 📄 Licença

Este projeto está sob licença educacional. Desenvolvido para fins académicos no IPCB.

## 🙏 Agradecimentos

Agradeço a todos que contribuíram para o sucesso deste projeto:

- 🏫 **Instituto Politécnico de Castelo Branco (IPCB)** - Pela formação de excelência
- 👨‍🏫 **Corpo Docente** - Pela orientação e conhecimento partilhado
- 📚 **Disciplina de Padrões de Design de Software** - Pelo desafio motivador
- 👥 **Colegas de Curso** - Pelo feedback e sugestões
- 💻 **Comunidade Java** - Pela documentação e recursos disponíveis
- 🎨 **Recursos Gráficos** - Pelos assets de qualidade

## 📚 Referências

- **Design Patterns**: Gang of Four (GoF)
- **Clean Code**: Robert C. Martin
- **Effective Java**: Joshua Bloch
- **Java Swing Tutorial**: Oracle Documentation
- **Regras de Xadrez**: FIDE (Federação Internacional de Xadrez)

---

<div align="center">

⭐ **Se este projeto foi útil, considera dar uma estrela no repositório!** ⭐

**Desenvolvido com ☕ e 💙 em Portugal 🇵🇹**

</div>
