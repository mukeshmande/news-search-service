package com.news.search.utils;

import com.news.search.beans.guardian.GuardianBean;
import com.news.search.beans.guardian.GuardianResponseBean;
import com.news.search.beans.NewsDetailsResponseBean;
import com.news.search.beans.NewsSearchResponseBean;
import com.news.search.common.Constants;
import com.news.search.configs.RestTemplateConfig;
import com.news.search.exceptions.NewsSearchException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class GuardianNews implements NewsProvider{

    private static final Logger LOGGER = LoggerFactory.getLogger(GuardianNews.class);

    @Override
    public NewsSearchResponseBean getNews(String query, int page) throws NewsSearchException {
        LOGGER.info("getting news from The Guardian: {}", query);
        NewsSearchResponseBean newsSearchResponseBean = new NewsSearchResponseBean();
        List<NewsDetailsResponseBean> newsDetailsResponseBeanList = new ArrayList<>();

        GuardianBean guardianBean;
        try {
            guardianBean = RestTemplateConfig.getNewsFromProviderAPI(GuardianBean.class, Constants.GUARDIAN_API, query, page, Constants.GUARDIAN_API_KEY);

        GuardianResponseBean guardianResponseBean = guardianBean.getResponse();
        newsSearchResponseBean.setTotalPages(guardianResponseBean.getPages());
        guardianResponseBean.getResults().forEach(items -> {
            NewsDetailsResponseBean detailsResponseBean = new NewsDetailsResponseBean();
            detailsResponseBean.setNewsWebsite(Constants.GUARDIAN);
            detailsResponseBean.setHeadLine(items.getWebTitle());
            detailsResponseBean.setUrl(items.getWebUrl());
            newsDetailsResponseBeanList.add(detailsResponseBean);
        });
            newsSearchResponseBean.setNewsDetailsResponseBeanList(newsDetailsResponseBeanList);
        } catch (Exception e){
            throw new NewsSearchException(e.getMessage());
        }
        return newsSearchResponseBean;
    }
}
