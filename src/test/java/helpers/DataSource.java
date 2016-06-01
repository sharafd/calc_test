package helpers;

/**
 *  Интерфейс с типами для источников данных
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DataSource {

    enum Type {
        RESOURCE
    }

    String value();
    Type type();

}
