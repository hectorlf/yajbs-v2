package com.hectorlopezfernandez.toilet.page;

import java.util.Optional;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hectorlopezfernandez.toilet.ContentNotFoundException;
import com.hectorlopezfernandez.toilet.metadata.MetadataService;
import com.hectorlopezfernandez.toilet.metadata.Preferences;

@Controller
@RequestMapping(value="/pages")
public class PagesController {

	private static final Logger logger = LoggerFactory.getLogger(PagesController.class);

	private final MetadataService metadataService;
	private final PagesService pagesService;

	@Inject
	public PagesController(MetadataService metadataService, PagesService pagesService) {
		this.metadataService = metadataService;
		this.pagesService = pagesService;
	}

	@RequestMapping("/{page}")
	public String bySlug(@PathVariable("page") String slug, ModelMap model) {
		logger.debug("Going into PagesController.bySlug()");
		Optional<Page> page = pagesService.getPageBySlug(slug);
		if (page.isEmpty() || !page.get().isPublished()) {
			throw new ContentNotFoundException("No page exists with slug: " + slug);
		}
		model.addAttribute("page", page.get());
		Preferences prefs = metadataService.getPreferences();
		model.addAttribute("preferences", prefs);
		return "web/pages/page";
	}

}