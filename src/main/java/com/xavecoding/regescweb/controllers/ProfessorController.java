package com.xavecoding.regescweb.controllers;

import com.xavecoding.regescweb.dto.RequisicaoNovoProfessor;
import com.xavecoding.regescweb.models.Professor;
import com.xavecoding.regescweb.models.StatusProfessor;
import com.xavecoding.regescweb.repositories.ProfessorRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ProfessorController {

    @Autowired
    ProfessorRepository professorRepository;

    @GetMapping("professores")
    public ModelAndView index(){
        List<Professor> professores = this.professorRepository.findAll();
        ModelAndView mv = new ModelAndView("professores/index");
        mv.addObject("professores", professores);
        return mv;
    }

    @GetMapping("professores/new")
    public ModelAndView nnew(){
        ModelAndView mv = new ModelAndView("professores/new");
        mv.addObject("listaStatus", StatusProfessor.values());
        mv.addObject("requisicaoNovoProfessor", new RequisicaoNovoProfessor());
        return mv;
    }

    @PostMapping("professores")
    public ModelAndView salvar(@Valid RequisicaoNovoProfessor requisicao, BindingResult result){
        if (result.hasErrors()) {
            ModelAndView mv = new ModelAndView("professores/new");
            mv.addObject("listaStatus", StatusProfessor.values());
            return mv;
        }else{
            Professor professor = requisicao.toProfessor();
            professorRepository.save(professor);
            return new ModelAndView("redirect:professores");
        }
    }


}
