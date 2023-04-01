import org.hibernate.Session;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.myaukalki.OwnerService;
import org.myaukalki.PetService;
import org.myaukalki.domain.implementations.CatImpl;
import org.myaukalki.domain.implementations.OwnerImpl;
import org.myaukalki.domain.utils.Color;
import org.myaukalki.dto.OwnerAnswerDto;
import org.myaukalki.dto.OwnerRequestDto;
import org.myaukalki.dto.PetAnswerDto;
import org.myaukalki.dto.PetRequestDto;
import org.myaukalki.implementations.OwnerDaoImpl;
import org.myaukalki.implementations.PetDaoImpl;
import org.myaukalki.mapper.CatMapper;
import org.myaukalki.mapper.OwnerMapper;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class MyaukalkiTest {

    private static Session session;
    private static OwnerService ownerService;
    private static OwnerDaoImpl ownerDao;
    private static PetDaoImpl petDao;
    private static PetService petService;
    private static OwnerImpl owner;
    private static CatImpl cat;

    @BeforeAll
    public static void setUp() {
        session = mock(Session.class);

        ownerDao = mock(OwnerDaoImpl.class);
        petDao = mock(PetDaoImpl.class);

        petService = new PetService(petDao);
//        ownerService = new OwnerService(ownerDao);
        ownerService = mock(OwnerService.class);

        owner = new OwnerImpl("John",
                LocalDate.of(1990, 1, 1));

        cat = new CatImpl("name",
                LocalDate.of(1990, 1, 1),
                owner,
                Color.blue,
                "breed");
    }

    @Test
    public void findTest() {

        var ownerDto = new OwnerAnswerDto();
        ownerDto.setId(owner.getId());
        ownerDto.setName(owner.getName());
        ownerDto.setBirthDate(owner.getBirthDate());

        var catDto = new PetAnswerDto();
        catDto.setId(cat.getId());
        catDto.setName(cat.getName());
        catDto.setBirthDate(cat.getBirthDate());
        catDto.setOwnerId(ownerDto.getId());
        catDto.setColor(cat.getColor());
        catDto.setBreed(cat.getBreed());

        when(ownerDao.find(owner.getId())).thenReturn(owner);
        when(petDao.find(cat.getId())).thenReturn(cat);

        assertEquals("John", ownerDao.find(owner.getId()).getName());
        assertEquals("name", petDao.find(cat.getId()).getName());
    }

    @Test
    public void assignPetTest(){
        var ownerDto = new OwnerRequestDto();
        ownerDto.setName(owner.getName());
        ownerDto.setId(1L);

        var catDto = new PetRequestDto();
        catDto.setName(cat.getName());
        catDto.setId(1L);
        catDto.setOwner(OwnerMapper.mapToDto(owner));

        var ownerAnswerDto = new OwnerAnswerDto();
        ownerAnswerDto.setId(ownerDto.getId());
        ownerAnswerDto.setBirthDate(owner.getBirthDate());
        ownerAnswerDto.setName(owner.getName());

        var response = new PetAnswerDto();
        response.setId(catDto.getId());
        response.setName(catDto.getName());
        response.setBirthDate(cat.getBirthDate());
        response.setOwnerId(ownerAnswerDto.getId());
        response.setColor(cat.getColor());
        response.setBreed(cat.getBreed());

        ownerAnswerDto.setPets(List.of(response));

        doReturn(response).when(ownerService).addPet(ownerDto, catDto);

        assertEquals(1L, ownerService.addPet(ownerDto, catDto).getOwnerId());
    }

    @Test
    public void koshachyaShavermaTest(){
        var ownerDto = new OwnerRequestDto();
        ownerDto.setName(owner.getName());
        ownerDto.setId(1L);

        var catDto = new PetRequestDto();
        catDto.setName(cat.getName());
        catDto.setId(1L);
        catDto.setOwner(OwnerMapper.mapToDto(owner));

        var ownerAnswerDto = new OwnerAnswerDto();
        ownerAnswerDto.setId(ownerDto.getId());
        ownerAnswerDto.setBirthDate(owner.getBirthDate());
        ownerAnswerDto.setName(owner.getName());

        var response = new PetAnswerDto();
        response.setId(catDto.getId());
        response.setName(catDto.getName());
        response.setBirthDate(cat.getBirthDate());
        response.setOwnerId(null);
        response.setColor(cat.getColor());
        response.setBreed(cat.getBreed());

        ownerAnswerDto.setPets(List.of(response));

        ownerService.addPet(ownerDto, catDto);

        when(ownerService.removePet(ownerDto, catDto)).thenReturn(response);

        assertEquals(null, ownerService.removePet(ownerDto, catDto).getOwnerId());
    }
}
