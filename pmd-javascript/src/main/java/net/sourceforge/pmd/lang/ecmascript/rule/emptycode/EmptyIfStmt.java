/**
 * BSD-style license; for more info see http://pmd.sourceforge.net/license.html
 */

package net.sourceforge.pmd.lang.ecmascript.rule.emptycode;

import java.util.Objects;

import net.sourceforge.pmd.lang.ecmascript.ast.ASTExpressionStatement;
import net.sourceforge.pmd.lang.ecmascript.ast.ASTIfStatement;
import net.sourceforge.pmd.lang.ecmascript.rule.AbstractEcmascriptRule;

public class EmptyIfStmt extends AbstractEcmascriptRule {

    @Override
    public Object visit(ASTIfStatement functionNode, Object data) {

        if (!Objects.equals(functionNode.getThen().toString(), "ExpressionStatement")) {

            if (functionNode.getThen().findDescendantsOfType(ASTExpressionStatement.class).isEmpty()) {

                if (Objects.equals(functionNode.getThen().toString(), "EmptyStatement")) {

                    if (functionNode.getThen().getEndColumn() - functionNode.getThen().getBeginColumn() <= 1) {
                        addViolation(data, functionNode);
                        return super.visit(functionNode, data);
                    } else {
                        return super.visit(functionNode, data);
                    }
                }
                addViolation(data, functionNode);
                return super.visit(functionNode, data);
            }
        }
        return super.visit(functionNode, data);
    }

}
