package com.onlinetaskmanagementsystem.otms.mapper;


import com.onlinetaskmanagementsystem.otms.DTO.OrganisationDTO;
import com.onlinetaskmanagementsystem.otms.entity.OrganisationEntity;
import org.springframework.stereotype.Component;

@Component
public class OrganisationMapper {
    public OrganisationDTO orgEntityToModel(OrganisationEntity organisationEntity){
        OrganisationDTO organisationDTO = new OrganisationDTO();
        organisationDTO.setOrgName(organisationEntity.getOrgName());
        organisationDTO.setOrgRef(organisationEntity.getOrgRef());
        organisationDTO.setActiveStatus(organisationEntity.getActiveStatus());
        organisationDTO.setAddress(organisationEntity.getAddress());

        return organisationDTO;

    }
}
