package com.example.services;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

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
	

}
