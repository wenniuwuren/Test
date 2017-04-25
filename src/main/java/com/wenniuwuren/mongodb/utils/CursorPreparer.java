package com.wenniuwuren.mongodb.utils;

import com.mongodb.DBCursor;

/**
 * 排序及过滤接口
 *
 */

public interface CursorPreparer {
    DBCursor prepare(DBCursor cursor);
}