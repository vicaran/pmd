---
title: Error Prone
summary: Rules to detect constructs that are either broken, extremely confusing or prone to runtime errors.
permalink: pmd_rules_apex_errorprone.html
folder: pmd/rules/apex
sidebaractiveurl: /pmd_rules_apex.html
editmepath: ../pmd-apex/src/main/resources/category/apex/errorprone.xml
keywords: Error Prone, AvoidDirectAccessTriggerMap, AvoidHardcodingId, EmptyCatchBlock, EmptyIfStmt, EmptyStatementBlock, EmptyTryOrFinallyBlock, EmptyWhileStmt, MethodWithSameNameAsEnclosingClass
---
## AvoidDirectAccessTriggerMap

**Since:** PMD 6.0.0

**Priority:** Medium (3)

Avoid directly accessing Trigger.old and Trigger.new as it can lead to a bug. Triggers should be bulkified and iterate through the map to handle the actions for each item separately.

```
//ArrayLoadExpression/TriggerVariableExpression | //ArrayLoadExpression/LiteralExpression
```

**Example(s):**

``` java
trigger AccountTrigger on Account (before insert, before update) {
   Account a = Trigger.new[0]; //Bad: Accessing the trigger array directly is not recommended.
   
   foreach ( Account a : Trigger.new ){   
        //Good: Iterate through the trigger.new array instead.
   }
}
```

**This rule has the following properties:**

|Name|Default Value|Description|
|----|-------------|-----------|
|cc_categories|[Style]|Code Climate Categories|
|cc_remediation_points_multiplier|1|Code Climate Remediation Points multiplier|
|cc_block_highlighting|false|Code Climate Block Highlighting|

**Use this rule by referencing it:**
``` xml
<rule ref="category/apex/errorprone.xml/AvoidDirectAccessTriggerMap" />
```

## AvoidHardcodingId

**Since:** PMD 6.0.0

**Priority:** Medium (3)

When deploying Apex code between sandbox and production environments, or installing Force.com AppExchange packages,
it is essential to avoid hardcoding IDs in the Apex code. By doing so, if the record IDs change between environments,
the logic can dynamically identify the proper data to operate against and not fail.

