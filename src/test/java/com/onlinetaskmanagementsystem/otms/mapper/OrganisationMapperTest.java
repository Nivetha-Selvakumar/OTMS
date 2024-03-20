package com.onlinetaskmanagementsystem.otms.mapper;

import com.onlinetaskmanagementsystem.otms.DTO.OrganisationDTO;
import com.onlinetaskmanagementsystem.otms.Enum.ActiveStatus;
import com.onlinetaskmanagementsystem.otms.entity.OrganisationEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class OrganisationMapperTest {
    @InjectMocks
    OrganisationMapper organisationMapper;

    OrganisationDTO organisationDTO = new OrganisationDTO();

    OrganisationEntity organisationEntity = new OrganisationEntity();

    @BeforeEach
    void init(){
        organisationDTO.setOrgName("Aaludra");
        organisationDTO.setActiveStatus(ActiveStatus.ACTIVE);
        organisationDTO.setOrgRef("ORG001");
        organisationDTO.setAddress("Coimbatore");

        organisationEntity.setAddress("Coimbatore");
        organisationEntity.setActiveStatus(ActiveStatus.ACTIVE);
        organisationEntity.setOrgRef("ORG001");
        organisationEntity.setOrgName("Aaludra");
    }

    @Test
    void orgEntityToMapperTest(){
        OrganisationDTO actual = organisationMapper.orgEntityToModel(organisationEntity);
        Assertions.assertEquals(organisationDTO.getOrgName(),actual.getOrgName());
    }

}
