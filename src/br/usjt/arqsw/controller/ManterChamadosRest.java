package br.usjt.arqsw.controller;

import java.io.IOException;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.usjt.arqsw.entity.Chamado;
import br.usjt.arqsw.entity.Fila;
import br.usjt.arqsw.service.ChamadoService;
import br.usjt.arqsw.service.FilaService;

/**
 * @author Caue Sampaio
 * RA: 816154566
 */

@RestController
@Transactional
public class ManterChamadosRest {
	private FilaService filaService;
	private ChamadoService chamadoService;

	@Autowired
	public ManterChamadosRest(FilaService filaService, ChamadoService chamadoService) {
		this.filaService = filaService;
		this.chamadoService = chamadoService;
	}

	private List<Fila> listarFilas() throws IOException {
		return filaService.listarFilas();
	}

	/**
	 * 
	 * @param model
	 *            Acesso à request http
	 * @return JSP de Listar Chamados
	 */
	@RequestMapping(method = RequestMethod.GET, value = "rest/filas")
	public @ResponseBody List<Fila> listarFilasExibir(Model model) {
		List<Fila> filas = null;
		try {
			filas =  listarFilas();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return filas;
	}

	@RequestMapping(method = RequestMethod.GET, value = "rest/chamados/{id}")
	public @ResponseBody List<Chamado> listarChamadosExibir(@PathVariable("id") int id) {
		List<Chamado> chamados = null;
		
		try {
			Fila fila = filaService.carregar(id);			

			chamados = chamadoService.listarChamados(fila);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return chamados;
	}

	@Transactional
	@RequestMapping(method = RequestMethod.POST, value = "rest/chamados")
	public ResponseEntity<Chamado> criarChamado(@RequestBody Chamado chamado) {
		try {
			chamadoService.criarChamado(chamado);
			return new ResponseEntity<Chamado>(chamado, HttpStatus.OK);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<Chamado>(chamado, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
