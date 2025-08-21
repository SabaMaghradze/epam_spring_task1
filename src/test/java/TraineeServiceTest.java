import com.example.gym.dao.TraineeDao;
import com.example.gym.model.Trainee;

import com.example.gym.service.TraineeService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.List;


public class TraineeServiceTest {

    @Test
    public void createsUsernameAndPasswordAndId() {
        TraineeDao dao = mock(TraineeDao.class);
        when(dao.findByName("John","Doe")).thenReturn(List.of()); // no duplicates

        TraineeService svc = new TraineeService();
        svc.setTraineeDao(dao);

        Trainee t = new Trainee();
        t.setFirstName("John");
        t.setLastName("Doe");
        t.setDateOfBirth(LocalDate.of(2000,1,1));

        Trainee created = svc.create(t);

        assertNotNull(created.getId());
        assertEquals("John.Doe", created.getUsername());
        assertNotNull(created.getPassword());
        assertEquals(10, created.getPassword().length());
        verify(dao).create(created.getId(), created);
    }

    @Test
    public void appendsSerialWhenDuplicateNameExists() {
        TraineeDao dao = mock(TraineeDao.class);
        when(dao.findByName("John","Doe")).thenReturn(List.of(new Trainee(), new Trainee()));

        TraineeService svc = new TraineeService();
        svc.setTraineeDao(dao);

        Trainee t = new Trainee();
        t.setFirstName("John"); t.setLastName("Doe");

        Trainee created = svc.create(t);
        assertTrue(created.getUsername().startsWith("John.Doe"));
        assertTrue(created.getUsername().matches("John\\.Doe\\d+"));
    }
}
