package com.ariv.Idea.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.ariv.Idea.models.Idea;
import com.ariv.Idea.models.User;
import com.ariv.Idea.services.IdeaService;
import com.ariv.Idea.services.UserService;

@Controller
public class IdeaController {

	private IdeaService ideaService;

	public IdeaController(IdeaService ideaService) {
		this.ideaService = ideaService;

	}

	@Autowired
	private UserService userService;

	@GetMapping("/ideas/new")
	public String createForm(Model model, @ModelAttribute("idea") Idea idea) {
		return "new.jsp";

	}

	@PostMapping(value = "/ideas/new")
	public String create(@Valid @ModelAttribute("idea") Idea idea, BindingResult result, HttpSession session) {
		if (result.hasErrors()) {
			return "new.jsp";
		}
		Long uID = (Long) session.getAttribute("userId");
		String userName = userService.findById(uID).getName();
		idea.setCreatedBy(userName);
		idea.setLikes(0);
		ideaService.createIdea(idea);
		return "redirect:/ideas";
	}

	// show Idea
	@GetMapping(value = "/ideas/{id}")
	public String show(@PathVariable("id") Long id, Model model, HttpSession session) {
		Idea idea = ideaService.findIdea(id);
		Long uID = (Long) session.getAttribute("userId");
		User user = userService.findById(uID);
		model.addAttribute("user", user);
		model.addAttribute("idea", idea);
		return "show.jsp";
	}

	@PostMapping(value = "/ideas/{id}/delete")
	public String destroy(@PathVariable("id") Long id) {
		ideaService.deleteIdea(id);
		return "redirect:/ideas";
	}

	@GetMapping(value = "/ideas/{id}/edit")
	public String edit(@PathVariable("id") Long id, Model model) {
		Idea idea = ideaService.findIdea(id);
		model.addAttribute("idea", idea);
		return "edit.jsp";
	}

	@PostMapping(value = "/ideas/{id}/edit")
	public String update(@Valid @ModelAttribute("idea") Idea idea, BindingResult result, HttpSession session,
			@PathVariable("id") Long id) {
		if (result.hasErrors()) {
			return "edit.jsp";
		} else {
			Idea idea1 = ideaService.findIdea(id);
			Long uID = (Long) session.getAttribute("userId");
			String userName = userService.findById(uID).getName();
			if (!idea1.getCreatedBy().equals(userName))
				return "redirect:/ideas";
			idea1.setTitle(idea.getTitle());
			ideaService.updateIdea(idea1);
			return "redirect:/ideas/" + idea1.getId();
		}

	}

	@GetMapping(value = "/ideas/{id}/like")
	public String like(@PathVariable("id") Long id, Model model, HttpSession session) {
		Idea idea = ideaService.findIdea(id);
		Long UID = (Long) session.getAttribute("userId");
		User user = userService.findById(UID);
		List<User> users = idea.getUsers();
		idea.setLikes(idea.getLikes() + 1);
		users.add(user);
		idea.setUsers(users);
		ideaService.updateIdea(idea);
		return "redirect:/ideas";
	}

	@GetMapping(value = "/ideas/{id}/Unlike")
	public String Unlike(@PathVariable("id") Long id, Model model, HttpSession session) {
		Idea idea = ideaService.findIdea(id);
		User user = userService.findById((Long) session.getAttribute("userId"));
		List<User> users = idea.getUsers();
		idea.setLikes(idea.getLikes() - 1);
		users.remove(user);
		idea.setUsers(users);
		ideaService.updateIdea(idea);
		return "redirect:/ideas";
	}
}
