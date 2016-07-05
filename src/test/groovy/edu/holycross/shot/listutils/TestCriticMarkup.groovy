package edu.holycross.shot.listutils

import static org.junit.Assert.*
import org.junit.Test


class TestCriticMarkup extends GroovyTestCase {

  def first = [1,2,3,4,5,6,7]
  def second = [0,1,2,3,6,7]

  void testBasics() {
    ListDiff ldiff = new ListDiff(first,second)


    assert ldiff

    def expectedLcs = [1,2,3,6,7]
    assert ldiff.lcs == expectedLcs

    def expectedFirstOnly = [4,5]
    assert ldiff.list1Only == expectedFirstOnly

    def expectedSecondOnly = [0]
    assert ldiff.list2Only == expectedSecondOnly

    String cm = ldiff.toCriticMarkup()
    def expectedTokens = [ "{++0++}","1","2","3","{--4--}","{--5--}","6","7" ]
    assert cm.split(/[\s]+/).eachWithIndex { token,i ->
      assert token == expectedTokens[i]
    }
    
  }

}
