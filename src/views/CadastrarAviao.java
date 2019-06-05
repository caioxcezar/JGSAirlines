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
		Aviao av = new Aviao();
		av.setCapacidade(0);
		av.setModelo("");
		InterfaceAviao(av);
	}
	public CadastrarAviao(int id) {
		try {
			Aviao av = (Aviao) Crud.buscar(id, Aviao.class);
			InterfaceAviao(av);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public void InterfaceAviao(Aviao av) {
		boolean cadastro = (av.getCapacidade() == 0 ? true : false);
		if(lista.size() != 0) {
			lastID = lista.get(lista.size() - 1).getId();
		}else {
			lastID = 0;
		}
		
		setTitle("Formulario Avião");
	    setSize(400, 600);
	    
	    JPanel panel = new JPanel();
	    JPanel panelSouth = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);
        
	    JLabel lblId = new JLabel("ID do Aviao: " + (cadastro ? (lastID + 1) : av.getId()));
	    JLabel lblModelo = new JLabel("Modelo: ");
	    JTextField txtModelo = new JTextField(20);
	    txtModelo.setText(av.getModelo());
	    JLabel lblCapacidade = new JLabel("Capacidade: ");
	    JTextField txtCapacidade = new JTextField(20);
	    txtCapacidade.setText(av.getCapacidade() + "");
	    JButton btnCadastro = new JButton((cadastro ? "Cadastrar" : "Editar"));
	    
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
					av.setCapacidade(capacidade);
					av.setModelo(txtModelo.getText());
					if (cadastro) {
						Crud.salvar(av);
						lastID++;
					}else {
						Crud.atualizar(av);
					}
					
					JOptionPane.showMessageDialog(new JFrame(), "Aviao cadastrado");
					
					lblId.setText("ID do Aviao: " + lastID);
					txtModelo.setText("");
					txtCapacidade.setText("");
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(new JFrame(), ex.getMessage(), "Aviso",
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
