/**
 * BSD-style license; for more info see http://pmd.sourceforge.net/license.html
 */

package net.sourceforge.pmd.lang.java.rule.design;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import net.sourceforge.pmd.lang.java.ast.ASTAnyTypeDeclaration;
import net.sourceforge.pmd.lang.java.ast.ASTCompilationUnit;
import net.sourceforge.pmd.lang.java.ast.ASTMethodDeclaration;
import net.sourceforge.pmd.lang.java.ast.ASTMethodOrConstructorDeclaration;
import net.sourceforge.pmd.lang.java.metrics.JavaMetrics;
import net.sourceforge.pmd.lang.java.metrics.api.JavaClassMetricKey;
import net.sourceforge.pmd.lang.java.metrics.api.JavaOperationMetricKey;
import net.sourceforge.pmd.lang.java.metrics.impl.CycloMetric;
import net.sourceforge.pmd.lang.java.metrics.impl.CycloMetric.CycloOption;
import net.sourceforge.pmd.lang.java.rule.AbstractJavaMetricsRule;
import net.sourceforge.pmd.lang.metrics.MetricOptions;
import net.sourceforge.pmd.lang.metrics.ResultOption;
import net.sourceforge.pmd.properties.EnumeratedMultiProperty;
import net.sourceforge.pmd.properties.IntegerProperty;


/**
 * Cyclomatic complexity rule using metrics.
 *
 * @author Clément Fournier, based on work by Alan Hohn and Donald A. Leckie
 * @see CycloMetric
 * @version 6.0.0
 */
public class CyclomaticComplexityRule extends AbstractJavaMetricsRule {
    
    private static final Logger LOG = Logger.getLogger(CyclomaticComplexityRule.class.getName());

    // Deprecated, kept for backwards compatibility (6.0.0)
    @Deprecated
    private static final IntegerProperty REPORT_LEVEL_DESCRIPTOR
        = IntegerProperty.named("reportLevel")
                         .desc("Deprecated! Cyclomatic Complexity reporting threshold")
                         .range(1, 30).defaultValue(10).uiOrder(1.0f).build();


    private static final IntegerProperty CLASS_LEVEL_DESCRIPTOR
        = IntegerProperty.named("classReportLevel")
                         .desc("Total class complexity reporting threshold")
                         .range(1, 600).defaultValue(80).uiOrder(1.0f).build();

    private static final IntegerProperty METHOD_LEVEL_DESCRIPTOR
        = IntegerProperty.named("methodReportLevel")
                         .desc("Cyclomatic complexity reporting threshold")
                         .range(1, 50).defaultValue(10).uiOrder(1.0f).build();

    private static final Map<String, CycloOption> OPTION_MAP;
    
    static {
        OPTION_MAP = new HashMap<>();
        OPTION_MAP.put(CycloOption.IGNORE_BOOLEAN_PATHS.valueName(), CycloOption.IGNORE_BOOLEAN_PATHS);
        OPTION_MAP.put(CycloOption.CONSIDER_ASSERT.valueName(), CycloOption.CONSIDER_ASSERT);
    }
    
    private static final EnumeratedMultiProperty<CycloOption> CYCLO_OPTIONS_DESCRIPTOR
        = EnumeratedMultiProperty.<CycloOption>named("cycloOptions").type(CycloOption.class)
                                                                    .desc("Choose options for the computation of Cyclo")
                                                                    .mappings(OPTION_MAP)
                                                                    .defaultValues(Collections.<CycloOption>emptyList())
                                                                    .uiOrder(3.0f).build();
    private int methodReportLevel;
    private int classReportLevel;
    private MetricOptions cycloOptions;


    public CyclomaticComplexityRule() {
        definePropertyDescriptor(CLASS_LEVEL_DESCRIPTOR);
        definePropertyDescriptor(METHOD_LEVEL_DESCRIPTOR);
        definePropertyDescriptor(CYCLO_OPTIONS_DESCRIPTOR);
        definePropertyDescriptor(REPORT_LEVEL_DESCRIPTOR);
    }

    // Kept for backwards compatibility // TODO remove the property sometime
    private void assignReportLevelsCompat() {
        int methodLevel = getProperty(METHOD_LEVEL_DESCRIPTOR);
        int classLevel = getProperty(CLASS_LEVEL_DESCRIPTOR);
        int commonLevel = getProperty(REPORT_LEVEL_DESCRIPTOR);
        
        if (methodLevel == METHOD_LEVEL_DESCRIPTOR.defaultValue()
            && classLevel == CLASS_LEVEL_DESCRIPTOR.defaultValue()
            && commonLevel != REPORT_LEVEL_DESCRIPTOR.defaultValue()) {
            LOG.warning("Rule CyclomaticComplexity uses deprecated property 'reportLevel'. " 
                        + "Future versions of PMD will remove support for this property. " 
                        + "Please use 'methodReportLevel' and 'classReportLevel' instead!");
            methodLevel = commonLevel;
            classLevel = commonLevel * 8;
        }
        
        methodReportLevel = methodLevel;
        classReportLevel = classLevel;
    }

    @Override
    public Object visit(ASTCompilationUnit node, Object data) {
        // methodReportLevel = getProperty(METHOD_LEVEL_DESCRIPTOR);
        // classReportLevel = getProperty(CLASS_LEVEL_DESCRIPTOR);
        assignReportLevelsCompat();
        
        cycloOptions = MetricOptions.ofOptions(getProperty(CYCLO_OPTIONS_DESCRIPTOR));


        super.visit(node, data);
        return data;
    }


    @Override
    public Object visit(ASTAnyTypeDeclaration node, Object data) {

        super.visit(node, data);

        if (JavaClassMetricKey.WMC.supports(node)) {
            int classWmc = (int) JavaMetrics.get(JavaClassMetricKey.WMC, node, cycloOptions);

            if (classWmc >= classReportLevel) {
                int classHighest = (int) JavaMetrics.get(JavaOperationMetricKey.CYCLO, node, cycloOptions, ResultOption.HIGHEST);

                String[] messageParams = {node.getTypeKind().name().toLowerCase(),
                                          node.getImage(),
                                          " total",
                                          classWmc + " (highest " + classHighest + ")", };

                addViolation(data, node, messageParams);
            }
        }
        return data;
    }


    @Override
    public final Object visit(ASTMethodOrConstructorDeclaration node, Object data) {

        int cyclo = (int) JavaMetrics.get(JavaOperationMetricKey.CYCLO, node, cycloOptions);
        if (cyclo >= methodReportLevel) {
            addViolation(data, node, new String[]{node instanceof ASTMethodDeclaration ? "method" : "constructor",
                                                  node.getQualifiedName().getOperation(),
                                                  "",
                                                  "" + cyclo, });
        }

        return data;
    }

}
