package com.svn.gsheetsserviceaccount.model;

import java.util.Arrays;
import java.util.List;

public enum GoogleSheetValues {
    VALUES_GOOD(Arrays.asList("12345", "NewContact", "91234344", "post@mail.kp")),
    VALUES_BADCODE(Arrays.asList("", "NewContact", "91234344", "post@mail.kp")),
    VALUES_BADNAME(Arrays.asList("12345", "", "91234344", "post@mail.kp")),
    VALUES_BADPHONE(Arrays.asList("12345", "NewContact", "", "post@mail.kp")),
    VALUES_BADEMAIL(Arrays.asList("12345", "NewContact", "91234344", "postmail.kp")),
    VALUES_INCORRECT_SIZE(Arrays.asList("12345", "NewContact", "91234344"))
    ;

    private final List<String> values;

    GoogleSheetValues(final List<String> values) {
        this.values = values;
    }

    public List<String> getValues() {
        return this.values;
    }
}
