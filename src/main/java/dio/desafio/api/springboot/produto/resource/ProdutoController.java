package dio.desafio.api.springboot.produto.resource;

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

import dio.desafio.api.springboot.produto.bean.ProdutoBean;
import dio.desafio.api.springboot.produto.jpa.ProdutoDao;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoController {
	
	@Autowired
	ProdutoDao produtoDao;
	
	@GetMapping
	public List<ProdutoBean> listar() {
		return produtoDao.listar();
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<ProdutoBean> selecionar(@PathVariable("codigo") Integer id) {
		ProdutoBean produtoBean = produtoDao.selecionar(id);
		
		if (produtoBean != null) {
			return ResponseEntity.ok(produtoBean);
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ProdutoBean inserir(@RequestBody ProdutoBean produtoBean) {
		return produtoDao.inserir(produtoBean);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ProdutoBean> excluir(@PathVariable("id") Integer codigo) {
		ProdutoBean produtoBean = produtoDao.selecionar(codigo);

		if(produtoBean != null) {
			produtoDao.excluir(produtoBean);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("{codigoproduto}")
	public ResponseEntity<ProdutoBean> alterar(@PathVariable("codigoproduto") Integer codigo, @RequestBody ProdutoBean produtoBean) {
		ProdutoBean produtoAtual = produtoDao.selecionar(codigo);
		
		if(produtoAtual != null) {
			BeanUtils.copyProperties(produtoBean, produtoAtual, "codigo");
			produtoDao.alterar(produtoAtual);
			return ResponseEntity.ok(produtoAtual);
		}
		return ResponseEntity.notFound().build();
	}
	
}
