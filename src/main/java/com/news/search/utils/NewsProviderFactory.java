package com.news.search.utils;

import com.news.search.common.Constants;

public class NewsProviderFactory {

    public NewsProvider getNewsProvider(String newsChannel){
        if (newsChannel == null || newsChannel.isEmpty())
            return null;
        switch (newsChannel) {
            case Constants.GUARDIAN:
                return new GuardianNews();
            case Constants.NYTIMES:
                return new NYTimesNews();
            default:
                throw new IllegalArgumentException("Unknown channel "+newsChannel);
        }
    }
}
