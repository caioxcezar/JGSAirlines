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
import models.Cliente;
import models.Passagem;
import models.Voo;
import utils.CboPopulate;
import utils.Crud;
import utils.TextNumber;

class VenderPassagem extends JFrame {

	private static final long serialVersionUID = -8290855046964155546L;
	private List<Cliente> clientes = Crud.listarCliente();
	private List<Voo> voos = Crud.listarVoo();

	public VenderPassagem() {
		CboPopulate p = new CboPopulate();

		setTitle("Vender Passagem");
		setSize(400, 600);

		JPanel panel = new JPanel();
		JPanel panelSouth = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(10, 10, 10, 10);

		JLabel lblAssento = new JLabel("ID do Assento");
		JTextField txtAssento = new JTextField(20);
		JLabel lblCliente = new JLabel("Cliente");
		JComboBox<String> cboCliente = p.populateCliente(clientes);
		JLabel lblVoo = new JLabel("Voo");
		JButton btnCadastrar = new JButton("Cadastrar");
		JComboBox<String> cboVoo = p.populateVoo(voos);

		constraints.gridx = 0;
		constraints.gridy = 0;
		panel.add(lblVoo, constraints);
		constraints.gridx = 1;
		panel.add(cboVoo, constraints);
		constraints.gridx = 0;
		constraints.gridy = 1;
		panel.add(lblCliente, constraints);
		constraints.gridx = 1;
		panel.add(cboCliente, constraints);
		constraints.gridx = 0;
		constraints.gridy = 2;
		panel.add(lblAssento, constraints);
		constraints.gridx = 1;
		panel.add(txtAssento, constraints);
		panelSouth.add(btnCadastrar);

		add(panel);
		setLocationRelativeTo(null);
		getContentPane().add(BorderLayout.SOUTH, panelSouth);
		setVisible(true);

		btnCadastrar.addActionListener(e -> {
			if (!txtAssento.getText().isEmpty() && cboCliente.getSelectedItem() != null
					&& cboVoo.getSelectedItem() != null) {
				int assento = TextNumber.textInteger(txtAssento.getText());
				Aviao av = voos.get(cboVoo.getSelectedIndex()).getAviao();
				if (assento >= 0 && assento < av.getCapacidade()) {
					this.setEnabled(false);
					Passagem a = new Passagem();
					a.setAssento(assento);
					a.setCliente(clientes.get(cboCliente.getSelectedIndex()));
					a.setVoo(voos.get(cboVoo.getSelectedIndex()));
					Crud.salvar(a);
					JOptionPane.showMessageDialog(new JFrame(), "Passagem Vendida");
					txtAssento.setText("");
					cboCliente.setSelectedIndex(0);
					cboVoo.setSelectedIndex(0);
				} else {
					JOptionPane.showMessageDialog(new JFrame(), "Assento Invalido", "Aviso",
							JOptionPane.WARNING_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(new JFrame(), "Erro ao Vender a Passagem", "Aviso",
						JOptionPane.WARNING_MESSAGE);
			}
		});
	}
}
