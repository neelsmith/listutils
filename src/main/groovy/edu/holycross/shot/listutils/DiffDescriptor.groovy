package edu.holycross.shot.listutils

/*
, a kind of composite mashup of
 * both source lists into a single list with properties
 * tagging status of elements not common to both source lists.
 */
class DiffDescriptor {


  DescriptorType dtype
  Object token

  DiffDescriptor(DescriptorType dt, Object o) {
    this.dtype = dt
    this.token = o
  }

}
