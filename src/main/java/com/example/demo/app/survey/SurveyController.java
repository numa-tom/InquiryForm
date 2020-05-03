package com.example.demo.app.survey;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Survey;
import com.example.demo.service.SurveyService;


@Controller
@RequestMapping("/survey")
public class SurveyController {
	
	private final SurveyService surveyService;
	
	@Autowired
	public SurveyController(SurveyService surveyService) {
		this.surveyService = surveyService;
	}
	
	@GetMapping
	public String index(Model model) {
		List<Survey> list = surveyService.getAll();
		
		
		model.addAttribute("surveyList", list);
		model.addAttribute("title", "アンケート一覧");
		return "/survey/index_boot";
	}
	
	@GetMapping("/form")
	public String form(SurveyForm surveyForm, Model model,
			@ModelAttribute("complete") String complete) {
		
		model.addAttribute("title", "アンケートフォーム");
		return "survey/form_boot";
	}
	
	@PostMapping("/form")
	public String formGoBack(SurveyForm surveyForm, Model model) {
		model.addAttribute("title", "アンケートフォーム");
		return "survey/form_boot";
	}
	
	@PostMapping("/confirm")
	public String confirm(@Validated SurveyForm surveyForm, 
			BindingResult result, Model model) {
		if(result.hasErrors()) {
			model.addAttribute("title", "アンケートフォーム");
			return "survey/form_boot";
		}
		model.addAttribute("title", "確認フォーム");
		return "survey/confirm_boot";
	}
	
	@PostMapping("/complete")
	public String complete(@Valid @ModelAttribute SurveyForm surveyForm,
			Model model, BindingResult result, 
			RedirectAttributes redirectAttributes) {
		
		if(result.hasErrors()) {
			model.addAttribute("title", "アンケートフォーム");
			return "survey/form";
		}
		
		Survey survey = new Survey();
		survey.setAge(surveyForm.getAge());
		survey.setSatisfaction(surveyForm.getSatisfaction());
		survey.setComment(surveyForm.getComment());
		survey.setCreated(LocalDateTime.now());
		
		surveyService.save(survey);
		
		redirectAttributes.addFlashAttribute("complete", "ご協力ありがとうございました！");
		return "redirect:/survey/form";
		
	}
}
