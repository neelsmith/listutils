package edu.holycross.shot.listutils

/**
* A class for working with a pair of ordered lists to compare.
* 
* Implementation of the Longest Common Sequence algorithm is based on the discussion at
* http://www.cs.princeton.edu/introcs/96optimization/LCS.java.html
*/

import groovy.xml.MarkupBuilder



class ListDiff {

    def debug = false


    /** The first of the two lists to compare.  It can contain any type of object.  */
    def list1 = []

    /** The second of the two lists to compare.  It should contain the same type of
    * object as list1.
    */
    def list2 = []
    
    /**  The Longest Common Sequence between the two lists. */
    def lcs = []

    /** A list of the objects appearing in list1 only, ordered by
    * their appearance in list1.  
    */
    def list1Only = []

    /** A list of the objects appearing in list2 only, ordered by
    * their appearance in list2.  
    */
    def list2Only = []

    /** Diff descriptor, a kind of composite mashup of
     * both source lists into a single list with properties
     * tagging status of elements not common to both source lists.
     */
    def diffs = []

    /** The 2-D memoizing array.
     */
    int[][] lcsLens



    /** Constructor
    * @param l1 The first list of objects to compare.
    * @param l2 The second list of objects to compare.
    */
    ListDiff (Object l1, Object l2) {
        l1.each {
            list1.add(it)
        }
        l2.each {
            list2.add(it)
        }

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
                diffs.add(["both": list1[i]])
                i++;
                j++;

            } else if (lcsLens[i+1][j] >= lcsLens[i][j+1]) { 
                list1Only.add(list1[i])
                if (list2.contains(list1[i])) {
                    def tagStr = ["OneElsewhereInTwo" : list1[i]]
//                    diffs.add("[1 only: " + list1[i] + " but appears elsewhere in 2]")
                    diffs.add(tagStr)
                } else {
                    def tagStr = ["oneOnly" : list1[i]]
                    diffs.add(tagStr)
//                    diffs.add("[1 only: " + list1[i] + "]")
                }
                i++;
   
            } else {                                 
/*
                //j++;
//                def count = j

                list2Only.add(list2[count])
                if (list1.contains(list2[count])) { 
                    diffs.add("[2 only: " + list2[count] + " but appears elsewhere in 1]")
                } else {
                    diffs.add("[2 only: " + list2[count] + "]")
                }  
*/


                list2Only.add(list2[j])
                if (list1.contains(list2[j])) { 
                    def tagStr = ["TwoElsewhereInOne" : list2[j]]
                    diffs.add(tagStr)
//                    diffs.add("[2 only: " + list2[j] + " but appears elsewhere in 1]")

                } else {
                    def tagStr = ["twoOnly" : list2[j]]
                    diffs.add(tagStr)
//                    diffs.add("[2 only: " + list2[j] + "]")
                }  
                j++;


            }
        }
    }
    // end constructor


    String diffs(String format) {
        if (format == "XML") {
            def writer = new StringWriter()
            def xml = new MarkupBuilder(writer)
            xml.diffs() {
                this.diffs.each { d ->
                    if (d instanceof java.lang.String ) {
                        psg("${d}")
                    } else {
                        def k
                        d.keySet().each {
                            k = it
                        }
                        
                        psg(type : "${k}", "${d[k]}")
                    }
//                    thingie ("${d.getClass()}: ${d}")
                }
            }
/*
                int i = 0, j = 0;
                
                while(i < list1.size() && j < list2.size()) {
                    if (list1[i] == list2[j]) {
                        // add token to output as is if both
                        // lists agree:
                        psg (list1[i])
                        i++;
                        j++;

                    } else if (lcsLens[i+1][j] >= lcsLens[i][j+1]) { 
                        def attrVal = ""
                        if (list2.contains(list1[i])) {
                            attrVal = "1elsewhereIn2"
                        } else {
                            attrVal = "oneOnly"
                        }
                        psg(type : attrVal,list1[i])
                        i++;   


                    } else {          
                        j++;
                        def attrVal = ""
                        if (list1.contains(list2[j])) { 
                            attrVal = "2elsewhereIn1"
                            
                        } else {
                            attrVal = "twoOnly"
                        }
                        psg(type : attrVal,list2[j])
                    }
                }
            }
*/
            return writer.toString()


        } else {
            return diffs.toString()
        }
    }

    String htmlReport() {
        StringBuffer sb =  new StringBuffer("<div><p>${list1.size()} tokens from passage 1</p>\n")
        sb.append( "<p>${list2.size()} tokens from passage 2</p>\n")
        sb.append( "<p>${lcs.size()} tokens in Longest Common Sequence</p>\n")
        if (list1Only.size() > 0) {
            sb.append( "<p>Passage 1 included, but 2 lacked " + list1Only + "</p>\n") 
        }
        if (list2Only.size() > 0) {
            sb.append( "<p>Passage 2 included, but 1 lacked " + list2Only + "</p></div>")
        }
        return sb.toString()

    }

    String shortReport () {
        StringBuffer sb =  new StringBuffer("${list1.size()} tokens from passage 1\n")
        sb.append( "${list2.size()} tokens from passage 2\n")
        sb.append( "${lcs.size()} tokens in Longest Common Sequence\n")
        if (list1Only.size() > 0) {
            sb.append( "Passage 1 included, but 2 lacked " + list1Only + "\n") 
        }
        if (list2Only.size() > 0) {
            sb.append( "Passage 2 included, but 1 lacked " + list2Only + "\n")
        }
        return sb.toString()
    }



    void printReport () {
        println "${list1.size()} tokens from passage 1: " + list1
        println "${list2.size()} tokens from passage 2: " + list2
        println "Longest Common Sequence = " + lcs
        if (list1Only.size() > 0) {
            println "Passage 1 included, but 2 lacked " + list1Only
        }
        if (list2Only.size() > 0) {
            println "Passage 2 included, but 1 lacked " + list2Only
        }
        println "Ordered list of all tokens (with diffs): " + diffs
    }


    String plusReff(String format) {
        if (format == "XML") {
            def writer = new StringWriter()
            def xml = new MarkupBuilder(writer)
            xml.plusReff() {
                list1.each {
                    psg(it)
                }
            }
            return writer.toString()
        } else {
            return list1.toString()
        }
    }

    String minusReff(String format) {
        if (format == "XML") {
            def writer = new StringWriter()
            def xml = new MarkupBuilder(writer)
            xml.minusReff() {
                list2.each {
                    psg(it)
                }
            }
            return writer.toString()
        } else {
            return list2.toString()
        }
    }

    String LCS(String format) {
        if (format == "XML") {
            def writer = new StringWriter()
            def xml = new MarkupBuilder(writer)
            xml.LCS() {
                lcs.each {
                    psg(it)
                }
            }
            return writer.toString()
        } else {
            return lcs.toString()
        }
    }

}
