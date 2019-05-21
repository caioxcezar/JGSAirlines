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

import models.Aviao;
import models.Funcionario;
import models.Voo;
import utils.CboPopulate;
import utils.Crud;
import utils.MainIcon;
import utils.TextNumber;

public class CadastrarVoo extends JFrame {

	private static final long serialVersionUID = -7505245210918255090L;
	private List<Voo> voos = Crud.listarVoo();
	private int lastID;
	private List<Funcionario> funcionarios = Crud.listarFuncionario();
	private List<Aviao> avioes = Crud.listarAviao();
	public CadastrarVoo() {
		
		if(voos.size() != 0) {
			lastID = voos.get(voos.size() - 1).getId();
		}else {
			lastID = 0;
		}
		
		CboPopulate p = new CboPopulate();
		setTitle("Cadastrar Voo");
	    setSize(400, 600);
	    
	    JPanel panel = new JPanel();
	    JPanel panelSouth = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);
        
	    JLabel lblId = new JLabel("ID do Voo: " + (lastID + 1));
	    JLabel lblPreco = new JLabel("Preco: ");
	    JTextField textPreco = new JTextField(20);
	    JLabel lblOrigem = new JLabel("Origem: ");
	    JTextField textOrigem = new JTextField(20);
	    JLabel lblDestino = new JLabel("Destino: ");
	    JTextField textDestino = new JTextField(20);
	    JButton btnCadastro = new JButton("Cadastrar");
	    JLabel lblPiloto = new JLabel("Piloto: ");
		JComboBox<String> cboPiloto = p.populatePiloto(funcionarios);
	    JLabel lblCopiloto = new JLabel("Coiloto: ");
	    JComboBox<String> cboCopiloto = p.populateCopiloto(funcionarios);
	    JLabel lblAviao = new JLabel("Avião: ");
	    JComboBox<String> cboAviao = p.populateAviao(avioes);
	    JButton btnFunc = new JButton("Add Funcionario");
	    JButton btnAviao = new JButton("Add Avião");
	    
        constraints.gridx = 0;
        constraints.gridy = 0;     
        panel.add(lblId, constraints);
        
        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(lblAviao, constraints);
        constraints.gridx = 1;
        panel.add(cboAviao, constraints);
        
        constraints.gridx = 0;
        constraints.gridy = 2;     
        panel.add(lblPiloto, constraints);
        constraints.gridx = 1;
        panel.add(cboPiloto, constraints);
        
        constraints.gridx = 0;
        constraints.gridy = 3;
        panel.add(lblCopiloto, constraints);
        constraints.gridx = 1;
        panel.add(cboCopiloto, constraints);
        
        constraints.gridx = 0;
        constraints.gridy = 4;     
        panel.add(lblPreco, constraints);
        constraints.gridx = 1;
        panel.add(textPreco, constraints);
        
        constraints.gridx = 0;
        constraints.gridy = 5;     
        panel.add(lblOrigem, constraints);
        constraints.gridx = 1;
        panel.add(textOrigem, constraints);
        
        constraints.gridx = 0;
        constraints.gridy = 6;     
        panel.add(lblDestino, constraints);
        constraints.gridx = 1;
        panel.add(textDestino, constraints);

        panelSouth.add(btnCadastro);
        panelSouth.add(btnAviao);
        panelSouth.add(btnFunc);
        
	    add(panel);
	    getContentPane().add(BorderLayout.SOUTH, panelSouth);
	    setLocationRelativeTo(null);
	    MainIcon m = new MainIcon(this,"/icon.png");
		setVisible(true);
		
		btnAviao.addActionListener(e->{
			CadastrarAviao a = new CadastrarAviao();
			dispose();
		});

		btnFunc.addActionListener(e -> {
			CadastrarFuncionario cadFunc = new CadastrarFuncionario();
			dispose();
        });
		
		btnCadastro.addActionListener(e->{
			if(!textDestino.getText().isEmpty() &&
				!textOrigem.getText().isEmpty() &&
				!textPreco.getText().isEmpty() &&
				cboAviao.getSelectedItem() != null &&
				cboCopiloto.getSelectedItem() != null &&
				cboPiloto.getSelectedItem() != null) {
				
				double preco = TextNumber.textDouble(textPreco.getText());
				int piloto = p.idCbo((String)cboPiloto.getSelectedItem());
				int copiloto = p.idCbo((String)cboCopiloto.getSelectedItem());
				if(preco != -1) {
					Voo v = new Voo();
					Aviao a = avioes.get(cboAviao.getSelectedIndex());
					v.setAviao(a);
					for(Funcionario f : funcionarios) {
						if(piloto == f.getId()) {
							v.setPiloto(f);
						}else if(copiloto == f.getId()) {
							v.setCopiloto(f);
						}
					}
					v.setDestino(textDestino.getText());
					v.setOrigem(textOrigem.getText());
					v.setPrecoPassagem(preco);
					Crud.salvar(v);
					lastID++;
					lblId.setText("ID do Voo: " + lastID);
					textDestino.setText("");
					textOrigem.setText("");
					textPreco.setText("");
					cboAviao.setSelectedIndex(0);
					cboCopiloto.setSelectedIndex(0);
					cboPiloto.setSelectedIndex(0);
					JOptionPane.showMessageDialog(new JFrame(), "Voo cadastrado");
				}
			}else {
        		JOptionPane.showMessageDialog(new JFrame(),
    				    "Erro ao Cadastrar Voo",
    				    "Aviso",
    				    JOptionPane.WARNING_MESSAGE);
			}

		});
	}

}
