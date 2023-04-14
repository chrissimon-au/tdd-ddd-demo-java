# TDD & DDD Demo in Java

Are you interested in using Domain-Driven Design (DDD) to create maintainable and scalable software, but not sure how to get started? Or perhaps you've heard that DDD is only suitable for complex domains - and when starting out, you're not sure if your project will need it?

This repository contains a demo of applying Test-Driven Development (TDD) in the implementation of a simple CRUD system and gradually refactoring towards DDD tactical patterns as more complex requirements arise.

It is used to support a [live-coding conference talk](https://sessionize.com/s/chris-simon/tdd-ddd-c-from-the-ground-up-a-live-coding-demo/64826).

This demo is in Java.  Other demos are:

* [DotNet](https://github.com/chrissimon-au/tdd-ddd-demo-dotnet)

## Explanation

TDD follows the `red` -> `green` -> `refactor` cycle:

1. Write a failing test
2. Write the simplest implementation that will help the test pass
3. Refactor the implementation to improve code structure, readability etc.

To demonstrate this cycle, this repository includes a commit after each step.  (Normally you would not commit after the `red` step, i.e. while the tests are failing.)

## Domain

The domain is a system to help a university handle student enrolments. We'll gradually add more complex requirements, such as the need to ensure subjects don't become over-enrolled - which will prompt us to do some code-smell refactoring, strangely enough arriving at things that start to look like the DDD tactical patterns of repositories, aggregates, value objects and domain services.

In implementing these requirements, inspiration will strike! What if the model were changed - what if we allowed all enrolments and then allocated resources to the most popular subjects as required so we never have to prevent a student from enrolling? We'll now see how the TDD tests and the neatly refactored domain models make it much easier to embark on this dramatic change - in other words, how much more maintainable our DDD codebase has become.