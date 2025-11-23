package pds.peca;

import java.awt.*;

import javax.swing.ImageIcon;

public class Tabuleiro {

    private static final int DIMENSAO = 8;
    private ImageIcon figura;
    private int dimensaoCasa, meiaCasa;
    private int borda;
    private Point topo;

    private Peca asPecas[][] = new Peca[DIMENSAO][DIMENSAO];

    // em java isto não é necessário, pois a inicialização é automática
    {
        for (int x = 0; x < DIMENSAO; x++)
            for (int y = 0; y < DIMENSAO; y++)
                asPecas[x][y] = null;
    }

    /**
     * Cria um tabuleiro de 8x8 casas
     * 
     * @param fig     imagem de fundo do tabuleiro
     * @param t       coordenada onde deve ser desenhado o tabuleiro
     * @param dimCasa a dimensão de cada casa (assume-se que cada casa é um
     *                quadrado)
     * @param brd     dimensão da borda do tabuleiro (assume-se que a borda é a
     *                mesma a toda a volta do tabuleiro)
     */
    public Tabuleiro(ImageIcon fig, Point t, int dimCasa, int brd) {
        dimensaoCasa = dimCasa;
        meiaCasa = dimensaoCasa / 2;
        borda = brd;
        topo = t;
        figura = fig;
    }

    /**
     * Desenha o tabuleiro e respetivas peças no ambiente gráfico g
     * 
     * @param g ambiente onde desenhar o tabuleiro
     */
    public void desenhar(Graphics2D g) {
        int offsetX = topo.x + borda;
        int offsetY = topo.y + borda + dimensaoCasa * 7;

        figura.paintIcon(null, g, topo.x, topo.y);

        for (int x = 0; x < DIMENSAO; x++)
            for (int y = 0; y < DIMENSAO; y++)
                if (asPecas[x][y] != null)
                    asPecas[x][y].desenhar(g,
                            new Point(offsetX + dimensaoCasa * x + meiaCasa, offsetY - dimensaoCasa * y
                                    + meiaCasa));
    }

    /**
     * testa se a coordenada indicada se refere a uma casa válida
     * 
     * @param casa coordenada a testar
     * @return true se for válida
     */
    public boolean eCasaValida(Point casa) {
        return casa != null && casa.x >= 1 && casa.x <= DIMENSAO && casa.y >= 1 && casa.y <= DIMENSAO;
    }

    /**
     * método auxiliar que testa se uma coordenada é válida e,
     * caso não seja, atira uma exceção IllegalArgumentException
     * 
     * @param p a coordenada a testar
     */
    private void checkCasaValida(Point p) {
        if (!eCasaValida(p))
            throw new IllegalArgumentException(p + " não é casa válida");
    }

    /**
     * coloca uma peça no tabuleiro na posição indicada
     * 
     * @param casa    onde colocar a peça
     * @param umaPeca peça a colocar
     */
    public void colocarPeca(Point casa, Peca umaPeca) {
        checkCasaValida(casa);

        umaPeca.setTabuleiro(this);
        umaPeca.setPosicao(casa);
        asPecas[casa.x - 1][casa.y - 1] = umaPeca;
    }

    /**
     * remove a peça que está na posição indicada
     * 
     * @param casa posição de onde remover a peça
     * @return a peça removida, null se não houver peça na posição dada
     */
    public Peca removerPeca(Point casa) {
        checkCasaValida(casa);

        int x = casa.x - 1;
        int y = casa.y - 1;
        Peca old = asPecas[x][y];
        asPecas[x][y] = null;
        return old;
    }

    /**
     * Move a peça da origem para o destino. A peça só se move se obedecer às regras
     * do jogo.
     * 
     * @param origem  coordenada onde está a peça a mover
     * @param destino coordenada para onde mover a peça
     * @return true se a jogada foi efetuada, false caso contrário
     */
    public boolean moverPeca(Point origem, Point destino) {
        if (!eCasaValida(destino))
            return false;
        Peca p = getPeca(origem);
        if (p == null || !p.podeMover(destino))
            return false;
        p.mover(destino);
        asPecas[destino.x - 1][destino.y - 1] = p;
        asPecas[origem.x - 1][origem.y - 1] = null;
        return true;
    }

