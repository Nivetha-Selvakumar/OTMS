package com.onlinetaskmanagementsystem.otms.service;

import com.onlinetaskmanagementsystem.otms.DTO.OrganisationDTO;
import com.onlinetaskmanagementsystem.otms.Exception.CommonException;
import com.onlinetaskmanagementsystem.otms.entity.OrganisationEntity;
import com.onlinetaskmanagementsystem.otms.mapper.OrganisationMapper;
import com.onlinetaskmanagementsystem.otms.repository.OrganisationRepo;
import com.onlinetaskmanagementsystem.otms.service.impl.OrganisationImpl;
import com.onlinetaskmanagementsystem.otms.validation.Validation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrganisationServiceTest {
    @InjectMocks
    OrganisationImpl organisationImpl;

    @Mock
    Validation validation;

    @Mock
    OrganisationMapper organisationMapper;

    OrganisationEntity organisationEntity = new OrganisationEntity();

    OrganisationDTO organisationDTO = new OrganisationDTO();

    @Test
    void viewOrgList() throws CommonException {
        when(validation.orgRefValidation(Mockito.any())).thenReturn(organisationEntity);
        when(organisationMapper.orgEntityToModel(organisationEntity)).thenReturn(organisationDTO);
        Assertions.assertEquals(organisationDTO,organisationImpl.viewOrgList(Mockito.any()));

    }
}
