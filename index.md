---
title: List utilities
layout: page
---

A JVM library to compare two lists, `l1` and `l1`, of objects of any kind.


- version: **1.1.3**.  [Release notes](releases)
- requires: java version >= 7

## Overview

Use the `ListDiff` class to find:

- the longest common subsequence (LCS) of the two lists (an ordered intersection of `l1` and `l2`)
- the shortest common supersequence (SCS) of the two lists (an ordered union of `l1` and `l2`)
- the ordered list of items contained only in `l1`
- the ordered list of items contained only in `l2`
- differences between `l1` and `l2`


## Documentation and maven identifiers

- [specifications](specs/listutils/ListUtils.html) with concordion tests
- [API documentation](api)
- maven identifiers: group `edu.holycross.shot`, package `listutils`, available from the repository at <http://beta.hpcc.uh.edu/nexus/content/groups/public>


## CL scripts for printing LCS and SCS of two strings

Groovy scripts with a grapes dependency on `listutils` to apply LCS and SCS to sequences of characters in two strings:

- [lcs.groovy](https://gist.github.com/neelsmith/393dbe8a56e5eb1bd1c6)
- [scs.groovy](https://gist.github.com/neelsmith/7fc5ae04ae58bcf3ac8a)

Examples:

     groovy lcs.groovy 'Dr Rock-and-Roll Star' 'Doctor Rock Star'

prints

     Dr Rock Star

while

     groovy scs.groovy 'Dr Rock-and-Roll Star' 'Doctor Rock Star'

prints

     Doctor Rock-and-Roll Star
