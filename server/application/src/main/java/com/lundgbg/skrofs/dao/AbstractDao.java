package com.lundgbg.skrofs.dao;

import java.util.Date;

abstract class AbstractDao {

    protected Date toUtilDate(final java.sql.Timestamp sqlTimestamp) {
        return sqlTimestamp != null ? new Date(sqlTimestamp.getTime()) : null;
    }

    protected Date toUtilDate(final java.sql.Date sqlDate) {
        return sqlDate != null ? new Date(sqlDate.getTime()) : null;
    }
}
