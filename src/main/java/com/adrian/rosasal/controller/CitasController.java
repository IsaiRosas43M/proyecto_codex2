package com.adrian.rosasal.controller;
import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.adrian.rosasal.model.Citas;
import com.adrian.rosasal.service.IntCitasService;

@Controller
@RequestMapping("/usuarioV")
public class CitasController {
	
	@Autowired
	private IntCitasService citasService ;
	
	@GetMapping("/buscar")
    public String buscar(@RequestParam("id")int idCita, Model model) {
        Citas citas = citasService.buscarPorId(idCita);
        System.out.println(citas);
        model.addAttribute("citas", citas);
        return "/usuarioV/formCitas";
    }
	
	@PostMapping("/guardar")
	public String guardar(Citas Citas, RedirectAttributes atributo ){
		citasService.guardar(Citas);
		return "redirect:index";
	}
	
	@GetMapping("/detalle")
	public String crear(Citas citas) {
		return "/usuarioV/formCitas";
	}
	
	@GetMapping("/eliminar/{id}")
	public  String eliminar(@PathVariable("id") int idCita) {
		citasService.eliminar(idCita);
		System.out.print("producto eliminado con id"+ idCita);
		//peticion de index mediante un redireccionamiento	
		return "redirect:/usuarioV/index";
	}
	
	@GetMapping("/index")
	public String mostrarIndex(Model model) {
		List <Citas> Citas = citasService.obtenerTodas();
		model.addAttribute("Citas", Citas);
		model.addAttribute("total", citasService.totalC());
		return "/usuarioV/agendarCitas";
	}
	@InitBinder
    protected void initBinder(WebDataBinder binder) {
      binder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport() {
        @Override
        public void setAsText(String text) throws IllegalArgumentException{
          setValue(LocalDate.parse(text, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        }

 

        @Override
        public String getAsText() throws IllegalArgumentException {
          return DateTimeFormatter.ofPattern("yyyy-MM-dd").format((LocalDate) getValue());
        }
      });
    }

}