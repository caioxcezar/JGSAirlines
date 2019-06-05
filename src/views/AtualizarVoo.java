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

import models.Funcionario;
import models.Voo;
import utils.CboPopulate;
import utils.Crud;
import utils.MainIcon;

public class AtualizarVoo extends JFrame {

	private static final long serialVersionUID = 4749896939824805149L;
	private List<Voo> voos = Crud.listarVoo();

	public AtualizarVoo() {

		setTitle("Atualizar Informacoes do Voo");
		setSize(800, 600);
		setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(10, 10, 10, 10);

		JComboBox<String> cboVoo = CboPopulate.populateVoo(voos);
		JButton btnEditar = new JButton("Editar");
		JButton btnExcluir = new JButton("Excluir");
		JLabel lblVoo = new JLabel("Selecione o Voo: ");
		JLabel lblListVoo = new JLabel("Lista de Funcionarios Cadastrados");
		String[] colunaVoo = {"ID", "Avião", "Piloto", "Copiloto", "Preco", "Origem", "Destino", "Data"};
		DefaultTableModel tmVoo = new DefaultTableModel(colunaVoo, 0);
		JTable tabVoo = new JTable(tmVoo);
		JScrollPane barVoo = new JScrollPane(tabVoo);

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

		constraints.gridx = 0;
		constraints.gridy = 0;
		add(lblVoo, constraints);
		constraints.gridx = 1;
		add(cboVoo, constraints);
		constraints.gridx = 2;
		add(btnEditar, constraints);
		constraints.gridx = 3;
		add(btnExcluir, constraints);
		constraints.gridx = 0;
		constraints.gridy = 1;
		add(lblListVoo, constraints);
		constraints.gridy = 2;
		constraints.gridwidth = 4;
		add(barVoo, constraints);

		MainIcon m = new MainIcon(this, "/icon.png");
		setVisible(true);
		setLocationRelativeTo(null);

		btnEditar.addActionListener(e -> {
			CadastrarVoo cf = new CadastrarVoo(CboPopulate.idCbo((String) cboVoo.getSelectedItem()));
			dispose();
		});
		btnExcluir.addActionListener(e -> {
        	setEnabled(false);
        	try {
        		int id = CboPopulate.idCbo((String)cboVoo.getSelectedItem());
        		Crud.excluir(Crud.buscar(id, Voo.class));
        		setEnabled(false);
        		JOptionPane.showMessageDialog(new JFrame(), "Voo Excluido");
        		dispose();
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(new JFrame(), e2.getMessage(), "Erro ao Excluir Voo",
						JOptionPane.WARNING_MESSAGE);
				setEnabled(true);
			}
		});
	}
}