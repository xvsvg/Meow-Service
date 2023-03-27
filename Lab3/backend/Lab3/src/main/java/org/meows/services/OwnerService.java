package org.meows.services;

import org.meows.entities.CatEntity;
import org.meows.entities.OwnerEntity;
import org.meows.exceptions.OwnerServiceException;
import org.meows.models.CreateOwnerRequest;
import org.meows.models.OwnerResponse;
import org.meows.models.UpdateOwnerRequest;
import org.meows.repositories.CatEntityRepository;
import org.meows.repositories.OwnerEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class OwnerService {

    private final OwnerEntityRepository ownerRepository;
    private final CatEntityRepository catRepository;


    @Autowired
    public OwnerService(OwnerEntityRepository ownerRepository, CatEntityRepository catRepository) {
        this.ownerRepository = ownerRepository;
        this.catRepository = catRepository;
    }


    public OwnerResponse getById(Long id) throws OwnerServiceException {
        Optional<OwnerEntity> owner = ownerRepository.findById(id);

        if (owner.isEmpty()){
            throw new OwnerServiceException(String.format("Owner with id %s not found", id));
        }

        return OwnerResponse.toModel(owner.get());
    }

    public List<OwnerResponse> getAll() {
        Iterable<OwnerEntity> owners = ownerRepository.findAll();

        List<OwnerResponse> ownersList = new ArrayList<OwnerResponse>();
        owners.forEach(owner -> ownersList.add(OwnerResponse.toModel(owner)));

        return ownersList;
    }

    public OwnerResponse create(CreateOwnerRequest request) {

        var ownerEntity = new OwnerEntity(
                request.getName(),
                request.getBirthDate()
        );

        return OwnerResponse.toModel(ownerRepository.save(ownerEntity));
    }

    public OwnerResponse update(UpdateOwnerRequest request) throws OwnerServiceException{
        Optional<OwnerEntity> owner = ownerRepository.findById(request.getId());

        if (owner.isEmpty()){
            throw new OwnerServiceException(String.format("Owner with id %s not found", request.getId()));
        }

        OwnerEntity ownerEntity = owner.get();

        ownerEntity.setName(request.getName());
        ownerEntity.setBirthDate(request.getBirthDate());

        return OwnerResponse.toModel(ownerRepository.save(ownerEntity));
    }

    public OwnerResponse delete(Long id) throws OwnerServiceException{
        Optional<OwnerEntity> owner = ownerRepository.findById(id);

        if (owner.isEmpty()){
            throw new OwnerServiceException(String.format("Owner with id %s not found", id));
        }

        ownerRepository.delete(owner.get());

        return OwnerResponse.toModel(owner.get());
    }

    public OwnerResponse addCat(Long id, Long catId) throws OwnerServiceException{
        Optional<OwnerEntity> owner = ownerRepository.findById(id);

        if (owner.isEmpty()){
            throw new OwnerServiceException(String.format("Owner with id %s not found", id));
        }

        Optional<CatEntity> cat = catRepository.findById(catId);

        if (cat.isEmpty()){
            throw new OwnerServiceException(String.format("Cat with id %s not found", catId));
        }

        cat.get().setOwner(owner.get());

        var petList = new ArrayList<>(owner.get().getPets());
        petList.equals(cat.get());
        owner.get().setPets(petList);

        return OwnerResponse.toModel(owner.get());
    }

    public OwnerResponse removeCat(Long id, Long catId) throws OwnerServiceException{
        Optional<OwnerEntity> owner = ownerRepository.findById(id);

        if (owner.isEmpty()){
            throw new OwnerServiceException(String.format("Owner with id %s not found", id));
        }

        Optional<CatEntity> cat = catRepository.findById(catId);

        if (cat.isEmpty()){
            throw new OwnerServiceException(String.format("Cat with id %s not found", catId));
        }

        catRepository.deleteById(catId);
        ownerRepository.save(owner.get());

        return OwnerResponse.toModel(owner.get());
    }
}
