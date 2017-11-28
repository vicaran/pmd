/**
 * BSD-style license; for more info see http://pmd.sourceforge.net/license.html
 */

package net.sourceforge.pmd.lang.ecmascript.rule.codestyle;

import net.sourceforge.pmd.lang.ecmascript.ast.ASTFunctionNode;
import net.sourceforge.pmd.lang.ecmascript.ast.ASTName;
import net.sourceforge.pmd.lang.ecmascript.rule.AbstractEcmascriptRule;

public class AvoidShortFunctionNamesRule extends AbstractEcmascriptRule {

    @Override
    public Object visit(ASTFunctionNode functionNode, Object data) {
        if (functionNode.findDescendantsOfType(ASTName.class).size() > 0
                && functionNode.findDescendantsOfType(ASTName.class).get(0).getIdentifier() != null
                && functionNode.findDescendantsOfType(ASTName.class).get(0).getIdentifier().length() > 0
                && functionNode.findDescendantsOfType(ASTName.class).get(0).getIdentifier().length() < 3) {
            addViolation(data, functionNode);
        }
        return super.visit(functionNode, data);
    }
}
