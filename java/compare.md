```tex
StopWatch 'List Get': running time (millis) = 5439
-----------------------------------------
ms        %          Task name
-----------------------------------------
00081  001%  linkedListIterator
00010  000%  arrayListIterator
05342  098%  linkedListGetRandom
00006  000%  arrayListGetRandom

before add, size: 100000
after add, size: 200000
StopWatch 'List Insert': running time (millis) = 28602
-----------------------------------------
ms        %          Task name
-----------------------------------------
00006  000%  linkedListAdd
00004  000%  arrayListAdd
26593  093%  linkedListAddRandom
01990  007%  arrayListAddRandom
00005  000%  arrayListAddUnDetermine
00004  000%  arrayListAddDetermine

```



https://catonmat.net/low-level-bit-hacks

```c
// Check if the integer is even or odd.
if ((x & 1) == 0) {
  x is even
}
else {
  x is odd
}
```

```c
// Test if the n-th bit is set.
if (x & (1<<n) == 0) {
  n-th bit is not set
}
else {
  n-th bit is set
}
```

```c
// Set the n-th bit.
y = x | (1<<n)
    
// Unset the n-th bit.
y = x & ~(1<<n)
    
// Toggle the n-th bit.
y = x ^ (1<<n)
```

```c
// Turn off the rightmost 1-bit.
y = x & (x-1)
    
// Isolate the rightmost 1-bit.
y = x & (-x)

// Right propagate the rightmost 1-bit.
y = x | (x-1)

// Isolate the rightmost 0-bit.
y = ~x & (x+1)
    
// Turn on the rightmost 0-bit.
y = x | (x+1)
```

