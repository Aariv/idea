package com.ariv.Idea.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ariv.Idea.models.Idea;
import com.ariv.Idea.repositories.IdeaRepository;

@Service
public class IdeaService {
private final IdeaRepository ideaRepository;

    
    public IdeaService (IdeaRepository ideaRepository) {
        this.ideaRepository = ideaRepository;
    }
    public Iterable<Idea> allIdeas() {
        return ideaRepository.findAll();
    }
    public Iterable<Idea> allIdeasL() {
        return ideaRepository.findAllByOrderByLikesAsc();
    }
    public Iterable<Idea> allIdeasH() {
        return ideaRepository.findAllByOrderByLikesDesc();
    }
    public Idea findIdea (Long id) {
        Optional<Idea> optionalIdea = ideaRepository.findById(id);
        if(optionalIdea.isPresent()) {
            return optionalIdea.get();
        } else {
            return null;
        }
    }
    
    public Idea createIdea(Idea i) {
        return ideaRepository.save(i);
    }
    
    
    
    public Idea updateIdea(Idea UIdea ) {
        Optional<Idea> optionalIdea = ideaRepository.findById(UIdea.getId());
        if(optionalIdea.isPresent()) {
            Idea i= optionalIdea.get();
            i.setTitle(UIdea.getTitle());
            i.setCreatedBy(UIdea.getCreatedBy());
            i.setLikes(UIdea.getLikes());
            i.setUsers(UIdea.getUsers());
            return ideaRepository.save(i);  
        } else {
            return null;
        }
        
    }

    //Delete
    public  void deleteIdea(Long id) {
    	ideaRepository.deleteById(id);
    	
    }
}
