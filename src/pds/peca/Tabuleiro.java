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
}
