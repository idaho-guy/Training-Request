package com.teamproject.trainingrequest.service;

import com.teamproject.trainingrequest.entity.TrainingRequestEntity;
import com.teamproject.trainingrequest.model.CreateTrainingRequest;
import com.teamproject.trainingrequest.model.Employee;
import com.teamproject.trainingrequest.model.TrainingRequest;
import com.teamproject.trainingrequest.repository.TrainingRequestRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrainingRequestServiceTest {

    @InjectMocks
    private TrainingRequestService trainingRequestService;

    @Mock
    EmployeeService service;

    @Mock
    TrainingRequestRepository repo;

    @Test
    public void createTrainingRequest() {

        Long employeeId = 1L;
        BigDecimal cost = new BigDecimal(100.00);
        String desc = "Marks Spring Help";
        String location = "Jamaica";

        CreateTrainingRequest createTrainingRequest =
                createTrainingRequest(employeeId, cost, desc, location);


        String firstName = "Mark";
        String lastName = "Sage";
        Employee employee = createEmployee(employeeId, firstName, lastName);


        when(service.getEmployeeById(employeeId)).thenReturn(employee);

        TrainingRequestEntity savedEntity = new TrainingRequestEntity();
        savedEntity.setId(22L);
        TrainingRequestEntity newEntity = new TrainingRequestEntity();
        newEntity.setRequestedByFirstName(firstName);
        newEntity.setRequestedByLastName(lastName);
        newEntity.setCost(cost);
        newEntity.setDescription(desc);
        newEntity.setLocation(location);
        newEntity.setEmployeeId(1L);
        when(repo.save(newEntity)).thenReturn(savedEntity);
        Long trainingRequestId = trainingRequestService.createTrainingRequest(createTrainingRequest);
        assertEquals(22L, trainingRequestId.longValue());


    }

    @Test
    public void getOpenTrainingRequests() {

        String firstName = "Mark";
        String lastName = "Sage";
        BigDecimal cost = new BigDecimal(100.00);
        String desc = "Marks Spring Help";
        String location = "Jamaica";

        TrainingRequestEntity newEntity = new TrainingRequestEntity();

        newEntity.setRequestedByFirstName(firstName);
        newEntity.setRequestedByLastName(lastName);
        newEntity.setCost(cost);
        newEntity.setDescription(desc);
        newEntity.setLocation(location);
        newEntity.setEmployeeId(1L);

        TrainingRequest expectedTrainingRequest = new TrainingRequest();
        expectedTrainingRequest.setRequestedByFirstName(firstName);
        expectedTrainingRequest.setRequestedByLastName(lastName);
        expectedTrainingRequest.setLocation(location);
        expectedTrainingRequest.setDescription(desc);
        expectedTrainingRequest.setCost(cost);

        List<TrainingRequestEntity> trainingRequests = Arrays.asList(newEntity);
        when(repo.getOpenTrainingRequests()).thenReturn(trainingRequests);

        List<TrainingRequest> openTrainingRequests = trainingRequestService.getOpenTrainingRequests();
        assertArrayEquals(new TrainingRequest[]{expectedTrainingRequest}, openTrainingRequests.toArray());
        TrainingRequest req = openTrainingRequests.get(0);
        assertEquals(firstName, req.getRequestedByFirstName());
        assertEquals(lastName, req.getRequestedByLastName());
        assertEquals(location, req.getLocation());
        assertEquals(desc, req.getDescription());
        assertEquals(cost, req.getCost());
        assertEquals(1L, req.getEmployeeId().longValue());

    }

    @Test
    public void getOpenTrainingRequestsAboveDollarAmount() {

        String firstName = "Mark";
        String lastName = "Sage";
        BigDecimal cost = new BigDecimal(100.00);
        String desc = "Marks Spring Help";
        String location = "Jamaica";

        TrainingRequestEntity newEntity = new TrainingRequestEntity();

        newEntity.setRequestedByFirstName(firstName);
        newEntity.setRequestedByLastName(lastName);
        newEntity.setCost(cost);
        newEntity.setDescription(desc);
        newEntity.setLocation(location);
        newEntity.setEmployeeId(1L);

        TrainingRequest expectedTrainingRequest = new TrainingRequest();
        expectedTrainingRequest.setRequestedByFirstName(firstName);
        expectedTrainingRequest.setRequestedByLastName(lastName);
        expectedTrainingRequest.setLocation(location);
        expectedTrainingRequest.setDescription(desc);
        expectedTrainingRequest.setCost(cost);

        List<TrainingRequestEntity> trainingRequests = Arrays.asList(newEntity);
        BigDecimal amount = cost.subtract(new BigDecimal(0.01));
        when(repo.getTrainingRequestEntitiesByCostAfter(amount)).thenReturn(trainingRequests);

        List<TrainingRequest> costTrainingRequests = trainingRequestService.getTrainingRequestByCost(amount);
        assertArrayEquals(new TrainingRequest[]{expectedTrainingRequest}, costTrainingRequests.toArray());
        TrainingRequest req = costTrainingRequests.get(0);
        assertEquals(firstName, req.getRequestedByFirstName());
        assertEquals(lastName, req.getRequestedByLastName());
        assertEquals(location, req.getLocation());
        assertEquals(desc, req.getDescription());
        assertEquals(cost, req.getCost());
        assertEquals(1L, req.getEmployeeId().longValue());

    }

    private Employee createEmployee(Long employeeId, String firstName, String lastName) {
        Employee employee = new Employee();
        employee.setId(employeeId);
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        return employee;
    }

    private CreateTrainingRequest createTrainingRequest(Long employeeId, BigDecimal cost, String desc, String location) {
        CreateTrainingRequest createTrainingRequest = new CreateTrainingRequest();
        createTrainingRequest.setEmployeeId(employeeId);
        createTrainingRequest.setCost(cost);
        createTrainingRequest.setDescription(desc);
        createTrainingRequest.setLocation(location);
        return createTrainingRequest;
    }
}