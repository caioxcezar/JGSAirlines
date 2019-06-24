package models;

import java.sql.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "voo")
public class Voo {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private	int id;
	@OneToOne
	@JoinColumn(name="aviao", nullable = false)
	private Aviao aviao;
	@OneToOne
	@OnDelete( action = OnDeleteAction.CASCADE )
	@JoinColumn(name = "piloto", nullable = false)
	private	Funcionario piloto;
	@OneToOne
	@OnDelete( action = OnDeleteAction.CASCADE )
	@JoinColumn(name = "copiloto", nullable = false)
	private	Funcionario copiloto;
	@Column(name = "preco_passagem", nullable = false)
	private double precoPassagem;
	@Column(name = "origem", nullable = false)
	private	String origem;
	@Column(name = "destino", nullable = false)
	private	String  destino;
	@Column(name="data", nullable = false)
	private Date data;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "voo")
	@OrderColumn(name = "id")
	private Set<Passagem> passagens;
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
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
	public Set<Passagem> getPassagens() {
		return passagens;
	}
	public void setPassagens(Set<Passagem> passagens) {
		this.passagens = passagens;
	}
}
