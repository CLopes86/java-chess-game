package pds.xadrez;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import pds.peca.Peca;
import pds.peca.Tabuleiro;
import pds.xadrez.Torre;
import pds.xadrez.Bispo;

public class Xadrez extends JFrame implements ActionListener {

	/** serial number */
	private static final long serialVersionUID = 1L;

	// constantes para o set das imagens
	private static String set = "art/set1/";
	private static int DIMCASA = 72; // set1 = 72, set2 = 80
	private static int BORDA = 7; // set1 = 8, set2 = 20

	// o tabuleiro a usar no jogo (podem usar outra imagem)
	private Tabuleiro oTabuleiro = new Tabuleiro(new ImageIcon(set + "tabuleiro.png"), new Point(0, 0), DIMCASA, BORDA);

	// imagens para os vários icones das peças
	private static Icon peaoPreto = new ImageIcon(set + "peao_preto.png");
	private static Icon torrePreta = new ImageIcon(set + "torre_preta.png");
	private static Icon cavaloPreto = new ImageIcon(set + "cavalo_preto.png");
	private static Icon bispoPreto = new ImageIcon(set + "bispo_preto.png");
	private static Icon reiPreto = new ImageIcon(set + "rei_preto.png");
	private static Icon rainhaPreta = new ImageIcon(set + "rainha_preta.png");

	private static Icon peaoBranco = new ImageIcon(set + "peao_branco.png");
	private static Icon torreBranca = new ImageIcon(set + "torre_branca.png");
	private static Icon cavaloBranco = new ImageIcon(set + "cavalo_branco.png");
	private static Icon bispoBranco = new ImageIcon(set + "bispo_branco.png");
	private static Icon reiBranco = new ImageIcon(set + "rei_branco.png");
	private static Icon rainhaBranca = new ImageIcon(set + "rainha_branca.png");

	// peça a mover
	private Peca selecionada;
	// qual o jogador a jogar
	private int turno;

	// coordenadas a usar nos movimentos
	private Point origem; // casa onde clicou para selecionar
	private Point destino; // casa de destino da peça selecionada
	private Point posicaoCursor; // posição do cursor do rato (usada para desenhar a peça selecionada)

	/**
	 * Cria a janela para o jogo de xadrez
	 */
	public Xadrez() {
		super("Xadrez");
		setupFrame();
		iniciarJogo();
	}

	/**
	 * Configura o tabuleiro de jogo para um novo jogo
	 */
	public void iniciarJogo() {
		oTabuleiro.limpar();

		// criação e colocação dos peões
		for (int i = 0; i < 8; i++) {
			oTabuleiro.colocarPeca(new Point(i + 1, 2), new Peao(Peca.BRANCAS, peaoBranco));
			oTabuleiro.colocarPeca(new Point(i + 1, 7), new Peao(Peca.PRETAS, peaoPreto));
		}

		//Torres brancas
		oTabuleiro.colocarPeca(new Point(1, 1), new Torre(Peca.BRANCAS, torreBranca));
		oTabuleiro.colocarPeca(new Point(8, 1), new Torre(Peca.BRANCAS, torreBranca));
		
		//Torres pretas
		oTabuleiro.colocarPeca(new Point(1, 8), new Torre(Peca.PRETAS, torrePreta));
		oTabuleiro.colocarPeca(new Point(8, 8), new Torre(Peca.PRETAS, torrePreta));

		// Bispos brancos
		oTabuleiro.colocarPeca(new Point(3,1), new Bispo(Peca.BRANCAS, bispoBranco));
		oTabuleiro.colocarPeca(new Point(6, 1), new Bispo(Peca.BRANCAS, bispoBranco));

		// Bispos pretos
		oTabuleiro.colocarPeca(new Point(3,8), new Bispo(Peca.PRETAS, bispoPreto));
		oTabuleiro.colocarPeca(new Point(6, 8), new Bispo(Peca.PRETAS, bispoPreto));

		// Cavalos brancos
		oTabuleiro.colocarPeca(new Point(2,1), new Cavalo(Peca.BRANCAS, cavaloBranco));
		oTabuleiro.colocarPeca(new Point(7,1), new Cavalo(Peca.BRANCAS, cavaloBranco));

		// Cavalos pretos
		oTabuleiro.colocarPeca(new Point(2,8), new Cavalo(Peca.PRETAS, cavaloPreto));
		oTabuleiro.colocarPeca(new Point(7,8), new Cavalo(Peca.PRETAS, cavaloPreto));

		// Rainha branca
		oTabuleiro.colocarPeca(new Point(4, 1), new Rainha(Peca.BRANCAS, rainhaBranca));
		// Rei branco
		oTabuleiro.colocarPeca(new Point(5, 1), new Rei(Peca.BRANCAS, reiBranco));
		// Rainha preta
		oTabuleiro.colocarPeca(new Point(4, 8), new Rainha(Peca.PRETAS, rainhaPreta));
		// Rei preto
		oTabuleiro.colocarPeca(new Point(5, 8), new Rei(Peca.PRETAS, reiPreto));

		selecionada = null;
		turno = Peca.BRANCAS;
	}

