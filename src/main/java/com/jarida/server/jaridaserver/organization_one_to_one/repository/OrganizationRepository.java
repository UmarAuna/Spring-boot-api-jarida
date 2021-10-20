package com.jarida.server.jaridaserver.organization_one_to_one.repository;

import com.jarida.server.jaridaserver.organization_one_to_one.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationRepository  extends JpaRepository<Organization, Long> {
}
