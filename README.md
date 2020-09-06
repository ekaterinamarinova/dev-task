# dev-task
## Implementation task
- A) The persistent storage is implemented in the PersistentMap class.
- B) The custom implementation is in the Map class.
- C) A distributed version of the storage could be made possible by having the storage instance run either on organised network servers, independent computers or other organisation networks.
Distributed databases may improve performance at end-user worksites by allowing transactions to be processed on many machines, instead of being limited to one.
In order for the current persistent storage to be distributed, it has to be physically available in 
different machines. Those machines have to somehow be connected to each other - by residing in the same network for example.
Additional logic needs to ensure that the data is persisted regularly to all of the instances.
This could be implemented with a scheduler that performs this task in a certain amount of time.
When one or more users perform requests at the same time, a configured thread pool could make sure to send each request to 
an available instance of the storage.
## Object-oriented design
- The object model represents a stapler object. It is a tool of many possibilities,
there are three main types of stamplers - office stampler, surgical and construction one.
Office ones are used mostly to pin paper together, surgical ones are used to 
pin skin together after surgeries and construction stamplers can be used on hard materials such as wood.
The programmed object model could be applied as an object used to "pin" other programming objects together.
For example, if we have an application that handles files and we need a functionality to 
virtually relate or pin two or more files together, we can implement the Stampler#pin method to do that.
## Open question - How many buses are there in Plovdiv?
- A literal answer would be this:
officially there are 29 lines of busses. If each line has drivers that work on two shifts and the buses 
come between bus stops in 15 minutes, and it takes about an hour to make a full cirle from first to last bus stop and back,
then there are approximetely 174 buses.
- A more abstract answer would be this:
The number of bus lines is definitely equal to or bigger than the number of boulevards in the City. Which by the way is 16.


