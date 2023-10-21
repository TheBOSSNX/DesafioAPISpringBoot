package dio.desafio.api.springboot.produto.bean;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "produto")
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class ProdutoBean {
	
	@Id
	@Column(name = "codigo")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codigo;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "valor")
	private Double valor;
	
}