	/**
	 * Desenha o tabuleiro de jogo. Método chamado quando
	 * a zona de jogo precisa de ser redesenhada.
	 * <br>
	 * Este método é chamado automaticamente, pelo que não deve ser chamado
	 * diretamente.<br>
	 * Para redesenhar o jogo, deve chamar o método repaint do painel de jogo
	 * 
	 * @param g onde desenhar o jogo
	 */
	private void desenharJogo(Graphics2D g) {
		oTabuleiro.desenhar(g);

		if (selecionada != null) {
			if (destino != null) {
				 // decidir a cor do rectângulo baseado na validade da jogada
				 if(selecionada.podeMover(destino)){
					g.setColor(Color.GREEN);  // movimento válido
				 } else {
					g.setColor(Color.RED); // movimento inválido
				 }

				Point topo = oTabuleiro.getEcran(destino);
				g.drawRect(topo.x + 2, topo.y + 2, oTabuleiro.dimensaoCasa() - 4, oTabuleiro.dimensaoCasa() - 4);
			}
			// desenhar um rectângulo para indicar qual a peça que se está a mexer
			g.setColor(Color.BLUE);
			Point topo = oTabuleiro.getEcran(selecionada.getPosicao());
			g.drawRect(topo.x + 2, topo.y + 2, oTabuleiro.dimensaoCasa() - 4, oTabuleiro.dimensaoCasa() - 4);
			Icon figPeca = selecionada.getFigura();
			figPeca.paintIcon(Xadrez.this, g, posicaoCursor.x - figPeca.getIconWidth() / 2,
					posicaoCursor.y - figPeca.getIconHeight() / 2);
		}

		// desenhar algo que indique de quem é vez de jogar
		int largTab = oTabuleiro.larguraTotal();
		g.setColor(Color.WHITE);
		if (turno == Peca.BRANCAS)
			g.fillOval(largTab + 10, largTab - 50, 20, 20);
		else
			g.fillOval(largTab + 10, 40, 20, 20);
	}

	/**
	 * método que lida com o escolher a peça para mover
	 * 
	 * @param e informações do rato
	 */
	private void pegarPeca(MouseEvent e) {
		Point ecran = e.getPoint();

		Point posicao = oTabuleiro.getCasa(ecran);

		Peca peca = oTabuleiro.getPeca(posicao);

		if (peca != null && peca.getCor() == turno) {
			selecionada = peca;
			origem = posicao;
			posicaoCursor = ecran;
			repaint();
		}


		
	}

	/**
	 * método que lida com o jogador a arrastar a peça
	 * 
	 * @param e informações do rato
	 */
	private void arrastarPeca(MouseEvent e) {
		if (selecionada == null)
			return;

		posicaoCursor = e.getPoint();
		destino = oTabuleiro.getCasa(posicaoCursor);
		Xadrez.this.repaint();
	}

