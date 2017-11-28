/**
 * BSD-style license; for more info see http://pmd.sourceforge.net/license.html
 */

package net.sourceforge.pmd.lang.ecmascript.rule.errorprone;

import net.sourceforge.pmd.lang.ecmascript.ast.ASTEmptyStatement;
import net.sourceforge.pmd.lang.ecmascript.ast.ASTExpressionStatement;
import net.sourceforge.pmd.lang.ecmascript.ast.ASTIfStatement;
import net.sourceforge.pmd.lang.ecmascript.ast.ASTScope;
import net.sourceforge.pmd.lang.ecmascript.rule.AbstractEcmascriptRule;

public class EmptyIfStmtRule extends AbstractEcmascriptRule {

    @Override
    public Object visit(ASTIfStatement ifStatement, Object data) {

        // In case of absence of curly braces, a scope won't be identified therefore the length of the EmptyStatement is checked
        if (ifStatement.findDescendantsOfType(ASTScope.class).size() == 0) {
            if (ifStatement.findDescendantsOfType(ASTEmptyStatement.class).size() > 0) {
                if (ifStatement.findDescendantsOfType(ASTEmptyStatement.class).get(0).getEndColumn()
                        - ifStatement.findDescendantsOfType(ASTEmptyStatement.class).get(0).getBeginColumn()
                        < 3) {
                    addViolation(data, ifStatement);
                }
            }

        } else if (ifStatement.findDescendantsOfType(ASTScope.class).size() > 0
                && ifStatement.findDescendantsOfType(ASTScope.class).get(0).findDescendantsOfType(ASTEmptyStatement.class).isEmpty()
                && ifStatement.findDescendantsOfType(ASTScope.class).get(0).findDescendantsOfType(ASTExpressionStatement.class).isEmpty()) {
            addViolation(data, ifStatement);
        }
        return super.visit(ifStatement, data);
    }
}
