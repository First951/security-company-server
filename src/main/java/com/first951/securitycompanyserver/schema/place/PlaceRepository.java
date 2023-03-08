package com.first951.securitycompanyserver.schema.place;

import com.first951.securitycompanyserver.schema.organization.Organization;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlaceRepository extends CrudRepository<Place, Long> {

    List<Place> findAllByOrganizationOrderByName(Organization organization, Pageable pageable);

    @Query("""
            SELECT
                o
            FROM
                Place o
            WHERE
                (:name IS NULL OR o.name LIKE %:name%)
            ORDER BY
                o.id
            """)
    List<Place> findAllByName(@Param("name") String name, Pageable pageable);

}
