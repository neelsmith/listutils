package edu.holycross.shot.listutils

import static org.junit.Assert.*
import org.junit.Test


class TestTextDiff extends GroovyTestCase {

  String surface = "e<sm>lues"
  String underlying = "lu_es"

  def first = surface.toCharArray() as ArrayList
  def second = underlying.toCharArray() as ArrayList

  void testBasics() {
    ListDiff ldiff = new ListDiff(first,second)
    assert ldiff
    assert ldiff.getScs().join("") == "e<sm>lu_es"
  }

}
