package com.airhacks;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

/**
 *
 * @author airhacks.com
 */
@Model
public class Index {

    @Inject
    Reports reports;

    public String getMessage() {
        reports.reports();
        return "seems to work";
    }

}
