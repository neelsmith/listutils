package edu.holycross.shot.listutils

import static org.junit.Assert.*
import org.junit.Test



class TestDescriptorEnum extends GroovyTestCase {

  @Test
  void testValues() {
    def expectedLabels = ["both", "one only", "two only", "in one elsewhere", "in two elsewhere" ]

    ArrayList testList = DescriptorType.values()  as ArrayList
    testList.eachWithIndex { n, i ->
      assert n.getLabel() == expectedLabels[i]
    }
  }
}
