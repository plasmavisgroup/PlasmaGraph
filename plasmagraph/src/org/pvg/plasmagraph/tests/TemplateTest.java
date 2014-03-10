package org.pvg.plasmagraph.tests;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;
import org.pvg.plasmagraph.utils.template.Template;
import org.pvg.plasmagraph.utils.types.ChartType;
import org.pvg.plasmagraph.utils.types.InterpolationType;
import org.pvg.plasmagraph.utils.types.OutlierResponse;

public class TemplateTest {
    String default_template_path = "C:/Users/tako/Documents/GitHub/PlasmaGraph/plasmagraph/test/template/template_test.tem";
    
    @Test
    public void testSaveAndOpenTemplate () {
        Template t1 = new Template ();
        t1.setChartName ("Name");
        t1.setChartType (ChartType.XY_GRAPH);
        t1.setInterpolationType (InterpolationType.SPLINE);
        t1.setOutlierResponse (OutlierResponse.REMOVE);
        
        // Save it
        t1.saveTemplate (default_template_path);
        
        // Now open it!
        
        Template t2 = new Template ();
        t2.openTemplate (new File (default_template_path));
        
        assertTrue ("Name Changed? ", t2.getChartName ().equals ("Name"));
        assertTrue ("Chart Type Changed? ", t2.getChartType ().equals (ChartType.XY_GRAPH));
        assertEquals ("Interpolation Type Changed? ", t2.getInterpolationType (), InterpolationType.SPLINE);
        assertTrue ("Outlier Response Changed? ", t2.getOutlierResponse ().equals (OutlierResponse.REMOVE));
    }
    
}
