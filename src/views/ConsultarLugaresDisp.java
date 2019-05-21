package views;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import models.Voo;
import utils.Crud;
import utils.MainIcon;

public class ConsultarLugaresDisp extends JFrame {

	private static final long serialVersionUID = 8012001114392287132L;
	private List<Voo> voos = Crud.listarVoo();
	public ConsultarLugaresDisp() {
		setTitle("Atualizar Informacoes do Lugares");
		setSize(800 , 600);
		
        String[] colunaLugares = {"ID Voo", "Valor da Passagem", "Lugares Ocupados", "Overbooking?", "Lugares Disponiveis"};
        DefaultTableModel tmLugares = new DefaultTableModel(colunaLugares, 0);
        JTable tabLugares = new JTable(tmLugares);
        JScrollPane barLugares = new JScrollPane(tabLugares);
        for(Voo v : voos) {
        	String[] linha = {
	        	v.getId() + "",
	        	"R$ " + v.getPrecoPassagem(),
	        	v.getPassagens().length + "",
	        	(v.getPassagens().length > v.getAviao().getCapacidade() ? "Sim" : "Não"),
	        	(v.getAviao().getCapacidade() - v.getPassagens().length) + ""
        	};
        	tmLugares.addRow(linha);
        }
		
    	add(barLugares);
    	
		MainIcon m = new MainIcon(this, "/icon.png");
        setVisible(true);
        setLocationRelativeTo(null);
	}
}
