import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.junit.jupiter.api.*;
import org.myaukalki.*;
import org.myaukalki.domain.contracts.*;
import org.myaukalki.domain.implementations.*;
import org.myaukalki.domain.utils.Color;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.criteria.Predicate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class MyaukalkiTest {

    private static Session session;
    private static OwnerService ownerService;
    private static PetService petService;
    private static OwnerImpl owner;
    private static CatImpl cat;

    @BeforeAll
    public static void setUp() {
        session = mock(Session.class);
        ownerService = mock(OwnerService.class);
        petService = mock(PetService.class);

        owner = new OwnerImpl("John",
                LocalDate.of(1990, 1, 1));

        cat = new CatImpl("name",
                LocalDate.of(1990, 1, 1),
                owner,
                Color.blue,
                "breed");

        ownerService.addPet(owner, cat);
    }

    @Test
    public void findTest() {

        when(ownerService.find(owner.getId())).thenReturn(owner);
        when(petService.find(cat.getId())).thenReturn(cat);

        assertEquals("John", ownerService.find(owner.getId()).getName());
        assertEquals("name", petService.find(cat.getId()).getName());
    }

//    @Test
//    public void findAllTest() {
//        List<Owner> mockOwners = List.of(owner, owner, owner);
//        var restriction = Restrictions.eq("name", "John");
//        when(ownerService
//                .findAll(criteria -> {
//                    criteria.add(restriction);
//                    return criteria.list();
//                }))
//                .thenReturn(mockOwners.stream().filter(i -> i.getName().equals("John")).toList());
//
//        List<Owner> owners = ownerService.findAll(criteria -> {
//
//            criteria.add(restriction);
//            return criteria.list();
//        });
//
//        assertEquals(3, owners.size());
//    }
}
