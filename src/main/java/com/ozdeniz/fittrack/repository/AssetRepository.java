package com.ozdeniz.fittrack.repository;

import com.ozdeniz.fittrack.model.Asset;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AssetRepository extends JpaRepository<Asset, UUID> {

    Optional<Asset> findByName(String name);

}
