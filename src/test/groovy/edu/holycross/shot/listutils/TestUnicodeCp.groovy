package edu.holycross.shot.listutils

import static org.junit.Assert.*
import org.junit.Test


class TestUnicodeCp extends GroovyTestCase {

  String s1 = "μῆνιν ἄειδε θεά"
  String s2 = "Μῆνιν ἄειδε, θεά,"


  void testBasics() {
    ListDiff ldiff = new ListDiff(s1,s2)
    assert ldiff
    assert  ldiff.getScs().join("") == "μΜῆνιν ἄειδε, θεά,"
    assert ldiff.getLcs().join("") == "ῆνιν ἄειδε θεά"
  }

}
