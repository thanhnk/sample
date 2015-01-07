package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/security")
@Controller
public class SecurityController {
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model uiModel) {
		return "security/login";
	}
}
