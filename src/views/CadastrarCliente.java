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
		Cliente c = new Cliente();
		c.setNome("");
		c.setCartaoFidelidade(0);
		c.setRg("");
		interfaceCliente(c);
	}
	public CadastrarCliente(int id) {
		try {
			Cliente c = (Cliente)Crud.buscar(id, Cliente.class);
			interfaceCliente(c);
		}catch (Exception e) {
			JOptionPane.showMessageDialog(new JFrame(),
				    "Erro ao buscar o funcionário",
				    "Erro",
				    JOptionPane.ERROR_MESSAGE);
		}
	}
	public void interfaceCliente(Cliente cli) {
		boolean cadastro = cli.getNome().equals("");
		if(lista.size() != 0) {
			lastID = lista.get(lista.size() - 1).getId();
		}else {
			lastID = 0;
		}
		
		lastID = Crud.listarCliente().size();
		setTitle("Formulario Cliente");
	    setSize(500, 600);
	    JPanel panelSouth = new JPanel();
	    JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);
        
        JLabel lblId = new JLabel("ID do Cliente: " + (cadastro ? (lastID + 1) : cli.getId()));
        JLabel lblNome = new JLabel("Nome do Cliente: ");
        JTextField txtNome = new JTextField(20);
        txtNome.setText(cli.getNome());
        JLabel lblRg = new JLabel("RG do Cliente: ");
        JTextField txtRg = new JTextField(20);
        txtRg.setText(cli.getRg());
        JLabel lblFidelidade = new JLabel("Cartão Fidelidade do Cliente: ");
        JTextField txtFidelidade = new JTextField(20);
        txtFidelidade.setText(cli.getCartaoFidelidade() + "");
        JButton btnCadastrar = new JButton((cadastro ? "Cadastrar" : "Editar"));
        
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
            	try {
            		int fidelidade = TextNumber.textInteger(txtFidelidade.getText());
                	if(fidelidade!=-1) {
                		
                    	cli.setNome(txtNome.getText());
                    	cli.setCartaoFidelidade(fidelidade);
                    	cli.setRg(txtRg.getText());
                		if(cadastro == true) {
                        	Crud.salvar(cli);
                        	lastID++;
                		}else {
                			Crud.atualizar(cli);
                		}
                    	lblId.setText("ID do Cliente: " + lastID);
                    	txtNome.setText("");
                    	txtRg.setText("");
                    	txtFidelidade.setText("");
                    	JOptionPane.showMessageDialog(new JFrame(), "operação realizada com sucesso");
                	}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(new JFrame(),
	    				    e2.getMessage(),
	    				    "Erro",
	    				    JOptionPane.WARNING_MESSAGE);
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
