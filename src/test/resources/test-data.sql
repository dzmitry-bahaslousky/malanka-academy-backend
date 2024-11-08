INSERT INTO users (username, email, password)
VALUES ('jcoldsun', 'email@email.com', 'test'),
       ('test', 'email1@email.com', 'test');

INSERT INTO users_roles (user_id, role_id)
VALUES ((SELECT id FROM users WHERE username = 'jcoldsun'), (SELECT id FROM roles WHERE name = 'ROLE_ADMIN')),
       ((SELECT id FROM users WHERE username = 'test'), (SELECT id FROM roles WHERE name = 'ROLE_USER'));

INSERT INTO courses (id, title, description, created_by, last_modified_by)
VALUES ('3c91cc57-298a-4e88-9b40-a52d4720bb93', 'test', 'test desc',
        (SELECT id FROM users WHERE username = 'jcoldsun'),
        (SELECT id FROM users WHERE username = 'jcoldsun'));

INSERT INTO topics (title, order_id, course_id, created_by, last_modified_by)
VALUES ('topic 1', 1,
        (SELECT id FROM courses WHERE title = 'test'),
        (SELECT id FROM users WHERE username = 'jcoldsun'),
        (SELECT id FROM users WHERE username = 'jcoldsun')),
       ('topic 2', 2,
        (SELECT id FROM courses WHERE title = 'test'),
        (SELECT id FROM users WHERE username = 'jcoldsun'),
        (SELECT id FROM users WHERE username = 'jcoldsun'));

INSERT INTO topic_items (title, order_id, topic_id, created_by, last_modified_by)
VALUES ('lecture 1', 1,
        (SELECT id FROM topics WHERE title = 'topic 1'),
        (SELECT id FROM users WHERE username = 'jcoldsun'),
        (SELECT id FROM users WHERE username = 'jcoldsun')),
       ('recourse 1', 2,
        (SELECT id FROM topics WHERE title = 'topic 1'),
        (SELECT id FROM users WHERE username = 'jcoldsun'),
        (SELECT id FROM users WHERE username = 'jcoldsun')),
       ('lecture 1', 1,
        (SELECT id FROM topics WHERE title = 'topic 2'),
        (SELECT id FROM users WHERE username = 'jcoldsun'),
        (SELECT id FROM users WHERE username = 'jcoldsun')),
       ('recourse 1', 2,
        (SELECT id FROM topics WHERE title = 'topic 2'),
        (SELECT id FROM users WHERE username = 'jcoldsun'),
        (SELECT id FROM users WHERE username = 'jcoldsun'))