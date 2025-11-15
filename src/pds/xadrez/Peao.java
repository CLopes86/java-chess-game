package pds.xadrez;

import java.awt.Point;

import javax.swing.Icon;

import pds.peca.Peca;
import pds.peca.PecaDefault;

public class Peao extends PecaDefault {

	private boolean moveu = false;

	public Peao(int corPeca, Icon fig) {
		super(corPeca, fig);
	}

	public boolean podeMover(Point dest) {
		if (!getTabuleiro().eCasaValida(dest))
			return false;

		// ver a direção do movimento
		int dy = dest.y - getPosicao().y;

		// ver se anda na direção certa
		if (dy < 0 && getCor() == Peca.BRANCAS)
			return false;

		// ver se anda na direção certa
		if (dy > 0 && getCor() == Peca.PRETAS)
			return false;

		// ver as distâncias
		dy = Math.abs(dy);
		int dx = Math.abs(dest.x - getPosicao().x);

		if (dy > 2 || dx > 1)
			return false;

		// ver se é para comer
		if (dx == 1) {
			// tem de ter peça no destino e andar apenas 1 na diagonal
			Peca p = getTabuleiro().getPeca(dest);
			return dy == 1 && p != null && p.getCor() != getCor();
		}

		// se para mover normalmente
		if (moveu && dy == 2)
			return false;

		return caminhoLivre(dest) && getTabuleiro().getPeca(dest) == null;
	}
}
