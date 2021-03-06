package org.motechproject.ebodac.constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public final class EbodacConstants {

    public static final int LOGIN_WAIT_TIME = 1000;

    public static final String UI_CONFIG = "custom-ui.js";

    public static final String SUBJECT_ID_FIELD_NAME = "subjectId";

    public static final String SITE_ID_FOR_STAGE_I = "B05-SL10001";

    public static final String ZETES_UPDATE_EVENT = "zetes_update";
    public static final String EMAIL_CHECK_EVENT = "email_check";

    public static final String ZETES_URL = "zetes_url";
    public static final String ZETES_USERNAME = "zetes_username";
    public static final String ZETES_PASSWORD = "zetes_password";
    public static final String START_TIME = "start_time";

    public static final Pattern CSV_FILENAME_PATTERN = Pattern.compile(".*_(.*_.*)\\.csv");
    public static final String CSV_DATE_FORMAT = "yyyyMMdd_HHmmss";

    public static final String FETCHED_EMAIL_FLAG = "fetched";
    public static final String JOB_SUCCESS_STATUS = "COMPLETION";
    public static final String JOB_FAILURE_STATUS = "FAILURE";

    public static final String REPORT_DATE_FORMAT = "yyyy-MM-dd";
    public static final String REPORT_START_DATE_FORMAT = "yyyy-MM-ddHH:mm";
    public static final String DAILY_REPORT_EVENT = "daily_report_event";
    public static final String DAILY_REPORT_EVENT_START_DATE = "daily_report_event_start_date";
    public static final String DAILY_REPORT_EVENT_START_HOUR = "00:01";

    public static final String BOOSTER_RELATED_MESSAGES = "Booster related messages";

    public static final String PDF_EXPORT_FORMAT="pdf";
    public static final String CSV_EXPORT_FORMAT="csv";
    public static final String XLSX_EXPORT_FORMAT="xlsx";
    public static final String XLS_EXPORT_FORMAT="xls";

    public static final String EBODAC_MODULE="EBODAC Module";

    public static final List<String> DAYS_OF_WEEK = new ArrayList<>(Arrays.asList("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"));

    public static final List<String> AVAILABLE_CAMPAIGNS = new ArrayList<>(Arrays.asList("Screening", "Prime Vaccination Day",
            "Booster related messages", "Prime Vaccination Follow-up visit", "Boost Vaccination Day", "Boost Vaccination First Follow-up visit",
            "Boost Vaccination Second Follow-up visit", "Boost Vaccination Third Follow-up visit", "First Long-term Follow-up visit",
            "Second Long-term Follow-up visit", "Third Long-term Follow-up visit"));

    public static final String ENROLLMENT_DATE_FORMAT = "yyyy-MM-dd";

    public static final Map<String, String> DAILY_CLINIC_VISIT_SCHEDULE_REPORT_MAP = new LinkedHashMap<String, String>() {
        {
            put("Planned Visit Date","motechProjectedDate");
            put("Subject ID",       "subject.subjectId");
            put("Subject Name",     "subject.name");
            put("Phone Number",     "subject.phoneNumber");
            put("Address",          "subject.address");
            put("Visit type",       "type");
        }
    };

    public static final Map<String, String> FOLLOW_UPS_AFTER_PRIME_INJECTION_REPORT_MAP = new LinkedHashMap<String, String>() {
        {
            put("Subject Name",             "subject.name");
            put("Household name",           "subject.householdName");
            put("Head Of Household",        "subject.headOfHousehold");
            put("DOB",                      "subject.dateOfBirth");
            put("Gender",                   "subject.gender");
            put("Subject Community",        "subject.community");
            put("Address",                  "subject.address");
            put("Primer Vaccination Date",  "subject.primerVaccinationDate");
            put("Booster Vaccination Date", "subject.boosterVaccinationDate");
            put("Date Of Follow-up Visit",  "date");
        }
    };

    public static final Map<String, String> FOLLOW_UPS_MISSED_CLINIC_VISITS_REPORT_MAP = new LinkedHashMap<String, String>() {
        {
            put("Subject Name",             "subject.name");
            put("Household name",           "subject.householdName");
            put("Head Of Household",        "subject.headOfHousehold");
            put("DOB",                      "subject.dateOfBirth");
            put("Gender",                   "subject.gender");
            put("Subject Community",        "subject.community");
            put("Address",                  "subject.address");
            put("Visit type",               "type");
            put("Planned Visit Date",       "motechProjectedDate");
            put("No Of Days Exceeded Visit","noOfDaysExceededVisit");
        }
    };
}
