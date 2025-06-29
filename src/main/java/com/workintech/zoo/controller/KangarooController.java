package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Kangaroo;
import com.workintech.zoo.exceptions.ZooException;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/kangaroos")
public class KangarooController {

    private Map<Long, Kangaroo> kangaroos;

    @PostConstruct
    public void init() {
        kangaroos = new HashMap<>();
    }

    @PostMapping
    public Kangaroo save(@RequestBody Kangaroo kangaroo) {
        if (kangaroo.getId() <= 0 || kangaroo.getName() == null) {
            throw new ZooException("Invalid Kangaroo data", HttpStatus.BAD_REQUEST);
        }
        kangaroos.put(kangaroo.getId(), kangaroo);
        return kangaroo;
    }

    @GetMapping
    public List<Kangaroo> findAll() {
        return new ArrayList<>(kangaroos.values());
    }

    @GetMapping("/{id}")
    public Kangaroo findById(@PathVariable long id) {
        if (!kangaroos.containsKey(id)) {
            throw new ZooException("Kangaroo not found", HttpStatus.NOT_FOUND);
        }
        return kangaroos.get(id);
    }

    @PutMapping("/{id}")
    public Kangaroo update(@PathVariable long id, @RequestBody Kangaroo kangaroo) {
        if (!kangaroos.containsKey(id)) {
            throw new ZooException("Kangaroo not found", HttpStatus.NOT_FOUND);
        }
        kangaroo.setId(id);
        kangaroos.put(id, kangaroo);
        return kangaroo;
    }

    @DeleteMapping("/{id}")
    public Kangaroo delete(@PathVariable long id) {
        if (!kangaroos.containsKey(id)) {
            throw new ZooException("Kangaroo not found", HttpStatus.NOT_FOUND);
        }
        return kangaroos.remove(id);
    }
}