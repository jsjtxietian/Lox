#ReadMe

## todo

* Extend the resolver to report an error if a local variable is never used.
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

## test case

* ```js
  fun addPair(a, b) {
    return a + b;
  }
  
  fun identity(a) {
    return a;
  }
  
  print identity(addPair)(1, 2); // Prints "3".
  ```

* ```js
  fun returnFunction() {
    var outside = "outside";
  
    fun inner() {
      print outside;
    }
  
    return inner;
  }
  
  var fn = returnFunction();
  fn();
  ```

* ```
  class Breakfast {
    init(meat, bread) {
      this.meat = meat;
      this.bread = bread;
    }
  
    // ...
  }
  
  var baconAndToast = Breakfast("bacon", "toast");
  baconAndToast.serve("Dear Reader");
  // "Enjoy your bacon and toast, Dear Reader."
  ```

* ```
  var a = "global a";
  var b = "global b";
  var c = "global c";
  {
    var a = "outer a";
    var b = "outer b";
    {
      var a = "inner a";
      print a;
      print b;
      print c;
    }
    print a;
    print b;
    print c;
  }
  print a;
  print b;
  print c;
  ```

* ```
  var a = 0;
  var b = 1;
  
  while (a < 10000) {
    print a;
    var temp = a;
    a = b;
    b = temp + b;
  }
  ```

* ```
  fun makePoint(x, y) {
    fun closure(method) {
      if (method == "x") return x;
      if (method == "y") return y;
      print "unknown method " + method;
    }
  
    return closure;
  }
  
  var point = makePoint(2, 3);
  print point("x"); // "2".
  print point("y"); // "3".
  ```

* ```
  class DevonshireCream {
    serveOn() {
      return "Scones";
    }
  }
  
  print DevonshireCream; // Prints "DevonshireCream".
  
  class Person {
    sayName() {
      print this.name;
    }
  }
  
  var jane = Person();
  jane.name = "Jane";
  
  var bill = Person();
  bill.name = "Bill";
  
  bill.sayName = jane.sayName;
  bill.sayName(); // ?
  ```

* ```
  class Cake {
    taste() {
      var adjective = "delicious";
      print "The " + this.flavor + " cake is " + adjective + "!";
    }
  }
  
  var cake = Cake();
  cake.flavor = "German chocolate";
  cake.taste(); // Prints "The German chocolate cake is delicious!".
  
  class Foo {
    init() {
      print this;
    }
  }
  
  var foo = Foo();
  print foo.init();
  ```

* ```
  class Doughnut {
    cook() {
      print "Fry until golden brown.";
    }
  }
  
  class BostonCream < Doughnut {}
  
  BostonCream().cook();
  ```