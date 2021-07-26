package com.adrian.rosasal.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.adrian.rosasal.model.Usuario;
import com.adrian.rosasal.repository.UsuariosRepository;

@Service
public class UsuariosServiceJpa implements IntUsuariosService {
    
    @Autowired
    private UsuariosRepository repoUsuarios;


    @Override
    public List<Usuario> obtenerTodas() {
        // TODO Auto-generated method stub
        return repoUsuarios.findAll();
    }

 

    @Override
    public void guardar(Usuario usuario) {
        // TODO Auto-generated method stub
        repoUsuarios.save(usuario);

 

    }

 

    @Override
    public void eliminar(Integer idUsuario) {
        // TODO Auto-generated method stub
        repoUsuarios.deleteById(idUsuario);

 

    }

 

    @Override
    public Usuario buscarPorId(Integer idUsuario) {
        // TODO Auto-generated method stub
        
        Optional<Usuario> optional = repoUsuarios.findById(idUsuario);
        if(optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

 

    @Override
    public Page<Usuario> buscarTodas(Pageable page) {
        // TODO Auto-generated method stub
        return repoUsuarios.findAll(page);
    }

 

    @Override
    public Integer numeroEntidades() {
        // TODO Auto-generated method stub
        //return repoUsuarios.findAll().size();
        return repoUsuarios.totalEntidades();
    }

 

}