	/**
	 * lida com o jogador a pousar a peça
	 * 
	 * @param e informações do rato
	 */
	private void pousarPeca(MouseEvent e) {
    Point ecran = e.getPoint();
    Point destino = oTabuleiro.getCasa(ecran);

    if (selecionada == null)
        return;
    
    // Verificar se o movimento é válido
    if (selecionada.podeMover(destino)) {
        // Mover a peça manualmente
        oTabuleiro.removerPeca(origem);
        oTabuleiro.colocarPeca(destino, selecionada);
        selecionada.mover(destino);
        
        // Verificar se precisa promover
        if (selecionada instanceof Peao) {
            if ((selecionada.getCor() == Peca.BRANCAS && destino.y == 8) || 
                (selecionada.getCor() == Peca.PRETAS && destino.y == 1)) {
                Peca nova = promover(selecionada.getCor());
                oTabuleiro.colocarPeca(destino, nova);
            }
        }
        
        // Trocar turno
        turno = (turno == Peca.BRANCAS) ? Peca.PRETAS : Peca.BRANCAS;
    }

    // Desselecionar a peça e redesenhar
    selecionada = null;
    repaint();
}
	/**
	 * pede qual a peça a usar para substituir o peão
	 * 
	 * @param cor cor da peça a criar
	 * @return a peça substituta
	 */
	private Peca promover(int cor) {
		Icon opcoesBrancas[] = { rainhaBranca, torreBranca, bispoBranco, cavaloBranco };
		Icon opcoesPretas[] = { rainhaPreta, torrePreta, bispoPreto, cavaloPreto };
		Icon opcoes[] = cor == Peca.BRANCAS ? opcoesBrancas : opcoesPretas;

		int res;
		do {
			res = JOptionPane.showOptionDialog(this, "Escolha a peça que quer", "Promoção", JOptionPane.DEFAULT_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);
		} while (res == JOptionPane.CLOSED_OPTION);
		switch (res) {
			case 0: return new Rainha(cor, cor == Peca.BRANCAS ? rainhaBranca : rainhaPreta);
			case 1: return new Torre(cor, cor == Peca.BRANCAS ? torreBranca : torrePreta);
			case 2: return new Bispo(cor, cor == Peca.BRANCAS ? bispoBranco : bispoPreto);
			case 3: return new Cavalo(cor, cor == Peca.BRANCAS ? cavaloBranco : cavaloPreto);
			default:
				return new Rainha(cor, cor == Peca.BRANCAS ? rainhaBranca : rainhaPreta);
		}
		
	}

	/**
	 * classe responsável por processar os eventos do rato
	 */
	class OuveRato extends MouseAdapter implements MouseMotionListener {
		@Override
		public void mousePressed(MouseEvent e) {
			pegarPeca(e);
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			arrastarPeca(e);
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			pousarPeca(e);
		}

		@Override
		public void mouseMoved(MouseEvent e) {
		}
	}

	/**
	 * classe privada que representa um painel onde vai ser desenhado o jogo
	 */
	private class PainelDesenho extends JPanel {
		// serial id
		private static final long serialVersionUID = 1L;

		public PainelDesenho() {
			int largTab = oTabuleiro.larguraTotal();
			setPreferredSize(new Dimension(largTab + 50, largTab));
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			desenharJogo((Graphics2D) g);
		}
	}

	/**
	 * método chamado quando o utilizador escolhe um opção no menu
	 */
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if (cmd.equals("sair")) {
			System.exit(0);
		} else if (cmd.equals("novoJogo")) {
			iniciarJogo();
		}
	}

	/**
	 * configura a janela da aplicação
	 */
	private void setupFrame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JMenuBar barra = new JMenuBar();
		JMenu jogoMenu = new JMenu("Jogo");
		JMenuItem novoMenu = new JMenuItem("Novo jogo");
		novoMenu.setActionCommand("novoJogo");
		novoMenu.addActionListener(this);
		jogoMenu.add(novoMenu);

		JMenuItem sairMenu = new JMenuItem("Sair");
		sairMenu.setActionCommand("sair");
		sairMenu.addActionListener(this);
		jogoMenu.add(sairMenu);

		barra.add(jogoMenu);

		setJMenuBar(barra);

		PainelDesenho panel = new PainelDesenho();
		panel.setBackground(Color.BLUE);

		getContentPane().add(panel, BorderLayout.CENTER);

		// ativar os listeners
		OuveRato or = new OuveRato();
		panel.addMouseListener(or);
		panel.addMouseMotionListener(or);
		pack();
	}

	public static void main(String[] args) {
		Xadrez xadrez = new Xadrez();
		xadrez.setVisible(true);
	}
}
