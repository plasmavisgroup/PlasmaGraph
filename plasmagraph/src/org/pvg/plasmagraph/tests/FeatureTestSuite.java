package org.pvg.plasmagraph.tests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	CSVTest.class,
	MatlabTest.class,
	DataSetTest.class,
	TemplateTest.class,
	ReferenceTest.class,
	GraphTest.class,
	InterpolatorTest.class,
	OutlierSearchTest.class
})

public class FeatureTestSuite {
	// Empty Class
	// Exists only for the annotations above.
}