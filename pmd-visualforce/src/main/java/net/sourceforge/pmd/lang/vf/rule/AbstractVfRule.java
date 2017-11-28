/**
 * BSD-style license; for more info see http://pmd.sourceforge.net/license.html
 */

package net.sourceforge.pmd.lang.vf.rule;

import java.util.List;

import net.sourceforge.pmd.RuleContext;
import net.sourceforge.pmd.lang.LanguageRegistry;
import net.sourceforge.pmd.lang.ast.Node;
import net.sourceforge.pmd.lang.rule.AbstractRule;
import net.sourceforge.pmd.lang.rule.ImmutableLanguage;
import net.sourceforge.pmd.lang.vf.VfLanguageModule;
import net.sourceforge.pmd.lang.vf.ast.ASTArguments;
import net.sourceforge.pmd.lang.vf.ast.ASTAttribute;
import net.sourceforge.pmd.lang.vf.ast.ASTAttributeValue;
import net.sourceforge.pmd.lang.vf.ast.ASTCData;
import net.sourceforge.pmd.lang.vf.ast.ASTCompilationUnit;
import net.sourceforge.pmd.lang.vf.ast.ASTContent;
import net.sourceforge.pmd.lang.vf.ast.ASTDeclaration;
import net.sourceforge.pmd.lang.vf.ast.ASTDoctypeDeclaration;
import net.sourceforge.pmd.lang.vf.ast.ASTDoctypeExternalId;
import net.sourceforge.pmd.lang.vf.ast.ASTDotExpression;
import net.sourceforge.pmd.lang.vf.ast.ASTElExpression;
import net.sourceforge.pmd.lang.vf.ast.ASTElement;
import net.sourceforge.pmd.lang.vf.ast.ASTExpression;
import net.sourceforge.pmd.lang.vf.ast.ASTHtmlScript;
import net.sourceforge.pmd.lang.vf.ast.ASTIdentifier;
import net.sourceforge.pmd.lang.vf.ast.ASTLiteral;
import net.sourceforge.pmd.lang.vf.ast.ASTNegationExpression;
import net.sourceforge.pmd.lang.vf.ast.ASTText;
import net.sourceforge.pmd.lang.vf.ast.VfNode;
import net.sourceforge.pmd.lang.vf.ast.VfParserVisitor;

public abstract class AbstractVfRule extends AbstractRule implements VfParserVisitor, ImmutableLanguage {

    public AbstractVfRule() {
        super.setLanguage(LanguageRegistry.getLanguage(VfLanguageModule.NAME));
    }

    public void apply(List<? extends Node> nodes, RuleContext ctx) {
        visitAll(nodes, ctx);
    }

    protected void visitAll(List<? extends Node> nodes, RuleContext ctx) {
        for (Object element : nodes) {
            if (element instanceof ASTCompilationUnit) {
                ASTCompilationUnit node = (ASTCompilationUnit) element;
                visit(node, ctx);
            } else {
                VfNode node = (VfNode) element;
                visit(node, ctx);
            }
        }
    }

    public Object visit(VfNode node, Object data) {
        node.childrenAccept(this, data);
        return null;
    }

    public Object visit(ASTCompilationUnit node, Object data) {
        return visit((VfNode) node, data);
    }

    public Object visit(ASTText node, Object data) {
        return visit((VfNode) node, data);
    }

    public Object visit(ASTAttributeValue node, Object data) {
        return visit((VfNode) node, data);
    }

    public Object visit(ASTElExpression node, Object data) {
        return visit((VfNode) node, data);
    }

    public Object visit(ASTCData node, Object data) {
        return visit((VfNode) node, data);
    }

    public Object visit(ASTElement node, Object data) {
        return visit((VfNode) node, data);
    }

    public Object visit(ASTAttribute node, Object data) {
        return visit((VfNode) node, data);
    }

    public Object visit(ASTDeclaration node, Object data) {
        return visit((VfNode) node, data);
    }

    public Object visit(ASTDoctypeDeclaration node, Object data) {
        return visit((VfNode) node, data);
    }

    public Object visit(ASTDoctypeExternalId node, Object data) {
        return visit((VfNode) node, data);
    }

    public Object visit(ASTHtmlScript node, Object data) {
        return visit((VfNode) node, data);
    }

    public Object visit(ASTLiteral node, Object data) {
        return visit((VfNode) node, data);
    }

    public Object visit(ASTIdentifier node, Object data) {
        return visit((VfNode) node, data);
    }

    public Object visit(ASTExpression node, Object data) {
        return visit((VfNode) node, data);
    }

    public Object visit(ASTArguments node, Object data) {
        return visit((VfNode) node, data);
    }

    public Object visit(ASTDotExpression node, Object data) {
        return visit((VfNode) node, data);
    }

    public Object visit(ASTContent node, Object data) {
        return visit((VfNode) node, data);
    }

    public Object visit(ASTNegationExpression node, Object data) {
        return visit((VfNode) node, data);
    }

}
