package es.codeurjc.daw;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import es.codeurjc.daw.dto.BasicPostDTO;
import es.codeurjc.daw.dto.CommentDTO;
import es.codeurjc.daw.dto.FullPostDTO;
import es.codeurjc.daw.dto.NewPostDTO;

@Service
public class PostService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private PostRepository postRepository;
	@Autowired
	private CommentRepository commentRepository;

	public List<FullPostDTO> getPosts() {

		return postRepository.findAll().stream()
				.map(post -> modelMapper.map(post, FullPostDTO.class))
				.collect(Collectors.toList());

	}

	public FullPostDTO getPost(Long id) {

		Post post = postRepository.findById(id).get();

		return modelMapper.map(post, FullPostDTO.class);

	}

	public BasicPostDTO addPost(NewPostDTO postDTO) {
		Post post = modelMapper.map(postDTO, Post.class);
		return modelMapper.map(postRepository.save(post), BasicPostDTO.class);
	}

	public CommentDTO saveComment(Long postid, CommentDTO commentDTO) {

		Comment comment = modelMapper.map(commentDTO, Comment.class);

		Post post = postRepository.findById(postid).get();

		comment.setPost(post);

		return modelMapper.map(commentRepository.save(comment), CommentDTO.class);
	}

	public CommentDTO getComment(Long postId, Long commentId) {

		return modelMapper.map(postRepository.findById(postId).get().getComment(commentId), CommentDTO.class);
	}

	public void deleteComment(Long postId, Long commentId) {

		Optional<Post> post = postRepository.findById(postId);

		if(post.isPresent()) {
			commentRepository.deleteById(commentId);
		}

	}

}
