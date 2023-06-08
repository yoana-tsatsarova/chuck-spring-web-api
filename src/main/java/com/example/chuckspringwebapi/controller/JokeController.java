package com.example.chuckspringwebapi.controller;

import com.example.chuckspringwebapi.dto.Joke;
import com.example.chuckspringwebapi.dto.JokeList;

import com.example.chuckspringwebapi.http.JokeFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;
@RestController
@RequestMapping("/api/jokes")
public class JokeController {

    JokeFetcher fetcher;

    public JokeController(@Autowired JokeFetcher fetcher) {
        this.fetcher = fetcher;
    }
    @PutMapping
    public ResponseEntity<Joke> putRandomJoke() {
        Joke joke = fetcher.fetchJoke();
        if(joke == null) {
            return ResponseEntity.notFound().build();
        }
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("location", "/api/jokes/" + joke.id());
        responseHeaders.add("content-type", "application/json");
        return ResponseEntity.ok().headers(responseHeaders).body(joke);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Joke> getJokeById(@PathVariable UUID id) {
        Joke joke = fetcher.getJokeById(id);
        if (joke == null) {
            ResponseEntity.notFound().build();
        }
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("location", "/api/jokes/" + joke.id());
        responseHeaders.add("content-type", "application/json");
        return ResponseEntity.ok().headers(responseHeaders).body(joke);
    }
    @GetMapping
    public ResponseEntity<JokeList> getAllJokes(){
        JokeList jokeList = new JokeList(fetcher.getAllJokes());
        return ResponseEntity.ok().body(jokeList);
    }
}











