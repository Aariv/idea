package com.ariv.Idea.repositories;

import org.springframework.data.repository.CrudRepository;

import com.ariv.Idea.models.Idea;

public interface IdeaRepository extends CrudRepository<Idea, Long> {
	Iterable<Idea> findAllByOrderByLikesAsc();
	Iterable<Idea> findAllByOrderByLikesDesc();
}
