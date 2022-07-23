package com.news.search.utils;

import com.news.search.beans.GuardianBean;
import com.news.search.beans.GuardianResponseBean;
import com.news.search.beans.NewsDetailsResponseBean;
import com.news.search.beans.NewsSearchResponseBean;
import com.news.search.common.Constants;
import com.news.search.configs.RestTemplateConfig;

import java.util.ArrayList;
import java.util.List;

public class GuardianNews implements NewsProvider{

    @Override
    public NewsSearchResponseBean getNews(String query, int page) {

        NewsSearchResponseBean newsSearchResponseBean = new NewsSearchResponseBean();
        List<NewsDetailsResponseBean> newsDetailsResponseBeanList = new ArrayList<>();
        GuardianBean guardianBean = RestTemplateConfig.getNewsFromProviderAPI(GuardianBean.class, Constants.GUARDIAN_API, query, page, Constants.GUARDIAN_API_KEY);
        GuardianResponseBean guardianResponseBean = guardianBean.getResponse();
        newsSearchResponseBean.setTotalPages(newsSearchResponseBean.getTotalPages()+guardianResponseBean.getPages());
        newsSearchResponseBean.setSearchKeyword(query);
        newsSearchResponseBean.setCurrentPage(page);

        guardianResponseBean.getResults().forEach(items -> {
            NewsDetailsResponseBean detailsResponseBean = new NewsDetailsResponseBean();
            detailsResponseBean.setNewsWebsite(Constants.GUARDIAN);
            detailsResponseBean.setHeadLine(items.getWebTitle());
            detailsResponseBean.setUrl(items.getWebUrl());
            newsDetailsResponseBeanList.add(detailsResponseBean);
        });


        newsSearchResponseBean.setNewsDetailsResponseBeanList(newsDetailsResponseBeanList);
        return newsSearchResponseBean;
    }
}
