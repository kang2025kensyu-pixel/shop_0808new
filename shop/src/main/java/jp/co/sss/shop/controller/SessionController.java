package jp.co.sss.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jp.co.sss.shop.form.LoginFormWithAnnotation;
import jp.co.sss.shop.form.LoginFormWithValidation;


@Controller
public class SessionController {
	//
		@RequestMapping(path = "/login", method = RequestMethod.GET)
		public String login() {
			return "session/login";
		}

	//ログインのメゾット
	@RequestMapping(path = "/doLoginOnRequest", method = RequestMethod.POST)
	public String doLoginOnRequest(LoginForm form, Model model) {
		model.addAttribute("userId", form.getUserId());
		return "session/login_on_request";
	}

	@RequestMapping(path = "/loginOnSession", method = RequestMethod.GET)
	public String loginOnSession() {
		return "session/login_on_session";
	}

	@RequestMapping(path = "/doLoginOnSession", method = RequestMethod.POST)
	public String doLoginOnSession(LoginForm form, HttpSession session) {
		if (form.getUserId() == 123) {
			//入力したユーザID をセッション属性 userId としてセッションスコープに保存 
			session.setAttribute("userId", form.getUserId());

			return "redirect:/";
		} else {
			return "session/login_on_session";
		}
	}

	@RequestMapping(path = "/loginWithValidation", method = RequestMethod.GET)
	public String loginWithValidation(@ModelAttribute LoginFormWithValidation form) {
		return "session/login_with_validation";
	}

	@RequestMapping(path = "/loginWithValidation", method = RequestMethod.POST)
	public String doLoginWithValidation(
			@Valid @ModelAttribute LoginFormWithValidation form,
			BindingResult result, HttpSession session) {
		if (result.hasErrors()) {
			return "session/login_with_validation";
		}
		if (form.getUserId() == 123) {
			//入力したユーザID をセッション属性 userId としてセッションスコープに保存 
			session.setAttribute("userId", form.getUserId());
			return "redirect:/";
		} else {
			return "session/login_with_validation";
		}
	}

	@RequestMapping(path = "/loginWithAnnotation", method = RequestMethod.GET)
	public String loginWithAnnotation(@ModelAttribute LoginFormWithAnnotation form) {
		return "session/login_with_annotation";
	}

	@RequestMapping(path = "/loginWithAnnotation", method = RequestMethod.POST)
	public String doLoginWithAnnotation(@Valid @ModelAttribute LoginFormWithAnnotation form,BindingResult result, HttpSession session) {
		if (result.hasErrors()) {
			return "session/login_with_annotation";
		}
		
		if (form.getUserId() == 123) {
			//入力したユーザID をセッション属性userId としてセッションスコープに保存 
			session.setAttribute("userId", form.getUserId());
			return "redirect:/";
		} else {
			return "session/login_with_annotation";
		}
	}

	@RequestMapping(path = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		//セッションの破棄  
		session.invalidate();
		return "redirect:/";
	}

}
