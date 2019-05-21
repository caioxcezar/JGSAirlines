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

class AtualizarPiloto extends JFrame {

	private static final long serialVersionUID = 4749896939824805149L;
	private List<Funcionario> funcionarios = Crud.listarFuncionario();
	public AtualizarPiloto() {
		
		CboPopulate p = new CboPopulate();
		setTitle("Atualizar Informacoes do Piloto");
		setSize(800 , 600);
		setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);
        
        JComboBox<String> cboFunc = p.populatePiloto(funcionarios);
        JButton btnEditar = new JButton("Editar");
        JLabel lblPiloto = new JLabel("Selecione o Piloto: ");
        JLabel lblListPiloto = new JLabel("Lista de Piloto Cadastrados");
        String[] colunaPiloto = {"ID", "Nome", "Cargo", "RG"};
        DefaultTableModel tmPiloto = new DefaultTableModel(colunaPiloto, 0);
        JTable tabPiloto = new JTable(tmPiloto);
        JScrollPane barPiloto = new JScrollPane(tabPiloto);
        
        for(Funcionario f : funcionarios) {
    		if(f.getCargoFuncionario().equals("Piloto")) {
    			String[] linha = {f.getId() + "", f.getNome(), f.getCargoFuncionario(), f.getRg() + ""};
    			tmPiloto.addRow(linha);
    		}
        }
        
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(lblPiloto, constraints);
        constraints.gridx = 1;
        add(cboFunc, constraints);
        constraints.gridx = 2;
        add(btnEditar, constraints);
        constraints.gridx = 0;
        constraints.gridy = 1;
        add(lblListPiloto, constraints);
        constraints.gridy = 2;
        constraints.gridwidth = 3;
        add(barPiloto, constraints);
        
        MainIcon m = new MainIcon(this, "/icon.png");
        setVisible(true);
        setLocationRelativeTo(null);
        
        btnEditar.addActionListener(e->{
        	CadastrarFuncionario cf = new CadastrarFuncionario(p.idCbo((String)cboFunc.getSelectedItem()));
        	dispose();
        });
	}
}
