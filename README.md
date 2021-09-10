# Spring REST API for benchmark tests

*Free online scholarships, for Brazilian people, can be found on https://lnkd.in/e2f-aeNX*

This repository was created in order to host a Spring REST API for benchmark tests by comparing it to other ones.

This REST API was built in Java with Spring Boot to store data in a PostgreSQL database.

The API was deployed to the Heroku platform and it provides a GET endpoint that returns a page of products. Before running requests to the API, you should consult its status by requesting it on https://rest-sp.herokuapp.com/actuator/health. It may take some seconds until the app returns "UP" status because it's hosted for free to Heroku. You will get a response like that:

```
HTTP/1.1 200 
Connection: keep-alive
Content-Type: application/vnd.spring-boot.actuator.v3+json
Date: Thu, 09 Sep 2021 19:12:26 GMT
Keep-Alive: timeout=60
Transfer-Encoding: chunked

{
    "status": "UP"
}
```

But when the server wakes up, the app has a great performance. 

To make requests, type https://rest-sp.herokuapp.com/api/v1/products and you must expect an answer like that:

```
HTTP/1.1 200 
Connection: keep-alive
Content-Type: application/json
Date: Thu, 09 Sep 2021 19:11:20 GMT
Keep-Alive: timeout=60
Transfer-Encoding: chunked

{
    "content": [
        {
            "id": "1",
            "name": "Vacuum",
            "price": 199.0
        }
    ],
    "empty": false,
    "first": true,
    "last": true,
    "number": 0,
    "numberOfElements": 1,
    "pageable": {
        "offset": 0,
        "pageNumber": 0,
        "pageSize": 20,
        "paged": true,
        "sort": {
            "empty": true,
            "sorted": false,
            "unsorted": true
        },
        "unpaged": false
    },
    "size": 20,
    "sort": {
        "empty": true,
        "sorted": false,
        "unsorted": true
    },
    "totalElements": 1,
    "totalPages": 1
}
```

You can make benchmark tests with your favorite benchmark tool and check how much time the app takes to respond to concurrent requests. I suggest you use the "ab" (ApacheBench) application on Unix-like systems. For instance, on Ubuntu 20, I use the following command to do that:

:~$ ab -n 1000 -c 1000 https://rest-sp.herokuapp.com/api/v1/products
With the above command, I make 1000 concurrent requisitions to the server.

By running it at localhost, I got the following response:

```
This is ApacheBench, Version 2.3 <$Revision: 1843412 $>
Copyright 1996 Adam Twiss, Zeus Technology Ltd, http://www.zeustech.net/
Licensed to The Apache Software Foundation, http://www.apache.org/

Benchmarking localhost (be patient)
Completed 100 requests
Completed 200 requests
Completed 300 requests
Completed 400 requests
Completed 500 requests
Completed 600 requests
Completed 700 requests
Completed 800 requests
Completed 900 requests
Completed 1000 requests
Finished 1000 requests

Server Software:        
Server Hostname:        localhost
Server Port:            8080

Document Path:          /api/v1/products
Document Length:        356 bytes

Concurrency Level:      1000
Time taken for tests:   3.244 seconds
Complete requests:      1000
Failed requests:        0
Total transferred:      461000 bytes
HTML transferred:       356000 bytes
Requests per second:    308.26 [#/sec] (mean)
Time per request:       3244.005 [ms] (mean)
Time per request:       3.244 [ms] (mean, across all concurrent requests)
Transfer rate:          138.78 [Kbytes/sec] received

Connection Times (ms)
              min  mean[+/-sd] median   max
Connect:        0  434 535.7     63    1211
Processing:    73 1007 326.3   1075    2083
Waiting:       67  956 333.2    983    2074
Total:        102 1441 597.5   1325    2467

Percentage of the requests served within a certain time (ms)
  50%   1325
  66%   1681
  75%   2049
  80%   2141
  90%   2306
  95%   2389
  98%   2449
  99%   2455
 100%   2467 (longest request)
```

The other two APIs are also being developed to compare their results with this one. I will update this README file next few days.

Thanks for reading it!

*Free online scholarships, for Brazilian people, can be found on https://lnkd.in/e2f-aeNX