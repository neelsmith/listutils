package edu.holycross.shot.listutils

import static org.junit.Assert.*
import org.junit.Test


// <#>lu^qh= and luqh| -> <#>lu^qh==
class TestDiffList2Trails extends GroovyTestCase {

  def first = ["q", "h", "|"]
  def second = ["q", "h" , "r"]

  void testBasics() {
    def debug = 10
    ListDiff ldiff = new ListDiff(first,second,debug)

    assert ldiff
    assert ldiff.getScs().join("") == "qh|r"
    assert ldiff.getLcs().join("") == "qh"
    assert ldiff.list1Only == ["|"]
    assert ldiff.list2Only == ["r"]

  }

}
