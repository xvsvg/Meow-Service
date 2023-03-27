package org.meows.services;

import org.meows.entities.CatEntity;
import org.meows.entities.FriendEntity;
import org.meows.entities.OwnerEntity;
import org.meows.exceptions.CatServiceException;
import org.meows.models.CatResponse;
import org.meows.models.CreateCatRequest;
import org.meows.models.UpdateCatRequest;
import org.meows.repositories.CatEntityRepository;
//import org.meows.repositories.FriendEntityRepository;
import org.meows.repositories.OwnerEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class CatService {

    private final CatEntityRepository catRepository;

    private final OwnerEntityRepository ownerRepository;

//    private final FriendEntityRepository friendRepository;

    @Autowired
    public CatService(CatEntityRepository catRepository,
                      OwnerEntityRepository ownerRepository
//                      FriendEntityRepository friendRepository
    ) {
        this.catRepository = catRepository;
        this.ownerRepository = ownerRepository;
//        this.friendRepository = friendRepository;
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

    public CatResponse getById(Long id) throws CatServiceException {
        Optional<CatEntity> cat = catRepository.findById(id);

        if (cat.isEmpty()) {
            throw new CatServiceException(String.format("Cat with id %s not found", id));
        }

        return CatResponse.toModel(cat.get());
    }

    public List<CatResponse> getAllCats() {
        Iterable<CatEntity> cats = catRepository.findAll();

        List<CatResponse> catResponses = new ArrayList<>();
        cats.forEach(cat -> catResponses.add(CatResponse.toModel(cat)));

        return catResponses;
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

        var friends = new FriendEntity();
        friends.setFriends(friendList);

        catRepository.save(catEntity.get());
        catRepository.save(friendEntity.get());
//        friendRepository.save(friends);

        return CatResponse.toModel(friendEntity.get());
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

        var friends = new FriendEntity();
        friends.setFriends(friendList);

        catRepository.save(catEntity.get());
        catRepository.save(friendEntity.get());
//        friendRepository.delete(friends);

        return CatResponse.toModel(friendEntity.get());
    }
}
