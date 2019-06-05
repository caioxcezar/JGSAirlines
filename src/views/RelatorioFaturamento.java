package views;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        	ArrayList<Passagem> passagens = new ArrayList<Passagem>();
        	for(int i = 0; i < v.getPassagens().length; i++) {
        		if(v.getPassagens()[i] != null) {
        			passagens.add(v.getPassagens()[i]);
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
