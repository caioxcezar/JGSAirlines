package models;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
@Entity
@Table(name = "cliente")
public class Cliente extends PessoaFisica {
	@Column(name = "cartao_fidelidade")
	private int cartaoFidelidade;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "cliente")
	@OnDelete( action = OnDeleteAction.CASCADE )
	@OrderColumn(name = "id")
	private Set<Passagem> passagens;
	public int getCartaoFidelidade() {
		return cartaoFidelidade;
	}
	public void setCartaoFidelidade(int cartaoFidelidade) {
		this.cartaoFidelidade = cartaoFidelidade;
	}
}