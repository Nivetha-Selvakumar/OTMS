package com.onlinetaskmanagementsystem.otms.service.impl;

import com.onlinetaskmanagementsystem.otms.DTO.OrganisationDTO;
import com.onlinetaskmanagementsystem.otms.Exception.CommonException;
import com.onlinetaskmanagementsystem.otms.entity.OrganisationEntity;
import com.onlinetaskmanagementsystem.otms.mapper.OrganisationMapper;
import com.onlinetaskmanagementsystem.otms.service.OrganisationService;
import com.onlinetaskmanagementsystem.otms.validation.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganisationImpl implements OrganisationService {
    @Autowired
    Validation validation;

    @Autowired
    OrganisationMapper organisationMapper;

    @Override
    public OrganisationDTO viewOrgList(String orgRef) throws CommonException {
        OrganisationEntity organisationEntity = validation.orgRefValidation(orgRef);
        return organisationMapper.orgEntityToModel(organisationEntity);
    }
}
