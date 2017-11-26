/**
 * BSD-style license; for more info see http://pmd.sourceforge.net/license.html
 */

package net.sourceforge.pmd.lang.ecmascript.rule.emptycode;

import net.sourceforge.pmd.testframework.SimpleAggregatorTst;

public class EmptyCodeRulesTest extends SimpleAggregatorTst {

    private static final String RULESET = "category/ecmascript/emptycode.xml";

    @Override
    public void setUp() {
        addRule(RULESET, "EmptyIfStmt");
    }
}
