/**
 * BSD-style license; for more info see http://pmd.sourceforge.net/license.html
 */

package net.sourceforge.pmd.lang.ecmascript.rule.codestyle;

import net.sourceforge.pmd.lang.ecmascript.ast.ASTForLoop;
import net.sourceforge.pmd.lang.ecmascript.ast.ASTName;
import net.sourceforge.pmd.lang.ecmascript.ast.ASTVariableInitializer;
import net.sourceforge.pmd.lang.ecmascript.rule.AbstractEcmascriptRule;

public class ShortVariableRule extends AbstractEcmascriptRule {

    @Override
    public Object visit(ASTVariableInitializer functionNode, Object data) {
        if (functionNode.findDescendantsOfType(ASTName.class).get(0).getIdentifier().length() < 3 &&
                functionNode.getParentsOfType(ASTForLoop.class).isEmpty()) {
            addViolation(data, functionNode);
        }
        return super.visit(functionNode, data);
    }
}
