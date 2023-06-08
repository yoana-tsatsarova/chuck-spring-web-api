package com.example.chuckspringwebapi.http;

import com.example.chuckspringwebapi.db.JokeRepository;
import com.example.chuckspringwebapi.dto.Joke;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME;
@Service
public class JokeFetcher {
    JokeRepository jokeRepository;
    public JokeFetcher(@Autowired JokeRepository jokeRepository) {
        this.jokeRepository = jokeRepository;
    }
    @Value("${joker.source.url}")
    private String apiUrl;
    public Joke fetchJoke(){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Joke> entity =
                restTemplate.getForEntity(apiUrl, Joke.class);
        Joke dto = entity.getBody();
        String now = ISO_LOCAL_DATE_TIME.format(LocalDateTime.now());
        UUID id = UUID.randomUUID();
        assert dto != null;
        Joke joke = new Joke(dto.text(), now, id);
        jokeRepository.addJoke(joke);
        return joke;
    }
    public Joke getJokeById(UUID id) {
        return jokeRepository.getJokeById(id);
    }
    public List<Joke> getAllJokes() {
        return jokeRepository.listJokes();
    }
}












