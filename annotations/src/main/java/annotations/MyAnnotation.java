package annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by peacepassion on 15/12/5.
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.METHOD)
public @interface MyAnnotation {

}
