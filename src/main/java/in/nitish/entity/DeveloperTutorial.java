package in.nitish.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class DeveloperTutorial {
	
	@Id
	private Integer id;
	private String title;
	private String description;
	private boolean published;
	
	@Override
	public String toString() {
		return "DeveloperTutorial [id=" + id + ", title=" + title + ", description=" + description + ", published="
				+ published + "]";
	}
	
}
