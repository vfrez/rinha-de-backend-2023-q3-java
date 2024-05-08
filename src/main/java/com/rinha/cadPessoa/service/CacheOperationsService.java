package com.rinha.cadPessoa.service;

import com.rinha.cadPessoa.model.Pessoa;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ConcurrentMap;

@Slf4j
@Service
public class CacheOperationsService {

    @Autowired
    private CacheManager cacheManager;

    public void clearCache() {
        cacheManager.getCache("pessoas").clear();
    }

    public void storeInCache(Pessoa pessoa) {
        Cache pessoasCache = cacheManager.getCache("pessoas");
        pessoasCache.put(pessoa.getId(), pessoa);
    }

    public List<Pessoa> findByTermInCache(String term) {
        ConcurrentMap<Object, Object> cacheMap = getCacheMap();

        return cacheMap.values().stream().filter(value -> {
            Pessoa pessoa = (Pessoa) value;
            String searchableValues = pessoa.getNome() + pessoa.getApelido() + pessoa.getStack();
            return searchableValues.contains(term);
        }).map(value -> (Pessoa) value).toList();
    }

    public long countByApelidoInCache(String apelido) {
        ConcurrentMap<Object, Object> cacheMap = getCacheMap();

        return cacheMap.values().stream().filter(value -> {
            Pessoa pessoa = (Pessoa) value;
            return pessoa.getApelido().equalsIgnoreCase(apelido);
        }).map(value -> (Pessoa) value).count();
    }

    private ConcurrentMap<Object, Object> getCacheMap() {
        CaffeineCache caffeineCache = (CaffeineCache) cacheManager.getCache("pessoas");
        ConcurrentMap<Object, Object> cacheMap = caffeineCache.getNativeCache().asMap();
        return cacheMap;
    }
}
