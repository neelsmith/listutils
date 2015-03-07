# Specification for `listutils`, version @version@ #

`listutils` compares two ordered lists, `l1` and `l2`, of objects of any type.  


## Analyses
It generates ordered lists containing:

- the longest common sequence (LCS) (an ordered intersection of `l1` and `l2`)
- the ordered list of items contained only in `l1`
- the ordered list of items contained only in `l2`
- differences between `l1` and `l2`
- the composite list (an ordered union of `l1` and `l2`)

@openex@

### Examples: lists of integers ###


List utils can compare simple values like integers.  Consider the following two lists, list 1

<pre concordion:set="#l1">1,2,3,4,5,6,7</pre>

and list 2 


<pre concordion:set="#l2">0,1,2,3,6,7</pre>

@closeex@


@openex@

### Example: finding LCS ###

The longest common sequence of the two integer lists is:


 <table concordion:verifyRows="#item : getLcs(#l1,#l2)">
<tr><th concordion:assertEquals="#item">Item</th></tr>

<tr><td>1</td></tr>
<tr><td>2</td></tr>
<tr><td>3</td></tr>
<tr><td>6</td></tr>
<tr><td>7</td></tr>
</table>



@closeex@




@openex@

### Example: finding unique elements ###

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


## Reporting and output ##

Specifications for possible formats for reporting output may be added here.

