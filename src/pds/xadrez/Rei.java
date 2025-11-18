package pds.xadrez;

import java.awt.Point;
import javax.swing.Icon;

import pds.peca.Peca;
import pds.peca.PecaDefault;

public class Rei  extends PecaDefault{

    public Rei(int corPeca, Icon fig) {
        super(corPeca, fig);
        //TODO Auto-generated constructor stub
    }

    @Override
    public boolean podeMover(Point dest) {
        if(!getTabuleiro().eCasaValida(dest))
        return false;

        int dy = Math.abs(dest.y - getPosicao().y);
        int dx = Math.abs(dest.x - getPosicao().x);

        if (dx > 1 || dy > 1)
        return false;

        Peca p = getTabuleiro().getPeca(dest);
        return p == null || p.getCor() != getCor();
        
    }
    
}
