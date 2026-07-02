MERGE INTO tbl_instructor (id, username, password) KEY (username) VALUES (1, 'admin', 'admin123');

MERGE INTO tbl_category (id, name, frequency) KEY (name) VALUES (1, 'java', 2);
MERGE INTO tbl_category (id, name, frequency) KEY (name) VALUES (2, 'spring', 2);
MERGE INTO tbl_category (id, name, frequency) KEY (name) VALUES (3, 'python', 1);
MERGE INTO tbl_category (id, name, frequency) KEY (name) VALUES (4, 'javascript', 1);
MERGE INTO tbl_category (id, name, frequency) KEY (name) VALUES (5, 'database', 1);

MERGE INTO tbl_course (id, title, description, content, status, category, created_at, updated_at) KEY (id) VALUES
(1, 'Java Fundamentals', 'Learn core Java programming from scratch', 'Covers OOP, collections, streams, and exception handling with hands-on exercises.', 2, 'java', NOW(), NOW());
MERGE INTO tbl_course (id, title, description, content, status, category, created_at, updated_at) KEY (id) VALUES
(2, 'Spring Boot Masterclass', 'Build production-ready REST APIs with Spring Boot', 'Topics: dependency injection, JPA, security, actuator, and deployment.', 2, 'spring,java', NOW(), NOW());
MERGE INTO tbl_course (id, title, description, content, status, category, created_at, updated_at) KEY (id) VALUES
(3, 'Python for Data Science', 'Data analysis, visualization, and machine learning with Python', 'Covers NumPy, Pandas, Matplotlib, and scikit-learn.', 2, 'python', NOW(), NOW());
MERGE INTO tbl_course (id, title, description, content, status, category, created_at, updated_at) KEY (id) VALUES
(4, 'SQL & Database Design', 'Master relational databases and complex queries', 'Normalization, indexes, joins, subqueries, and transaction management.', 2, 'database', NOW(), NOW());
MERGE INTO tbl_course (id, title, description, content, status, category, created_at, updated_at) KEY (id) VALUES
(5, 'Draft: Advanced Topics', 'Preview of upcoming advanced content', 'Work in progress - full content coming soon.', 1, 'java,spring', NOW(), NOW());

MERGE INTO tbl_review (id, course_id, author_name, email, rating, content, status, created_at) KEY (id) VALUES
(1, 1, 'Alice Nguyen', 'alice@example.com', 5, 'Excellent introductory course! Very well explained.', 2, NOW());
MERGE INTO tbl_review (id, course_id, author_name, email, rating, content, status, created_at) KEY (id) VALUES
(2, 1, 'Bob Tran', 'bob@example.com', 4, 'Great content but could use more exercises.', 2, NOW());
MERGE INTO tbl_review (id, course_id, author_name, email, rating, content, status, created_at) KEY (id) VALUES
(3, 2, 'Charlie Le', 'charlie@example.com', 5, 'Best Spring Boot course I have taken!', 2, NOW());
MERGE INTO tbl_review (id, course_id, author_name, email, rating, content, status, created_at) KEY (id) VALUES
(4, 3, 'Diana Vu', 'diana@example.com', 3, 'Good overview but lacks depth in ML section.', 1, NOW());
MERGE INTO tbl_review (id, course_id, author_name, email, rating, content, status, created_at) KEY (id) VALUES
(5, 4, 'Eve Pham', 'eve@example.com', 4, 'Very practical database course.', 1, NOW());

MERGE INTO tbl_lookup (id, type, lookup_key, lookup_value) KEY (id) VALUES
(1, 'course_status', '1', 'Draft');
MERGE INTO tbl_lookup (id, type, lookup_key, lookup_value) KEY (id) VALUES
(2, 'course_status', '2', 'Published');
MERGE INTO tbl_lookup (id, type, lookup_key, lookup_value) KEY (id) VALUES
(3, 'course_status', '3', 'Archived');
MERGE INTO tbl_lookup (id, type, lookup_key, lookup_value) KEY (id) VALUES
(4, 'review_status', '1', 'Pending');
MERGE INTO tbl_lookup (id, type, lookup_key, lookup_value) KEY (id) VALUES
(5, 'review_status', '2', 'Approved');
