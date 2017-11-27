---
title: Ecmascript Rules
permalink: pmd_rules_ecmascript.html
folder: pmd/rules
---
List of rulesets and rules contained in each ruleset.

*   [Best Practices](pmd_rules_ecmascript_bestpractices.html): Rules which enforce generally accepted best practices.
*   [Code Style](pmd_rules_ecmascript_codestyle.html): Rules which enforce a specific coding style.
*   [Error Prone](pmd_rules_ecmascript_errorprone.html): Rules to detect constructs that are either broken, extremely confusing or prone to runtime errors.

## Best Practices
*   [AvoidWithStatement](pmd_rules_ecmascript_bestpractices.html#avoidwithstatement): Avoid using with - it's bad news
*   [ConsistentReturn](pmd_rules_ecmascript_bestpractices.html#consistentreturn): ECMAScript does provide for return types on functions, and therefore there is no solid rule as to...
*   [GlobalVariable](pmd_rules_ecmascript_bestpractices.html#globalvariable): This rule helps to avoid using accidently global variables by simply missing the "var" declaratio...
*   [ScopeForInVariable](pmd_rules_ecmascript_bestpractices.html#scopeforinvariable): A for-in loop in which the variable name is not explicitly scoped to the enclosing scope with the...
*   [UseBaseWithParseInt](pmd_rules_ecmascript_bestpractices.html#usebasewithparseint): This rule checks for usages of parseInt. While the second parameter is optional and usually defau...

## Code Style
*   [AssignmentInOperand](pmd_rules_ecmascript_codestyle.html#assignmentinoperand): Avoid assignments in operands; this can make code more complicated and harder to read.  This is s...
*   [AvoidDollarSigns](pmd_rules_ecmascript_codestyle.html#avoiddollarsigns): Avoid using dollar signs in variable/functions names.
*   [ForLoopsMustUseBraces](pmd_rules_ecmascript_codestyle.html#forloopsmustusebraces): Avoid using 'for' statements without using curly braces.
*   [FunctionNamingConventions](pmd_rules_ecmascript_codestyle.html#functionnamingconventions): Function names should always begin with a lower case character, and should not contain underscores.
*   [IfElseStmtsMustUseBraces](pmd_rules_ecmascript_codestyle.html#ifelsestmtsmustusebraces): Avoid using if..else statements without using curly braces.
*   [IfStmtsMustUseBraces](pmd_rules_ecmascript_codestyle.html#ifstmtsmustusebraces): Avoid using if statements without using curly braces.
*   [NoElseReturn](pmd_rules_ecmascript_codestyle.html#noelsereturn): The else block in a if-else-construct is unnecessary if the 'if' block contains a return.Then the...
*   [UnnecessaryBlock](pmd_rules_ecmascript_codestyle.html#unnecessaryblock): An unnecessary Block is present.  Such Blocks are often used in other languages tointroduce a new...
*   [UnnecessaryParentheses](pmd_rules_ecmascript_codestyle.html#unnecessaryparentheses): Unnecessary parentheses should be removed.
*   [UnreachableCode](pmd_rules_ecmascript_codestyle.html#unreachablecode): A 'return', 'break', 'continue', or 'throw' statement should be the last in a block. Statements a...
*   [WhileLoopsMustUseBraces](pmd_rules_ecmascript_codestyle.html#whileloopsmustusebraces): Avoid using 'while' statements without using curly braces.

## Error Prone
*   [AvoidTrailingComma](pmd_rules_ecmascript_errorprone.html#avoidtrailingcomma): This rule helps improve code portability due to differences in browser treatment of trailing comm...
*   [EqualComparison](pmd_rules_ecmascript_errorprone.html#equalcomparison): Using == in condition may lead to unexpected results, as the variables are automatically casted t...
*   [InnaccurateNumericLiteral](pmd_rules_ecmascript_errorprone.html#innaccuratenumericliteral): The numeric literal will have a different value at runtime, which can happen if you provide too m...
*   [ReturnFromFinallyBlock](pmd_rules_ecmascript_errorprone.html#returnfromfinallyblock): Avoid returning from a finally block, this can discard exceptions.

