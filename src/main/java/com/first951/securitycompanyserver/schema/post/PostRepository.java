package com.first951.securitycompanyserver.schema.post;

import com.first951.securitycompanyserver.schema.place.Place;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("""
            SELECT
                p
            FROM
                Post p
            WHERE
                (:place IS NULL OR p.place = :place)
            AND
                (:name IS NULL OR lower(p.name) LIKE lower(concat('%', :name, '%')))
            AND
                (:comment IS NULL OR lower(p.comment) LIKE lower(concat('%', :comment, '%')))
            ORDER BY
                p.id
            """)
    List<Post> search(@Param("place") Place place,
                      @Param("name") String name,
                      @Param("comment") String comment,
                      Pageable pageable);

}
