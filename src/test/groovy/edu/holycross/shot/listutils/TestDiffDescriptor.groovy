package edu.holycross.shot.listutils

import static org.junit.Assert.*
import org.junit.Test



class TestDiffDescriptor extends GroovyTestCase {

  @Test
  void testConstructor() {
    DiffDescriptor dd = new DiffDescriptor(DescriptorType.BOTH,"δέ")
    assert dd.dtype.getLabel() == "both"
    assert dd.token == "δέ"
  }
}
