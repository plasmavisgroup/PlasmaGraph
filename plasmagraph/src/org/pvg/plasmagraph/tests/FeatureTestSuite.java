package org.pvg.plasmagraph.tests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	CSVTest.class,
	MATTest.class,
	DataSetTest.class,
	TemplateTest.class,
	GraphTest.class,
	InterpolatorTest.class,
	OutlierSearchTest.class,
	HeaderDataTest.class
})

@SuppressWarnings ("javadoc")
public class FeatureTestSuite {
	// Empty Class
	// Exists only for the annotations above.
}