package ru.aksndr.commons;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;
import ru.aksndr.model.BaseTest;
import ru.aksndr.model.Counter;

import java.util.Collection;
import java.util.Set;

import static junit.framework.Assert.assertNotNull;

/**
 * User: a.arzamastsev Date: 27.08.14 Time: 9:42
 */
public class CommonsTest extends BaseTest {
    @Test
    public void test() {
        Set<Counter> counters = api.getCountersByFlatId(1L);
        Collection<Long> counterSns = CollectionUtils.collect(counters, new CounterTransformer());
        String str = CounterTransformer.getInQueryFromCollection(counterSns);
        assertNotNull(str);

    }
}
