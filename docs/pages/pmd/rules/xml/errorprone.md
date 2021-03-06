---
title: Errorprone
summary: Rules to detect constructs that are either broken, extremely confusing or prone to runtime errors.
permalink: pmd_rules_xml_errorprone.html
folder: pmd/rules/xml
sidebaractiveurl: /pmd_rules_xml.html
editmepath: ../pmd-xml/src/main/resources/category/xml/errorprone.xml
keywords: Errorprone, MistypedCDATASection
---
## MistypedCDATASection

**Since:** PMD 5.0

**Priority:** Medium (3)

An XML CDATA section begins with a <!CDATA[ marker, which has only one [, and ends with a ]]> marker, which has only two ].

```
//cdata-section[starts-with(@Image,'[') or ends-with(@Image,']')]
```

**Example(s):**

``` xml
An extra [ looks like &lt;!CDATA[[]]&gt;, and an extra ] looks like &lt;!CDATA[]]]&gt;.
```

**Use this rule by referencing it:**
``` xml
<rule ref="category/xml/errorprone.xml/MistypedCDATASection" />
```

