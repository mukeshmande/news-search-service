package com.news.search.services;

import com.news.search.exceptions.NewsSearchException;

public interface NewsSearchService {
    String getNewsSearch(String query, int pageNumber) throws NewsSearchException;
}
