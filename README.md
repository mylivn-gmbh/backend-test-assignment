## Scala/Java developer test assignment

### Description
In this test assignment candidate should implement a simple message queue.
Message broker and message interfaces are provided as a part of this test assignment.

Candidate should submit following implementations:
* in-memory message queue 
* local filesystem message queue
* wrapper around Amazon SQS (_optional_)

First two implementations are mandatory. The last one is optional (if you have some time left)

### Requirements

#### General
* You can implement this test assignment in Java, Scala or Kotlin. 
* Message broker should support multiple queues.
* When producer tries to send a message to a queue that does not exist, it should be created automatically.
* Writing/reading to/from one queue should not affect writing/reading to/from another queue.
* Each queue should be able to serve multiple producers and consumers.
* Queue should not contain messages that were successfully processed. Only new ones and those that are being processed.

#### In-memory
* Each queue should be thread safe.

#### Local filesystem
* Each queue should be thread and inter-process safe. 
* Performance can be a trade-off here. When one consumer or producer is using the queue, other consumers and producers can wait for this queue.
* Choose simplicity over complexity. You should not spend more than 2 hours for this implementation.

#### Amazon SQS
* Start this implementation only if you have everything else covered.

#### Tests
* You should cover provided interfaces with tests.
* You can use JUnit, TestNG, Scalatest, Specs, etc.

#### Project
* You project should use Maven, Gradle or SBT.
* Do not use unnecessary libraries.

#### Quality
This is a test assignment. However, it is used not only to determine your coding skills but also how you write your code.
When we review applicant's solution, we expect to see production ready code, which does not break in any real-world scenario (handles all important corner cases). Ability to predict corner cases for the problem an applicant solves is one of the most important engineering skills that we check.

### Bonus
If you have completed whole test assignment, polished it and consider it ideal and want more challenge to show your skills you can do a bonus part.

Design interfaces and implement a library that allows making RMI calls over MessageBroker. If you'll decide to do this part, please focus on interface design.   

### Submission
After you finished your test assignment, please send us a link to download it to hr@mylivn.com. Please do not put your solution to any publicly available place on the internet (like GitHub, BitBucket). Best way to share files with us is to put them to any private cloud storage and provide us a private link.
Also, your project should contain a README.md file with following info:
* How to build your code
* Short description of your solution
* Assumptions you made while planning and structuring your solution  