# Warwick Business School Project

This project aims to provide a comprehensive backend system for managing daily records, teams, and metrics for the Warwick Business School.

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Technologies](#technologies)
- [Setup and Installation](#setup-and-installation)
- [Usage](#usage)
- [Endpoints](#endpoints)
- [Database](#database)
- [Tests](#tests)



## Overview

The Warwick Business School Project provides a backend system to manage daily records, teams, and metrics. It facilitates the storage, retrieval, and manipulation of data related to daily records and associated metrics.

## Features

- Retrieve daily records by date,metric and teams
- Create, update, and delete teams and metrics (to be implemented)
- Secure endpoints and user authentication (to be implemented)
- Compare individual team performance and results (to be implemented)
- Handle server loads with JWT authentication and API limiting (to be implemented)
- Additional data filtering endpoints for records (to be implemented)

## Technologies

- Spring Boot
- Spring Data JPA
- MySQL
- Hibernate
- JWT Authentication (to be implemented)

## Setup and Installation

1. Clone the repository:
    ```bash
    git clone <repository_url>
    ```

2. Navigate to the project directory:
    ```bash
    cd <project_directory>
    ```

3. Build and run the application using Maven:
    ```bash
    mvn spring-boot:run
    ```

4. Set up a MySQL database and configure the database URL, username, and password in `application.yml`.

## Usage

The backend provides RESTful APIs for managing daily records, teams, and metrics. Detailed documentation of the endpoints is available in the [Endpoints](#endpoints) section.

## Endpoints

Detailed documentation of the API endpoints is available here:

- `GET /records/day?date=<date>`: Retrieve daily records for a specific date.
- `GET /records/metric?date=<date>&metric=<metric>`: Retrieve daily records for a specific date and metric.

## Database

The database schema consists of three main tables:
1. `daily_record`: Stores daily records with metrics, teams, values, and dates.
2. `metric`: Contains information about metrics, such as name and measurement.
3. `team`: Stores information about teams, such as name.

SQL queries to define the database schema:
```sql
create table metric (
    id integer not null auto_increment,
    measurement varchar(255),
    name varchar(255),
    primary key (id)
);

create table team (
    id integer not null auto_increment,
    name varchar(255),
    primary key (id)
);
create table daily_record (
    id integer not null auto_increment,
    metric_id integer,
    team_id integer,
    value float(53) not null,
    date datetime(6),
    primary key (id)
);
alter table daily_record 
   add constraint fk_daily_record_metric 
   foreign key (metric_id) 
   references metric (id);

alter table daily_record 
   add constraint fk_daily_record_team 
   foreign key (team_id) 
   references team (id);

```
## Testing

### Unit Tests

This project includes unit tests for the `MetricService` and `TeamService`. To run the unit tests, execute the following command:

```bash
mvn test