    /**
     * Move a peça indicada para o local indicado. A peça só se move se obedecer às
     * regras do jogo.
     * 
     * @param p       peça a mover
     * @param destino para onde mover a peça
     * @return true se a jogada foi efetuada, false caso contrário
     */
    public boolean moverPeca(Peca p, Point destino) {
        return moverPeca(p.getPosicao(), destino);
    }

    /**
     * retorna a peça que está numa dada casa
     * 
     * @param casa onde procurar a peça
     * @return a peça que está no local, ou null caso não haja peça
     */
    public Peca getPeca(Point casa) {
        checkCasaValida(casa);
        return asPecas[casa.x - 1][casa.y - 1];
    }

    /**
     * Transforma uma coordenada de écran em coordenadas de tabuleiro, isto é,
     * indica a que casa do tabuleiro corresponde uma coordenada do écran
     * 
     * @param ecran coordenada, em pixéis, do local do écran
     * @return a casa do tabuleiro correspondente ao local do écran,
     *         null caso a coordenada não esteja dentro do tabuleiro
     */
    public Point getCasa(Point ecran) {
        // calcular em que casa se premiu
        // lembrar que tabuleiro tem as coordenadas em baixo, da direita para a
        // esquerda e a começar em (1,1)
        int x = ((ecran.x - topo.x - borda) / dimensaoCasa) + 1;
        int y = DIMENSAO - ((ecran.y - topo.y - borda) / dimensaoCasa);

        // verificar se a casa está dentro do tabuleiro
        // se não estiver retornar (0,0)
        Point casa = new Point(x, y);
        if (!eCasaValida(casa))
            return null;
        return casa;
    }

    /**
     * retorna a coordenada do écran correspondente ao ponto superior esquerdo
     * da casa do tabuleiro indicada
     * 
     * @param casa acasa da qual se quer a coordenada
     * @return a coordenada do écran correspondente ao ponto superior esquerdo da
     *         casa indicada
     */
    public Point getEcran(Point casa) {
        checkCasaValida(casa);
        int x = (casa.x - 1) * dimensaoCasa + borda + topo.x;
        int y = (DIMENSAO - casa.y) * dimensaoCasa + borda + topo.y;

        return new Point(x, y);
    }

    /**
     * retorna a coordenada do écran correspondente ao centro
     * da casa do tabuleiro indicada
     * 
     * @param casa a casa da qual se quer a coordenada
     * @return a coordenada do écran correspondente ao ponto superior esquerdo da
     *         casa indicada
     */
    public Point getEcranCentro(Point casa) {
        checkCasaValida(casa);
        int x = (casa.x - 1) * dimensaoCasa + borda + topo.x + meiaCasa;
        int y = (DIMENSAO - casa.y) * dimensaoCasa + borda + topo.y + meiaCasa;

        return new Point(x, y);
    }

    /**
     * remove todas as peças do tabuleiro
     */
    public void limpar() {
        for (int x = 0; x < DIMENSAO; x++)
            for (int y = 0; y < DIMENSAO; y++) {
                if (asPecas[x][y] != null) {
                    asPecas[x][y].setTabuleiro(null);
                    asPecas[x][y] = null;
                }
            }
    }

    /**
     * retorna a largura total, em pixeis, do tabuleiro.
     * A largura total inclui as dimensões da borda
     * 
     * @return a largura total do tabuleiro.
     */
    public int larguraTotal() {
        return dimensaoCasa * DIMENSAO + borda * 2;
    }

    /**
     * retorna a dimensão de cada casa do tabuleiro, em pixeis
     * 
     * @return a dimensão de cada casa do tabuleiro
     */
    public int dimensaoCasa() {
        return dimensaoCasa;
    }

