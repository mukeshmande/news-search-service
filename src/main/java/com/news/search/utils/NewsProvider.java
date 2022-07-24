package com.news.search.utils;

import com.news.search.beans.NewsSearchResponseBean;
import com.news.search.exceptions.NewsSearchException;

public interface NewsProvider {

    NewsSearchResponseBean getNews(String query, int page) throws NewsSearchException;
}
