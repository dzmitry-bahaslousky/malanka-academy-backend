INSERT INTO users (username, email, password)
VALUES ('jcoldsun', 'email@email.com', '{bcrypt}$2a$12$V4vh7Hd/oTT9YYkbY.b5zuHoLbZCNB9PVfQMOr2w40OB.HtYPC.Da'),
       ('test', 'email1@email.com', '{bcrypt}$2a$12$V4vh7Hd/oTT9YYkbY.b5zuHoLbZCNB9PVfQMOr2w40OB.HtYPC.Da');

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

INSERT INTO topic_items (title, type, order_id, topic_id, created_by, last_modified_by)
VALUES ('lecture 1', 'LECTURE',1,
        (SELECT id FROM topics WHERE title = 'topic 1'),
        (SELECT id FROM users WHERE username = 'jcoldsun'),
        (SELECT id FROM users WHERE username = 'jcoldsun')),
       ('recourse 1', 'RESOURCE',2,
        (SELECT id FROM topics WHERE title = 'topic 1'),
        (SELECT id FROM users WHERE username = 'jcoldsun'),
        (SELECT id FROM users WHERE username = 'jcoldsun')),
       ('lecture 2', 'LECTURE',1,
        (SELECT id FROM topics WHERE title = 'topic 2'),
        (SELECT id FROM users WHERE username = 'jcoldsun'),
        (SELECT id FROM users WHERE username = 'jcoldsun')),
       ('recourse 2', 'RESOURCE',2,
        (SELECT id FROM topics WHERE title = 'topic 2'),
        (SELECT id FROM users WHERE username = 'jcoldsun'),
        (SELECT id FROM users WHERE username = 'jcoldsun'));

INSERT INTO lectures (item_id, video_id, created_by, last_modified_by)
VALUES ((SELECT id FROM topic_items WHERE title = 'lecture 1'), 'ueEa6utvPrzFRgCd3hPDXJ',
        (SELECT id FROM users WHERE username = 'jcoldsun'),
        (SELECT id FROM users WHERE username = 'jcoldsun')),
       ((SELECT id FROM topic_items WHERE title = 'lecture 2'), 'vc1SHgKNdMXYPsjpGSmHsH',
        (SELECT id FROM users WHERE username = 'jcoldsun'),
        (SELECT id FROM users WHERE username = 'jcoldsun'));

INSERT INTO resources (item_id, content, created_by, last_modified_by)
VALUES ((SELECT id FROM topic_items WHERE title = 'recourse 1'), '## Заголовок\n\nЭто **жирный** текст и _курсив_.\n\n- Пункт 1\n- Пункт 2',
        (SELECT id FROM users WHERE username = 'jcoldsun'),
        (SELECT id FROM users WHERE username = 'jcoldsun')),
       ((SELECT id FROM topic_items WHERE title = 'recourse 2'), '## sdfasdfasdf\n\nЭто **жирный** текст и _курсив_.\n\n- Пункт 1\n- Пункт 2',
        (SELECT id FROM users WHERE username = 'jcoldsun'),
        (SELECT id FROM users WHERE username = 'jcoldsun'));