package com.onlinetaskmanagementsystem.otms.controller;

import com.onlinetaskmanagementsystem.otms.DTO.OrganisationDTO;
import com.onlinetaskmanagementsystem.otms.Exception.CommonException;
import com.onlinetaskmanagementsystem.otms.service.OrganisationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class OrganisationControllerTest {
    @InjectMocks
    OrganisationController organisationController;

    @Mock
    OrganisationService organisationService;

    OrganisationDTO organisationDTO = new OrganisationDTO();

    @Test
    void listOrgTest() throws CommonException {
        when(organisationService.viewOrgList(Mockito.any())).thenReturn(organisationDTO);
        OrganisationDTO response = organisationController.listOrg(Mockito.any()).getBody();
        Assertions.assertEquals(organisationDTO, response);
    }

}
