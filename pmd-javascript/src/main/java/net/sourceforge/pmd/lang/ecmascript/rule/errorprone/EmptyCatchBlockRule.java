/**
 * BSD-style license; for more info see http://pmd.sourceforge.net/license.html
 */

package net.sourceforge.pmd.lang.ecmascript.rule.errorprone;

import net.sourceforge.pmd.lang.ecmascript.ast.ASTBlock;
import net.sourceforge.pmd.lang.ecmascript.ast.ASTCatchClause;
import net.sourceforge.pmd.lang.ecmascript.ast.ASTEmptyStatement;
import net.sourceforge.pmd.lang.ecmascript.rule.AbstractEcmascriptRule;

public class EmptyCatchBlockRule extends AbstractEcmascriptRule {

    @Override
    public Object visit(ASTCatchClause catchClause, Object data) {
        if (catchClause.findDescendantsOfType(ASTBlock.class).get(0).findDescendantsOfType(ASTEmptyStatement.class).isEmpty()) {
            addViolation(data, catchClause);
        }
        return super.visit(catchClause, data);
    }
}
