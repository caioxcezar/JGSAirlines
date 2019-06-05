package views;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import models.Funcionario;
import utils.Crud;
import utils.MainIcon;

class CadastrarFuncionario extends JFrame {
	
	private static final long serialVersionUID = 4967670627874545640L;
	
	private List<Funcionario> lista = Crud.listarFuncionario();
	private int lastID;
	public CadastrarFuncionario() {
		Funcionario f = new Funcionario();
		f.setNome("");
		f.setCargoFuncionario("");
		f.setRg("");
		interfaceFunc(f);
	}
	public CadastrarFuncionario(int id) {
		try {
			Funcionario f = (Funcionario)Crud.buscar(id, Funcionario.class);
			interfaceFunc(f);
		}catch (Exception e) {
			JOptionPane.showMessageDialog(new JFrame(),
				    "Erro ao buscar o funcion�rio",
				    "Erro",
				    JOptionPane.ERROR_MESSAGE);
		}
		int cargo = 0;
	}
	private void interfaceFunc(Funcionario f) {
		boolean cadastro = f.getNome().equals("");
		if(lista.size() != 0) {
			lastID = lista.get(lista.size() - 1).getId();
		}else {
			lastID = 0;
		}
		
		String[] cargo = {"Piloto", "Copiloto"};
		setTitle("Formulario Funcionario");
	    setSize(450, 600);
	    JPanel panelSouth = new JPanel();
	    JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);
        
        JLabel lblId = new JLabel("ID do Funcionario: " + (cadastro ? (lastID + 1):f.getId()));
        JLabel lblNome = new JLabel("Nome do Funcionario: ");
        JTextField txtNome = new JTextField(20);
        txtNome.setText(f.getNome());
        JLabel lblCargo = new JLabel("Cargo do Funcionario: ");
        JComboBox<String> cboCargo = new JComboBox<String>(cargo);
        cboCargo.setSelectedIndex((f.getCargoFuncionario().equals("Piloto") ? 0 : 1 ));
        JLabel lblRg = new JLabel("RG do Funcionario: ");
        JTextField txtRg = new JTextField(20);
        txtRg.setText(f.getRg());
        JButton btnCadastrar = new JButton((cadastro?"Cadastrar":"Editar"));
        
        constraints.gridx = 0;
        constraints.gridy = 0;     
        panel.add(lblId, constraints);
        
        constraints.gridx = 0;
        constraints.gridy = 1;     
        panel.add(lblNome, constraints);
        constraints.gridx = 1;
        panel.add(txtNome, constraints);
        
        constraints.gridx = 0;
        constraints.gridy = 2;     
        panel.add(lblCargo, constraints);
        constraints.gridx = 1;
        panel.add(cboCargo, constraints);
        
        constraints.gridx = 0;
        constraints.gridy = 3;     
        panel.add(lblRg, constraints);
        constraints.gridx = 1;
        panel.add(txtRg, constraints);
        
        panelSouth.add(btnCadastrar);
		btnCadastrar.addActionListener(e -> {
			if (!txtNome.getText().isEmpty() && !txtRg.getText().isEmpty() && cboCargo.getSelectedItem() != null) {
				try {
					f.setNome(txtNome.getText());
					f.setRg(txtRg.getText());
					f.setCargoFuncionario(cargo[cboCargo.getSelectedIndex()]);
					if (cadastro == true) {
						Crud.salvar(f);
						lastID++;
					} else {
						Crud.atualizar(f);
					}
					lblId.setText("ID do Funcionario: " + lastID);
					txtNome.setText("");
					txtRg.setText("");
					cboCargo.setSelectedIndex(0);
					f.setNome("");
					f.setCargoFuncionario("");
					f.setRg("");
					JOptionPane.showMessageDialog(new JFrame(), "Operação concluida com sucesso");
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(new JFrame(), ex.getMessage(), "Erro",
							JOptionPane.WARNING_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(new JFrame(), "Erro Durante a Operacao", "Aviso",
						JOptionPane.WARNING_MESSAGE);
			}
		});
        
	    add(panel);
	    setLocationRelativeTo(null);
	    getContentPane().add(BorderLayout.SOUTH, panelSouth);
	    MainIcon m = new MainIcon(this,"/icon.png");
	    setVisible(true);
	}
}
