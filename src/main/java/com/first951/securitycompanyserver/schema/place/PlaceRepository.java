package com.first951.securitycompanyserver.schema.place;

import com.first951.securitycompanyserver.schema.organization.Organization;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlaceRepository extends JpaRepository<Place, Long> {

    List<Place> findAllByOrganizationOrderByName(Organization organization, Pageable pageable);

    @Query("""
            SELECT
                p
            FROM
                Place p
            WHERE
                (:organization IS NULL OR p.organization = :organization)
            AND
                (:name IS NULL OR lower(p.name) LIKE lower(concat('%', :name, '%')))
            ORDER BY
                p.id
            """)
    List<Place> search(@Param("organization") Organization organization, @Param("name") String name, Pageable pageable);

}
