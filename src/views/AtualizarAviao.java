package views;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import models.Aviao;
import utils.CboPopulate;
import utils.Crud;
import utils.MainIcon;

public class AtualizarAviao extends JFrame{

	private static final long serialVersionUID = 8499266505857442803L;
	private List<Aviao> avioes = Crud.listarAviao();
	public AtualizarAviao() {
		setTitle("Atualizar Informacoes do Copiloto");
		setSize(800 , 600);
		setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);
        
        JComboBox<String> cboAvioes = CboPopulate.populateAviao(avioes);
        JButton btnEditar = new JButton("Editar");
        JButton btnExcluir = new JButton("Excluir");
        JLabel lblAviao = new JLabel("Selecione Avião: ");
        JLabel lblListAviao = new JLabel("Lista de Aviões Cadastrados");
        String[] colunaAviao = {"ID", "Modelo", "Capacidade"};
        DefaultTableModel tmAviao = new DefaultTableModel(colunaAviao, 0);
        JTable tabAviao = new JTable(tmAviao);
        JScrollPane barCliente = new JScrollPane(tabAviao);
        
        for(Aviao av : avioes) {
        	String[] linha = {av.getId() + "", av.getModelo(), av.getCapacidade() + ""};
        	tmAviao.addRow(linha);
        }
        
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(lblAviao, constraints);
        constraints.gridx = 1;
        add(cboAvioes, constraints);
        constraints.gridx = 2;
        add(btnEditar, constraints);
        constraints.gridx = 3;
        add(btnExcluir, constraints);
        constraints.gridx = 0;
        constraints.gridy = 1;
        add(lblListAviao, constraints);
        constraints.gridy = 2;
        constraints.gridwidth = 4;
        add(barCliente, constraints);
        
        MainIcon m = new MainIcon(this, "/icon.png");
        setVisible(true);
        setLocationRelativeTo(null);
        btnExcluir.addActionListener(e->{
        	setEnabled(false);
        	try {
        		int id = CboPopulate.idCbo((String)cboAvioes.getSelectedItem());
        		Crud.excluir(Crud.buscar(id, Aviao.class));
        		setEnabled(false);
        		JOptionPane.showMessageDialog(new JFrame(), "Aviao Excluido");
        		dispose();
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(new JFrame(), e2.getMessage(), "Erro ao Excluir Cliente",
						JOptionPane.WARNING_MESSAGE);
				setEnabled(true);
			}
        });
        btnEditar.addActionListener(e->{
        	CadastrarAviao cf = new CadastrarAviao(CboPopulate.idCbo((String)cboAvioes.getSelectedItem()));
        	dispose();
        });
	}
}
