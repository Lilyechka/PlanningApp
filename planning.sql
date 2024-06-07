USE planner;
CREATE TABLE users (
    id_user BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    role VARCHAR(5) NOT NULL,
    password VARCHAR(100) NOT NULL,
    email VARCHAR(50) NOT NULL,
    date_of_registration DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE tasks (
    id_task BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_user BIGINT,
    topic VARCHAR(500) NOT NULL,
    date_of_creation DATETIME DEFAULT CURRENT_TIMESTAMP,
    deadline DATETIME,
    description_task VARCHAR(10000),
    FOREIGN KEY (id_user) REFERENCES users (id_user) ON DELETE CASCADE
);

INSERT INTO users (username, role, password, email)
 VALUES 
('Julia', 'ADMIN', '$2a$10$7QZjEvv5YV58z1jGxKZTZO96QGlFQUnIo1F8nJhZSv8EbU1rD8AXG', 'admin@example.com'), -- password: admin
('Kira', 'USER', '$2a$10$7QZjEvv5YV58z1jGxKZTZO96QGlFQUnIo1F8nJhZSv8EbU1rD8AXG', 'user1@example.com'), -- password: user
('Mira', 'USER', '$2a$10$7QZjEvv5YV58z1jGxKZTZO96QGlFQUnIo1F8nJhZSv8EbU1rD8AXG', 'user2@example.com'); -- password: user


INSERT INTO tasks (id_user, topic, deadline, description_task)
 VALUES 
(2, 'Task for user1', '2024-06-15T12:00:00', 'Description for task1'),
(3, 'Task for user2', '2024-06-20T12:00:00', 'Description for task2');


-- DROP TABLE users;
-- DROP TABLE tasks;

ALTER TABLE tasks MODIFY COLUMN id_user BIGINT;


