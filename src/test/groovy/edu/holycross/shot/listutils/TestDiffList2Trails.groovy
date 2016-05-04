package edu.holycross.shot.listutils

import static org.junit.Assert.*
import org.junit.Test


// <#>lu^qh= and luqh| -> <#>lu^qh==
class TestDiffList2Trails extends GroovyTestCase {



  void testList2Longer() {

    def first = ["q", "h", "|"]
    def second = ["q", "h" , "r", "w", "n"]

    def debug = 0
    ListDiff ldiff = new ListDiff(first,second,debug)


    assert ldiff
    assert ldiff.getScs().join("") == "qh|rwn"
    assert ldiff.getLcs().join("") == "qh"
    assert ldiff.list1Only == ["|"]
    assert ldiff.list2Only == ["r", "w", "n"]

  }



  void testList1Longer() {
    def debug = 0
    def second = ["q", "h", "|"]
    def first = ["q", "h" , "r", "w", "n"]

    ListDiff ldiff = new ListDiff(first,second,debug)

    assert ldiff
    assert ldiff.getScs().join("") == "qhrwn|"
    assert ldiff.getLcs().join("") == "qh"
    assert ldiff.list2Only == ["|"]
    assert ldiff.list1Only == ["r", "w", "n"]


  }

}
