package org.homework.spring.service;

import org.homework.spring.config.AppProps;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class LocalizationService {

    private final MessageSource messageSource;

    private final Locale locale;

    public LocalizationService(AppProps appProps, MessageSource messageSource) {
        this.messageSource = messageSource;
        locale = appProps.getLocale();
    }

    public String getMessage(String bundleKey, String[] extra) {
        return messageSource.getMessage(bundleKey, extra, locale);
    }

    public String getMessage(String bundleKey) {
        return messageSource.getMessage(bundleKey, null, locale);
    }
}
