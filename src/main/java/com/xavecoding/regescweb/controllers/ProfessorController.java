package com.xavecoding.regescweb.controllers;

import com.xavecoding.regescweb.dto.RequisicaoFormProfessor;
import com.xavecoding.regescweb.models.Professor;
import com.xavecoding.regescweb.models.StatusProfessor;
import com.xavecoding.regescweb.repositories.ProfessorRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/professores")
public class ProfessorController {

    @Autowired
    ProfessorRepository professorRepository;

    @GetMapping("")
    public ModelAndView index(){
        List<Professor> professores = this.professorRepository.findAll();
        ModelAndView mv = new ModelAndView("professores/index");
        mv.addObject("professores", professores);
        return mv;
    }

    @GetMapping("/new")
    public ModelAndView nnew(){
        ModelAndView mv = new ModelAndView("professores/new");
        mv.addObject("listaStatus", StatusProfessor.values());
        mv.addObject("requisicaoNovoProfessor", new RequisicaoFormProfessor());
        return mv;
    }

    @PostMapping("")
    public ModelAndView salvar(@Valid RequisicaoFormProfessor requisicao, BindingResult result){
        if (result.hasErrors()) {
            ModelAndView mv = new ModelAndView("professores/new");
            mv.addObject("listaStatus", StatusProfessor.values());
            return mv;
        }else{
            Professor professor = requisicao.toProfessor();
            professorRepository.save(professor);
            return new ModelAndView("redirect:professores/" + professor.getId());
        }
    }

    @GetMapping("/{id}")
    public ModelAndView show(@PathVariable Long id){
        Optional<Professor> optional = this.professorRepository.findById(id);
        if (optional.isPresent()){
            Professor professor = optional.get();
            ModelAndView mv = new ModelAndView("professores/show");
            mv.addObject("professor", professor);
            return mv;

        }else{

            return new ModelAndView("redirect:/professores");
        }
    }

    @GetMapping("/{id}/edit")
    public ModelAndView edit(@PathVariable Long id, RequisicaoFormProfessor requisicao){
        Optional<Professor> optional = this.professorRepository.findById(id);
        if (optional.isPresent()) {
            Professor professor = optional.get();
            requisicao.fromProfessor(professor);
            ModelAndView mv = new ModelAndView("professores/edit");
            mv.addObject("professorId", professor.getId());
            mv.addObject("listaStatus", StatusProfessor.values());
            return mv;

        }else{
            return new ModelAndView("redirect:/professores");
        }
    }


}
