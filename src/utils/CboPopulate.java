package utils;
import java.util.List;

import javax.swing.JComboBox;

import models.Aviao;
import models.Cliente;
import models.Funcionario;
import models.Voo;

public class CboPopulate {
	public JComboBox<String> populateVoo(List<Voo> data){
		JComboBox<String> cboReturn = new JComboBox<String>();
		for(Voo v : data) {
			cboReturn.addItem(v.getId()+": "+ v.getOrigem()+" para "+ v.getDestino());
		}
		return cboReturn;
	}
	public JComboBox<String> populateCliente(List<Cliente> data){
		JComboBox<String> cboReturn = new JComboBox<String>();
		for(Cliente c : data ) {
			cboReturn.addItem(c.getId()+": "+ c.getNome());
		}
		return cboReturn;
	}
	public JComboBox<String> populateFunc(List<Funcionario> data){
		JComboBox<String> cboReturn = new JComboBox<String>();
		for(Funcionario f : data) {
			cboReturn.addItem(f.getId()+": "+ f.getNome());
		}
		return cboReturn;
	}
	public JComboBox<String> populatePiloto(List<Funcionario> funcionarios){
		JComboBox<String> cboReturn = new JComboBox<String>();
		for(Funcionario f : funcionarios) {
			if(f.getCargoFuncionario().equals("Piloto"))
				cboReturn.addItem(f.getId() + ": " + f.getNome());
		}
		return cboReturn;
	}
	public JComboBox<String> populateCopiloto(List<Funcionario> data){
		JComboBox<String> cboReturn = new JComboBox<String>();
		for(Funcionario f : data) {
			if(f.getCargoFuncionario().equals("Copiloto"))
				cboReturn.addItem(f.getId() + ": " + f.getNome());
		}
		return cboReturn;
	}
	public JComboBox<String> populateAviao(List<Aviao> avioes){
		JComboBox<String> cboReturn = new JComboBox<String>();
		for(Aviao a : avioes) {
			cboReturn.addItem(a.getModelo());
		}
		return cboReturn;
	}
	public int idCbo(String str) {
		String s [] = str.split(":");
		str = s[0];
		return Integer.parseInt(str);
	}
}
