package pds.peca;

import java.awt.*;

import javax.swing.Icon;

abstract public class PecaDefault implements Peca {

	private Icon figura;
	private int cor;
	private Point casa;

	private Tabuleiro tabuleiro;

	public PecaDefault(int corPeca, Icon fig) {
		figura = fig;
		cor = corPeca;
	}

	@Override
	public boolean mover(Point dest) {
		if (!podeMover(dest))
			return false;
		casa = dest;
		return true;
	}

	@Override
	public boolean ePromovivel() {
		return false; // por defeito a figura não é promovivel
	}

	@Override
	public Point getPosicao() {
		return casa;
	}

	@Override
	public void setPosicao(Point pos) {
		casa = pos;
	}

	@Override
	public Tabuleiro getTabuleiro() {
		return tabuleiro;
	}

	@Override
	public void setTabuleiro(Tabuleiro tab) {
		tabuleiro = tab;
	}

	@Override
	public int getCor() {
		return cor;
	}

	@Override
	public void setCor(int cor) {
		this.cor = cor;
	}

	@Override
	public void desenhar(Graphics2D g, Point p) {
		figura.paintIcon(null, g, p.x - figura.getIconWidth() / 2, p.y - figura.getIconHeight() / 2);
	}

	@Override
	public Icon getFigura() {
		return figura;
	}

	@Override
	public void setFigura(Icon icon) {
		figura = icon;
	}

	// discutir que estes métodos poderiam estar numa classe utilitária
	// mas neste caso esta classe pode funcionar como essa classe utilitária
	// indica se o movimento até ao destino é horizontal
	protected boolean eHorizontal(Point pos) {
		return getPosicao().y == pos.y;
	}

	// indica se o movimento até ao destino é vertical
	protected boolean eVertical(Point dest) {
		return getPosicao().x == dest.x;
	}

	// indica se o movimento até ao destino é obliquo
	protected boolean eObliquo(Point dest) {
		return Math.abs(getPosicao().x - dest.x) == Math.abs(getPosicao().y - dest.y);
	}

	/**
	 * verifica até chegar ao destino se a casa está livre
	 * 
	 * @param dest casa de destino da peça
	 * @return true se tem caminho livew, false caso contrário
	 */
	protected boolean caminhoLivre(Point dest) {
		int dx = 0; // deslocamento em x
		int dy = 0; // deslocamento em y
		if (dest.x - getPosicao().x < 0)
			dx = -1;
		else if (dest.x - getPosicao().x > 0)
			dx = 1;
		if (dest.y - getPosicao().y < 0)
			dy = -1;
		else if (dest.y - getPosicao().y > 0)
			dy = 1;

		// podia estar dentro do for, mas assim torna-se mais legível
		Point atual = new Point(getPosicao().x + dx, getPosicao().y + dy);
		for (; !atual.equals(dest); atual.translate(dx, dy)) {
			if (getTabuleiro().getPeca(atual) != null)
				return false;
		}

		return true;
	}
}
