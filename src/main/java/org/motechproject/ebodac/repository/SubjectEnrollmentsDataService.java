package org.motechproject.ebodac.repository;

import org.motechproject.ebodac.domain.EnrollmentStatus;
import org.motechproject.ebodac.domain.SubjectEnrollments;
import org.motechproject.mds.annotations.Lookup;
import org.motechproject.mds.annotations.LookupField;
import org.motechproject.mds.service.MotechDataService;
import org.motechproject.mds.util.Constants;

import java.util.List;

public interface SubjectEnrollmentsDataService extends MotechDataService<SubjectEnrollments> {

    @Lookup(name="Find unique Enrollment By Participant Id")
    SubjectEnrollments findEnrollmentBySubjectId(@LookupField(name = "subject.subjectId") String subjectId);

    @Lookup(name="Find Enrollments By Participant Id")
    List<SubjectEnrollments> findEnrollmentsByMatchesCaseInsensitiveSubjectId(@LookupField(name = "subject.subjectId",
            customOperator = Constants.Operators.MATCHES_CASE_INSENSITIVE) String subjectId);

    @Lookup(name="Find Enrollments By Participant Name")
    List<SubjectEnrollments> findEnrollmentsBySubjectName(@LookupField(name = "subject.name",
            customOperator = Constants.Operators.MATCHES_CASE_INSENSITIVE) String name);

    @Lookup
    List<SubjectEnrollments> findEnrollmentsByStatus(@LookupField(name = "status") EnrollmentStatus status);
}
