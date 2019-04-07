package com.nogueira4j.xyinc.repositories;

import com.nogueira4j.xyinc.domain.Poi;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PoiRepository extends JpaRepository<Poi, Long> {

}
