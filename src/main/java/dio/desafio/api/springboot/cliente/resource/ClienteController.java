package dio.desafio.api.springboot.cliente.resource;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import dio.desafio.api.springboot.cliente.bean.ClienteBean;
import dio.desafio.api.springboot.cliente.jpa.ClienteDao;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {
	
	@Autowired
	ClienteDao clienteDao;
	
	@GetMapping
	public List<ClienteBean> listar() {
		return clienteDao.listar();
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<ClienteBean> selecionar(@PathVariable("codigo") Integer id) {
		ClienteBean clienteBean = clienteDao.selecionar(id);
		
		if (clienteBean != null) {
			return ResponseEntity.ok(clienteBean);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ClienteBean inserir(@RequestBody ClienteBean clienteBean) {
		return clienteDao.inserir(clienteBean);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ClienteBean> excluir(@PathVariable("id") Integer codigo) {
		ClienteBean clienteBean = clienteDao.selecionar(codigo);
		
		if (clienteBean != null) {
			clienteDao.excluir(clienteBean);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{codigocliente}")
	public ResponseEntity<ClienteBean> alterar(@PathVariable("codigocliente") Integer codigo, 
			@RequestBody ClienteBean clienteBean) {
		ClienteBean clienteAtual = clienteDao.selecionar(codigo);
		
		if(clienteAtual != null) {
			BeanUtils.copyProperties(clienteBean, clienteAtual, "codigo");
			clienteDao.alterar(clienteAtual);
			return ResponseEntity.ok(clienteAtual);
		}
		return ResponseEntity.notFound().build();
	}
	
}
