# Shuffler
Shuffler is a Java class that is used to randomize a list of objects. The input (call it numObjects) is an integer; this is the number of items in your list. The output is an array of integers of size numObjects, where the array contains the values between zero and (numObjects - 1). This array contains the indices to use when shuffling your list of objects. For example, if the input is 4, the output might be [2, 1, 3, 0].

This class has only one method:

```
  public static int[] randomize(int nSize)
```

A sample call is:

```
  int[] data = Shuffler.randomize(10);
```

This call would return an array of 10 integers, containing the values zero to nine.
This class provides functionality similar to the Shuffle method in the Java API. The reason I wrote this class was out of curiosity to see if there was another way to perform the same operation. The algorithm behind this method is fairly straightforward, and probably best explained by an example.

If the size of the list is 6, then we want to shuffle the numbers [0, 1, 2, 3, 4, 5]. First, the method generates a random number between zero and five. Assume this number is 1. Now, our output list is {1}, so we remove '1' from the input list, leaving us with [0, 2, 3, 4, 5] to shuffle. Next, we generate another random number, this time between zero and four. Assume this number is 3. The method now finds the third number in the input list and moves that to the end of the output list. Now our input set is [0, 2, 4, 5] and our output set is {1, 3}. This process continues, until there is only one number left in the input set. That number is moved to the output set, and the process is complete.

The source code is released under the MIT license.
