package cn.edu.scau.cmi.colorCheck.domain.database;


public class Rule implements java.io.Serializable {

	// Fields

	private Long id;
	private Project project;
	private String name;
	private Boolean type;
	private Integer red;
	private Integer green;
	private Integer blue;
	private Float result;
	private String memo;

	// Constructors

	/** default constructor */
	public Rule() {
	}

	/** full constructor */
	public Rule(Project project, String name, Boolean type, Integer red, Integer green, Integer blue, Float result,
			String memo) {
		this.project = project;
		this.name = name;
		this.type = type;
		this.red = red;
		this.green = green;
		this.blue = blue;
		this.result = result;
		this.memo = memo;
	}


	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Project getProject() {
		return this.project;
	}

	public void setProject(Project project) {
		this.project = project;
	}


	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getType() {
		return this.type;
	}

	public void setType(Boolean type) {
		this.type = type;
	}

	public Integer getRed() {
		return this.red;
	}

	public void setRed(Integer red) {
		this.red = red;
	}

	public Integer getGreen() {
		return this.green;
	}

	public void setGreen(Integer green) {
		this.green = green;
	}

	public Integer getBlue() {
		return this.blue;
	}

	public void setBlue(Integer blue) {
		this.blue = blue;
	}

	public Float getResult() {
		return this.result;
	}

	public void setResult(Float result) {
		this.result = result;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}