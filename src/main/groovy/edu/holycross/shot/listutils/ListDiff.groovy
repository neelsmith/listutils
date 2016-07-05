package edu.holycross.shot.listutils


import com.ibm.icu.text.UCharacterIterator

/**
* A class for working with a pair of ordered lists to compare.
*
* Implementation of the Longest Common Sequence algorithm is based on the discussion at
* http://www.cs.princeton.edu/introcs/96optimization/LCS.java.html
*
*/
class ListDiff {

  def debug = 0

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

  /** A list of DiffDescriptor objects.
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

    if (debug > 1) {
      System.err.println "${list1} of size " + list1Size
      System.err.println "${list2} of size " + list2Size
    }

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

    if (debug > 0) {
      System.err.println "lcsLens: " + lcsLens
    }
    int i = 0, j = 0;
    while(i < list1Size && j < list2Size) {
      if (debug > 0) {System.err.print "at ${i}, ${j}: "}

      if (list1[i] == list2[j]) {
	if (debug > 0) {System.err.println "agree ${list1[i]}"}
	// if both lists agree, add to both LCS
	// and composite, diffs
	lcs.add(list1[i])
	scs.add(list1[i])
	diffs.add(["both": list1[i]])
	i++;
	j++;
	if(debug > 0) { System.err.println "Bump both: ${i}/${j}" }
	// check here for limit on one, but not other?
	if (i == list1Size) {
	  if (debug > 0) {System.err.println "Hit end of i = ${i}, j is ${j} and list2[j] is ${list2[j]}"}
	  while (j < list2Size) {
	    scs.add(list2[j])
	    list2Only.add(list2[j])
	    j++
	  }
	}
	


      } else if (lcsLens[i+1][j] >= lcsLens[i][j+1]) {

	list1Only.add(list1[i])
	if (debug > 0) {System.err.println "1 only: ${list1Only}"}
	scs.add(list1[i])
	if (list2.contains(list1[i])) {
	  def tagStr = ["OneElsewhereInTwo" : list1[i]]
	  diffs.add(tagStr)
	} else {
	  def tagStr = ["oneOnly" : list1[i]]
	  diffs.add(tagStr)
	}
	i++;
	if (debug > 0) {System.err.println "Bump i to " + i}
	if (i == list1Size) {
	  if (debug > 0) {System.err.println "Hit end of i = ${i}, j is ${j} and list2[j] is ${list2[j]}"}
	  while (j < list2Size) {
	    scs.add(list2[j])
	    list2Only.add(list2[j])
	    j++
	  }
	}

		
      } else {

	list2Only.add(list2[j])
	if (debug > 0) {System.err.println "2 only: ${list2Only}"}
	scs.add(list2[j])
	if (list1.contains(list2[j])) {
	  def tagStr = ["TwoElsewhereInOne" : list2[j]]
	  diffs.add(tagStr)
	  if (debug > 0) {System.err.println "elsewhere in one"}

	} else {
	  def tagStr = ["twoOnly" : list2[j]]
	  if (debug > 0) {System.err.println "what's left? ${list2Only}"}
	  diffs.add(tagStr)
	}
	j++;
	if (debug > 0) {System.err.println "Bump j to " + j}
	if (j >= list2Size) {
	  if (debug > 0) {System.err.println "Hit end of j = ${j}, i is ${i} and list1[i] is ${list1[i]}"}
	  while (i < list1Size) {
	    scs.add(list1[i])
	    list1Only.add(list1[i])
	    i++
	  }

	}

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


  /** Constructor with parameter to set debugging level.
   * @param l1 The first list of objects to compare.
   * @param l2 The second list of objects to compare.
   * @param debugLevel Enable debugging at given level.
   */
  ListDiff (ArrayList l1, ArrayList l2, int debugLevel) {
    this.debug = debugLevel
    l1.each {
      list1.add(it)
    }
    l2.each {
      list2.add(it)
    }
    computeSequences()
  }



  ListDiff (String s1, String s2, def debugLevel) {
    this.debug = debugLevel

    def iter1 = UCharacterIterator.getInstance (s1)
    def cp
    while(( cp=iter1.nextCodePoint())!= UCharacterIterator.DONE){
      list1.add(new String(Character.toChars(cp)))
    }
    def iter2 = UCharacterIterator.getInstance (s2)
    while(( cp=iter2.nextCodePoint())!= UCharacterIterator.DONE){
      list2.add(new String(Character.toChars(cp)))
    }
    computeSequences()
  }
  ListDiff (String s1, String s2) {
    def iter1 = UCharacterIterator.getInstance (s1)
    def cp
    while(( cp=iter1.nextCodePoint())!= UCharacterIterator.DONE){
      list1.add(new String(Character.toChars(cp)))
    }
    def iter2 = UCharacterIterator.getInstance (s2)
    while(( cp=iter2.nextCodePoint())!= UCharacterIterator.DONE){
      list2.add(new String(Character.toChars(cp)))
    }
    computeSequences()
  }


}
