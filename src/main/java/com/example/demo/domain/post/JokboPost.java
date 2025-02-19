package com.example.demo.domain.post;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;

@Entity
@DiscriminatorValue("jokbo")
@Getter
public class JokboPost extends Post {
}