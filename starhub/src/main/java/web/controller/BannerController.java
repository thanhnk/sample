package web.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import service.BannerService;
import web.form.Message;
import web.util.FileUtil;
import web.util.UrlUtil;
import domain.Banner;

@RequestMapping("/banners")
@Controller
public class BannerController {
	final Logger logger = LoggerFactory.getLogger(BannerController.class);

	@Autowired
	MessageSource messageSource;

	@Autowired
	private BannerService bannerService;

	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public String update(@Valid Banner banner, BindingResult bindingResult, Model uiModel,
			HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, Locale locale,
			@RequestParam(value = "file", required = false) Part file, @PathVariable("id") Long id) {
		logger.info("Updating banner");
		if (bindingResult.hasErrors()) {
			uiModel.addAttribute("message",
					new Message("error", messageSource.getMessage("banner_save_fail", new Object[] {}, locale)));
			uiModel.addAttribute("banner", banner);
			return "banners/update";
		}
		uiModel.asMap().clear();
		redirectAttributes.addFlashAttribute("message",
				new Message("success", messageSource.getMessage("banner_save_success", new Object[] {}, locale)));
		// Process upload file
		if (file != null && file.getSize() > 0) {
			banner.setPhoto(FileUtil.getFileContent(file));
		} else {
			Banner oldBanner = bannerService.findById(id);
			if (oldBanner != null && oldBanner.getPhoto() != null) {
				banner.setPhoto(oldBanner.getPhoto());
			}
		}
		bannerService.save(banner);
		logger.info("Banner saved");
		return "redirect:/banners/" + UrlUtil.encodeUrlPathSegment(banner.getId().toString(), httpServletRequest);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model uiModel) {
		uiModel.addAttribute("banner", bannerService.findById(id));
		return "banners/update";
	}

	@RequestMapping(value = "/photo/{id}", method = RequestMethod.GET)
	@ResponseBody
	public byte[] downloadPhoto(@PathVariable("id") Long id) {
		Banner banner = bannerService.findById(id);
		return banner.getPhoto();
	}
}
