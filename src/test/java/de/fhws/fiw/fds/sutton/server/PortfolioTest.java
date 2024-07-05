package de.fhws.fiw.fds.sutton.server;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.time.LocalDate;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.fhws.fiw.fds.demo.client.models.ClientModule;
import de.fhws.fiw.fds.demo.client.models.ClientUni;
import de.fhws.fiw.fds.demo.client.rest.Rest;

public class PortfolioTest {
    private Rest client;

    @BeforeEach
    public void setUp() throws IOException {
        this.client = new Rest();
        this.client.resetDatabase();
    }

    // CREATING A UNIVERSITY
    @Test
    void create_uni_test() throws IOException {

        client.start();
        client.getAllUniversities();

        var university = getUniversityExample();
        assertTrue(client.isCreateUniversityAllowed());
        client.createUniversity(university);
        assertEquals(201, client.getLastStatusCode());
    }

    // CREATING A UNIVERSITY with Wrong Input
    @Test
    void create_uni_falseinput_incoming_test() throws IOException {
        // GET ALL
        client.start();
        client.getAllUniversities();

        var university = getUniversityExample();
        university.setStudentsIncoming(-1);
        assertTrue(client.isCreateUniversityAllowed());
        client.createUniversity(university);
        assertEquals(400, client.getLastStatusCode());
    }

    // GET DISPATCHER
    @Test
    public void dispatcher_availability() throws IOException {
        client.start();
        assertEquals(200, client.getLastStatusCode());
    }

    // GET ALL UNIVERSITIES
    @Test
    public void get_all_universities_dispatcher() throws IOException {
        client.start();
        assertTrue(client.isGetAllUniversitiesAllowed());
        client.getAllUniversities();
        assertEquals(200, client.getLastStatusCode());
    }

    // GET SINGLE UNIVERSITY
    @Test
    void create_and_get_university_dispatcher() throws IOException {
        client.start();
        client.getAllUniversities();

        var university = getUniversityExample();
        client.createUniversity(university);
        assertEquals(201, client.getLastStatusCode());

        assertTrue(client.isGetSingleUniversityAllowed());
        client.getSingleUniversity();
        assertEquals(200, client.getLastStatusCode());
        assertEquals(96, client.universityData().getFirst().getUniversityRank());
    }

    // GET ALL WITH FILTERING
    @Test
    void create_universities_filter_name() throws IOException {
        client.fillDatabase();

        var university = getUniversityExample();

        client.start();
        client.getAllUniversities();
        client.createUniversity(university);
        assertEquals(201, client.getLastStatusCode());

        client.start();
        client.getAllUniversities();
        assertTrue(client.isGetByNameAllowed());
        client.getByName("Uni");
        assertEquals(1, client.universityData().size());
    }

    // GET 20 UNIVERSITY 
    @Test
    void create_5_universities_and_get_all() throws IOException {
        for (int i = 0; i < 20; i++) {
            client.start();
            var university = getUniversityExample();

            client.createUniversity(university);
            assertEquals(201, client.getLastStatusCode());
        }
    
            client.start();
            assertTrue(client.isGetAllUniversitiesAllowed());
            client.getAllUniversities();
            assertEquals(200, client.getLastStatusCode());
            assertEquals(20, client.universityData().size());
    }


    // GET Single University ON Position 2
    @Test
    void create_two_universities_and_get_second() throws IOException {
        var university = getUniversityExample();
        var university2 = getUniversityExample();
        university2.setCountry("SecondCountry");

        client.start();
        client.getAllUniversities();
        client.createUniversity(university);
        assertEquals(201, client.getLastStatusCode());
        client.start();
        client.getAllUniversities();
        client.createUniversity(university2);
        assertEquals(201, client.getLastStatusCode());

        client.start();
        client.getAllUniversities();
        assertEquals(200, client.getLastStatusCode());
        assertEquals(2, client.universityData().size());

        client.setUniversityCursor(1);
        assertTrue(client.isGetSingleUniversityAllowed());
        client.getSingleUniversity(client.cursorUniversityData);
        assertEquals("SecondCountry", client.universityData().getFirst().getCountry());
    }

