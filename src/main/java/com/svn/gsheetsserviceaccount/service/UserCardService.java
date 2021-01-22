package com.svn.gsheetsserviceaccount.service;

import com.svn.gsheetsserviceaccount.model.UserCard;

import java.util.Optional;

public interface UserCardService {

    public Optional<UserCard> save(final UserCard contact);
}
