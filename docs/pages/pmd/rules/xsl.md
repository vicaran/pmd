---
title: XSL Rules
permalink: pmd_rules_xsl.html
folder: pmd/rules
---
## Codestyle

{% include callout.html content="Rules which enforce a specific coding style." %}

*   [UseConcatOnce](pmd_rules_xsl_codestyle.html#useconcatonce): The XPath concat() functions accepts as many arguments as required so you can have"concat($a,'b',...

## Performance

{% include callout.html content="Rules that flag suboptimal code." %}

*   [AvoidAxisNavigation](pmd_rules_xsl_performance.html#avoidaxisnavigation): Avoid using the 'following' or 'preceeding' axes whenever possible, as these can cutthrough 100% ...

## Additional rulesets

*   XPath in XSL (`rulesets/xsl/xpath.xml`):

    <span style="border-radius: 0.25em; color: #fff; padding: 0.2em 0.6em 0.3em; display: inline; background-color: #d9534f; font-size: 75%;">Deprecated</span>  This ruleset is for backwards compatibility.

    It contains the following rules:

    [AvoidAxisNavigation](pmd_rules_xsl_performance.html#avoidaxisnavigation), [UseConcatOnce](pmd_rules_xsl_codestyle.html#useconcatonce)