    /**
     * retorna a dimensão da borda, em pixeis, presentes na borda do tabuleiro
     * 
     * @return a dimensão da borda
     */
    public int borda() {
        return borda;
    }

    /**
 * Verifica se uma determinada casa está sob ataque por peças de uma cor específica
 * 
 * @param casa a casa a verificar
 * @param corAtacante a cor das peças atacantes (Peca.BRANCAS ou Peca.PRETAS)
 * @return true se a casa está sob ataque, false caso contrário
 */
public boolean estaSobAtaque(Point casa, int corAtacante){
     // Verificar se a casa é válida
    if (!eCasaValida(casa)){
        return false;
    }

    // Percorrer todas as casas do tabuleiro
    for (int x = 0; x < 8; x++){
        for(int y = 0; y < 8; y++){
            // Verificar se há uma peça nesta posição
            Peca peca = asPecas[x][y];

             // Se há peça E é da cor atacante
             if(peca != null && peca.getCor() == corAtacante){
                 // Calcular a posição no formato Point (1-8, não 0-7)
                 Point posicaoPeca = new Point(x + 1, y +1);

                // Verificar se esta peça pode mover para a casa alvo 
                if(peca.podeMover(casa)) {
                    return true; // Casa está sob ataque!
                }

             }

        }
    }
    return false;    // Nenhuma peça pode atacar esta casa
}

/**
 * Encontra a posição do Rei de uma determinada cor
 * 
 * @param cor a cor do Rei a procurar (Peca.BRANCAS ou Peca.PRETAS)
 * @return a posição do Rei, ou null se não encontrar
 */
public Point encontrarRei(int cor) {
    // Percorrer todas as casas do tabuleiro
    for (int x = 0; x < 8; x++) {
        for (int y = 0; y < 8; y++) {
            Peca peca = asPecas[x][y];
            
            // Verificar se é um Rei da cor certa
            if (peca != null && peca.getCor() == cor) {
                String nomePeca = peca.getClass().getSimpleName();
                
                // Verificar se é um Rei
                if (nomePeca.equals("Rei")) {
                    return new Point(x + 1, y + 1);
                }
            }
        }
    }
    
    return null;
}

/**
 * Verifica se o Rei de uma determinada cor está em xeque
 * 
 * @param cor a cor do Rei a verificar
 * @return true se o Rei está em xeque, false caso contrário
 */
public boolean reiEmXeque(int cor) {
     // Encontrar a posição do Rei
     Point posicaoRei = encontrarRei(cor);

     // Se não encontrar o Rei, retornar false
     if(posicaoRei == null) {
        return false;
     }

       // Determinar a cor do adversário
       int corAdversario = (cor == Peca.BRANCAS) ? Peca.PRETAS : Peca.BRANCAS;

        // Verificar se a posição do Rei está sob ataque do adversário
        return estaSobAtaque(posicaoRei, corAdversario);
}

/**
 * Simula um movimento temporariamente para verificar se é seguro
 * 
 * @param origem casa de origem
 * @param destino casa de destino
 * @return a peça que estava no destino (null se vazio, ou peça capturada)
 */
/**
 * Simula um movimento temporariamente para verificar se é seguro
 * 
 * @param origem casa de origem
 * @param destino casa de destino
 * @return a peça que estava no destino (null se vazio, ou peça capturada)
 */
public Peca simularMovimento(Point origem, Point destino) {
    if (!eCasaValida(origem) || !eCasaValida(destino)) {
        return null;
    }

    // Pegar a peça que está na origem
    Peca pecaMovida = asPecas[origem.x - 1][origem.y - 1];

    // Guardar a peça que está no destino (pode ser null ou uma peça capturada)
    Peca pecaCapturada = asPecas[destino.x - 1][destino.y - 1];

    // Fazer o movimento temporário no array
    asPecas[destino.x - 1][destino.y - 1] = pecaMovida;
    asPecas[origem.x - 1][origem.y - 1] = null;

    // Atualizar posição interna da peça movida
    if (pecaMovida != null) {
        pecaMovida.setPosicao(destino);
    }
    
    // IMPORTANTE: Atualizar tabuleiro da peça capturada
    if (pecaCapturada != null) {
        pecaCapturada.setTabuleiro(null);
    }

    // Retornar a peça capturada (para poder desfazer depois)
    return pecaCapturada;
}
/**
 * Desfaz um movimento simulado
 * 
 * @param origem casa de origem original
 * @param destino casa de destino
 * @param pecaCapturada a peça que estava no destino (null se estava vazio)
 */
public void desfazerMovimento(Point origem, Point destino, Peca pecaCapturada) {
    if (!eCasaValida(origem) || !eCasaValida(destino)) {
        return;
    }
    
    // Pegar a peça que está no destino
    Peca pecaMovida = asPecas[destino.x - 1][destino.y - 1];
    
    // Devolver a peça à origem no array
    asPecas[origem.x - 1][origem.y - 1] = pecaMovida;
    
    // Restaurar o que estava no destino
    asPecas[destino.x - 1][destino.y - 1] = pecaCapturada;
    
    // IMPORTANTE: Restaurar posição interna da peça movida
    if (pecaMovida != null) {
        pecaMovida.setPosicao(origem);
    }
    
    // IMPORTANTE: Restaurar tabuleiro da peça capturada
    if (pecaCapturada != null) {
        pecaCapturada.setTabuleiro(this);
        pecaCapturada.setPosicao(destino);
    }
}
/**
 * Verifica se o Rei de uma cor está em xeque-mate
 * 
 * @param cor a cor do jogador a verificar
 * @return true se está em xeque-mate, false caso contrário
 */
/**
 * Verifica se o Rei de uma cor está em xeque-mate
 * 
 * @param cor a cor do jogador a verificar
 * @return true se está em xeque-mate, false caso contrário
 */
public boolean estaEmXequeMate(int cor) {
    // Primeiro, verificar se o Rei está em xeque
    if (!reiEmXeque(cor)) {
        return false; // Se não está em xeque, não pode estar em xeque-mate
    }
    
    // O Rei está em xeque! Verificar se há alguma jogada legal
    
    // Percorrer todas as peças da cor em xeque
    for (int x = 0; x < 8; x++) {
        for (int y = 0; y < 8; y++) {
            Peca peca = asPecas[x][y];
            
            // Se há peça E é da cor em xeque
            if (peca != null && peca.getCor() == cor) {
                Point origem = new Point(x + 1, y + 1);
                
                // OTIMIZAÇÃO: Testar apenas casas próximas (raio de 7)
                // Em vez de testar todas as 64 casas
                for (int dx = -7; dx <= 7; dx++) {
                    for (int dy = -7; dy <= 7; dy++) {
                        int destinoX = x + dx;
                        int destinoY = y + dy;
                        
                        // Verificar se está dentro do tabuleiro
                        if (destinoX >= 0 && destinoX < 8 && destinoY >= 0 && destinoY < 8) {
                            Point destino = new Point(destinoX + 1, destinoY + 1);
                            
                            // Verificar se a peça pode mover para este destino
                            if (peca.podeMover(destino)) {
                                // Simular o movimento
                                Peca pecaCapturada = simularMovimento(origem, destino);
                                
                                // Verificar se o Rei ainda está em xeque
                                boolean aindaEmXeque = reiEmXeque(cor);
                                
                                // Desfazer o movimento
                                desfazerMovimento(origem, destino, pecaCapturada);
                                
                                // Se o Rei NÃO está mais em xeque, há uma saída!
                                if (!aindaEmXeque) {
                                    return false; // NÃO é xeque-mate!
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    
    // Percorremos TODAS as peças e TODOS os movimentos possíveis
    // Nenhum movimento tira o Rei do xeque
    // É XEQUE-MATE!
    return true;
}
}
