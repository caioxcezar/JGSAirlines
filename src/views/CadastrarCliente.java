package views;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import models.Cliente;
import utils.Crud;
import utils.MainIcon;
import utils.TextNumber;

class CadastrarCliente extends JFrame {

	private static final long serialVersionUID = 378523119810287049L;
	private List<Cliente> lista = Crud.listarCliente();
	private int lastID;
	public CadastrarCliente() {
		
		if(lista.size() != 0) {
			lastID = lista.get(lista.size() - 1).getId();
		}else {
			lastID = 0;
		}
		
		lastID = Crud.listarCliente().size();
		setTitle("Cadastrar Cliente");
	    setSize(500, 600);
	    JPanel panelSouth = new JPanel();
	    JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);
        
        JLabel lblId = new JLabel("ID do Cliente: " + (lastID + 1));
        JLabel lblNome = new JLabel("Nome do Cliente: ");
        JTextField txtNome = new JTextField(20);
        JLabel lblRg = new JLabel("RG do Cliente: ");
        JTextField txtRg = new JTextField(20);
        JLabel lblFidelidade = new JLabel("Cartão Fidelidade do Cliente: ");
        JTextField txtFidelidade = new JTextField(20);
        JButton btnCadastrar = new JButton("Cadastrar");
        
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
        panel.add(lblFidelidade, constraints);
        constraints.gridx = 1;
        panel.add(txtFidelidade, constraints);
        
        constraints.gridx = 0;
        constraints.gridy = 3;     
        panel.add(lblRg, constraints);
        constraints.gridx = 1;
        panel.add(txtRg, constraints);
        
        panelSouth.add(btnCadastrar);
        
        btnCadastrar.addActionListener(e -> {
        	if(!txtFidelidade.getText().isEmpty() && !txtNome.getText().isEmpty() && !txtRg.getText().isEmpty()) {
            	int fidelidade = TextNumber.textInteger(txtFidelidade.getText());
            	if(fidelidade!=-1) {
                	Cliente c = new Cliente();
                	c.setNome(txtNome.getText());
                	c.setCartaoFidelidade(fidelidade);
                	c.setRg(txtRg.getText());
                	Crud.salvar(c);
                	lastID++;
                	lblId.setText("ID do Cliente: " + lastID);
                	txtNome.setText("");
                	txtRg.setText("");
                	txtFidelidade.setText("");
                	JOptionPane.showMessageDialog(new JFrame(), "Cliente cadastrado");
            	}
        	}else {
    			JOptionPane.showMessageDialog(new JFrame(),
    				    "Erro Durante a Operacao",
    				    "Aviso",
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
