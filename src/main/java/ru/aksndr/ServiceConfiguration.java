package ru.aksndr;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.aksndr.datalayer.CollectionLayer;
import ru.aksndr.datalayer.DataLayer;

/**
 * User: a.arzamastsev Date: 18.08.14 Time: 14:34
 */
@Configuration
public class ServiceConfiguration {

    @Bean
    public DataLayer getDataLayer() {
        return new CollectionLayer();
    }

}
