---
title: Codestyle
summary: Rules which enforce a specific coding style.
permalink: pmd_rules_xsl_codestyle.html
folder: pmd/rules/xsl
sidebaractiveurl: /pmd_rules_xsl.html
editmepath: ../pmd-xml/src/main/resources/category/xsl/codestyle.xml
keywords: Codestyle, UseConcatOnce
---
## UseConcatOnce

**Since:** PMD 5.0

**Priority:** Medium (3)

The XPath concat() functions accepts as many arguments as required so you can have
"concat($a,'b',$c)" rather than "concat($a,concat('b',$c)".

```
//node()[contains(substring-after(@select,'concat'),'concat')]
```

**Example(s):**

``` xsl
<xsl:variable name="var" select="concat("Welcome",concat("to you ",$name))"/>
<xsl:variable name="var" select="concat("Welcome","to you ",$name))">
```

**Use this rule by referencing it:**
``` xml
<rule ref="category/xsl/codestyle.xml/UseConcatOnce" />
```

