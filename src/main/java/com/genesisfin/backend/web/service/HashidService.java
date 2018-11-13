package com.genesisfin.backend.web.service;

import org.hashids.Hashids;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class HashidService {
    private Hashids hashids;

    public HashidService(@Value("${hashid.salt}") String salt,
                         @Value("${hashid.length}") int length,
                         @Value("${hashid.alphabet}") String alphbet) {
        this.hashids = new Hashids(salt, length, alphbet);
    }

    public String encode(long id) {
        return hashids.encode(id);
    }

    public long decode(String hex) {
        return hashids.decode(hex)[0];
    }
}
