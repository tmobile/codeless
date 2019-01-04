package com.tmobile.ct.codeless.ui.model.controls;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;

/**
 * The Class FutureElementLocator.
 *
 * @author Rob Graff
 */
public class FutureElementLocator implements ElementLocator {

	/** The search context. */
	private final Future<WebDriver> searchContext;
	
	/** The should cache. */
	private final boolean shouldCache;
	
	/** The by. */
	private final By by;
	
	/** The cached element. */
	private WebElement cachedElement;
	
	/** The cached element list. */
	private List<WebElement> cachedElementList;

	/**
	 * Instantiates a new future element locator.
	 *
	 * @param searchContext the search context
	 * @param by the by
	 */
	public FutureElementLocator(Future<WebDriver> searchContext, By by) {
		this.searchContext = searchContext;
		this.by = by;
		this.shouldCache = false;
	}

	/* (non-Javadoc)
	 * @see org.openqa.selenium.support.pagefactory.ElementLocator#findElement()
	 */
	@Override
	public WebElement findElement() {
		if (cachedElement != null && shouldCache) {
			return cachedElement;
		}

		WebElement element;
		try {
			element = searchContext.get().findElement(by);
		} catch (InterruptedException | ExecutionException e) {
			RuntimeException ex = new RuntimeException("WebDriver not available");
			ex.initCause(e);
			throw ex;
		}
		if (shouldCache) {
			cachedElement = element;
		}

		return element;
	}

	/* (non-Javadoc)
	 * @see org.openqa.selenium.support.pagefactory.ElementLocator#findElements()
	 */
	@Override
	public List<WebElement> findElements() {
		if (cachedElementList != null && shouldCache) {
			return cachedElementList;
		}

		List<WebElement> elements;
		try {
			elements = searchContext.get().findElements(by);
		} catch (InterruptedException | ExecutionException e) {
			RuntimeException ex = new RuntimeException("WebDriver not available");
			ex.initCause(e);
			throw ex;
		}
		if (shouldCache) {
			cachedElementList = elements;
		}

		return elements;
	}

}
