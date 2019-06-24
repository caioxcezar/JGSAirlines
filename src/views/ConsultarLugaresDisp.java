package views;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import models.Passagem;
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
        	List<Passagem> passagens = v.getPassagens().stream().collect(Collectors.toList());
        	for(int i = 0; i < passagens.size(); i++) {
        		if(passagens.get(i) == null) {
        			passagens.remove(i);
        		}
        	}
        	String[] linha = {
	        	v.getId() + "",
	        	"R$ " + v.getPrecoPassagem(),
	        	passagens.size() + "",
	        	(passagens.size() > v.getAviao().getCapacidade() ? "Sim" : "Não"),
	        	(v.getAviao().getCapacidade() - passagens.size()) + ""
        	};
        	tmLugares.addRow(linha);
        }
		
    	add(barLugares);
    	
		MainIcon m = new MainIcon(this, "/icon.png");
        setVisible(true);
        setLocationRelativeTo(null);
	}
}
