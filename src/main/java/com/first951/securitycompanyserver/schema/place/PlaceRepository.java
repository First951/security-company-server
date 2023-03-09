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
                p
            FROM
                Place p
            WHERE
                (:organization IS NULL OR p.organization = :organization)
            AND
                (:name IS NULL OR p.name LIKE %:name%)
            ORDER BY
                p.id
            """)
    List<Place> search(@Param("organization") Organization organization, @Param("name") String name, Pageable pageable);

}
