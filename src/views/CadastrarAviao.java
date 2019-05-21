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

import models.Aviao;
import utils.Crud;
import utils.MainIcon;
import utils.TextNumber;

class CadastrarAviao extends JFrame {

	private static final long serialVersionUID = -5649863518388712101L;
	private List<Aviao> lista = Crud.listarAviao();
	private int lastID;
	public CadastrarAviao() {
		
		if(lista.size() != 0) {
			lastID = lista.get(lista.size() - 1).getId();
		}else {
			lastID = 0;
		}
		
		setTitle("Cadastrar Aviao");
	    setSize(400, 600);
	    
	    JPanel panel = new JPanel();
	    JPanel panelSouth = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);
        
	    JLabel lblId = new JLabel("ID do Aviao: " + (lastID + 1));
	    JLabel lblModelo = new JLabel("Modelo: ");
	    JTextField txtModelo = new JTextField(20);
	    JLabel lblCapacidade = new JLabel("Capacidade: ");
	    JTextField txtCapacidade = new JTextField(20);
	    JButton btnCadastro = new JButton("Cadastrar");
	    
	    constraints.gridx = 0;
	    constraints.gridy = 0;
	    panel.add(lblId, constraints);
	    
        constraints.gridx = 0;
        constraints.gridy = 1;     
        panel.add(lblModelo, constraints);
        constraints.gridx = 1;
        panel.add(txtModelo, constraints);
        
        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(lblCapacidade, constraints);
        constraints.gridx = 1;
        panel.add(txtCapacidade, constraints);
        
        constraints.gridy = 3;
        panel.add(btnCadastro, constraints);
        
        btnCadastro.addActionListener(e->{
			int capacidade = TextNumber.textInteger(txtCapacidade.getText());
			if (capacidade > 0 && txtModelo.getText() != "") {
				try {
					Aviao a = new Aviao();
					a.setCapacidade(capacidade);
					a.setModelo(txtModelo.getText());
					Crud.salvar(a);
					JOptionPane.showMessageDialog(new JFrame(), "Aviao cadastrado");
					lastID++;
					lblId.setText("ID do Aviao: " + lastID);
					txtModelo.setText("");
					txtCapacidade.setText("");
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(new JFrame(), "Erro ao Cadastrar Aviao", "Aviso",
							JOptionPane.WARNING_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(new JFrame(), "Campos invalidos", "Aviso", JOptionPane.WARNING_MESSAGE);
			}
        });
        
	    add(panel);
	    setLocationRelativeTo(null);
	    getContentPane().add(BorderLayout.SOUTH, panelSouth);
	    MainIcon m = new MainIcon(this,"/icon.png");
	    setVisible(true);
	}
}
