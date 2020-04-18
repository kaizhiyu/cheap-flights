package com.cheapflights.tickets.service;

import com.cheapflights.tickets.domain.dto.CityDTO;
import com.cheapflights.tickets.domain.model.City;
import com.cheapflights.tickets.repository.CityRepository;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

@Component
public class CityMapper {
    private final CityRepository cityRepository;

    public CityMapper(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public City toEntity(CityDTO cityDTO) {
        return City.builder()
//                .id(cityDTO.getId())
                .name(cityDTO.getName())
                .description(cityDTO.getDescription())
                .build();
    }

    public Collection<City> toEntity(Collection<CityDTO> citiesDTO) {
        return citiesDTO.stream().map(this::toEntity).collect(Collectors.toList());
    }

    public CityDTO toDTO(City city) {
        return CityDTO.builder()
                .id(city.getId())
                .name(city.getName())
                .description(city.getDescription())
                .build();
    }

    public Collection<CityDTO> toDTO(Collection<City> cities) {
        return cities.stream().map(this::toDTO).collect(Collectors.toList());
    }
}