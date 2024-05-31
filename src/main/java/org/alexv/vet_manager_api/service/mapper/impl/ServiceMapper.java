package org.alexv.vet_manager_api.service.mapper.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.alexv.vet_manager_api.commons.mapper.Mapper;
import org.alexv.vet_manager_api.service.domain.dto.ServiceDTO;
import org.alexv.vet_manager_api.service.domain.entity.Service;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ServiceMapper implements Mapper<Service, ServiceDTO> {

    ModelMapper modelMapper;

    @Override
    public ServiceDTO toDTO(Service service) {
        return modelMapper.map(service, ServiceDTO.class);
    }

    @Override
    public Service toEntity(ServiceDTO serviceDTO) {
        return modelMapper.map(serviceDTO, Service.class);
    }

    @Override
    public List<ServiceDTO> toDTO(List<Service> a) {
        return a
                .stream()
                .map(service -> modelMapper.map(service, ServiceDTO.class))
                .toList();
    }

    @Override
    public List<Service> toEntity(List<ServiceDTO> b) {
        return b
                .stream()
                .map(serviceDTO -> modelMapper.map(serviceDTO, Service.class))
                .toList();
    }
}
