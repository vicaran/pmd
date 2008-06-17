package net.sourceforge.pmd.lang.java.rule.basic;

import java.io.InputStream;

import net.sourceforge.pmd.lang.java.ast.ASTExpression;
import net.sourceforge.pmd.lang.java.ast.ASTPrimaryExpression;
import net.sourceforge.pmd.lang.java.ast.ASTPrimaryPrefix;
import net.sourceforge.pmd.lang.java.ast.ASTStatementExpression;
import net.sourceforge.pmd.lang.java.ast.ASTVariableDeclaratorId;
import net.sourceforge.pmd.lang.java.ast.JavaNode;
import net.sourceforge.pmd.lang.java.rule.AbstractJavaRule;
import net.sourceforge.pmd.lang.java.symboltable.NameOccurrence;
import net.sourceforge.pmd.lang.java.typeresolution.TypeHelper;

public class CheckSkipResult extends AbstractJavaRule {
    
    public Object visit(ASTVariableDeclaratorId node, Object data) {
        if (!TypeHelper.isA(node.getTypeNode(), InputStream.class)) {
            return data;
        }
        for (NameOccurrence occ: node.getUsages()) {
            NameOccurrence qualifier = occ.getNameForWhichThisIsAQualifier();
            if (qualifier != null && qualifier.getImage().equals("skip")) {
                JavaNode loc = occ.getLocation();
                if ( loc != null ) {
                    ASTPrimaryExpression exp = loc.getFirstParentOfType(ASTPrimaryExpression.class);
                    while (exp != null) {
                        if (exp.jjtGetParent() instanceof ASTStatementExpression) {
                            // if exp is in a bare statement,
                            // the returned value is not used
                            addViolation(data, occ.getLocation());
                            break;
                        } else if (exp.jjtGetParent() instanceof ASTExpression &&
                                   exp.jjtGetParent().jjtGetParent() instanceof ASTPrimaryPrefix) {
                            // if exp is enclosed in a pair of parenthesis
                            // let's have a look at the enclosing expression
                            // we'll see if it's in a bare statement
                            exp = exp.getFirstParentOfType(ASTPrimaryExpression.class);
                        } else {
                            // if exp is neither in a bare statement
                            // or between a pair of parentheses,
                            // it's in some other kind of statement
                            // or assignement so the returned value is used
                            break;
                        }
                    }
                }
            }
        }
        return data;
    }
    
}
