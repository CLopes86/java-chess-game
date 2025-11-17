package pds.xadrez;

import java.awt.Point;
import javax.swing.Icon;

import pds.peca.Peca;
import pds.peca.PecaDefault;

public class Bispo extends PecaDefault{

    public Bispo(int corPeca, Icon fig) {
        super(corPeca, fig);
        //TODO Auto-generated constructor stub
    }

    @Override
    public boolean podeMover(Point dest) {
        if(!getTabuleiro().eCasaValida(dest))
        return false;

        if(!eObliquo(dest))
        return false;

        if(!caminhoLivre(dest))
        return false;

        Peca p = getTabuleiro().getPeca(dest);
        return p == null || p.getCor() != getCor();
    }
    
}
