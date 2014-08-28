package ru.aksndr.commons;

import org.apache.commons.collections.Transformer;
import ru.aksndr.model.Counter;

import java.util.Collection;

/**
 * User: a.arzamastsev Date: 27.08.14 Time: 9:18
 */
public class CounterTransformer implements Transformer {
    @Override
    public Long transform(Object o) {
        ((Counter) o).getSn();
        return ((Counter) o).getSn();
    }

    public static String getInQueryFromCollection(Collection<Long> counterSns) {
        return counterSns.toString().replaceAll("\\[", "(").replaceAll("\\]", ")");
    }
}
