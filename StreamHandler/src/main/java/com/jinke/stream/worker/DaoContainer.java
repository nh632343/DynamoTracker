package com.jinke.stream.worker;

import com.jinke.basecommon.dao.impl.ThirdUserDynamoDaoImpl;
import com.jinke.basecommon.dao.impl.UserDetailDynamoDaoImpl;
import com.jinke.basecommon.dao.impl.UserDynamoDaoImpl;

public class DaoContainer {
    private static final ThirdUserDynamoDaoImpl thirdUserDynamoDao = new ThirdUserDynamoDaoImpl();

    private static final UserDynamoDaoImpl userDynamoDao = new UserDynamoDaoImpl();

    private static final UserDetailDynamoDaoImpl userDetailDynamoDao = new UserDetailDynamoDaoImpl();

    public static ThirdUserDynamoDaoImpl getThirdUserDynamoDao() {
        return thirdUserDynamoDao;
    }

    public static UserDynamoDaoImpl getUserDynamoDao() {
        return userDynamoDao;
    }

    public static UserDetailDynamoDaoImpl getUserDetailDynamoDao() {
        return userDetailDynamoDao;
    }
}
