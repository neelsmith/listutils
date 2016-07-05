package edu.holycross.shot.listutils

public enum DescriptorType {

  BOTH ("both"),
  ONE("one only"),
  TWO("two only"),
  ONE_ELSEWHERE("in one elsewhere"),
  TWO_ELSEWHERE("in two elsewhere")
  // here in one, elsewhere in two
  // here in two, elsewhere in one

  private String label
  private DescriptorType(String label) {
    this.label = label
  }

  /** Gets a human-readable label for this value. */
    public String getLabel() {
      return label
    }
    static getByLabel(String labelStr) {
      values().find { it.label == labelStr }
    }
}
