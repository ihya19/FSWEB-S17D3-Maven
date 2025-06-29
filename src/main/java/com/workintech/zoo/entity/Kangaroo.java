package com.workintech.zoo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Kangaroo {
    private long id;
    private String name;
    private double height;
    private double weight;
    private String gender;
    private Boolean isAggressive;
}