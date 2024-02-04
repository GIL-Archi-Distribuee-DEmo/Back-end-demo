-- Insert example schools
INSERT INTO School (name, location_school, website, description)
VALUES
    ('School A', '40.7128,-74.0060', 'http://www.schoola.com', 'Description of School A'),
    ('School B', '34.0522,-118.2437', 'http://www.schoolb.com', 'Description of School B'),
    ('School C', '51.5074,-0.1278', 'http://www.schoolc.com', 'Description of School C'),
    ('School D', '48.8566,2.3522', 'http://www.schoold.com', 'Description of School D'),
    ('School E', '52.5200,13.4050', 'http://www.schoole.com', 'Description of School E');

-- Insert example courses (renamed Formation to Course)
INSERT INTO Course (name, description, price, capacities, course_language, course_type)
VALUES
    ('Course 1', 'Description of Course 1', 500.00, 30, 'ENGLISH', 'BACHELOR_DEGREE'),
    ('Course 2', 'Description of Course 2', 750.00, 25, 'FRENCH', 'MASTER_DEGREE'),
    ('Course 3', 'Description of Course 3', 600.00, 40, 'ENGLISH_AND_FRENCH', 'PHD_DOCTORATE'),
    ('Course 4', 'Description of Course 4', 800.00, 20, 'FRENCH', 'CROSS_FACULTY_GRADUATE_RESEARCH'),
    ('Course 5', 'Description of Course 5', 900.00, 35, 'ENGLISH', 'SHORT_COURSES');


-- Insert associations between schools and courses (renamed Formation to Course)
INSERT INTO school_course (school_id, course_id)
VALUES
    (1, 1), -- School A offers Course 1
    (2, 2), -- School B offers Course 2
    (2, 3), -- School B offers Course 3
    (3, 4), -- School C offers Course 4
    (4, 5), -- School D offers Course 5
    (5, 1), -- School E offers Course 1
    (5, 3); -- School E offers Course 3

