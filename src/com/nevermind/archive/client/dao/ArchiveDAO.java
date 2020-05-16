package com.nevermind.archive.client.dao;

import java.util.List;

public interface ArchiveDAO {

    boolean create();

    Record read(long id);

    List<Record> readAll();

    boolean update(long id, Record record);

}
