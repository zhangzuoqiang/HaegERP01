package org.haegerp.entity;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ArticleTest extends TestCase {
	/**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public ArticleTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( ArticleTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testArticle()
    {
        assertTrue( true );
    }
}
