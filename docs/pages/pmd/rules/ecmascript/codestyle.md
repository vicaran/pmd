---
title: Code Style
summary: Rules which enforce a specific coding style.
permalink: pmd_rules_ecmascript_codestyle.html
folder: pmd/rules/ecmascript
sidebaractiveurl: /pmd_rules_ecmascript.html
editmepath: ../pmd-javascript/src/main/resources/category/ecmascript/codestyle.xml
keywords: Code Style, AssignmentInOperand, AvoidDollarSigns, ForLoopsMustUseBraces, FunctionNamingConventions, IfElseStmtsMustUseBraces, IfStmtsMustUseBraces, NoElseReturn, UnnecessaryBlock, UnnecessaryParentheses, UnreachableCode, WhileLoopsMustUseBraces
---
## AssignmentInOperand

**Since:** PMD 5.0

**Priority:** Medium High (2)

Avoid assignments in operands; this can make code more complicated and harder to read.  This is sometime
indicative of the bug where the assignment operator '=' was used instead of the equality operator '=='.

```
//IfStatement[$allowIf = "false"]/child::node()[1]/descendant-or-self::node()[self::Assignment or self::UnaryExpression[$allowIncrementDecrement = "false" and (@Image = "--" or @Image = "++")]]
|
    //WhileLoop[$allowWhile = "false"]/child::node()[1]/descendant-or-self::node()[self::Assignment or self::UnaryExpression[$allowIncrementDecrement = "false" and (@Image = "--" or @Image = "++")]]
|
    //DoLoop[$allowWhile = "false"]/child::node()[2]/descendant-or-self::node()[self::Assignment or self::UnaryExpression[$allowIncrementDecrement = "false" and (@Image = "--" or @Image = "++")]]
|
    //ForLoop[$allowFor = "false"]/child::node()[2]/descendant-or-self::node()[self::Assignment or self::UnaryExpression[$allowIncrementDecrement = "false" and (@Image = "--" or @Image = "++")]]
|
   //ConditionalExpression[$allowTernary = "false"]/child::node()[1]/descendant-or-self::node()[self::Assignment or self::UnaryExpression[$allowIncrementDecrement = "false" and (@Image = "--" or @Image = "++")]]
|
   //ConditionalExpression[$allowTernaryResults = "false"]/child::node()[position() = 2 or position() = 3]/descendant-or-self::node()[self::Assignment or self::UnaryExpression[$allowIncrementDecrement = "false" and (@Image = "--" or @Image = "++")]]
```

**Example(s):**

``` javascript
var x = 2;
// Bad
if ((x = getX()) == 3) {
    alert('3!');
}

function getX() {
    return 3;
}
```

**This rule has the following properties:**

|Name|Default Value|Description|
|----|-------------|-----------|
|allowIf|false|Allow assignment within the conditional expression of an if statement|
|allowFor|false|Allow assignment within the conditional expression of a for statement|
|allowWhile|false|Allow assignment within the conditional expression of a while statement|
|allowTernary|false|Allow assignment within the conditional expression of a ternary operator|
|allowTernaryResults|false|Allow assignment within the result expressions of a ternary operator|
|allowIncrementDecrement|false|Allow increment or decrement operators within the conditional expression of an if, for, or while statement|

**Use this rule by referencing it:**
``` xml
<rule ref="category/ecmascript/codestyle.xml/AssignmentInOperand" />
```

## AvoidDollarSigns

**Since:** PMD 1.5

**Priority:** Medium (3)

Avoid using dollar signs in variable/functions names.

