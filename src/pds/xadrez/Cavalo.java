package pds.xadrez;

import java.awt.Point;
import javax.swing.Icon;

import pds.peca.Peca;
import pds.peca.PecaDefault;

public class Cavalo extends PecaDefault {

    public Cavalo(int corPeca, Icon fig) {
        super(corPeca, fig);
        //TODO Auto-generated constructor stub
    }

    @Override
   public boolean podeMover(Point dest) {
	if (!getTabuleiro().eCasaValida(dest))
		return false;
	
	int dx = Math.abs(dest.x - getPosicao().x);
	int dy = Math.abs(dest.y - getPosicao().y);
	
	if (!((dx == 2 && dy == 1) || (dx == 1 && dy == 2)))
		return false;
	
	Peca p = getTabuleiro().getPeca(dest);
	return p == null || p.getCor() != getCor();
}
    
}
