#ReadMe

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

  
