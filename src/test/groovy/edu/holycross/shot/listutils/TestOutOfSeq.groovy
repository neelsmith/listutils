package edu.holycross.shot.listutils

import static org.junit.Assert.*
import org.junit.Test


class TestOutOfSeq extends GroovyTestCase {



  def first = [1,2,3,4,5,6,7]
  def second = [2,0,1,3,6,7]

  void testBasics() {
    ListDiff ldiff = new ListDiff(first,second)

    assert ldiff

    def expectedLcs = [2,3,6,7]
    assert ldiff.lcs == expectedLcs

    println "first " + ldiff.list1Only
    println "second: " + ldiff.list2Only
    println "Composite:"
    println ldiff.diffs

  }

}
