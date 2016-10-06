package com.github.itpkg.magnolia.auth;

import com.github.itpkg.magnolia.auth.repositiries.LocaleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.AbstractMessageSource;

import javax.annotation.Resource;
import java.text.MessageFormat;
import java.util.Locale;

/**
 * Created by bandari on 16-10-5.
 */
public class DatabaseMessageSource extends AbstractMessageSource {
    @Override
    protected MessageFormat resolveCode(String code, Locale locale) {
        com.github.itpkg.magnolia.auth.models.Locale l = localeRepository.findByLangAndCode(locale.toLanguageTag(), code);


        return createMessageFormat(l == null ?
                (getParentMessageSource() == null ?
                        null :
                        getParentMessageSource().getMessage(code, null, locale)
                ) :
                l.getBody(), locale);
    }


    @Resource
    LocaleRepository localeRepository;

    private static final Logger logger = LoggerFactory
            .getLogger(DatabaseMessageSource.class);


}
