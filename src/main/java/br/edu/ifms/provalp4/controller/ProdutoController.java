package br.edu.ifms.provalp4.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.ifms.provalp4.modelo.Informacoes;
import br.edu.ifms.provalp4.modelo.Produto;
import br.edu.ifms.provalp4.repository.InformacoesRepository;
import br.edu.ifms.provalp4.repository.ProdutoRepository;

@Controller
public class ProdutoController {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private InformacoesRepository informacoesRepository;

	
	//Controle do Produto
	
	@RequestMapping("/")
	public String listarProdutos(Model model) {
		List<Produto> listaProdutos = produtoRepository.findAll();
		model.addAttribute("produtos", listaProdutos);
		return "tabela-produto";
	}

	@GetMapping("/novoProduto")
	public String adicionarProduto(Model model) {
		model.addAttribute("produto", new Produto());
		return "formulario-produto";
	}

	@PostMapping("/salvarProduto")
	public String salvarProduto(@Valid Produto produto, BindingResult result) {
		if (result.hasErrors()) {
			return "formulario-produto";
		}
		produtoRepository.save(produto);
		return "redirect:/";
	}

	

	//Controle das Informacoes Adicionais
	
	@GetMapping("/novasInformacoes/{idProduto}")
	public String adicionarInformacoes(@PathVariable("idProduto") long idProduto, Model model) {

		Optional<Produto> prd = produtoRepository.findById(idProduto);
		Produto produto = prd.get();

		Informacoes informacoes = new Informacoes();
		informacoes.setProduto(produto);

		model.addAttribute("informacoes", informacoes);
		return "formulario-novas-informacoes";
	}
	
	@PostMapping("/salvarInformacoes/{idProduto}")
	public String salvarInformacoes(@PathVariable("idProduto") long idProduto, @Valid Informacoes informacoes,
			BindingResult result) {

		Optional<Produto> prd = produtoRepository.findById(idProduto);
		Produto produto = prd.get();
		informacoes.setProduto(produto);
		if (result.hasErrors()) {
			return "formulario-novas-informacoes";
		}

		String preco = informacoes.getPreco();
		String pref = "R$ ";
		informacoes.setPreco(pref.concat(preco));
		
		informacoesRepository.save(informacoes);
		return "redirect:/";
	}
	
	
	@GetMapping("/alterarInformacoes/{idProduto}")
	public String alterarInformacoes(@PathVariable("idProduto") long idProduto, Model model) {

		Optional<Produto> prd = produtoRepository.findById(idProduto);
		Produto produto = prd.get();

		Informacoes informacoes = new Informacoes();
		informacoes.setProduto(produto);

		model.addAttribute("informacoes", informacoes);
		return "formulario-alterar-informacoes";
	}
	
	@PostMapping("/salvarInformacoesAlteradas/{idProduto}")
	public String salvarInformacoesAlteradas(@PathVariable("idProduto") long idProduto, @Valid Informacoes informacoes,
			BindingResult result) {

		Optional<Produto> prd = produtoRepository.findById(idProduto);
		Produto produto = prd.get();
		informacoes.setProduto(produto);
		
		Optional<Informacoes> info = Optional.of(informacoesRepository.findByProdutoId(idProduto));
		informacoes.setId(info.get().getId());
		if (result.hasErrors()) {
			return "formulario-alterar-informacoes";
		}

		String preco = informacoes.getPreco();
		String pref = "R$ ";
		informacoes.setPreco(pref.concat(preco));
		
		informacoesRepository.save(informacoes);
		return "redirect:/";
	}
}
