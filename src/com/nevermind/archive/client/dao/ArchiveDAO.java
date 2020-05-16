package com.nevermind.archive.client.dao;

import java.util.List;
import com.nevermind.archive.client.model.Record;

public interface ArchiveDAO {

    boolean create(Record record);

    Record read(long id);

    List<Record> readAll();

    boolean update(long id, Record record);

}
