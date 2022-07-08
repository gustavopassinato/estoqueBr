package br.com.estoqueBr.controller.teste;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.estoqueBr.model.dto.FabricanteDto;

import javax.servlet.http.HttpSession;

@Controller
public class SegundoTesteController {

    @GetMapping("/cadastro/funcionalidade1")
    public String dominioUm(FabricanteDto fabricanteDto, Model model, HttpSession session){
        String pathToRedirect = "/redirecionamento1";
        
        model.addAttribute("path", pathToRedirect);

        return "teste/teste_form";
    }
    
    @GetMapping("/cadastro/funcionalidade2")
    public String dominioTres(FabricanteDto fabricanteDto, Model model, HttpSession session){
        String pathToRedirect = "/redirecionamento2";
        
        model.addAttribute("path", pathToRedirect);

        return "teste/teste_form";
    }
    
    @PostMapping("/redirecionamento1")
    public String dominioDois(FabricanteDto fabricanteDto) {
    	System.out.println(fabricanteDto.getNome() + "redirecionamento1");
    	
    	return "redirect:/";
    }
    
    @PostMapping("/redirecionamento2")
    public String dominioQuatro(FabricanteDto fabricanteDto) {
    	System.out.println(fabricanteDto.getNome() + "redirecionamento2");
    	
    	return "redirect:/";
    }

}
