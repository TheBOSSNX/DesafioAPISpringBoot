package dio.desafio.api.springboot.cliente.jpa;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import dio.desafio.api.springboot.cliente.bean.ClienteBean;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Component
public class ClienteDao {

	@PersistenceContext
	EntityManager manager;
	
	public List<ClienteBean> listar(){
		return manager.createQuery("from ClienteBean", ClienteBean.class).getResultList();
	}
	
	public ClienteBean selecionar(Integer codigo) {
		return manager.find(ClienteBean.class, codigo);
	}
	
	@Transactional
	public ClienteBean inserir(ClienteBean clienteBean) {
		return manager.merge(clienteBean);
	}
	
	@Transactional
	public void excluir(ClienteBean clienteBean) {
		manager.remove(clienteBean);
	}
	
	@Transactional
	public ClienteBean alterar(ClienteBean clienteBean) {
		ClienteBean c1 = selecionar(clienteBean.getCodigo());
		c1.setNome(clienteBean.getNome());
		return manager.merge(c1);
	}
}
