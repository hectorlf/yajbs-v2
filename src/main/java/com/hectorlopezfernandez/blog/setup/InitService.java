package com.hectorlopezfernandez.blog.setup;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.hectorlopezfernandez.blog.author.AuthorService;
import com.hectorlopezfernandez.blog.metadata.MetadataService;
import com.hectorlopezfernandez.blog.page.PageService;
import com.hectorlopezfernandez.blog.post.ArchiveEntryService;
import com.hectorlopezfernandez.blog.post.PostService;
import com.hectorlopezfernandez.blog.security.SecurityService;
import com.hectorlopezfernandez.blog.tag.TagService;

@Service
public class InitService {

	private final SecurityService securityService;
	private final MetadataService metadataService;
	private final PageService pageService;
	private final TagService tagService;
	private final PostService postService;
	private final ArchiveEntryService archiveEntryService;
	private final AuthorService authorService;

	@Inject
	public InitService(SecurityService securityService, MetadataService metadataService, PageService pageService, 
			TagService tagService, PostService postService, ArchiveEntryService archiveEntryService, AuthorService authorService) {
		this.securityService = securityService;
		this.metadataService = metadataService;
		this.pageService = pageService;
		this.tagService = tagService;
		this.postService = postService;
		this.authorService = authorService;
		this.archiveEntryService = archiveEntryService;
	}

	public void initialize() {
		if (metadataService.isAlreadyInitialized()) return;

		metadataService.initialize();
		securityService.initialize();
		authorService.initialize();
	}

	// FIXME this helper function should only live until the admin console is built
	public void sample() {
		metadataService.sample();
		securityService.sample();
		authorService.sample();
		pageService.sample();
		postService.sample();
		archiveEntryService.sample();
		tagService.sample();
	}

}