package com.news.search.utils;

import com.news.search.beans.NewsSearchResponseBean;

public interface NewsProvider {

    NewsSearchResponseBean getNews(String query, int page);
}
