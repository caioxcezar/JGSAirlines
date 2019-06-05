package views;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import models.Aviao;
import models.Cliente;
import models.Voo;
import utils.Crud;
import utils.MainIcon;
public class Main {
    private static DefaultTableModel tmCli;
    private static DefaultTableModel tmAv;
    private static DefaultTableModel tmVoo;
    private static final JFrame frame = new JFrame("JGS Airlines");
    public static void main(String[] args) {    	
		
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setExtendedState(frame.getExtendedState()|JFrame.MAXIMIZED_BOTH);
        
        JPanel panelCenter = new JPanel();
        panelCenter.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10,0,0,0);
        
        JMenuBar menuBar = new JMenuBar();

        JMenu arquivo = new JMenu("Arquivo");
        arquivo.setIcon(new ImageIcon("./resources/file.png"));
        menuBar.add(arquivo);

        JMenu voo = new JMenu("Voo");
        voo.setIcon(new ImageIcon("./resources/plane.png"));
        menuBar.add(voo);

        JMenuItem cadFunc = new JMenuItem(new AbstractAction("Cadastrar Funcionario") {
			private static final long serialVersionUID = -3916950764046929574L;
			public void actionPerformed(ActionEvent ae) {
    			CadastrarFuncionario c = new CadastrarFuncionario();
        	}
		});
        arquivo.add(cadFunc);
        
        JMenuItem cadCli = new JMenuItem(new AbstractAction("Cadastrar Cliente") {
			private static final long serialVersionUID = 6452134752295152766L;
			public void actionPerformed(ActionEvent ae) {
    			CadastrarCliente c = new CadastrarCliente();
        	}
		});
        arquivo.add(cadCli);
        
        JMenuItem cadAviao = new JMenuItem(new AbstractAction("Cadastrar Aviao") {
			private static final long serialVersionUID = 3076575170378216298L;
			public void actionPerformed(ActionEvent ae) {
    			CadastrarAviao c = new CadastrarAviao();
        	}
		});
        arquivo.add(cadAviao);
        
        JMenuItem cadVoo = new JMenuItem(new AbstractAction("Cadastrar Voo") {
			private static final long serialVersionUID = -8990859666383975541L;
			public void actionPerformed(ActionEvent ae) {
    			CadastrarVoo c = new CadastrarVoo();
        	}
		});
        arquivo.add(cadVoo);
        
        JMenuItem sair = new JMenuItem(new AbstractAction("Sair", new ImageIcon("./resources/exit.png")) {
			private static final long serialVersionUID = 8643274279262940911L;
			public void actionPerformed(ActionEvent ae) {
                JFrame exiting = new JFrame("Saindo");
                exiting.setLayout(new GridBagLayout());
                GridBagConstraints constraints = new GridBagConstraints();
                constraints.anchor = GridBagConstraints.WEST;
                constraints.insets = new Insets(10, 10, 10, 10);
                exiting.setSize(300,150);
                JLabel lblSair = new JLabel("Deseja sair?");
                JButton btnOk = new JButton("ok");
                JButton btnCancelar = new JButton("cancelar");
                constraints.gridx = 0;
                constraints.gridy = 0;
                exiting.add(lblSair, constraints);
                constraints.gridy = 1;
                exiting.add(btnOk, constraints);
                constraints.gridx = 1;
                exiting.add(btnCancelar, constraints);
                btnOk.addActionListener(e -> {
                    frame.dispose();
                    exiting.dispose();
                });
                btnCancelar.addActionListener(e ->  {
                    exiting.dispose();
                });
                exiting.setLocationRelativeTo(frame);
                exiting.setVisible(true);
            }
        });

        arquivo.add(sair);

        JMenuItem vender = new JMenuItem(new AbstractAction("Vender Passagem", new ImageIcon("./resources/sells.png")) {
			private static final long serialVersionUID = 9176522561805102958L;
			public void actionPerformed(ActionEvent ae) {
            	VenderPassagem vp = new VenderPassagem();
            }
        });
        voo.add(vender);

        JMenuItem cancelar = new JMenuItem(new AbstractAction("Cancelar Passagem", new ImageIcon("./resources/cancel.png")) {
			private static final long serialVersionUID = -7414714644055145755L;
			public void actionPerformed(ActionEvent ae) {
                CancelarPassagem cp = new CancelarPassagem();
            }
        });
        voo.add(cancelar);

        JMenuItem relatorio = new JMenuItem(new AbstractAction("Relatario de faturamento", new ImageIcon("./resources/report.png")) {
			private static final long serialVersionUID = 274710883931639325L;
			public void actionPerformed(ActionEvent ae) {
				RelatorioFaturamento rf = new RelatorioFaturamento();
            }
        });
        voo.add(relatorio);

        JMenuItem consultar = new JMenuItem(new AbstractAction("Consultar total de lugares disponíveis", new ImageIcon("./resources/plane.png")) {
			private static final long serialVersionUID = 4830186066476497099L;
			public void actionPerformed(ActionEvent ae) {
                ConsultarLugaresDisp cld = new ConsultarLugaresDisp();
            }
        });
        voo.add(consultar);

