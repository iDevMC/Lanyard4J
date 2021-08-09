package world.micks.lanyardapi.internal.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Is not null if the value exists.
 * @author Michael Faton
 */
@Retention( RetentionPolicy.RUNTIME )
@Documented
@Target(ElementType.METHOD)
public @interface NonNullValid {}