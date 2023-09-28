package com.farmacia.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.farmacia.modelo.Farmacia;
import com.farmacia.repositorio.IFarmaciaRepositorio;

@Controller
@RequestMapping("/farmacias")
public class FarmaController {
	@Autowired
	private IFarmaciaRepositorio farmaciaRepositorio;
	
	@GetMapping("/")
	public String listarFarmacia(Model model) {
		List<Farmacia> farmacias = farmaciaRepositorio.findAll();
		model.addAttribute("farmacias",farmacias);
		return "listarFarmacia";
	}
	
	@GetMapping("/nuevo") //http://localhost:8080/farmacia/
	public String nuevoFarmacia(Model model) {
		model.addAttribute("farmacia", new Farmacia());
		return "nuevo";
	}

	@PostMapping("/guardar")
	public String guardarFarmacia(@ModelAttribute Farmacia farmacia) {
		farmaciaRepositorio.save(farmacia);
		return "redirect:/farmacias/";
	}

	@GetMapping("/editar/{id}")
	public String editarFarmacia(@PathVariable Integer id, Model model) {
		Farmacia farmacia = farmaciaRepositorio.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("ID del medicamento no válido"));
		model.addAttribute("farmacia", farmacia);
		return "EditarFarmacia";
	}

	@GetMapping("/eliminar/{id}")
	public String eliminarFarmacia(@PathVariable Integer id) {
		farmaciaRepositorio.deleteById(id);
		return "redirect:/farmacias/";
	}
}
