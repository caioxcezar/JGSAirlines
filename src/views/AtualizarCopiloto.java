package views;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import models.Funcionario;
import utils.CboPopulate;
import utils.Crud;
import utils.MainIcon;

public class AtualizarCopiloto extends JFrame{

	private static final long serialVersionUID = 8499266505857442803L;
	private List<Funcionario> funcionarios = Crud.listarFuncionario();
	public AtualizarCopiloto() {
		
		CboPopulate p = new CboPopulate();
		setTitle("Atualizar Informacoes do Copiloto");
		setSize(800 , 600);
		setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);
        
        JComboBox<String> cboFunc = p.populateCopiloto(funcionarios);
        JButton btnEditar = new JButton("Editar");
        JLabel lblCopiloto = new JLabel("Selecione o Copiloto: ");
        JLabel lblListCopiloto = new JLabel("Lista de Copiloto Cadastrados");
        String[] colunaCopiloto = {"ID", "Nome", "Cargo", "RG"};
        DefaultTableModel tmCopiloto = new DefaultTableModel(colunaCopiloto, 0);
        JTable tabCopiloto = new JTable(tmCopiloto);
        JScrollPane barCopiloto = new JScrollPane(tabCopiloto);
        
        for(Funcionario f : funcionarios) {
    		if(f.getCargoFuncionario().equals("Copiloto")) {
    			String[] linha = {f.getId() + "", f.getNome(), f.getCargoFuncionario(), f.getRg() + ""};
    			tmCopiloto.addRow(linha);
    		}
        }
        
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(lblCopiloto, constraints);
        constraints.gridx = 1;
        add(cboFunc, constraints);
        constraints.gridx = 2;
        add(btnEditar, constraints);
        constraints.gridx = 0;
        constraints.gridy = 1;
        add(lblListCopiloto, constraints);
        constraints.gridy = 2;
        constraints.gridwidth = 3;
        add(barCopiloto, constraints);
        
        MainIcon m = new MainIcon(this, "/icon.png");
        setVisible(true);
        setLocationRelativeTo(null);
        
        btnEditar.addActionListener(e->{
        	CadastrarFuncionario cf = new CadastrarFuncionario(p.idCbo((String)cboFunc.getSelectedItem()));
        	dispose();
        });
	}
}
