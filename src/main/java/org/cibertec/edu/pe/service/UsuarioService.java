package org.cibertec.edu.pe.service;

import org.cibertec.edu.pe.dto.UsuarioRegistroDTO;
import org.cibertec.edu.pe.model.Usuario;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UsuarioService extends UserDetailsService {
    public Usuario guardar(UsuarioRegistroDTO registroDTO);

    public Usuario actualizaCliente(UsuarioRegistroDTO registroDTO);

    public List<Usuario> listarUsuarios();
}
