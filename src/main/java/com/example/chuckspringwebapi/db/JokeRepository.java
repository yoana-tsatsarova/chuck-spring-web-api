package com.example.chuckspringwebapi.db;
import com.example.chuckspringwebapi.dto.Joke;
import org.springframework.stereotype.Repository;
import java.util.*;
@Repository
public class JokeRepository {
    private static final Map<UUID, Joke> jokeRepository = new HashMap<>();
    public boolean addJoke(Joke jokeToAdd) {
        jokeRepository.put(jokeToAdd.id(), jokeToAdd);
        return jokeRepository.containsKey(jokeToAdd.id());
    }
    public boolean removeJoke(UUID id) {
        jokeRepository.remove(id);
        return !jokeRepository.containsKey(id);
    }
    public Joke getJokeById(UUID jokeID) {
        return jokeRepository.get(jokeID);
    }
    public List<Joke> listJokes(){
        return new ArrayList<>(jokeRepository.values());
    }
}