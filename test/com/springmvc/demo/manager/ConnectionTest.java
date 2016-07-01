package com.springmvc.demo.manager;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Slf4jReporter;
import com.codahale.metrics.Timer;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Created by Martha on 6/30/2016.
 */
public abstract  class ConnectionTest {

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(ConnectionTest.class);

    private static final int MAX_ITERATIONS = 1000;

    private Slf4jReporter logReporter;

    private Timer timer;

    protected abstract DataSource getDataSource();

    @Before
    public void init() {
        MetricRegistry metricRegistry = new MetricRegistry();
        this.logReporter = Slf4jReporter
                .forRegistry(metricRegistry)
                .outputTo(LOGGER)
                .build();
        timer = metricRegistry.timer("connection");
    }

    @Test
    public void testOpenCloseConnections() throws SQLException {
        for (int i = 0; i < MAX_ITERATIONS; i++) {
            Timer.Context context = timer.time();
            getDataSource().getConnection().close();
            context.stop();
        }
        logReporter.report();
    }
}
