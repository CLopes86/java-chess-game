package pds.peca;

import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.Icon;

public interface Peca {

	int BRANCAS = 0;
	int PRETAS = 1;

	/**
	 * Indica se a peça se pode mover para a casa de destino.
	 * 
	 * @param dest casa para onde a peça se deve mover
	 * @return true se a jogada á válida, false caso contrário
	 */
	boolean podeMover(Point dest);

	/**
	 * Move a peça, se for possível, para a casa de destino
	 * 
	 * @param dest casa para onde a peça se deve mover
	 * @return true se a jogada foi efetuada
	 */
	public boolean mover(Point dest);

	/**
	 * Indica se a peça é promovivel, isto é, se
	 * pode ser substituida por outra
	 * 
	 * @return true se a peça é promovivel
	 */
	public boolean ePromovivel();

	/**
	 * define a posição da Peça. <strong>Este Método
	 * só deve ser chamado pelo tabuleiro!</strong>
	 * 
	 * @param pos casa onde deve ficar a peça
	 */
	public void setPosicao(Point pos);

	/**
	 * Retorna a casa ondde a peça se encontra
	 * 
	 * @return a casa ondde a peça se encontra, null
	 *         se a peça não estiver colocado em nenhum tabuleiro
	 */
	public Point getPosicao();

	/**
	 * define em que tabuleiro a peça deve ser colocada.
	 * <strong>Este método só deve ser chamado pelo Tabuleiro!</strong>
	 * Para colocar uma peça no tabuleiro deve usar a classe Tabuleiro
	 * 
	 * @param tab o tabuleiro onde colocar a peça
	 */
	public void setTabuleiro(Tabuleiro tab);

	/**
	 * Retorna o tabuleiro onde a peça está colocada.
	 * 
	 * @return o tabuleiro onde a peça está colocada, ou null se não
	 *         estiver colocada em nenhum tabuleiro
	 */
	public Tabuleiro getTabuleiro();

	/**
	 * retorna a cor da peça
	 * 
	 * @return a cor da peça
	 */
	public int getCor();

	/**
	 * Define a cor da peça
	 * 
	 * @param cor a cor da peça
	 */
	public void setCor(int cor);

	/**
	 * Deenha a peça na posição do écran indicada. A posição indica onde deve ser
	 * desenhado o centro da peça.
	 * 
	 * @param g ambiente gráfico onde desenhar
	 * @param p a coordenada do centro da peça
	 */
	public void desenhar(Graphics2D g, Point p);

	/**
	 * devolve a imagem associada à peça
	 * 
	 * @return a imagem associada à peça
	 */
	public Icon getFigura();

	/**
	 * define a imagem que deve ser usada para apresentar a peça
	 * 
	 * @param icon a imagem que deve ser usada para apresentar a peça
	 */
	public void setFigura(Icon icon);
}
