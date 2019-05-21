package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Aviao")
public class Aviao {
	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;
	@Column(name = "modelo")
	private String modelo;
	@Column(name = "capacidade")
	private int capacidade;
	@ManyToOne
	@JoinColumn(name = "voo")
	private Voo[] voo;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public int getCapacidade() {
		return capacidade;
	}
	public void setCapacidade(int capacidade) {
		this.capacidade = capacidade;
	}
	public Voo[] getVoo() {
		return voo;
	}
	public void setVoo(Voo[] voo) {
		this.voo = voo;
	}
	
}