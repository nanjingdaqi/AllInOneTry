import groovy.transform.Field;

@Field x = 10;

def foo() {
  println x
}

foo()