package com.manager.dao;

import com.manager.domain.User;

public interface DaoUser {

    public User searchUserByLogin(String login);

    public User save(User user);

}
