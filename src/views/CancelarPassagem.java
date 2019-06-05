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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import models.Passagem;
import models.Voo;
import utils.CboPopulate;
import utils.Crud;
import utils.MainIcon;
import utils.TextNumber;

public class CancelarPassagem extends JFrame {

	private static final long serialVersionUID = 7934877063759132055L;
	private List<Passagem> passagens = Crud.listarPassagem();
	private List<Voo> voos = Crud.listarVoo();
	public CancelarPassagem() {
		
		setTitle("Cancelar Passagem");
		setSize(800 , 600);
		
		JPanel panel = new JPanel();
	    JPanel panelSouth = new JPanel();
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);
		
		CboPopulate p = new CboPopulate();
		JLabel lblVoo = new JLabel("Escolha o Voo: ");
		JComboBox<String> cboVoo = p.populateVoo(voos);
		JLabel lblAssento = new JLabel("Escolha a Passagem: ");
		JTextField txtAssento = new JTextField(20);
		JButton btnBuscar = new JButton("Buscar");
		JButton btnExcluir = new JButton("Excluir");
		String[] colunaAssento = {"ID/Assento ", "Cliente ID", "Nome do Cliente"};
        DefaultTableModel tmAssento = new DefaultTableModel(colunaAssento, 0);
        JTable tabAssento = new JTable(tmAssento);
        JScrollPane barAssento = new JScrollPane(tabAssento);
    	txtAssento.setEnabled(false);
    	btnExcluir.setEnabled(false);
    	
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(lblVoo, constraints);
        constraints.gridx = 1;
        add(cboVoo, constraints);
        constraints.gridx = 2;
        add(btnBuscar, constraints);
        constraints.gridx = 0;
        constraints.gridy = 1;
        add(lblAssento, constraints);
        constraints.gridx = 1;
        add(txtAssento, constraints);
        constraints.gridx = 2;
        add(btnExcluir, constraints);
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 3;
        add(barAssento, constraints);
        
        btnBuscar.addActionListener(e->{
        	if(cboVoo.getSelectedItem() != null) {
        		while(tmAssento.getRowCount() > 0) {
        			tmAssento.removeRow(0);
    			}
        		//add passagens na tabela
        		for(Passagem passagem : passagens) {
        			if(passagem.getVoo().getId() == voos.get(cboVoo.getSelectedIndex()).getId()) {
	        			String[] linha = { passagem.getId() + "", passagem.getCliente().getId() + "", passagem.getCliente().getNome() };
	        			tmAssento.addRow(linha);
        			}
        		}
        		cboVoo.setEnabled(false);
        		txtAssento.setEnabled(true);
        		btnExcluir.setEnabled(true);
        	}else {
        		JOptionPane.showMessageDialog(new JFrame(),
    				    "Nao foi possivel encontrar o voo",
    				    "Aviso",
    				    JOptionPane.WARNING_MESSAGE);
        	}
        });
        btnExcluir.addActionListener(e->{
        	if(cboVoo.getSelectedItem() != null && !txtAssento.getText().isEmpty()) {
        		try {
        			for(Passagem passagem : passagens) {
        				if(passagem.getId() == TextNumber.textDouble(txtAssento.getText()) && 
        						passagem.getVoo().getId() == voos.get(cboVoo.getSelectedIndex()).getId()) {
        					Crud.excluir(passagem);
        					JOptionPane.showMessageDialog(new JFrame(), "Passagem excluida com sucesso");
        				}
        			}
        			//limpa a tabela
            		while(tmAssento.getRowCount() > 0) {
            			tmAssento.removeRow(0);
        			}
        			//add as coisas de novo na tabela
            		for(Passagem passagem : passagens) {
            			if(passagem.getVoo().getId() == voos.get(cboVoo.getSelectedIndex()).getId()) {
    	        			String[] linha = { passagem.getId() + "", passagem.getCliente().getId() + "", passagem.getCliente().getNome() };
    	        			tmAssento.addRow(linha);
            			}
            		}
            		cboVoo.setEnabled(true);
            		txtAssento.setEnabled(false);
            		btnExcluir.setEnabled(false);
        		}catch (Exception ex) {
            		JOptionPane.showMessageDialog(new JFrame(),
        				    "Erro ao Cancelar a Passagem: " + ex.getMessage(),
        				    "Erro",
        				    JOptionPane.WARNING_MESSAGE);
				}
        	}else {
        		JOptionPane.showMessageDialog(new JFrame(),
    				    "Nao foi possivel Cancelar a Passagem",
    				    "Aviso",
    				    JOptionPane.WARNING_MESSAGE);
        	}
        });
		MainIcon m = new MainIcon(this, "/icon.png");
        setVisible(true);
        setLocationRelativeTo(null);
	}
}
