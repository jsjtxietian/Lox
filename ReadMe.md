#ReadMe

## todo

* * Extend the resolver to report an error if a local variable is never used.
    全局变量无法检测
    对于全局变量没有beginscope，endscope导致的全局不报错，但是进大括号报错

  ```js
  var a = 1;
  var a = a ;
  print a;
  ```
  区分了全局变量和非全局，需要把整个程序当成一个scope，但这样又会导致传参等出错。

* 增加对于三目运算符的支持 : ?

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

* Extend the resolver to associate a unique index for each local variable declared in a scope. When resolving a variable access, look up both the scope the variable is in and its index and store that. In the interpreter, use that to quickly access a variable by its index instead of using a map. 

* “static” methods that can be called directly on the class object itself

* “getters” and “setters”

* mixins, traits, multiple inheritance, virtual inheritance, extension methods, etc

* When calling a method on a class, prefer the method highest on the class’s inheritance chain. Inside the body of a method, a call to inner looks for a method with the same name in the nearest subclass along the inheritance chain between the class containing the inner and the class of this. If there is no matching method, the inner call does nothing.

