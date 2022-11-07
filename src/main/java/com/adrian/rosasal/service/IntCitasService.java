package com.adrian.rosasal.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.adrian.rosasal.model.Citas;

public interface IntCitasService {

	public List<Citas> obtenerTodas();
	public void guardar(Citas citas);
	public void eliminar(Integer idCita);
	public Citas buscarPorId(Integer idCita);
	public Page<Citas> buscarTodas(Pageable page);

	    public Integer totalC();

}
