package dio.desafio.api.springboot.produto.jpa;

import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import dio.desafio.api.springboot.produto.bean.ProdutoBean;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Component
public class ProdutoDao {
	
	@PersistenceContext
	EntityManager manager;
	
	public List<ProdutoBean> listar(){
		return manager.createQuery("from ProdutoBean", ProdutoBean.class).getResultList();
	}
	
	public ProdutoBean selecionar(Integer codigo) {
		return manager.find(ProdutoBean.class, codigo);
	}
	
	@Transactional
	public ProdutoBean inserir(ProdutoBean produtoBean) {
		return manager.merge(produtoBean);
	}
	
	@Transactional
	public void excluir(ProdutoBean produtoBean) {
		manager.remove(produtoBean);
	}
	
	@Transactional
	public ProdutoBean alterar(ProdutoBean produtoBean) {
		ProdutoBean p1 = selecionar(produtoBean.getCodigo());
		p1.setNome(produtoBean.getNome());
		return manager.merge(p1);
	}
}
