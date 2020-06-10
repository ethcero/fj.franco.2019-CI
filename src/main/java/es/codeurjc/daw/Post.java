package es.codeurjc.daw;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String title;

	private String content;

	@OneToMany(mappedBy = "post")
	private List<Comment> commentsMap = new ArrayList<>();

	public Post() {
	}

	public Post(long id, String title, String content) {
		this.id = id;
		this.title = title;
		this.content = content;
	}

	public Post(String title, String content) {
		this.title = title;
		this.content = content;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setCommentsMap(List<Comment> commentsMap) {
		this.commentsMap = commentsMap;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public List<Comment> getComments() {
		return this.commentsMap;
	}

	public Comment getComment(long id) {
		return this.commentsMap.stream().filter(comment -> comment.getId() == id).findFirst().get();
	}

	public void addComment(Comment comment) {
		this.commentsMap.add(comment);
	}

	public void deleteComment(long commentId) {
		this.commentsMap.removeIf(comment -> comment.getId() == commentId);
	}

	@Override
	public String toString() {
		return this.title + " (" + commentsMap.size() + " comments)";
	}

}
