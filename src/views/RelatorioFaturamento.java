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

public class RelatorioFaturamento extends JFrame {

	private static final long serialVersionUID = 5887931571704436159L;
	private List<Voo> voos = Crud.listarVoo();
	public RelatorioFaturamento() {
		setTitle("Atualizar Informacoes do Faturamento");
		setSize(800 , 600);
		
        String[] colunaFaturamento = {"ID Voo", "Valor da Passagem", "Pessoas no Voo", "Overbooking?", "Faturamento"};
        DefaultTableModel tmFaturamento = new DefaultTableModel(colunaFaturamento, 0);
        JTable tabFaturamento = new JTable(tmFaturamento);
        JScrollPane barFaturamento = new JScrollPane(tabFaturamento);
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
	        	(passagens.size() * v.getPrecoPassagem()) + ""
        	};
        	tmFaturamento.addRow(linha);
        }
		
    	add(barFaturamento);
    	
		MainIcon m = new MainIcon(this, "/icon.png");
        setVisible(true);
        setLocationRelativeTo(null);
	}
}