        JMenuItem funcionario = new JMenuItem(new AbstractAction("Atualizar informacoes dos funcionarios", new ImageIcon("./resources/pilot.png")) {
			private static final long serialVersionUID = -3012848524821010412L;
			public void actionPerformed(ActionEvent ae) {
                AtualizarFuncionario ap = new AtualizarFuncionario();
            }
        });
        voo.add(funcionario);

        JMenuItem aviao = new JMenuItem(new AbstractAction("Atualizar informacoes dos Aviões", new ImageIcon("./resources/plane.png")) {
			private static final long serialVersionUID = -3012848524821010412L;
			public void actionPerformed(ActionEvent ae) {
                AtualizarAviao ap = new AtualizarAviao();
            }
        });
        voo.add(aviao);
        
        JMenuItem cliente = new JMenuItem(new AbstractAction("Atualizar informacoes do cliente", new ImageIcon("./resources/client.png")) {
			private static final long serialVersionUID = 8927384498719241005L;
			public void actionPerformed(ActionEvent ae) {
                AtualizarCliente ac = new AtualizarCliente();
            }
        });
        voo.add(cliente);
        
        JMenuItem AtualizarVoo = new JMenuItem(new AbstractAction("Atualizar informacoes do voo", new ImageIcon("./resources/plane.png")) {
			private static final long serialVersionUID = 8927384498719241005L;
			public void actionPerformed(ActionEvent ae) {
                AtualizarVoo ac = new AtualizarVoo();
            }
        });
        voo.add(AtualizarVoo);
        
        JLabel lblCli = new JLabel("Lista de Clientes Cadastrados ");
        JLabel lblAv = new JLabel("Lista de Avioes Cadastrados");
        JLabel lblVoo = new JLabel("Lista de Voos Cadastrados");
        String[] colunaCli = {"ID", "Nome", "Cartao Fidelidade", "RG"};
        String[] colunaAv = {"ID", "Modelo", "Capacidade"};
        String[] colunaVoo = {"ID", "Avião", "Piloto", "Copiloto", "Preco", "Origem", "Destino", "Data"};

        tmCli = new DefaultTableModel(colunaCli, 0);
        tmAv = new DefaultTableModel(colunaAv, 0);
        tmVoo = new DefaultTableModel(colunaVoo, 0);
        
        JTable tabCli = new JTable(tmCli);
        JTable tabAv = new JTable(tmAv);
        JTable tabVoo = new JTable(tmVoo);
        JScrollPane barCli = new JScrollPane(tabCli);
        JScrollPane barAv = new JScrollPane(tabAv);
        JScrollPane barVoo = new JScrollPane(tabVoo);

        constraints.gridy = 0;
        panelCenter.add(lblCli, constraints);
        constraints.gridy = 1;
        constraints.ipadx = frame.getWidth();
        panelCenter.add(barCli, constraints);
        constraints.gridy = 3;
        constraints.ipadx = frame.getWidth();
        constraints.gridy = 4;
        constraints.ipadx = 0;
        panelCenter.add(lblAv, constraints);
        constraints.gridy = 5;
        constraints.ipadx = frame.getWidth();
        panelCenter.add(barAv, constraints);
        constraints.gridy = 6;
        constraints.ipadx = 0;
        panelCenter.add(lblVoo, constraints);
        constraints.gridy = 7;
        constraints.ipadx = frame.getWidth();
        panelCenter.add(barVoo, constraints);
        
        JButton refresh = new JButton("Atualizar");
        updateTable();
        refresh.addActionListener(e->{
        	updateTable();
        });
        JScrollPane bar = new JScrollPane(panelCenter);
        frame.getContentPane().add(BorderLayout.CENTER, bar);
        MainIcon m = new MainIcon(frame, "/icon.png");
        frame.setLocationRelativeTo(null);
        frame.getContentPane().add(BorderLayout.NORTH, menuBar);
        frame.getContentPane().add(BorderLayout.SOUTH, refresh);
        frame.setVisible(true);
    }
    //se tiver linhas nas tabelas ele limpa, se n�o ele preenche com o que estiver no banco de dados
    public static void updateTable() {
    	frame.setEnabled(false);
    	try {
			while(tmCli.getRowCount() > 0) {
				tmCli.removeRow(0);
			}
			while(tmAv.getRowCount() > 0) {
				tmAv.removeRow(0);
			}
			while(tmVoo.getRowCount() > 0) {
				tmVoo.removeRow(0);
			}
		
    	for(Cliente c : Crud.listarCliente()) {
    		String[] s = {c.getId() + "", c.getNome(), c.getCartaoFidelidade() + "", c.getRg()};
    		tmCli.addRow(s);
    	}
    	for(Aviao a : Crud.listarAviao()) {
    		String[] s = {a.getId() + "", a.getModelo(), a.getCapacidade() + ""};
    		tmAv.addRow(s);
    	}
    	for(Voo v : Crud.listarVoo()) {
    		String[] s = {v.getId() + "", 
    				v.getAviao().getModelo(), 
    				v.getPiloto().getNome(), 
    				v.getCopiloto().getNome(), 
    				"R$" + v.getPrecoPassagem(), 
    				v.getOrigem(), 
    				v.getDestino(), 
    				(v.getData() == null ? "" : v.getData().toString())};
    		tmVoo.addRow(s);
    	}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "Erro ao Atualizar",
					JOptionPane.WARNING_MESSAGE);
		}
    	frame.setEnabled(true);
    }
}