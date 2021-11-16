package com.example.controller;

import javax.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.domain.Anime;
import com.example.services.AnimeServices;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
@RequestMapping("/anime")
public class AnimeController {

	private final AnimeServices services;

	@GetMapping
	public Flux<Anime> listAll() {
		return services.findAll();
	}

	@GetMapping(path = "{id}")
	public Mono<Anime> findByID(@PathVariable("id") Long id) {
		return services.findById(id).switchIfEmpty(monoResponseStatusNotFoundException());
	}

	public <T> Mono<T> monoResponseStatusNotFoundException() {
		return Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Anime not found"));
	}

	@PutMapping(path = "{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public Mono<Void> update(@PathVariable Long id, @Valid @RequestBody Anime anime) {
		return services.update(anime.withId(id));
	}

	@DeleteMapping(path = "{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public Mono<Void> delete(@PathVariable Long id) {
		return services.delete(id);
	}
}
