package fixtures;

/*
 * Used as a base for anything that can be looked at or interacted with
 */

public abstract class Fixture {
	private String name;
	private String shortDesc;
	private String longDesc;
	
	public Fixture(String name, String shortDesc, String longDesc) {
		this.name = name;
		this.shortDesc = shortDesc;
		this.longDesc = longDesc;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getShortDesc() {
		return shortDesc;
	}
	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
	}
	public String getLongDesc() {
		return longDesc;
	}
	public void setLongDesc(String longDesc) {
		this.longDesc = longDesc;
	}
}
