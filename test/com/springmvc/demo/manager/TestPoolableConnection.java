package com.springmvc.demo.manager;

/**
 * Created by Martha on 6/30/2016.
 */

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.apache.commons.dbcp.DriverConnectionFactory;
import org.apache.commons.dbcp.PoolableConnection;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.TesterDriver;
import org.apache.commons.pool.ObjectPool;
import org.apache.commons.pool.impl.GenericObjectPool;

import java.sql.Connection;
import java.sql.SQLException;



public class TestPoolableConnection extends TestCase {
    public TestPoolableConnection(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(TestPoolableConnection.class);
    }

    private ObjectPool pool = null;


    public void setUp() throws Exception {
        pool = new GenericObjectPool();

        PoolableConnectionFactory factory =
                new PoolableConnectionFactory(
                        new DriverConnectionFactory(new TesterDriver(),"jdbc:apache:commons:testdriver", null),
                        pool, null, null, true, true);
        pool.setFactory(factory);
    }

    @org.junit.Test
    public void testConnectionPool() throws Exception {
        // Grab a new connection from the pool
        Connection c = (Connection)pool.borrowObject();

        assertNotNull("Connection should be created and should not be null", c);
        assertEquals("There should be exactly one active object in the pool", 1, pool.getNumActive());

        // Now return the connection by closing it
        c.close(); // Can't be null

        assertEquals("There should now be zero active objects in the pool", 0, pool.getNumActive());
    }

    // Bugzilla Bug 33591: PoolableConnection leaks connections if the
    // delegated connection closes itself.
    @org.junit.Test
    public void testPoolableConnectionLeak() throws Exception {
        // 'Borrow' a connection from the pool
        Connection conn = (Connection)pool.borrowObject();

        // Now close our innermost delegate, simulating the case where the
        // underlying connection closes itself
        ((PoolableConnection)conn).getInnermostDelegate().close();

        // At this point, we can close the pooled connection. The
        // PoolableConnection *should* realise that its underlying
        // connection is gone and invalidate itself. The pool should have no
        // active connections.

        try {
            conn.close();
        } catch (SQLException e) {
            // Here we expect 'connection already closed', but the connection
            // should *NOT* be returned to the pool
        }

        assertEquals("The pool should have no active connections",
                0, pool.getNumActive());
    }
}