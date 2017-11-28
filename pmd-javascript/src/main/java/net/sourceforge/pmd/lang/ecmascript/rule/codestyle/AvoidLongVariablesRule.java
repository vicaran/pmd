/**
 * BSD-style license; for more info see http://pmd.sourceforge.net/license.html
 */

package net.sourceforge.pmd.lang.ecmascript.rule.codestyle;

import net.sourceforge.pmd.lang.ecmascript.ast.ASTName;
import net.sourceforge.pmd.lang.ecmascript.ast.ASTVariableInitializer;
import net.sourceforge.pmd.lang.ecmascript.rule.AbstractEcmascriptRule;

public class AvoidLongVariablesRule extends AbstractEcmascriptRule {

    @Override
    public Object visit(ASTVariableInitializer variableInitializer, Object data) {
        if (variableInitializer.findDescendantsOfType(ASTName.class).size() > 0
                && variableInitializer.findDescendantsOfType(ASTName.class).get(0).getIdentifier() != null
                && variableInitializer.findDescendantsOfType(ASTName.class).get(0).getIdentifier().length() >= 17) {
            addViolation(data, variableInitializer);
        }
        return super.visit(variableInitializer, data);
    }
}
