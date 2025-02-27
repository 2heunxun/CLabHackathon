package com.example.demo.domain.post;

import org.springframework.data.jpa.domain.Specification;

public class PostSpecification {

    public static Specification<Post> hasType(String type) {
        return (root, query, criteriaBuilder) -> {
            if (type == null || type.isEmpty()) {
                return null;
            }
            return criteriaBuilder.equal(root.get("type"), type);
        };
    }

    public static Specification<Post> hasKeyword(String keyword) {
        return (root, query, criteriaBuilder) -> {
            if (keyword == null || keyword.isEmpty()) {
                return null;
            }
            return criteriaBuilder.or(
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), "%" + keyword.toLowerCase() + "%"),
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("content")), "%" + keyword.toLowerCase() + "%")
            );
        };
    }
}