CREATE TABLE School
(
    school_id       BIGSERIAL PRIMARY KEY,
    name            VARCHAR(255) NOT NULL,
    location_school VARCHAR(255) NOT NULL,
    website         VARCHAR(255) NOT NULL,
    description     TEXT NOT NULL
);

CREATE TABLE Course
(
    course_id       BIGSERIAL PRIMARY KEY,
    name            VARCHAR(255) NOT NULL,
    description     VARCHAR(255) NOT NULL,
    price           NUMERIC NOT NULL,
    capacities      INT NOT NULL,
    course_type     VARCHAR(255),       -- Course type field
    course_language VARCHAR(255)       -- Course language field
);

CREATE TABLE school_course
(
    school_id  BIGSERIAL NOT NULL,
    course_id  BIGSERIAL NOT NULL,
    PRIMARY KEY (school_id, course_id),
    FOREIGN KEY (school_id) REFERENCES School (school_id),
    FOREIGN KEY (course_id) REFERENCES Course (course_id)
);

