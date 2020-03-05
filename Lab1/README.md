# Lab 1

#### What Does This Do
This is a simple group of functions designed to address the prompts presented below.

3. Software Decode - 111 Detection: Write a function that accepts an in-order array of
unsigned short values. The function shall then scan the array for
a specific pattern: Three values contained within the array equally
spaced 20 units apart. The function shall return the index position
within the original array where the pattern begins or -1 if not present.
Given the input array: data[] = {10,20,31,40,55,60,65525}
The function shall return: 1

4. 1-1-0 Detection: Modify Problem 3 such that it returns the index of
the beginning of a sequence where the first two elements are 20 units
apart and there is NO element 40 units away from the first. Two
elements present, one absent.

5. Modify Problem 3 to generalize the gap spacing. Instead of hardcoding the difference of 20 into the function, change the function signature to include it as a parameter.

6. Modify Problem 3 such that the input array may wrap-around or overflow.
Given the input array: data[] = {65515,65525,9,20,31,40,55}
The function shall return: 0
