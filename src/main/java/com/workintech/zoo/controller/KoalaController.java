package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Koala;
import com.workintech.zoo.exceptions.ZooException;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/koalas")
public class KoalaController {

    private Map<Long, Koala> koalas;

    @PostConstruct
    public void init() {
        koalas = new HashMap<>();
    }

    @PostMapping
    public Koala save(@RequestBody Koala koala) {
        if (koala.getId() <= 0 || koala.getName() == null) {
            throw new ZooException("Invalid Koala data", HttpStatus.BAD_REQUEST);
        }
        koalas.put(koala.getId(), koala);
        return koala;
    }

    @GetMapping
    public List<Koala> findAll() {
        return new ArrayList<>(koalas.values());
    }

    @GetMapping("/{id}")
    public Koala findById(@PathVariable long id) {
        if (!koalas.containsKey(id)) {
            throw new ZooException("Koala not found", HttpStatus.NOT_FOUND);
        }
        return koalas.get(id);
    }

    @PutMapping("/{id}")
    public Koala update(@PathVariable long id, @RequestBody Koala koala) {
        if (!koalas.containsKey(id)) {
            throw new ZooException("Koala not found", HttpStatus.NOT_FOUND);
        }
        koala.setId(id);
        koalas.put(id, koala);
        return koala;
    }

    @DeleteMapping("/{id}")
    public Koala delete(@PathVariable long id) {
        if (!koalas.containsKey(id)) {
            throw new ZooException("Koala not found", HttpStatus.NOT_FOUND);
        }
        return koalas.remove(id);
    }
}