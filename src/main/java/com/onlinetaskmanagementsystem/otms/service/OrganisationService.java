package com.onlinetaskmanagementsystem.otms.service;

import com.onlinetaskmanagementsystem.otms.DTO.OrganisationDTO;
import com.onlinetaskmanagementsystem.otms.Exception.CommonException;
import org.springframework.stereotype.Service;

@Service
public interface OrganisationService {
    OrganisationDTO viewOrgList(String orgRef) throws CommonException;
}
