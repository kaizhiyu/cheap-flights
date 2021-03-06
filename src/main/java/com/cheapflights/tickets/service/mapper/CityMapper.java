package com.cheapflights.tickets.service.mapper;

import com.cheapflights.tickets.domain.dto.AirportDTO;
import com.cheapflights.tickets.domain.dto.CityDTO;
import com.cheapflights.tickets.domain.model.City;
import org.springframework.stereotype.Component;

import javax.persistence.Tuple;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CityMapper {
    private final CommentMapper commentMapper;
    private final AirportMapper airportMapper;

    public CityMapper(CommentMapper commentMapper, AirportMapper airportMapper) {
        this.commentMapper = commentMapper;
        this.airportMapper = airportMapper;
    }

    public City toEntity(CityDTO cityDTO) {
        return City.builder()
                .id(cityDTO.getId())
                .country(cityDTO.getCountry())
                .name(cityDTO.getName())
                .description(cityDTO.getDescription())
                .build();
    }

    public CityDTO toDTO(City city) {
        CityDTO cityDTO = CityDTO.builder()
                .id(city.getId())
                .name(city.getName())
                .country(city.getCountry())
                .description(city.getDescription())
                .build();
        if (city.getComments() != null) {
            cityDTO.setComments(commentMapper.toDTO(city.getComments()));
        }
        List<AirportDTO> airportDTOList = airportMapper.toDTO(city.getAirports());
        cityDTO.setAirports(airportDTOList);
        return cityDTO;
    }

    public CityDTO toDTO(Tuple tuple) {
        return CityDTO.builder()
                .id(tuple.get("id", Number.class).longValue())
                .country(tuple.get("country", String.class))
                .name(tuple.get("name", String.class))
                .comments(new ArrayList<>())
                .airports(new ArrayList<>())
                .description(tuple.get("description", String.class))
                .build();
    }

    public Collection<CityDTO> toDTO(Collection<City> cities) {
        return cities.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
