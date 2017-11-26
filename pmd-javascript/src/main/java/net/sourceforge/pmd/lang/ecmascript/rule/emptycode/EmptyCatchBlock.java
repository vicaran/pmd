/**
 * BSD-style license; for more info see http://pmd.sourceforge.net/license.html
 */

package net.sourceforge.pmd.lang.ecmascript.rule.emptycode;

import net.sourceforge.pmd.lang.ecmascript.ast.ASTBlock;
import net.sourceforge.pmd.lang.ecmascript.ast.ASTCatchClause;
import net.sourceforge.pmd.lang.ecmascript.ast.ASTEmptyStatement;
import net.sourceforge.pmd.lang.ecmascript.rule.AbstractEcmascriptRule;

public class EmptyCatchBlock extends AbstractEcmascriptRule {

    @Override
    public Object visit(ASTCatchClause functionNode, Object data) {
        if (functionNode.findDescendantsOfType(ASTBlock.class).get(0).findDescendantsOfType(ASTEmptyStatement.class).isEmpty()) {
            addViolation(data, functionNode);
        }
        return super.visit(functionNode, data);
    }
}
