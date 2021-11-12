package com.example.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.example.domain.Anime;

import reactor.core.publisher.Mono;

public interface AnimeRepositor extends ReactiveCrudRepository<Anime, Long>{

	@Override
	Mono<Anime> findById(Long id);
	
}
