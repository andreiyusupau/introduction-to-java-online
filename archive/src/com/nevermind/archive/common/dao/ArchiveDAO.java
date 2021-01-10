package com.nevermind.archive.common.dao;

import java.util.List;
import com.nevermind.archive.client.model.Record;

//интерфейс DAO для работы с делами. Необходим для унификации доступа со стороны контроллера независимо от типа базы данных
public interface ArchiveDAO {

    boolean create(Record record);

    Record read(long id);

    List<Record> readAll();

    boolean update(Record record);

}
