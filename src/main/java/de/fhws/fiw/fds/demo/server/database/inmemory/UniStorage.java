package de.fhws.fiw.fds.demo.server.database.inmemory;

import java.time.LocalDate;
import java.util.function.Predicate;

import de.fhws.fiw.fds.demo.server.UniversityDao;
import de.fhws.fiw.fds.demo.server.api.models.Uni;
import de.fhws.fiw.fds.sutton.server.database.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.inmemory.AbstractInMemoryStorage;
import de.fhws.fiw.fds.sutton.server.database.inmemory.InMemoryPaging;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;

public class UniStorage extends AbstractInMemoryStorage<Uni> implements UniversityDao {

    @Override
    public CollectionModelResult<Uni> readByUniversityNameCountry(String universityName, String uni_location, SearchParameter searchParameter) {
        return InMemoryPaging.page(this.readAllByPredicate(
                byUniversityNameCountry(universityName, uni_location),
                searchParameter
        ), searchParameter.getOffset(), searchParameter.getSize());
    }

    private Predicate<Uni> byUniversityNameCountry(String universityName, String uni_location) {
        return p -> (universityName.isEmpty() || p.getUniversityName().equals(universityName)) && (uni_location.isEmpty() || p.getCountry().equals(uni_location));
    }

    public void resetDatabase() {
        this.storage.clear();
    }

    @Override
    public void initializeDatabase() {
        for (int i = 1; i <= 26; i++) {
            Uni university = getUniversity("%c University", (char) ('A' + i - 1), i, "India");
            this.create(university);
        }

        for (int i = 1; i <= 9; i++) {
            Uni university = getUniversity("%d University", i, i, "Germany");
            this.create(university);
        }
    }

    private static Uni getUniversity(String format, int i, int j, String Germany) {
        String universityName = String.format(format, i);
        Uni university = new Uni();
        university.setUniversityName(universityName);
        university.setCountry(Germany);
        university.setDepartmentName("Science Dept");
        university.setDepartmentWebsite("http://www.def.edu/cs");
        university.setContactPerson("Dr. Smitha Vinod");
        university.setStudentsOutgoing(200);
        university.setStudentsIncoming(150);

        LocalDate springSemesterStart = LocalDate.parse("2025-01-10");
        LocalDate autumnSemesterStart = LocalDate.parse("2025-09-10");

        university.setNextSpringSemesterStart(springSemesterStart);
        university.setNextAutumnSemesterStart(autumnSemesterStart);

        university.setAnnualTuitionFees(1500.0);
        university.setUniversityRank(j);
        return university;
    }
}
