# Migrating the Monolithic Software
Application GridVis to a Microservice
Based Architecture


**Digitalization and Industry 4.0 drive the usage of cloud-based cost-efficient software.
Customers aim for service-oriented use of software solutions.** As a result, more and more
software producers have begun to migrate their legacy systems and infrastructure to a
more scalable fault tolerant architecture [12]. An architectural pattern opening such a perspective
is called microservice architecture.
There is plenty of literature and web information focused on common microservice principles and
design patterns. This literature frequently comes with short and straightforward examples to
demonstrate a specific technology or concept. But companies want to ship complex and feature-
rich enterprise software, which is a composition of these ideas. Developers must understand
every single technology and individually apply each technology on their project by balancing
pros and cons. Since divide and conquer is favourable, the compound set of design principles and
technologies are not trivial. Connecting system parts or technologies may become sophisticated.
A migration to a microservice system offers dozens of technology choices and engineers should
have knowledge about distributed systems.
Many companies maintain and extend an already existing monolithic legacy software system.
The maintenance of these systems can be increasingly expensive, since old technical debts still
exist. Migration to a microservice-based architecture can increase software quality.
But an architectural evaluation progress throws up several questions like the following:

- When is a migration of an existing monolithic software application to a microservice archi-
tecture reasonable?
- How could we start a migration?
- Which technologies are best suited for our project?
- What are the risks and challenges we will face during a migration?
- How do we split up a monolithic software application?

This thesis contains an empirical investigation dealing with the above-mentioned questions
by guiding the reader through a transparent real-life examination of a productive business
software called GridVis. Since the scope of microservice architectures is way too large, I will
mainly focus on implementation instead of deployment and maintenance. My focus basically lies
on the extraction of existing functionalities and their implementation. Please consider that I do
not claim that the presented techniques work best for any project.


![Overview](/alarm_sys_model.jpg)