    // UPDATING UNIVERSITY
    @Test
    void create_university_and_update() throws IOException {
        client.start();
        client.getAllUniversities();
        var university = getUniversityExample();
        client.createUniversity(university);
        assertEquals(201, client.getLastStatusCode());

        assertTrue(client.isGetSingleUniversityAllowed());
        client.getSingleUniversity();
        assertEquals(1, client.universityData().size());

        var uniNew = client.universityData().getFirst();
        var newCountry = "NewCountryName";
        uniNew.setCountry(newCountry);

        assertTrue(client.isUpdateUniversityAllowed());
        client.updateUniversity(uniNew);
        assertEquals(204, client.getLastStatusCode());

        client.start();
        client.getAllUniversities();
        assertEquals(newCountry, client.universityData().getFirst().getCountry());
    }

    // UPDATING UNIVERSITY WITH WRONG INPUT 1
    @Test
    void create_and_update_false_outgoing() throws IOException {
        client.start();
        client.getAllUniversities();
        var university = getUniversityExample();
        client.createUniversity(university);
        assertEquals(201, client.getLastStatusCode());

        assertTrue(client.isGetSingleUniversityAllowed());
        client.getSingleUniversity();
        assertEquals(1, client.universityData().size());

        var uniNew = client.universityData().getFirst();
        var newOutgoing = -1;
        uniNew.setStudentsOutgoing(newOutgoing);

        assertTrue(client.isUpdateUniversityAllowed());
        client.updateUniversity(uniNew);
        assertEquals(400, client.getLastStatusCode());
    }

    // DELETING UNIVERSITY
    @Test
    void create_and_delete_one() throws IOException {
        client.start();
        client.getAllUniversities();

        assertTrue(client.isCreateUniversityAllowed());
        var university = getUniversityExample();
        client.createUniversity(university);
        assertEquals(201, client.getLastStatusCode());

        assertTrue(client.isGetSingleUniversityAllowed());
        client.getSingleUniversity();

        assertTrue(client.isDeleteUniversityAllowed());
        client.deleteUniversity();
        assertEquals(204, client.getLastStatusCode());
        assertTrue(client.isGetAllUniversitiesAllowed());
        client.getAllUniversities();
        assertEquals(200, client.getLastStatusCode());
        assertEquals(0, client.universityData().size());
    }

    // CREATING AND CHECKING FOR PAGING
    @Test
    void create_and_check_paging() throws IOException {
        client.fillDatabase();
        client.fillDatabase();
        client.start();

        assertTrue(client.isGetAllUniversitiesAllowed());
        client.getAllUniversities();
        assertEquals(200, client.getLastStatusCode());

        assertTrue(client.isNextAvailable());
        client.getNextPageOfUniversities();
        assertEquals(200, client.getLastStatusCode());
        assertTrue(client.isPrevAvailable());
        assertTrue(client.isNextAvailable());
        assertEquals(20, client.getNumberOfResults());
        assertEquals(70, client.getTotalNumberOfResults());

        client.getNextPageOfUniversities();
        client.getNextPageOfUniversities();
        assertEquals(200, client.getLastStatusCode());
        assertEquals(10, client.getNumberOfResults());
        assertFalse(client.isNextAvailable());
    }

    // Modules

    private void startAndCreateUniversity() throws IOException {
        client.start();
        client.getAllUniversities();

        var university = getUniversityExample();

        client.createUniversity(university);
        assertEquals(201, client.getLastStatusCode());
        client.getSingleUniversity();
    }

    // GET ALL MODULES
    @Test
    void create_and_get_all_modules() throws IOException {
        startAndCreateUniversity();

        assertTrue(client.isGetAllModulesAllowed());
        client.getAllModules();
        assertEquals(200, client.getLastStatusCode());
    }

    private static @NotNull ClientModule getModuleExample() {
        var module = new ClientModule();
        module.setModuleName("Module");
        module.setSemesters(2);
        module.setCredits(25);
        return module;
    }

    private static @NotNull ClientUni getUniversityExample() {
        var university = new ClientUni();
        university.setUniversityName("Uni");
        university.setCountry("CountryName");
        university.setDepartmentName("Science Department");
        university.setDepartmentWebsite("http://uni.countryname/cs");
        university.setContactPerson("Test DOE");
        university.setStudentsOutgoing(200);
        university.setStudentsIncoming(150);
        university.setNextSpringSemesterStart(LocalDate.of(2025, 3, 1));
        university.setNextAutumnSemesterStart(LocalDate.of(2025, 9, 1));
        university.setAnnualTuitionFees(1500.0);
        university.setUniversityRank(96);
        return university;
    }
}
