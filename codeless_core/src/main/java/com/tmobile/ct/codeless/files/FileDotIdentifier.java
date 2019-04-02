package com.tmobile.ct.codeless.files;

public class FileDotIdentifier {

	String dot;
	String path;
	String extension;
	String qualifier;
	String name;
	
	public FileDotIdentifier() {}
	
	public FileDotIdentifier(String dot, String path, String extension, String qualifier, String name) {
		super();
		this.dot = dot;
		this.path = path;
		this.extension = extension;
		this.qualifier = qualifier;
		this.name = name;
	}
	
	public String getDot() {
		return dot;
	}

	public void setDot(String dot) {
		this.dot = dot;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getQualifier() {
		return qualifier;
	}

	public void setQualifier(String qualifier) {
		this.qualifier = qualifier;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FileDotIdentifier [dot=").append(dot).append(", path=").append(path).append(", extension=")
				.append(extension).append(", qualifier=").append(qualifier).append(", name=").append(name).append("]");
		return builder.toString();
	}
}
