package com.nevermind.archive.client.dao;

import java.util.List;

public class ServerArchiveDAO implements ArchiveDAO {

    @Override
    public boolean create() {
        return false;
    }

    @Override
    public Record read(long id) {
        return null;
    }

    @Override
    public List<Record> readAll() {
        return null;
    }

    @Override
    public boolean update(long id, Record record) {
        return false;
    }
}
