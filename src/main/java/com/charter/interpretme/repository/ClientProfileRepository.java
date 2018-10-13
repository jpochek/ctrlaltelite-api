package com.charter.interpretme.repository;

import com.charter.interpretme.rest.entity.ClientProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientProfileRepository extends JpaRepository<ClientProfile, String> {
}
