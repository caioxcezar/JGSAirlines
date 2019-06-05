package views;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
		Voo v = new Voo();
		v.setDestino("");
		v.setOrigem("");
		v.setPrecoPassagem(0.0);
		v.setAviao(new Aviao());
		v.setCopiloto(new Funcionario());
		v.setPiloto(new Funcionario());
		InterfaceVoo(v);
	}
	public CadastrarVoo(int id) {
		try {
			Voo v = (Voo)Crud.buscar(id, Voo.class);
			InterfaceVoo(v);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(new JFrame(),
				    "Erro ao buscar o Voo",
				    "Erro",
				    JOptionPane.ERROR_MESSAGE);
		}
		
	}

	public void InterfaceVoo(Voo voo) {
		boolean cadastrar = ( voo.getPrecoPassagem() == 0.0 ? true : false );
		if (voos.size() != 0) {
			lastID = voos.get(voos.size() - 1).getId();
		} else {
			lastID = 0;
		}

		setTitle("Formulario Voo");
		setSize(400, 600);

		JPanel panel = new JPanel();
		JPanel panelSouth = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(10, 10, 10, 10);

		JLabel lblId = new JLabel("ID do Voo: " + (cadastrar ? (lastID + 1) : voo.getId()));
		JLabel lblPreco = new JLabel("Preco: ");
		JTextField textPreco = new JTextField(20);
		textPreco.setText(voo.getPrecoPassagem() + "");
		JLabel lblOrigem = new JLabel("Origem: ");
		JTextField textOrigem = new JTextField(20);
		textOrigem.setText(voo.getOrigem());
		JLabel lblDestino = new JLabel("Destino: ");
		JTextField textDestino = new JTextField(20);
		textDestino.setText(voo.getDestino());
		JLabel lblData = new JLabel("Data: ");
		JTextField textData = new JTextField(20);
		String data = (voo.getData() == null ? "dd-mm-yyyy" : voo.getData().toString());
		textData.setText(data);
		JButton btnCadastro = new JButton(( cadastrar ? "Cadastrar":"Editar" ));
		JLabel lblPiloto = new JLabel("Piloto: ");
		JComboBox<String> cboPiloto = CboPopulate.populatePiloto(funcionarios);
		for(int i = 0; i < cboPiloto.getItemCount(); i++) {
			int id = CboPopulate.idCbo((String)cboPiloto.getItemAt(i));
			if(id == (voo.getPiloto().getId())) {
				cboPiloto.setSelectedIndex(i);
			}
		}
		JLabel lblCopiloto = new JLabel("Coiloto: ");
		JComboBox<String> cboCopiloto = CboPopulate.populateCopiloto(funcionarios);
		for(int i = 0; i < cboCopiloto.getItemCount(); i++) {
			int id = CboPopulate.idCbo((String)cboCopiloto.getItemAt(i));
			if(id == (voo.getCopiloto().getId())) {
				cboCopiloto.setSelectedIndex(i);
			}
		}
		JLabel lblAviao = new JLabel("Avião: ");
		JComboBox<String> cboAviao = CboPopulate.populateAviao(avioes);
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

		constraints.gridx = 0;
		constraints.gridy = 7;
		panel.add(lblData, constraints);
		constraints.gridx = 1;
		panel.add(textData, constraints);
		
		panelSouth.add(btnCadastro);
		panelSouth.add(btnAviao);
		panelSouth.add(btnFunc);

		add(panel);
		getContentPane().add(BorderLayout.SOUTH, panelSouth);
		setLocationRelativeTo(null);
		MainIcon m = new MainIcon(this, "/icon.png");
		setVisible(true);

		btnAviao.addActionListener(e -> {
			CadastrarAviao a = new CadastrarAviao();
			dispose();
		});

		btnFunc.addActionListener(e -> {
			CadastrarFuncionario cadFunc = new CadastrarFuncionario();
			dispose();
		});

		btnCadastro.addActionListener(e -> {
			if (!textDestino.getText().isEmpty() && !textOrigem.getText().isEmpty() && !textPreco.getText().isEmpty()
					&& !textData.getText().isEmpty() && cboAviao.getSelectedItem() != null
					&& cboCopiloto.getSelectedItem() != null && cboPiloto.getSelectedItem() != null) {
				
				try {
					double preco = TextNumber.textDouble(textPreco.getText());
					int piloto = CboPopulate.idCbo((String) cboPiloto.getSelectedItem());
					int copiloto = CboPopulate.idCbo((String) cboCopiloto.getSelectedItem());
					if (preco != -1) {
						Aviao a = avioes.get(cboAviao.getSelectedIndex());
						voo.setAviao(a);

						String startDate = textData.getText();
						SimpleDateFormat sdf1 = new SimpleDateFormat("dd-mm-yyyy");
						java.util.Date date;

						date = sdf1.parse(startDate);

						java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());

						voo.setData(sqlStartDate);
						for (Funcionario f : funcionarios) {
							if (piloto == f.getId()) {
								voo.setPiloto(f);
							} else if (copiloto == f.getId()) {
								voo.setCopiloto(f);
							}
						}
						voo.setDestino(textDestino.getText());
						voo.setOrigem(textOrigem.getText());
						voo.setPrecoPassagem(preco);
						
						if(cadastrar == true) {
							Crud.salvar(voo);
							lastID++;
						}else {
							Crud.atualizar(voo);
						}
						lblId.setText("ID do Voo: " + lastID);
						textDestino.setText("");
						textOrigem.setText("");
						textPreco.setText("");
						textData.setText("dd-mm-yyyy");
						cboAviao.setSelectedIndex(0);
						cboCopiloto.setSelectedIndex(0);
						cboPiloto.setSelectedIndex(0);
						JOptionPane.showMessageDialog(new JFrame(), "Operação realizada com sucesso");
					}
				} catch (ParseException e1) {
					JOptionPane.showMessageDialog(new JFrame(), "Erro na data", "Aviso",
							JOptionPane.WARNING_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(new JFrame(), "Erro ao Cadastrar Voo", "Aviso",
						JOptionPane.WARNING_MESSAGE);
			}

		});
	}

}
