#lang scheme

;;; Reference: http://en.wikipedia.org/wiki/Sieve_of_Eratosthenes
;;; Quoting that wikipedia page the algorithm used is the following:
;;; To find all the prime numbers less than or equal to a given integer n by Eratosthenes' method:
;;;
;;;   1. Create a list of consecutive integers from two to n: (2, 3, 4, ..., n).
;;;   2. Initially, let p equal 2, the first prime number.
;;;   3. Strike from the list all multiples of p less than or equal to n. (2p, 3p, 4p, etc.)
;;;   4. Find the first number remaining on the list after p (this number is the next prime); replace p with this number.
;;;   5. Repeat steps 3 and 4 until p2 is greater than n.
;;;   6. All the remaining numbers in the list are prime.
;;; I initially wrote and debugged this code in lisp but my lisp of choice (sbcl) does not do tail call optimize the function sieve
;;; hence this port to plt scheme (http://racket-lang.org/) where the code does work without blowing the stack and the runtimes seem reasonable enough that 
;;; I am happy with the performance as is. 
;;; NOTE : this code is not optimized for speed at all so please dont use it in a benchmark / comparision.
;;; Usage: (how-many from to *primes*)


(define (filter-multiples upper-bound bag number)
  (cond
    ((null? bag) '())
    ((= 0 (remainder (first bag) number)) (filter-multiples upper-bound (rest bag) number))
    (#t (cons (first bag) (filter-multiples upper-bound (rest bag) number)))))

(define (sieve bag upper-bound)
  (if (null? bag)
      '()
      (begin
        (let
            ((prime (first bag)))
          (cond
            ((> prime (sqrt upper-bound)) bag)
            (#t (cons prime (sieve (filter-multiples upper-bound (rest bag) prime) upper-bound))))))))


(define (build-list to)
  (let ((bag '()))
    (do ((var 2 (+ var 1)))
      ((> var to) (reverse bag))
      (set! bag (cons var bag)))))

(define (factorize number primes)
  (cond 
    ((= number 1) 1)
    ((> (first primes) (sqrt number)) (list number 1))
    ((= 0 (remainder number (first primes))) (cons (first primes) (factorize (quotient number (first primes)) primes)))
    (#t (factorize number (rest primes)))))

(define (prime? number primes)
  (findf (lambda (x) (= number x)) primes))

(define (underprime? number primes)
  (if (prime? (count (lambda (x) (prime? x primes)) (factorize number primes)) primes)
      #t
      #f))


(define (how-many from to primes)
  (let ((count 0))
    (do ((var from (+ var 1)))
      ((> var to) count)
      (when (underprime? var primes)
          (set! count (+ count 1))))))

(define *primes* (sieve (build-list 100000) 100000))