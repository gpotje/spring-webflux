package com.example.services;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.domain.Anime;
import com.example.repository.AnimeRepositor;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AnimeServices {

	private final AnimeRepositor repository;

	public Flux<Anime> findAll() {
		return repository.findAll();

	}
	
	public Mono<Anime> findById(Long id) {
		return repository.findById(id);

	}
    public Mono<Anime> save(Anime anime) {
        return repository.save(anime);
    }
    
    public <T> Mono<T> monoResponseStatusNotFoundException() {
        return Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Anime not found"));
    }

    public Mono<Void> update(Anime anime) {
        return findById(anime.getId())
            .flatMap(repository::save)
            .then();
    }

    public Mono<Void> delete(Long id) {
        return this.findById(id)
            .flatMap(repository::delete);
    }

}