**This rule is defined by the following Java class:** [net.sourceforge.pmd.lang.apex.rule.errorprone.AvoidHardcodingIdRule](https://github.com/pmd/pmd/blob/master/pmd-apex/src/main/java/net/sourceforge/pmd/lang/apex/rule/errorprone/AvoidHardcodingIdRule.java)

**Example(s):**

``` java
public without sharing class Foo {
    void foo() {
        //Error - hardcoded the record type id
        if(a.RecordTypeId == '012500000009WAr'){
            //do some logic here.....
        } else if(a.RecordTypeId == '0123000000095Km'){
            //do some logic here for a different record type...
        }
    }
}
```

**This rule has the following properties:**

|Name|Default Value|Description|
|----|-------------|-----------|
|cc_categories|[Style]|Code Climate Categories|
|cc_remediation_points_multiplier|1|Code Climate Remediation Points multiplier|
|cc_block_highlighting|false|Code Climate Block Highlighting|

**Use this rule by referencing it:**
``` xml
<rule ref="category/apex/errorprone.xml/AvoidHardcodingId" />
```

## EmptyCatchBlock

**Since:** PMD 6.0.0

**Priority:** Medium (3)

Empty Catch Block finds instances where an exception is caught, but nothing is done.  
In most circumstances, this swallows an exception which should either be acted on 
or reported.

```
//CatchBlockStatement[./BlockStatement[count(*) = 0]]
```

**Example(s):**

``` java
public void doSomething() {
  ...
  try {
    insert accounts;
  } catch (DmlException dmle) {
    // not good
  }
}
```

**This rule has the following properties:**

|Name|Default Value|Description|
|----|-------------|-----------|
|cc_categories|[Style]|Code Climate Categories|
|cc_remediation_points_multiplier|1|Code Climate Remediation Points multiplier|
|cc_block_highlighting|false|Code Climate Block Highlighting|

**Use this rule by referencing it:**
``` xml
<rule ref="category/apex/errorprone.xml/EmptyCatchBlock" />
```

## EmptyIfStmt

**Since:** PMD 6.0.0

**Priority:** Medium (3)

Empty If Statement finds instances where a condition is checked but nothing is done about it.

```
//IfBlockStatement
 [BlockStatement[count(*) = 0]]
```

**Example(s):**

``` java
public class Foo {
  public void bar(Integer x) {
    if (x == 0) {
      // empty!
    }
  }
}
```

**This rule has the following properties:**

|Name|Default Value|Description|
|----|-------------|-----------|
|cc_categories|[Style]|Code Climate Categories|
|cc_remediation_points_multiplier|1|Code Climate Remediation Points multiplier|
|cc_block_highlighting|false|Code Climate Block Highlighting|

**Use this rule by referencing it:**
``` xml
<rule ref="category/apex/errorprone.xml/EmptyIfStmt" />
```

## EmptyStatementBlock

**Since:** PMD 6.0.0

**Priority:** Medium (3)

Empty block statements serve no purpose and should be removed.

```
//Method/ModifierNode[@Abstract!='true' and ../BlockStatement[count(*) = 0]]
| //Method/BlockStatement//BlockStatement[count(*) = 0]
```

**Example(s):**

``` java
public class Foo {

   private int _bar;

   public void setBar(int bar) {
        // empty
   }

}
```

**This rule has the following properties:**

|Name|Default Value|Description|
|----|-------------|-----------|
|cc_categories|[Style]|Code Climate Categories|
|cc_remediation_points_multiplier|1|Code Climate Remediation Points multiplier|
|cc_block_highlighting|false|Code Climate Block Highlighting|

**Use this rule by referencing it:**
``` xml
<rule ref="category/apex/errorprone.xml/EmptyStatementBlock" />
```

## EmptyTryOrFinallyBlock

**Since:** PMD 6.0.0

**Priority:** Medium (3)

Avoid empty try or finally blocks - what's the point?

```
//TryCatchFinallyBlockStatement[./BlockStatement[count(*) = 0]]
```

**Example(s):**

``` java
public class Foo {
    public void bar() {
        try {
          // empty !
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

public class Foo {
    public void bar() {
        try {
            int x=2;
        } finally {
            // empty!
        }
    }
}
```

**This rule has the following properties:**

|Name|Default Value|Description|
|----|-------------|-----------|
|cc_categories|[Style]|Code Climate Categories|
|cc_remediation_points_multiplier|1|Code Climate Remediation Points multiplier|
|cc_block_highlighting|false|Code Climate Block Highlighting|

**Use this rule by referencing it:**
``` xml
<rule ref="category/apex/errorprone.xml/EmptyTryOrFinallyBlock" />
```

## EmptyWhileStmt

**Since:** PMD 6.0.0

**Priority:** Medium (3)

Empty While Statement finds all instances where a while statement does nothing.  
If it is a timing loop, then you should use Thread.sleep() for it; if it is
a while loop that does a lot in the exit expression, rewrite it to make it clearer.

```
//WhileLoopStatement[./BlockStatement[count(*) = 0]]
```

**Example(s):**

``` java
public void bar(Integer a, Integer b) {
  while (a == b) {
    // empty!
  }
}
```

**This rule has the following properties:**

|Name|Default Value|Description|
|----|-------------|-----------|
|cc_categories|[Style]|Code Climate Categories|
|cc_remediation_points_multiplier|1|Code Climate Remediation Points multiplier|
|cc_block_highlighting|false|Code Climate Block Highlighting|

**Use this rule by referencing it:**
``` xml
<rule ref="category/apex/errorprone.xml/EmptyWhileStmt" />
```

## MethodWithSameNameAsEnclosingClass

**Since:** PMD 5.5.0

**Priority:** Medium (3)

Non-constructor methods should not have the same name as the enclosing class.

**This rule is defined by the following Java class:** [net.sourceforge.pmd.lang.apex.rule.errorprone.MethodWithSameNameAsEnclosingClassRule](https://github.com/pmd/pmd/blob/master/pmd-apex/src/main/java/net/sourceforge/pmd/lang/apex/rule/errorprone/MethodWithSameNameAsEnclosingClassRule.java)

**Example(s):**

``` java
public class MyClass {
    // this is OK because it is a constructor
    public MyClass() {}
    // this is bad because it is a method
    public void MyClass() {}
}
```

**This rule has the following properties:**

|Name|Default Value|Description|
|----|-------------|-----------|
|cc_categories|[Style]|Code Climate Categories|
|cc_remediation_points_multiplier|1|Code Climate Remediation Points multiplier|
|cc_block_highlighting|false|Code Climate Block Highlighting|

**Use this rule by referencing it:**
``` xml
<rule ref="category/apex/errorprone.xml/MethodWithSameNameAsEnclosingClass" />
```

