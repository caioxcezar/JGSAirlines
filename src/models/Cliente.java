package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name = "cliente")
public class Cliente extends PessoaFisica {
	@Column(name = "cartao_fidelidade")
	private int cartaoFidelidade;
	@ManyToOne
	@JoinColumn(name = "passagem")
	private Passagem[] passagens;
	public int getCartaoFidelidade() {
		return cartaoFidelidade;
	}
	public void setCartaoFidelidade(int cartaoFidelidade) {
		this.cartaoFidelidade = cartaoFidelidade;
	}
	public Passagem[] getPassagens() {
		return passagens;
	}
	public void setPassagens(Passagem[] passagens) {
		this.passagens = passagens;
	}
}