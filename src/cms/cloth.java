package cms;

public class cloth {
	private Long id;
	private String color;
	private String brand;
	private Double size;
	private String season;
	public cloth(){
	
	}
	public cloth(Long id, String color, String brand, Double size, String season) {
		super();
		this.id = id;
		this.color = color;
		this.brand = brand;
		this.size = size;
		this.season = season;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public Double getSize() {
		return size;
	}
	public void setSize(Double size) {
		this.size = size;
	}
	public String getSeason() {
		return season;
	}
	public void setSeason(String season) {
		this.season = season;
	}
	@Override
	public String toString() {
		return "cloth [id=" + id + ", color=" + color + ", brand=" + brand
				+ ", size=" + size + ", season=" + season + "]";
	}
	
	
}


