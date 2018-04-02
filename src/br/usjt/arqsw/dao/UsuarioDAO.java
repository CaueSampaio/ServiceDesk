package br.usjt.arqsw.dao;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.usjt.arqsw.entity.Usuario;

/**
 * @author Caue Sampaio
 * RA: 816154566
 */

@Repository
public class UsuarioDAO {
	@PersistenceContext
	private EntityManager manager;

	public Usuario validar(Usuario usuario) throws IOException {
		// return manager.find(Usuario.class, usuario.getUsername());
		return (Usuario) manager.createQuery("select usuario from Usuario usuario where usuario.username = ?")
				.setParameter(1, usuario.getUsername()).getSingleResult();
	}
}
