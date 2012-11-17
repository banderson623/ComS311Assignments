Brian Anderson, Com S 311, Readme for Project 7.

Disclaimer
===========
I didn't have enough time to budget for a great solution to this problem.
I tried my best give the 4 full hours I had to do this project.


On enumerateAnagramsUnderE()
==============================
I am happy with my enumerateAnagramsUnderE() implementation. There isn't much pruning
happening at this level, though I have only elected to do the dictionary check
if the string length is the same for what I am looking for


On enumerateAnagramsUnderBagE
===============================
I didn't have enough time to optimize this. I basically find all of the anagram sets
that are less than or equal to the total length. But I didn't find a way to optimize
this to find the correct size only, so I need to loop through it again and weed
out the ones that are too small.

Oops.




A readme.txt file containing a discussion of your algorithm’s pruning strategies
and your choice in dictionary data structures. Were your choices successful?
How successful? Why do they work or not work? Back up your arguments with data for bonus points!