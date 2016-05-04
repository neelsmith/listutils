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


    void testMorphEx() {
      def debug = 0
      def second = "lush|"
      def first =  "<#>lu_sh"

      ListDiff ldiff = new ListDiff(first,second,debug)

      assert ldiff

      assert ldiff.getScs().join("") == "<#>lu_sh|"
      assert ldiff.getLcs().join("") == "lush"
      assert ldiff.list2Only == ["|"]
      assert ldiff.list1Only == ["<", "#", ">", "_"]
    
    }

}
