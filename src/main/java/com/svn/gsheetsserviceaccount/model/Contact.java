package com.svn.gsheetsserviceaccount.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Contact {

    private final String id;
    private final String code;
    private final String name;
    private final String phone;
    private final String email;
}
