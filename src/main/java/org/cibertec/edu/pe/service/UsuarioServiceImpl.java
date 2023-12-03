package org.cibertec.edu.pe.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.cibertec.edu.pe.dto.UsuarioRegistroDTO;
import org.cibertec.edu.pe.model.Rol;
import org.cibertec.edu.pe.model.Usuario;
import org.cibertec.edu.pe.repository.IRolRepo;
import org.cibertec.edu.pe.repository.IUsuarioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    @Autowired
    private IUsuarioRepo usuarioRepo;
    
    @Autowired
    private IRolRepo rolRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public UsuarioServiceImpl(IUsuarioRepo usuarioRepositorio) {
        super();
        this.usuarioRepo = usuarioRepositorio;
    }
    
    //Metodo para registrar un usuario nuevo

    @Override
    public Usuario guardar(UsuarioRegistroDTO registroDTO) {
        String tipoUsuario = determinarTipoUsuario(registroDTO.getUsername());

        Rol rol;
        switch (tipoUsuario.toLowerCase()) {
            case "cliente":
                rol = obtenerRolPorNombre("ROLE_CLIENTE");
                break;
            case "admin":
                rol = obtenerRolPorNombre("ROLE_ADMIN");
                break;
            default:
                rol = obtenerRolPorNombre("ROLE_MANTENEDOR");
        }

        Usuario usuario = new Usuario(
                registroDTO.getNombre(),
                registroDTO.getApellidoPat(),
                registroDTO.getApellidoMat(),
                registroDTO.getUsername(),
                passwordEncoder.encode(registroDTO.getPassword()),
                registroDTO.getTelefono(),
                Arrays.asList(rol)
        );

        return usuarioRepo.save(usuario);
    }

    @Override
    public Usuario actualizaCliente(UsuarioRegistroDTO registroDTO) {
        // Recuperar el cliente existente de la base de datos
        Usuario clienteExistente = usuarioRepo.findByUsername(registroDTO.getUsername());

        if (clienteExistente != null) {
            if (registroDTO.getNombre() != null) {
                clienteExistente.setNombre(registroDTO.getNombre());
            }
            if (registroDTO.getApellidoPat() != null) {
                clienteExistente.setApellidoPat(registroDTO.getApellidoPat());
            }
            if (registroDTO.getApellidoMat() != null) {
                clienteExistente.setApellidoMat(registroDTO.getApellidoMat());
            }
            if (registroDTO.getUsername() != null) {
                clienteExistente.setUsername(registroDTO.getUsername());
            }
            if (registroDTO.getTelefono() != null) {
                clienteExistente.setTelefono(registroDTO.getTelefono());
            }
            return usuarioRepo.save(clienteExistente);
        } else {
            // Manejar el caso en el que no se encuentre el cliente
            System.out.println("No se encontró el cliente con el nombre de usuario: " + registroDTO.getUsername());
            return null;
        }
    }

    private Rol obtenerRolPorNombre(String nombreRol) {
        Rol rol = rolRepo.findByNombre(nombreRol);
        if (rol != null) {
            return rol;
        } else {
            throw new IllegalStateException("No se encontró el rol: " + nombreRol);
        }
    }

    
    //Determinar rol
    private String determinarTipoUsuario(String email) {
        String dominio = obtenerDominio(email);

        switch (dominio.toLowerCase()) {
            case "mantenedor.sp.com":
                return "MANTENEDOR";
            case "admin.sp.com":
                return "ADMIN";
            default:
                return "CLIENTE";  // O cualquier otro valor por defecto
        }
    }
    //Obtener Dominio del email
    private String obtenerDominio(String email) {
        // Obtener el dominio del correo
        return email.substring(email.lastIndexOf("@") + 1).toLowerCase();
    }

    
    //Metodo para cargar las vistas por rol
    
    @Override
    public UserDetails loadUserByUsername(String username) throws BadCredentialsException {
        Usuario usuario = usuarioRepo.findByUsername(username);
        if (usuario == null) {
            throw new BadCredentialsException("Usuario o contraseña inválidos");
        }

        String role = getRoleBasedOnEmail(usuario.getUsername());
        String redirectUrl = determineRedirectUrlBasedOnRole(role);

        Collection<? extends GrantedAuthority> authorities = mapearAutoridadesRoles(usuario.getRoles());

        return new User(
            usuario.getUsername(),
            usuario.getPassword(),
            authorities
        );
    }


    private String getRoleBasedOnEmail(String email) {
        if (email.endsWith("@mantenedor.sp.com")) {
            return "ROLE_MANTENEDOR";
        } else if (email.endsWith("@admin.sp.com")) {
            return "ROLE_ADMIN";
        } else {
            return "ROLE_CLIENTE";  // Rol por defecto para otros casos
        }
    }

    private String determineRedirectUrlBasedOnRole(String role) {
        // Lï¿½gica para determinar la URL de redirecciï¿½n segï¿½n el rol
        switch (role) {
            case "ROLE_ADMIN":
                return "/admin";
            case "ROLE_CLIENTE":
                return "/cliente";
            case "ROLE_MANTENEDOR":
                return "/mantenedor";
            default:
                throw new IllegalStateException("Unexpected value: " + role);
        }
    }

    
    //Metodo para listar a los usuarios

    @Override
    public List<Usuario> listarUsuarios() {
        return usuarioRepo.findAll();
    }

    private Collection<? extends GrantedAuthority> mapearAutoridadesRoles(Collection<Rol> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getNombre()))
                .collect(Collectors.toList());
    }

}
