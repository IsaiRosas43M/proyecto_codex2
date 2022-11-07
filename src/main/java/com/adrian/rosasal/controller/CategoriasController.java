package com.adrian.rosasal.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.adrian.rosasal.model.Categoria;

import com.adrian.rosasal.service.IntCategoriasService;
import com.adrian.rosasal.util.Utileria;

@Controller
@RequestMapping("/categorias")
public class CategoriasController {
	
	@Value("${empleosapp.ruta.images}")
	private String ruta;
	
	@Autowired
	private IntCategoriasService categoriasService;
	
	@GetMapping("/buscar")
    public String buscar(@RequestParam("id")int idCategoria, Model model) {
        Categoria categoria = categoriasService.buscarPorId(idCategoria);
        System.out.println(categoria);
        model.addAttribute("categoria", categoria);
        return "categorias/formCategoria";
    }
	
	 @GetMapping(value = "/indexPaginado")
	    public String mostrarIndexPaginado(Model model, Pageable page) {
	    Page<Categoria> lista = categoriasService.buscarTodas(page);
	    model.addAttribute("categorias", lista);
	    return "categorias/listaCategorias";
	    }
	
	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable("id") int idCategoria, RedirectAttributes attribute) {
		//System.out.println("IdCategoria = " + idCategoria);
		attribute.addFlashAttribute("msg", "Registro eliminado con exito");
		categoriasService.eliminar(idCategoria);
		return "redirect:/categorias/indexPaginado";
	}
	
	@PostMapping("/guardar")
    //Data Binding (Vincular datos entre una clase)
    public String guardar(@Valid Categoria  categoria, BindingResult resultado, RedirectAttributes atributo, Model model,
    		 @RequestParam("archivoImagen") MultipartFile multiPart ) {

        if(resultado.hasErrors()){
            for(ObjectError error: resultado.getAllErrors()){
                System.out.println("Error :" + error.getDefaultMessage());
            }
            //model.addAttribute("categorias", categoriasService.obtenerTodas());
            return "categorias/formCategoria";
        }
		if (!multiPart.isEmpty()) {
			// String ruta = "/empleos/img-vacantes/"; // Linux/MAC
			//String ruta = "c:/empleos/img-vacantes/"; // Windows
			String nombreImagen = Utileria.guardarArchivo(multiPart, ruta);
			if (nombreImagen != null) { // La imagen si se subio
				// Procesamos la variable nombreImagen
				categoria.setImagen(nombreImagen);
			}
		}
        
        System.out.println(categoria);
        categoria.setId(categoriasService.obtenerTodas().size()+1);
        
        //guardar
        categoriasService.guardar(categoria);
        atributo.addFlashAttribute("msg", "¡La Mica se ha guardado!");
        //Mostrar la vista
        return "redirect:/categorias/indexPaginado";

 

    }
	
	/*@PostMapping("/guardar")
	public String guardar( @RequestParam("nombre") String nombre,
			@RequestParam("descripcion") String descripcion, Model model) {
		/*System.out.println("Nombre:" + nombre);
		System.out.println("Descripcion:" + descripcion);
		
		//guardar
		Categoria c = new Categoria();
		c.setId(categoriasService.obtenerTodas().size()+1);
		c.setNombre(nombre);
		c.setDescripcion(descripcion);
		categoriasService.guardar(c);
		model.addAttribute("categorias", categoriasService.obtenerTodas());	
		return "categorias/listaCategorias";
	}*/
	
	@GetMapping("/crear")
	public String crear(Categoria categoria) {
		return "categorias/formCategoria";
	}
	
	@GetMapping("/index")
	public String mostrarIndex(Model model) {
		model.addAttribute("categorias", categoriasService.obtenerTodas());
		for(Categoria categoria : categoriasService.obtenerTodas()) {
			System.out.println(categoria);
		}
		
		return "categorias/listaCategorias";
	}
}
