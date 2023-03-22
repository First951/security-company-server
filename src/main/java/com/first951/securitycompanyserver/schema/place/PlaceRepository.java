package com.first951.securitycompanyserver.schema.place;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlaceRepository extends JpaRepository<Place, Long> {

    @Query("""
            SELECT
                p
            FROM
                Place p
            WHERE
                (:address IS NULL OR lower(p.address) LIKE lower(concat('%', :address, '%')))
            ORDER BY
                p.id
            """)
    List<Place> search(@Param("address") String address,
                       Pageable pageable);

}
