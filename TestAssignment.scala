package com.mylivn.assignment

import zio.clock.Clock
import zio.duration._
import zio.{IO, UIO, URIO}

// All docs on ZIO can be found on zio.dev website.
// Unit tests are highly appreciated.
object TestAssignment {

  sealed case class Server(host: String, port: Int)

  sealed case class Request(body: String)
  sealed trait Response
  case class SuccessResponse(code: Int, body: String)
  case class FailureResponse(code: Int, body: String)

  sealed trait HttpClientError
  case object ConnectionError extends HttpClientError
  case object TimeoutError    extends HttpClientError

  sealed trait CircuitBreakerOpened
  case object CircuitBreakerOpened extends CircuitBreakerOpened

  // Please implement following method that load balances http calls between servers (round robin).
  def withLoadBalancer(servers: List[Server], call: Request => Server => IO[HttpClientError, Response]): UIO[Request => IO[HttpClientError, Response]] = ???

  // Please implement following method that retries HTTP calls.
  //
  // Should retry HTTP call while:
  //  - HTTP code is 5xx
  //  - connection error happens
  //  - maximum number retries not exceeded
  // Should stop as soon as:
  //  - HTTP 2xx,3xx,4xx received
  //  - timout error happens
  //  - maximum numbers of retries exceeded
  //
  // Max retries = 0 => should call remote at least once
  def withRetries(call: IO[HttpClientError, Response], maxRetries: Int = 5): IO[HttpClientError, Response] = ???

  // Please implement following method that wraps each server access with a circuit breaker.
  // Circuit breaker can be in following states:
  //  - Closed = requests pass through
  //  - Opened = requests immediately fail
  //  - Half-opened = all requests immediately fail, except one at a time that pass through to check if remote server still fails.
  //
  // Circuit breaker should consider only ConnectionError as failure.
  //
  // Should allow `maxFailures` number of failures in a row before opening. Success resets the counter.
  // Should remain opened until `remainOpened` elapsed, when time elapsed becomes half-opened.
  // When half-opened should allow one request at a time, to check if can be closed again
  //   (if call succeeds it becomes closed, otherwise remains opened for another `remainOpened` time).
  def withCircuitBreaker(
      call: Request => Server => IO[HttpClientError, Response],
      maxFailures: Int = 5,
      remainOpened: Duration = 15.seconds
  ): URIO[Clock, Request => Server => IO[Either[CircuitBreakerOpened, HttpClientError], Response]] = ???
}
