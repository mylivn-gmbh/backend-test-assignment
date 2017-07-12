# Test assignment

### Summary

In this test assignment you need to develop a small API gateway.
Our internal services are designed the way that they are responsible only for their own small scope and should not be accessed directly. The gateway you are going to implement provide REST API interface for external users and communicate with internal services also using REST API.

Three main responsibilities of API gateway:
- Aggregate data from internal services
- Validate requests from API users
- Be resilient to failures in internal services


### Internal services

There is actually one service, but we assume that they are 3 of them for this assignment purposes.

You can easily install and run test assignment services from hub.docker.com.

	docker pull mylivn/backend-test-asignment-services:latest

	docker run --name mylivn-test-services \
		-p 9999:9000 \
		-d mylivn/backend-test-asignment-services:latest

#### Posts services

- Create new post

	```
	POST /api/v0/posts/
	request:
	{
		"user_key": 123, //post author
		"title": "tilte",
		"description": "description"
	}
	response:
	{
		"post_key": 123
	}
	```

- Get post

	```
	GET /api/v0/posts/${id}
	response:
	{
	    "post_key": 321,
	    "user_key": 123,
	    "title": "tilte",
	    "description": "description"
	}
	```

- Delete post

	```
	DELETE /api/v0/posts/${id}
	```

#### Users service

- Create new user
	```
	POST /api/v0/users/
	request:
	{
		"username": "Elton John"
	}
	response:
	{
		"user_key": 321
	}
	```

- Get user

	```
	GET /api/v0/users/${id}
	response:
	{
		"user_key": 321
		"username": "Elton John"
	}
	```

- Delete user

	```
	DELETE /api/v0/users/${id}
	```

#### Comments service

- Create comment

	```
	POST /api/v0/comments/
	request:
	{
		"user_key": 321,
		"post_key": 1,
		"text": "Sorry Seems To Be The Hardest Word"
	}
	response:
	{
		"comment_key": 123
	}
	```

- Get post comments

	```
	GET /api/v0/comments/
	response:
	{
		"comments":[
			{
				"comment_key": 123
				"user_key": 321,
				"post_key": 1,
				"text": "Sorry Seems To Be The Hardest Word"
			}
		]
	}
	```

- Delete comment

	```
	DELETE /api/v0/comments/${id}
	```


### API Gateway

For simplicity we skip authentication process.
Any request can or can not contain HTTP Header `x-auth-token`. This header contains user key. If this header is present and *user exists* then user is authorized.


API gateway should provide following API:
- **Register user**

	Just create user in users service.
	Only unauthorized user can create new user.

- **Delete user**

	Delete user from users service
	Only authorized user can delete himself.

- **Create post**

	Create new post in posts service.
	Only existing user can create posts.

- **Delete post**

	Delete post from posts service.
	Only post owner can delete post.

- **Create comment**

	Create new comment in comments service.
	Post should exist.
	User should be authorized.


- **Delete comment**

	Delete comment from comments service.
	Only post owner or comment owner can delete comment.

- **Get post with comments**

	Should return an aggregate that should contain:

	- post key
	- post title
	- post description
	- all comments sorted by comment key
	- each comment should contain author name
	- if comment owner does not exist anymore, author name should be `<deleted user>`


Your API should be REST compliant including proper HTTP methods, response codes and headers.

### Language and framework

We ask you to choose technology stack that you will use for this assignment:

	- Scala + Play Framework
	- Java 8 + Spring Boot

You are applying for Scala developer position. So Scala is preferred. However if you have not enough skills in Play Framework or even Scala, but you still want to present your skills and continue with interview you can use Java 8.


### Implementation, solution and submission

- We need to be able to run and build your code ourselves, so please submit your code as a zipped file of source code and supporting files, without any compiled code. Please do not put your solution in open GitHub repository.

- Please include a brief explanation of your design and assumptions, along with your code, as well as detailed instructions to run your application.

- We expect you to submit what you believe is production-quality code; code that you’d be able to run, maintain, and evolve; your code should follow best Java/Scala practices.
