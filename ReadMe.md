#ReadMe

## todo

* 增加对于三目运算符的支持 : ?

* 增加对于break的支持 ———— 异常

* 加入匿名函数的支持

  ```c
  fun thrice(fn) {
    for (var i = 1; i <= 3; i = i + 1) {
      fn(i);
    	}
  }
  thrice(fun (a) {
    print a;
  });
  // "1".
  // "2".
  // "3".
  
  ```

* Extend the resolver to report an error if a local variable is never used. 

* Extend the resolver to associate a unique index for each local variable declared in a scope. When resolving a variable access, look up both the scope the variable is in and its index and store that. In the interpreter, use that to quickly access a variable by its index instead of using a map. 

*  “static” methods that can be called directly on the class object itself

* “getters” and “setters”

*  mixins, traits, multiple inheritance, virtual inheritance, extension methods, etc

* When calling a method on a class, prefer the method highest on the class’s inheritance chain. Inside the body of a method, a call to inner looks for a method with the same name in the nearest subclass along the inheritance chain between the class containing the inner and the class of this. If there is no matching method, the inner call does nothing.

