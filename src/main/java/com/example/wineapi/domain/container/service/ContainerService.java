package com.example.wineapi.domain.container.service;

import com.example.wineapi.domain.container.dto.ContainerDTO;
import com.example.wineapi.domain.container.dto.ContainerViewDto;

import java.util.List;

public interface ContainerService {
    ContainerDTO saveContainer(Long userId, ContainerDTO containerDTO);
    ContainerDTO getContainer(Long id);

    ContainerDTO getContainer(Long userId, Long wineId);

    ContainerViewDto getContainerView(Long wineId);
    ContainerViewDto getContainerView(Long userId, Long wineId);
    void deleteContainer(Long user_id, Long wine_id);

    ContainerDTO updateContainer(Long userId, Long wineId, boolean isLike);

    void deleteContainers(Long user_id);

    List<ContainerViewDto> getMyContainers(Long user_id);

    List<ContainerViewDto> getMyLikedContainers(Long user_id);
}
