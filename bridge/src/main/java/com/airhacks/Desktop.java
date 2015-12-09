package com.airhacks;

import static java.lang.annotation.ElementType.TYPE;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;
import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Stereotype;

/**
 *
 * @author airhacks.com
 */
@Alternative
@Stereotype
@Retention(RUNTIME)
@Target({TYPE})
public @interface Desktop {
}
