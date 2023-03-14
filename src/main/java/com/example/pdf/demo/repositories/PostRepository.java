package com.example.pdf.demo.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.pdf.demo.models.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer>{
    
    // Custom JPQL Query
    @Query("Select p from Post p where p.caption LIKE %:search%")
    List<Post> getPostsThatHasThisAsASubstring(@Param("search") String search, Pageable pageable);

    // Native SQL Query
    @Query(value = "Select * from post where caption LIKE %:search%", nativeQuery = true)
    List<Post> getPostsThatHasThisAsASubstringNative(@Param("search") String search);


    // = 'x'
    List<Post> findByCaption(String caption);
    List<Post> findByType(String type);

    // = 'x'
    int deleteByCaption(String caption);

    // = 'x'
    long countByCaption(String caption);


    // LIKE '%x%' 
    List<Post> findByCaptionContaining(String caption);
    List<Post> findByCaptionIsContaining(String caption);
    List<Post> findByCaptionContains(String caption);

    // LIKE 'x'
    List<Post> findByCaptionLike(String caption);

    // LIKE 'x%'
    List<Post> findByCaptionStartsWith(String caption);

    // LIKE '%x'
    List<Post> findByCaptionEndsWith(String caption);

    // you can add IgnoreCase after any method to disregard caps for a single property
    List<Post> findByCaptionContainingIgnoreCase(String caption);

    // you can add Not before any suffix to negate it
    List<Post> findByCaptionNotContaining(String caption);

    // p.caption = 'caption' AND p.type = 'type';
    List<Post> findByCaptionAndType(String caption, String type);

    // you can add AllIgnoreCase after any method to disregard caps for all property
    List<Post> findByCaptionAndTypeAllIgnoreCase(String caption, String type);

    // Enabling static ORDER BY for a query
    List<Post> findByTypeOrderByCaptionAsc(String type);


}
