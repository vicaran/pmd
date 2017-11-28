/**
 * BSD-style license; for more info see http://pmd.sourceforge.net/license.html
 */

package net.sourceforge.pmd.lang.ecmascript.rule.errorprone;

import net.sourceforge.pmd.testframework.SimpleAggregatorTst;

public class ErrorProneRulesTest extends SimpleAggregatorTst {

    private static final String RULESET = "category/ecmascript/errorprone.xml";

    @Override
    public void setUp() {
        addRule(RULESET, "AvoidTrailingComma");
        addRule(RULESET, "EqualComparison");
        addRule(RULESET, "InnaccurateNumericLiteral");
        addRule(RULESET, "EmptyIfStmt");
        addRule(RULESET, "EmptyCatchBlock");
    }
}
