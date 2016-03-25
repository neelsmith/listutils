package edu.holycross.shot.listutils


import groovy.xml.MarkupBuilder


/**
* A class for working with a pair of ordered lists to compare.
*
* Implementation of the Longest Common Sequence algorithm is based on the discussion at
* http://www.cs.princeton.edu/introcs/96optimization/LCS.java.html
*
*/
class ListDiff {

  /** The first of the two lists to compare.  It can contain any type of object.  */
  ArrayList list1 = []

  /** The second of the two lists to compare.  It should contain the same type of
   * object as list1.
   */
  ArrayList list2 = []

  /**  The Longest Common Sequence between the two lists. */
  ArrayList lcs = []

  /** The Shortest Common Supersequence of the two lists. */
  ArrayList scs = []
  
  /** A list of the objects appearing in list1 only, ordered by
   * their appearance in list1.
   */
  ArrayList list1Only = []

  /** A list of the objects appearing in list2 only, ordered by
   * their appearance in list2.
   */
  ArrayList list2Only = []

  /** Diff descriptor, a kind of composite mashup of
   * both source lists into a single list with properties
   * tagging status of elements not common to both source lists.
   */
  ArrayList diffs = []

  /** The 2-D memoizing array.
   */
  private int[][] lcsLens


  /** Computes relations of the two lists, stores results
   * in the publicly available ArrayLists.
   */
  private void computeSequences() {
    int list1Size = list1.size()
    int list2Size = list2.size()
    lcsLens = new int[list1Size+1][list2Size+1]
    // compute 2-D array of possible LCS lengths:
    for (int i = list1Size-1; i >= 0; i--) {
      for (int j = list2Size-1; j >= 0; j--) {
	if (list1[i] == list2[j]) {
	  lcsLens[i][j] = lcsLens[i+1][j+1] + 1
	} else {
	  lcsLens[i][j] = Math.max(lcsLens[i+1][j], lcsLens[i][j+1])
	}
      }
    }

    int i = 0, j = 0;
    while(i < list1Size && j < list2Size) {
      if (list1[i] == list2[j]) {
	// if both lists agree, add to both LCS
	// and composite, diffs
	lcs.add(list1[i])
	scs.add(list1[i])
	diffs.add(["both": list1[i]])
	i++;
	j++;

      } else if (lcsLens[i+1][j] >= lcsLens[i][j+1]) {
	list1Only.add(list1[i])
	scs.add(list1[i])
	if (list2.contains(list1[i])) {
	  def tagStr = ["OneElsewhereInTwo" : list1[i]]
	  diffs.add(tagStr)
	} else {
	  def tagStr = ["oneOnly" : list1[i]]
	  diffs.add(tagStr)
	}
	i++;

      } else {
	list2Only.add(list2[j])
	scs.add(list2[j])
	if (list1.contains(list2[j])) {
	  def tagStr = ["TwoElsewhereInOne" : list2[j]]
	  diffs.add(tagStr)

	} else {
	  def tagStr = ["twoOnly" : list2[j]]
	  diffs.add(tagStr)
	}
	j++;
      }
    }
  }
  
  /** Constructor
   * @param l1 The first list of objects to compare.
   * @param l2 The second list of objects to compare.
   */
  ListDiff (ArrayList l1, ArrayList l2) {
    l1.each {
      list1.add(it)
    }
    l2.each {
      list2.add(it)
    }
    computeSequences()
  }

}