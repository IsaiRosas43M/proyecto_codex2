package com.adrian.rosasal.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.adrian.rosasal.model.Citas;
import com.adrian.rosasal.repository.CitasRepository;


@Service
@Primary
public class CitasServicejpa implements IntCitasService {
	private List<Citas> lista;
	@Autowired
	private CitasRepository repoCitas;

	@Override
	public List<Citas> obtenerTodas() {
		lista=repoCitas.findAll();
		return lista;
	}

	@Override
	public void guardar(Citas Citas) {
		repoCitas.save(Citas);
	}

	@Override
	public void eliminar(Integer idCitas) {
		repoCitas.deleteById(idCitas);
	}
	@Override
	public Citas buscarPorId(Integer idCitas) {
		// TODO Auto-generated method stub
		Optional<Citas> optional = repoCitas.findById(idCitas);
		if(optional.isPresent()) {
			return optional.get();
		}
		
		else {
			return null;
		}
	}
	@Override
	public Page<Citas> buscarTodas(Pageable page) {
		// TODO Auto-generated method stub
		return repoCitas.findAll(page);
	}
	

	@Override
	public Integer totalC() {
		// TODO Auto-generated method stub
		return null;
	}
}