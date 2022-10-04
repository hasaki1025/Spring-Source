package Bean;

public class MyBean {

	@Override
	public String toString() {
		return "MyBean{" +
				"name='" + name + '\'' +
				", id='" + id + '\'' +
				'}';
	}

	private String name;
	private String id;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void SayYourName()
	{
		System.out.println("My Name is "+name);
	}
}
