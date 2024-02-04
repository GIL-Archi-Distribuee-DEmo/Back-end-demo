package com.gil.school.api.repository;


import com.gil.school.api.model.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SchoolRepository extends JpaRepository<School, Long> {
    List<School> findByName(String name);

    @Query(value = "SELECT * FROM school WHERE " +
            "cast(split_part(location_school, ',', 1) as double precision) = :latitude " +
            "AND cast(split_part(location_school, ',', 2) as double precision) = :longitude",
            nativeQuery = true)
    List<School> findSchoolsByLocation(
            @Param("latitude") double latitude,
            @Param("longitude") double longitude
    );


}
