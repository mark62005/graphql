package com.udacity.graphql.service;

import com.udacity.graphql.entity.Location;
import org.springframework.stereotype.Service;

import java.util.List;

public interface LocationService {
    List<Location> getAllLocations();
}
