package org.meows.services;

import org.meows.entities.CatEntity;
import org.meows.entities.OwnerEntity;
import org.meows.exceptions.CatServiceException;
import org.meows.models.*;
import org.meows.models.create.CreateCatRequest;
import org.meows.models.filter.CatFilterRequest;
import org.meows.models.get.CatGetResponse;
import org.meows.models.page.CatPageResponse;
import org.meows.models.update.UpdateCatRequest;
import org.meows.repositories.CatEntityRepository;
import org.meows.repositories.OwnerEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import static org.meows.repositories.CatEntityRepository.anyMatch;

@Service
public class CatService {

    private final CatEntityRepository catRepository;

    private final OwnerEntityRepository ownerRepository;

    @Autowired
    public CatService(CatEntityRepository catRepository,
                      OwnerEntityRepository ownerRepository) {
        this.catRepository = catRepository;
        this.ownerRepository = ownerRepository;
    }

    public CatResponse create(CreateCatRequest cat) throws CatServiceException {
        Optional<OwnerEntity> owner = ownerRepository.findById(cat.getOwnerId());

        if (owner.isEmpty()) {
            throw new CatServiceException(String.format("Owner with id %s not found", cat.getOwnerId()));
        }

        var entity = new CatEntity(cat.getName(), cat.getBirthDate(), cat.getBreed(), cat.getColor());
        entity.setOwner(owner.get());

        var ownerPets = new ArrayList<>(owner.get().getPets());
        ownerPets.add(entity);

        owner.get().setPets(ownerPets);

        var result = catRepository.save(entity);
        ownerRepository.save(owner.get());

        return CatResponse.toModel(result);
    }

    public CatGetResponse getById(Long id) throws CatServiceException {
        Optional<CatEntity> cat = catRepository.findById(id);

        if (cat.isEmpty()) {
            throw new CatServiceException(String.format("Cat with id %s not found", id));
        }

        return CatGetResponse.toModel(cat.get());
    }

    public CatPageResponse getAllCats(Pageable page, CatFilterRequest request) {
        var catPage = catRepository.findAll(anyMatch(request), page);

        return CatPageResponse.toModel(catPage);
    }

    public CatResponse update(UpdateCatRequest cat) throws CatServiceException {
        Optional<CatEntity> catEntity = catRepository.findById(cat.getId());

        if (catEntity.isEmpty()) {
            throw new CatServiceException(String.format("Cat with id %s not found", cat.getId()));
        }

        var ownerPets = new ArrayList<>(catEntity.get().getOwner().getPets());
        ownerPets.remove(catEntity.get());

        catRepository.deleteById(cat.getId());

        catEntity.get().setName(cat.getName());
        catEntity.get().setBreed(cat.getBreed());
        catEntity.get().setColor(cat.getColor());
        catEntity.get().setBirthDate(cat.getBirthDate());

        var owner = ownerRepository.findById(cat.getOwnerId());

        if (owner.isEmpty()) {
            throw new CatServiceException(String.format("Owner with id %s not found", cat.getOwnerId()));
        }

        ownerPets = new ArrayList<>(owner.get().getPets());
        ownerPets.add(catEntity.get());

        catEntity.get().setOwner(owner.get());
        owner.get().setPets(ownerPets);

        ownerRepository.save(owner.get());
        catRepository.save(catEntity.get());

        return CatResponse.toModel(catEntity.get());
    }

    public CatResponse deleteById(Long id) throws CatServiceException {
        Optional<CatEntity> catEntity = catRepository.findById(id);

        if (catEntity.isEmpty()) {
            throw new CatServiceException(String.format("Cat with id %s not found", id));
        }

        catRepository.delete(catEntity.get());

        return CatResponse.toModel(catEntity.get());
    }

    public CatResponse addFriend(Long petId, Long friendId) throws CatServiceException {
        Optional<CatEntity> catEntity = catRepository.findById(petId);

        if (catEntity.isEmpty()) {
            throw new CatServiceException(String.format("Cat with id %s not found", petId));
        }

        Optional<CatEntity> friendEntity = catRepository.findById(friendId);

        if (friendEntity.isEmpty()) {
            throw new CatServiceException(String.format("Friend with id %s not found", friendId));
        }

        var friendList = new ArrayList<>(catEntity.get().getFriends());
        friendList.add(friendEntity.get());

        friendList = new ArrayList<>(friendEntity.get().getFriends());
        friendList.add(catEntity.get());

        friendList = new ArrayList<>(Arrays.asList(catEntity.get(), friendEntity.get()));

        catRepository.save(catEntity.get());
        catRepository.save(friendEntity.get());

        return CatResponse.toModel(catEntity.get());
    }

    public CatResponse removeFriend(Long petId, Long friendId) throws CatServiceException {
        Optional<CatEntity> catEntity = catRepository.findById(petId);

        if (catEntity.isEmpty()) {
            throw new CatServiceException(String.format("Cat with id %s not found", petId));
        }

        Optional<CatEntity> friendEntity = catRepository.findById(friendId);

        if (friendEntity.isEmpty()) {
            throw new CatServiceException(String.format("Friend with id %s not found", friendId));
        }

        var friendList = new ArrayList<>(catEntity.get().getFriends());
        friendList.remove(friendEntity.get());

        friendList = new ArrayList<>(friendEntity.get().getFriends());
        friendList.remove(catEntity.get());

        friendList = new ArrayList<>(Arrays.asList(catEntity.get(), friendEntity.get()));

        catRepository.save(catEntity.get());
        catRepository.save(friendEntity.get());

        return CatResponse.toModel(catEntity.get());
    }
}
