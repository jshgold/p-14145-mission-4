package csh.service;

import csh.entity.WiseSaying;
import csh.repository.WiseSayingRepository;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class WiseSayingService {
    private int id = 1;
    private final WiseSayingRepository repo = new WiseSayingRepository();

    public int create(String content, String author) {
        WiseSaying ws = new WiseSaying
                .Builder()
                .id(id++)
                .author(author)
                .content(content)
                .build();
        repo.save(ws);
        return ws.getId();
    }

    public Map<Integer, WiseSaying> getList() {
        Map<Integer, WiseSaying> map = repo.findAll();
        return map;
    }

    public void update(int targetId, String content, String author) {
        WiseSaying ws = new WiseSaying
                .Builder()
                .id(targetId)
                .author(author)
                .content(content)
                .build();
        repo.update(ws);
    }

    public WiseSaying getWiseSayingById(int targetId) {
        WiseSaying ws = repo.findById(targetId);
        return ws;
    }

    public void deleteWiseSayingById(int targetId) {
        repo.delete(targetId);
    }
}
