package com.hectorlopezfernandez.toilet.page;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hectorlopezfernandez.toilet.BaseTest;

public class PageRepositoryTests extends BaseTest {
	
	@Autowired
	private PageRepository pageRepository;

	@BeforeEach
	public void setup() {
		Page p = new Page();
		p.setContent("Content1");
		p.setCreationTime(System.currentTimeMillis());
		p.setPublicationTime(System.currentTimeMillis());
		p.setLastModificationTime(System.currentTimeMillis());
		p.setTitle("Title1");
		p.setPublished(true);
		p.setSlug("title1");
		pageRepository.save(p);
		p = new Page();
		p.setContent("Content2");
		p.setCreationTime(System.currentTimeMillis());
		p.setPublicationTime(System.currentTimeMillis());
		p.setLastModificationTime(System.currentTimeMillis());
		p.setPublished(false);
		p.setTitle("Title2");
		p.setSlug("title2");
		pageRepository.save(p);
	}

	@AfterEach
	public void teardown() {
		pageRepository.deleteAll();
	}

	@Test
	public void testFindBySlug_PageExists_ReturnedOk() {
		assertThat(pageRepository.findAll(), hasSize(2));
		Page title1Page = pageRepository.findBySlug("title1");
		assertThat(title1Page, is(notNullValue()));
		assertThat(title1Page.getTitle(), is("Title1"));
	}
	
	@Test
	public void testFindAllByPublishedIsTrue_OnePublishedPageExists_ReturnedOk() {
		assertThat(pageRepository.findAll(), hasSize(2));
		List<SitemapPageView> pageList = pageRepository.findAllByPublishedIsTrue();
		assertThat(pageList, is(notNullValue()));
		assertThat(pageList, hasSize(1));
	}
	
	@Test
	public void testFindBySlug_PageDoesntExist_NullReturned() {
		assertThat(pageRepository.findAll(), hasSize(2));
		Page nonExistentPage = pageRepository.findBySlug("nonexistent");
		assertThat(nonExistentPage, is(nullValue()));
	}

}
