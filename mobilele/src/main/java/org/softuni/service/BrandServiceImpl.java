package org.softuni.service;


import org.softuni.model.dto.BrandDto;
import org.softuni.model.dto.ModelDto;
import org.softuni.repository.BrandRepository;
import org.softuni.repository.ModelRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BrandServiceImpl implements BrandService {


    private final BrandRepository brandRepository;

    public BrandServiceImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }


    @Override
    public List<BrandDto> getAllBrands() {

        return brandRepository.getAllBrands().stream()
                .map(brand -> new BrandDto(
                        brand.getName(),
                        brand.getModels().stream()
                                .map(model -> new ModelDto(model.getId(), model.getName()))
                                .sorted(Comparator.comparing(ModelDto::name))
                                .collect(Collectors.toList())
                ))
                .sorted(Comparator.comparing(BrandDto::name))
                .collect(Collectors.toList());
    }
}