**This rule is defined by the following Java class:** [net.sourceforge.pmd.lang.ecmascript.rule.codestyle.AvoidDollarSignsRule](https://github.com/pmd/pmd/blob/master/pmd-javascript/src/main/java/net/sourceforge/pmd/lang/ecmascript/rule/codestyle/AvoidDollarSignsRule.java)

**Example(s):**

``` javascript
function ko$o() {  // not a recommended name
}
```

**This rule has the following properties:**

|Name|Default Value|Description|
|----|-------------|-----------|
|rhinoLanguageVersion|VERSION_DEFAULT|Specifies the Rhino Language Version to use for parsing.  Defaults to Rhino default.|
|recordingLocalJsDocComments|true|Specifies that JsDoc comments are produced in the AST.|
|recordingComments|true|Specifies that comments are produced in the AST.|

**Use this rule by referencing it:**
``` xml
<rule ref="category/ecmascript/codestyle.xml/AvoidDollarSigns" />
```

## ForLoopsMustUseBraces

**Since:** PMD 5.0

**Priority:** Medium (3)

Avoid using 'for' statements without using curly braces.

```
//ForLoop[not(child::Scope)]
|
//ForInLoop[not(child::Scope)]
```

**Example(s):**

``` javascript
// Ok
for (var i = 0; i < 42; i++) {
    foo();
}

// Bad
for (var i = 0; i < 42; i++)
    foo();
```

**Use this rule by referencing it:**
``` xml
<rule ref="category/ecmascript/codestyle.xml/ForLoopsMustUseBraces" />
```

## FunctionNamingConventions

**Since:** PMD 1.2

**Priority:** High (1)

Function names should always begin with a lower case character, and should not contain underscores.

**This rule is defined by the following Java class:** [net.sourceforge.pmd.lang.ecmascript.rule.codestyle.FunctionNamingConventionsRule](https://github.com/pmd/pmd/blob/master/pmd-javascript/src/main/java/net/sourceforge/pmd/lang/ecmascript/rule/codestyle/FunctionNamingConventionsRule.java)

**Example(s):**

``` javascript
function getData() {
}
```

**This rule has the following properties:**

|Name|Default Value|Description|
|----|-------------|-----------|
|rhinoLanguageVersion|VERSION_DEFAULT|Specifies the Rhino Language Version to use for parsing.  Defaults to Rhino default.|
|recordingLocalJsDocComments|true|Specifies that JsDoc comments are produced in the AST.|
|recordingComments|true|Specifies that comments are produced in the AST.|

**Use this rule by referencing it:**
``` xml
<rule ref="category/ecmascript/codestyle.xml/FunctionNamingConventions" />
```

## IfElseStmtsMustUseBraces

**Since:** PMD 5.0

**Priority:** Medium (3)

Avoid using if..else statements without using curly braces.

```
//ExpressionStatement[parent::IfStatement[@Else = "true"]]
   [not(child::Scope)]
   [not(child::IfStatement)]
```

**Example(s):**

``` javascript
// Ok
if (foo) {
    x++;
} else {
    y++;
}

// Bad
if (foo)
    x++;
else
    y++;
```

**Use this rule by referencing it:**
``` xml
<rule ref="category/ecmascript/codestyle.xml/IfElseStmtsMustUseBraces" />
```

## IfStmtsMustUseBraces

**Since:** PMD 5.0

**Priority:** Medium (3)

Avoid using if statements without using curly braces.

```
//IfStatement[@Else = "false" and not(child::Scope)]
```

**Example(s):**

``` javascript
// Ok
if (foo) {
    x++;
}

// Bad
if (foo)
    x++;
```

**Use this rule by referencing it:**
``` xml
<rule ref="category/ecmascript/codestyle.xml/IfStmtsMustUseBraces" />
```

## NoElseReturn

**Since:** PMD 5.5.0

**Priority:** Medium (3)

The else block in a if-else-construct is unnecessary if the `if` block contains a return.
Then the content of the else block can be put outside.

See also: <http://eslint.org/docs/rules/no-else-return>

```
//IfStatement[@Else="true"][Scope[1]/ReturnStatement]
```

**Example(s):**

``` javascript
// Bad:
if (x) {
    return y;
} else {
    return z;
}

// Good:
if (x) {
    return y;
}
return z;
```

**Use this rule by referencing it:**
``` xml
<rule ref="category/ecmascript/codestyle.xml/NoElseReturn" />
```

## UnnecessaryBlock

**Since:** PMD 5.0

**Priority:** Medium (3)

An unnecessary Block is present.  Such Blocks are often used in other languages to
introduce a new variable scope.  Blocks do not behave like this in ECMAScipt, and using them can
be misleading.  Considering removing this unnecessary Block.

```
//Block[not(parent::FunctionNode or parent::IfStatement or parent::ForLoop or parent::ForInLoop
    or parent::WhileLoop or parent::DoLoop or parent::TryStatement or parent::CatchClause)]
|
//Scope[not(parent::FunctionNode or parent::IfStatement or parent::ForLoop or parent::ForInLoop
    or parent::WhileLoop or parent::DoLoop or parent::TryStatement or parent::CatchClause)]
```

**Example(s):**

``` javascript
if (foo) {
    // Ok
}
if (bar) {
    {
        // Bad
    }
}
```

**Use this rule by referencing it:**
``` xml
<rule ref="category/ecmascript/codestyle.xml/UnnecessaryBlock" />
```

## UnnecessaryParentheses

**Since:** PMD 5.0

**Priority:** Medium Low (4)

Unnecessary parentheses should be removed.

```
//ParenthesizedExpression/ParenthesizedExpression
```

**Example(s):**

``` javascript
var x = 1; // Ok
var y = (1 + 1); // Ok
var z = ((1 + 1)); // Bad
```

**Use this rule by referencing it:**
``` xml
<rule ref="category/ecmascript/codestyle.xml/UnnecessaryParentheses" />
```

## UnreachableCode

**Since:** PMD 5.0

**Priority:** High (1)

A 'return', 'break', 'continue', or 'throw' statement should be the last in a block. Statements after these
will never execute.  This is a bug, or extremely poor style.

```
//ReturnStatement[following-sibling::node()]
|
    //ContinueStatement[following-sibling::node()]
|
    //BreakStatement[following-sibling::node()]
|
    //ThrowStatement[following-sibling::node()]
```

**Example(s):**

``` javascript
// Ok
function foo() {
   return 1;
}
// Bad
function bar() {
   var x = 1;
   return x;
   x = 2;
}
```

**Use this rule by referencing it:**
``` xml
<rule ref="category/ecmascript/codestyle.xml/UnreachableCode" />
```

## WhileLoopsMustUseBraces

**Since:** PMD 5.0

**Priority:** Medium (3)

Avoid using 'while' statements without using curly braces.

```
//WhileLoop[not(child::Scope)]
```

**Example(s):**

``` javascript
// Ok
while (true) {
    x++;
}

// Bad
while (true)
    x++;
```

**Use this rule by referencing it:**
``` xml
<rule ref="category/ecmascript/codestyle.xml/WhileLoopsMustUseBraces" />
```

