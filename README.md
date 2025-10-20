Mini Doodle is a lightweight backend simulation of a meeting scheduling platform built with Spring Boot and Java.
It allows users to manage their personal calendars, define available time slots, schedule meetings, and query their free or busy availability over a selected time frame.

Key Features:

Time Slot Management – Create, modify, or delete availability slots.

Meeting Scheduling – Convert available slots into meetings with titles, descriptions, and participants.

Calendar View (Domain Concept) – Each user has a personal calendar where time is managed.

Availability Queries – Retrieve aggregated free/busy data for any given period.

Persistence – All data is stored in a PostgreSQL database for reliable querying and management.

Dockerized Setup – Easily runnable locally with docker-compose.

Scalable Design – Optimized entity structure and indexing for handling hundreds of users and thousands of slots.

Tech Stack:

Java 17

Spring Boot (Web, Data JPA, Validation)

PostgreSQL

Docker / Docker Compose

Maven
