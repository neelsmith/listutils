# Specification for `listutils`, version @version@ #

`listutils` compares two ordered lists, `l1` and `l2`, of objects of any type, and computes ordered lists containing:

- the longest common subsequence (LCS) of the two lists (an ordered intersection of `l1` and `l2`)
- the shortest common supersequence (SCS) of the two lists (an ordered union of `l1` and `l2`)
- the ordered list of items contained only in `l1`
- the ordered list of items contained only in `l2`
- differences between `l1` and `l2`




Computation of LCS, unique elements in each list, and differences between the two lists produces identical results for either ordering of the two lists.  That is, the results are identical whether you compare `l1` with `l2`, or `l2` with `l1`.

Computation of SCS, on the other hand, can result in ambiguous "ties" in the ordering when elements unique to each list appear at the same point in the list sequence.  In these cases, `listutils` gives priority to `l1`.  (See the examples of string comparison below.)



@openex@

## Example 1: comparing lists of integers ##

Consider the following two lists. List 1 has seven elements:

<pre concordion:set="#l1">1,2,3,4,5,6,7</pre>

and list 2 has six:


<pre concordion:set="#l2">0,1,2,3,6,7</pre>


The longest common subsequence of the two integer lists is:


 <table concordion:verifyRows="#item : getLcs(#l1,#l2)">
<tr><th concordion:assertEquals="#item">Item</th></tr>

<tr><td>1</td></tr>
<tr><td>2</td></tr>
<tr><td>3</td></tr>
<tr><td>6</td></tr>
<tr><td>7</td></tr>
</table>


The shortest common supersequence of the two integer lists is:


<table concordion:verifyRows="#item : getScs(#l1,#l2)">
<tr><th concordion:assertEquals="#item">Item</th></tr>

<tr><td>0</td></tr>
<tr><td>1</td></tr>
<tr><td>2</td></tr>
<tr><td>3</td></tr>
<tr><td>4</td></tr>
<tr><td>5</td></tr>
<tr><td>6</td></tr>
<tr><td>7</td></tr>
</table>




The elements unique to list 1 are


 <table concordion:verifyRows="#item : getUnique1(#l1,#l2)">
<tr><th concordion:assertEquals="#item">Item</th></tr>

<tr><td>4</td></tr>
<tr><td>5</td></tr>
</table>

and the elements unique to list 2 are



 <table concordion:verifyRows="#item : getUnique2(#l1,#l2)">
<tr><th concordion:assertEquals="#item">Item</th></tr>

<tr><td>0</td></tr>

</table>

@closeex@




@openex@

## Example 2: string comparison

A String can be considered a list of characters.  The following real example comes from work on a system for parsing ancient Greek morphology.  The first string is part of a morphological analysis that pairs a stem `lu_` with an ending `ai`:

<pre concordion:set="#s1">lu_-ai</pre>

(This is an ASCII representation of the Greek characters λυ and αι, with an explicit marker that `u` character is phonetically long.)

The second string is an actual surface form (λυσαι).  In addition to the stem and ending, it shows a modification to the stem (the `s` character):

<pre concordion:set="#s2">lusai</pre>


Finding the LCS and unique elements is straightforward. The longest commons subsequence is


 <table concordion:verifyRows="#item : getStrLcs(#s1,#s2)">
<tr><th concordion:assertEquals="#item">Item</th></tr>

<tr><td>l</td></tr>
<tr><td>u</td></tr>
<tr><td>a</td></tr>
<tr><td>i</td></tr>
</table>

the characters unique to string 1 are


 <table concordion:verifyRows="#item : getStrUnique1(#s1,#s2)">
<tr><th concordion:assertEquals="#item">Item</th></tr>

<tr><td>_</td></tr>
<tr><td>-</td></tr>
</table>

and the only character unique to string 2 is




 <table concordion:verifyRows="#item : getStrUnique2(#s1,#s2)">
<tr><th concordion:assertEquals="#item">Item</th></tr>

<tr><td>s</td></tr>

</table>



### SCS: order of comparison matters
In this real-world example, what would be particularly valuable  is to find the the shortest common supersequence.  We would like, in effect,  to merge the two strings, by projecting information from the analysis about vowel quantity onto the surface form.

But note that the two strings have unique characters at exactly the same point in the sequence (the "_" character in string 1 and the "s" in string 2).  Consequently, if we ask for a comparison of string 1 with string 2, we obtain the following results



<table concordion:verifyRows="#item : getStrScs(#s1, #s2)">
<tr><th concordion:assertEquals="#item">Item</th></tr>

<tr><td>l</td></tr>
<tr><td>u</td></tr>
<tr><td>_</td></tr>
<tr><td>-</td></tr>
<tr><td>s</td></tr>
<tr><td>a</td></tr>
<tr><td>i</td></tr>
</table>

which is exactly what we want.

If, on the other hand, we reverse the order, and compare string 2 with string 1, we get the following SCS:

<table concordion:verifyRows="#item : getStrScs(#s2, #s1)">
<tr><th concordion:assertEquals="#item">Item</th></tr>

<tr><td>l</td></tr>
<tr><td>u</td></tr>
<tr><td>s</td></tr>
<tr><td>_</td></tr>
<tr><td>-</td></tr>
<tr><td>a</td></tr>
<tr><td>i</td></tr>
</table>

The "_" is no longer adjacent to the "u" to mark it as long, and the results are nonsensical in this context (since consonants like "s" do not have quantity values of long or short).

All this really means is that it is important to understand the data you are comparing when you ask for a shortest common supersequence.

@closeex@
