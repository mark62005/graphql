package com.udacity.graphql.mutator;

import com.udacity.graphql.entity.Location;
import com.udacity.graphql.exception.LocationNotFoundException;
import com.udacity.graphql.repository.LocationRepository;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class Mutation implements GraphQLMutationResolver {
    private final LocationRepository locationRepository;

    public Mutation(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public Location newLocation(String name, String address) {
        Location location = new Location(name, address);
        locationRepository.save(location);
        return location;
    }

    public boolean deleteLocation(Long id) {
        locationRepository.deleteById(id);
        return true;
    }

    public Location updateLocationName(String newName, Long id) {
        Optional<Location> optionalLocation =
            locationRepository.findById(id);

        if(optionalLocation.isPresent()) {
            Location location = optionalLocation.get();
            location.setName(newName);
            locationRepository.save(location);
            return location;
        } else {
            throw new LocationNotFoundException("Location Not Found", id);
        }
    }
}
