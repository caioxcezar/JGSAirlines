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

import models.Cliente;
import models.Funcionario;
import utils.CboPopulate;
import utils.Crud;
import utils.MainIcon;

class AtualizarFuncionario extends JFrame {

	private static final long serialVersionUID = 4749896939824805149L;
	private List<Funcionario> funcionarios = Crud.listarFuncionario();

	public AtualizarFuncionario() {

		setTitle("Atualizar Informacoes do Piloto");
		setSize(800, 600);
		setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(10, 10, 10, 10);

		JComboBox<String> cboFunc = CboPopulate.populateFunc(funcionarios);
		JButton btnEditar = new JButton("Editar");
		JButton btnExcluir = new JButton("Excluir");
		JLabel lblFunc = new JLabel("Selecione o Funcionario: ");
		JLabel lblListFunc = new JLabel("Lista de Funcionarios Cadastrados");
		String[] colunaFunc = { "ID", "Nome", "Cargo", "RG" };
		DefaultTableModel tmFunc = new DefaultTableModel(colunaFunc, 0);
		JTable tabFunc = new JTable(tmFunc);
		JScrollPane barFunc = new JScrollPane(tabFunc);

		for (Funcionario f : funcionarios) {
			String[] linha = { f.getId() + "", f.getNome(), f.getCargoFuncionario(), f.getRg() + "" };
			tmFunc.addRow(linha);
		}

		constraints.gridx = 0;
		constraints.gridy = 0;
		add(lblFunc, constraints);
		constraints.gridx = 1;
		add(cboFunc, constraints);
		constraints.gridx = 2;
		add(btnEditar, constraints);
		constraints.gridx = 3;
		add(btnExcluir, constraints);
		constraints.gridx = 0;
		constraints.gridy = 1;
		add(lblListFunc, constraints);
		constraints.gridy = 2;
		constraints.gridwidth = 4;
		add(barFunc, constraints);

		MainIcon m = new MainIcon(this, "/icon.png");
		setVisible(true);
		setLocationRelativeTo(null);
		btnExcluir.addActionListener(e->{
        	setEnabled(false);
        	try {
        		int id = CboPopulate.idCbo((String)cboFunc.getSelectedItem());
        		Crud.excluir(Crud.buscar(id, Funcionario.class));
        		setEnabled(false);
        		JOptionPane.showMessageDialog(new JFrame(), "Funcionario Excluido");
        		dispose();
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(new JFrame(), e2.getMessage(), "Erro ao Excluir Funcionario",
						JOptionPane.WARNING_MESSAGE);
				setEnabled(true);
			}
        });
		btnEditar.addActionListener(e -> {
			CadastrarFuncionario cf = new CadastrarFuncionario(CboPopulate.idCbo((String) cboFunc.getSelectedItem()));
			dispose();
		});
	}
}
