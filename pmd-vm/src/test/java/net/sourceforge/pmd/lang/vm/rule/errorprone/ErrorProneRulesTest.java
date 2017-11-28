/**
 * BSD-style license; for more info see http://pmd.sourceforge.net/license.html
 */

package net.sourceforge.pmd.lang.vm.rule.errorprone;

import net.sourceforge.pmd.testframework.SimpleAggregatorTst;

public class ErrorProneRulesTest extends SimpleAggregatorTst {

    private static final String RULESET = "category/vm/errorprone.xml";

    @Override
    public void setUp() {
        addRule(RULESET, "EmptyForeachStmt");
        addRule(RULESET, "EmptyIfStmt");
    }
}
