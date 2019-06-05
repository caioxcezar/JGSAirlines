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
import utils.CboPopulate;
import utils.Crud;
import utils.MainIcon;

public class AtualizarCliente extends JFrame{

	private static final long serialVersionUID = 8499266505857442803L;
	private List<Cliente> clientes = Crud.listarCliente();
	public AtualizarCliente() {
		setTitle("Atualizar Informacoes do Copiloto");
		setSize(800 , 600);
		setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);
        
        JComboBox<String> cboClientes = CboPopulate.populateCliente(clientes);
        JButton btnEditar = new JButton("Editar");
        JButton btnExcluir = new JButton("Excluir");
        JLabel lblCliente = new JLabel("Selecione o Cliente: ");
        JLabel lblListCliente = new JLabel("Lista de Clientes Cadastrados");
        String[] colunaCliente = {"ID", "Nome", "Cartão Fidelidade", "RG"};
        DefaultTableModel tmCliente = new DefaultTableModel(colunaCliente, 0);
        JTable tabCliente = new JTable(tmCliente);
        JScrollPane barCliente = new JScrollPane(tabCliente);
        
        for(Cliente c : clientes) {
        	String[] linha = {c.getId() + "", c.getNome(), c.getCartaoFidelidade() + "", c.getRg() + ""};
        	tmCliente.addRow(linha);
        }
        
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(lblCliente, constraints);
        constraints.gridx = 1;
        add(cboClientes, constraints);
        constraints.gridx = 2;
        add(btnEditar, constraints);
        constraints.gridx = 3;
        add(btnExcluir, constraints);
        constraints.gridx = 0;
        constraints.gridy = 1;
        add(lblListCliente, constraints);
        constraints.gridy = 2;
        constraints.gridwidth = 4;
        add(barCliente, constraints);
        
        MainIcon m = new MainIcon(this, "/icon.png");
        setVisible(true);
        setLocationRelativeTo(null);
        btnExcluir.addActionListener(e->{
        	setEnabled(false);
        	try {
        		int id = CboPopulate.idCbo((String)cboClientes.getSelectedItem());
        		Crud.excluir(Crud.buscar(id, Cliente.class));
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
        	CadastrarCliente cf = new CadastrarCliente(CboPopulate.idCbo((String)cboClientes.getSelectedItem()));
        	dispose();
        });
	}
}
