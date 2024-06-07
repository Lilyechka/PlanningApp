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
('Oksana', 'ADMIN', '$2a$10$Zd4QVimJZnaso/W7ruNAL.9jwEw3zER6nR4mjOkLjpCEGjm45VQNy', 'Oksana@example.com'), -- password: Oksana.123@
('Julia', 'ADMIN', '$2a$10$BJ7wXKyyvZhfL4umXrgCKO6Iy2WJfU71Z7x8QaQ9y1pKkPuqsui1K', 'Julia@example.com'), -- password: Julia.123@
('Kira', 'USER', '$2a$10$liUNKJucPOijptbsSBUq.Oi/iDVZONpT7JSdgKQnxCx2/QyoUuHQm', 'Kira@example.com'); -- password: Kira.123@



INSERT INTO tasks (id_user, topic, deadline, description_task)
 VALUES 
(1, 'Task for Oksana', '2024-06-17T12:00:00', 'Description for Oksana`s task'),
(2, 'Task for Julia', '2024-06-15T12:00:00', 'Description for Julia`s task'),
(3, 'Task for Kira', '2024-06-20T12:00:00', 'Description for Kira`s task');


-- DROP TABLE users;
-- DROP TABLE tasks;

