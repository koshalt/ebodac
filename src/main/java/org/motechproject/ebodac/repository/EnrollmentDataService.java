package org.motechproject.ebodac.repository;

import org.motechproject.ebodac.domain.Enrollment;
import org.motechproject.mds.annotations.Lookup;
import org.motechproject.mds.annotations.LookupField;
import org.motechproject.mds.query.QueryParams;
import org.motechproject.mds.service.MotechDataService;

import java.util.List;

public interface EnrollmentDataService extends MotechDataService<Enrollment> {

    @Lookup(name="Find Enrollments By Participant Id")
    List<Enrollment> findEnrollmentsBySubjectId(@LookupField(name = "externalId") String externalId);

    @Lookup
    List<Enrollment> findEnrollmentsByCampaignName(@LookupField(name = "campaignName") String campaignName);

    @Lookup(name="Find Enrollments By Participant Id")
    List<Enrollment> findEnrollmentsBySubjectId(@LookupField(name = "externalId") String externalId, QueryParams queryParams);

    long countFindEnrollmentsBySubjectId(@LookupField(name = "externalId") String externalId);
}
