package com.in28minutes.rest.webservices.restful_web_services.user;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.in28minutes.rest.webservices.restful_web_services.jpa.PostRepository;
import com.in28minutes.rest.webservices.restful_web_services.jpa.UserRepository;

import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class UserJpaResource {

	private UserRepository repository;
	private PostRepository postRepository;


	@Autowired
	public UserJpaResource(UserRepository repository, PostRepository postRepository) {
		this.repository = repository;
		this.postRepository = postRepository;
	}

	@GetMapping(path = "/jpa/users")
	public List<User> Users() {
		return this.repository.findAll();
	}

	@GetMapping(path = "/jpa/users/{id}")
	public EntityModel<User> Users(@PathVariable int id) {
		Optional<User> user = this.repository.findById(id);

		if (user.isEmpty()) {
			throw new UserNotFoundException("No user found.");
		}

		EntityModel<User> entityModel = EntityModel.of(user.get());

		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).Users());
		entityModel.add(link.withRel("all-users"));
		return entityModel;
	}

	@DeleteMapping(path = "/jpa/users/{id}")
	public void DeleteUsers(@PathVariable int id) {
		this.repository.deleteById(id);
	}
	
	@GetMapping(path = "/jpa/users/{id}/posts")
	public List<Post> RetrievePostsForUser(@PathVariable int id) {
		Optional<User> user = this.repository.findById(id);

		if (user.isEmpty()) {
			throw new UserNotFoundException("No user found.");
		}
		
		return user.get().getPosts();
	}


	@PostMapping("/jpa/users")
	public ResponseEntity<User> CreateUser(@Valid @RequestBody User user) {
		User newUser = this.repository.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newUser.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PostMapping(path = "/jpa/users/{id}/posts")
	public ResponseEntity<Post> CreatePostForUser(@PathVariable int id, @Valid @RequestBody Post post) {
		Optional<User> user = this.repository.findById(id);

		if (user.isEmpty()) {
			throw new UserNotFoundException("No user found.");
		}
		
		post.setUser(user.get());
		
		Post savedPost = postRepository.save(post);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedPost.getId())
				.toUri();
		return ResponseEntity.created(location).build();	}
}
