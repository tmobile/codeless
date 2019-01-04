package com.tmobile.ct.codeless.service.httpclient;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

/**
 * The Class MultiParts.
 *
 * @author Rob Graff
 */
public class MultiParts implements Iterable<MultiPart> {

	/** The multi parts. */
	private List<MultiPart> multiParts;
	
	/**
	 * Instantiates a new multi parts.
	 */
	public MultiParts(){
		multiParts = new ArrayList<>();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<MultiPart> iterator() {
		return multiParts.iterator();
	}
	
	/**
	 * Stream.
	 *
	 * @return the stream
	 */
	public Stream<MultiPart> stream() {
        return multiParts.stream();
    }

}
