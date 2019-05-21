package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

@Entity
@Table(name = "voo")
public class Voo {
	@Id
	@GeneratedValue
	@Column(name = "id")
	private	int id;
	@OneToOne
	@JoinColumn(name="aviao")
	private Aviao aviao;
	@OneToOne
	@JoinColumn(name = "piloto")
	private	Funcionario piloto;
	@OneToOne
	@JoinColumn(name = "copiloto")
	private	Funcionario copiloto;
	@Column(name = "preco_passagem")
	private double precoPassagem;
	@Column(name = "origem")
	private	String origem;
	@Column(name = "destino")
	private	String  destino;
	@OneToMany
	@JoinColumn(name= "passagem")
	@OrderColumn(name="id")
	private Passagem[] passagens;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Aviao getAviao() {
		return aviao;
	}
	public void setAviao(Aviao aviao) {
		this.aviao = aviao;
	}
	public Funcionario getPiloto() {
		return piloto;
	}
	public void setPiloto(Funcionario piloto) {
		this.piloto = piloto;
	}
	public Funcionario getCopiloto() {
		return copiloto;
	}
	public void setCopiloto(Funcionario copiloto) {
		this.copiloto = copiloto;
	}
	public double getPrecoPassagem() {
		return precoPassagem;
	}
	public void setPrecoPassagem(double precoPassagem) {
		this.precoPassagem = precoPassagem;
	}
	public String getOrigem() {
		return origem;
	}
	public void setOrigem(String origem) {
		this.origem = origem;
	}
	public String getDestino() {
		return destino;
	}
	public void setDestino(String destino) {
		this.destino = destino;
	}
	public Passagem[] getPassagens() {
		return passagens;
	}
	public void setPassagens(Passagem[] passagens) {
		this.passagens = passagens;
	}
}
