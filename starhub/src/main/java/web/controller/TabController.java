package web.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import service.TabService;
import web.form.Message;
import web.util.FileUtil;
import web.util.UrlUtil;
import domain.Tab;

@RequestMapping("/tabs")
@Controller
public class TabController {
	final Logger logger = LoggerFactory.getLogger(TabController.class);

	@Autowired
	MessageSource messageSource;

	@Autowired
	private TabService tabService;

	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public String update(@Valid Tab tab, BindingResult bindingResult, Model uiModel,
			HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, Locale locale,
			@RequestParam(value = "file", required = false) Part file, @PathVariable("id") Long id) {
		logger.info("Updating banner");
		if (bindingResult.hasErrors()) {
			uiModel.addAttribute("message",
					new Message("error", messageSource.getMessage("tab_save_fail", new Object[] {}, locale)));
			uiModel.addAttribute("banner", tab);
			return "banners/update";
		}
		uiModel.asMap().clear();
		redirectAttributes.addFlashAttribute("message",
				new Message("success", messageSource.getMessage("tab_save_success", new Object[] {}, locale)));
		// Process upload file
		if (file != null && file.getSize() > 0) {
			tab.setPhoto(FileUtil.getFileContent(file));
		} else {
			Tab oldTab = tabService.findById(id);
			if (oldTab != null && oldTab.getPhoto() != null) {
				tab.setPhoto(oldTab.getPhoto());
			}
		}
		tabService.save(tab);
		logger.info("Tab saved");
		return "redirect:/tabs/" + UrlUtil.encodeUrlPathSegment(tab.getId().toString(), httpServletRequest);
	}

	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model uiModel) {
		Tab tab = tabService.findById(id);
		uiModel.addAttribute("tab", tab);

		if (tab.getParentId() != 0) {
			uiModel.addAttribute("parents", tabService.findByParentId(0L));
		}
		return "tabs/update";
	}

	@RequestMapping(value = "/photo/{id}", method = RequestMethod.GET)
	@ResponseBody
	public byte[] downloadPhoto(@PathVariable("id") Long id) {
		Tab tab = tabService.findById(id);
		return tab.getPhoto();
	}
}
