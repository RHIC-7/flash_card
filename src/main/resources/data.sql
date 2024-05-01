INSERT INTO users (username, email, created_at)
VALUES
('Ethan', 'ethan@example.com', '2019-11-12 08:34:19'),
('Olivia', 'olivia@example.com', '2020-01-15 09:00:00'),
('Ava', 'ava@example.com', '2020-05-21 10:21:36'),
('William', 'william@example.com', '2021-02-11 15:45:22'),
('Sophia', 'sophia@example.com', '2021-03-30 12:30:45');


INSERT INTO categories (category_name, user_id, created_at)
VALUES
('AWS', 1, '2019-01-03 11:02:35'),
('Azure', 2, '2020-01-04 12:05:00'),
('Google Cloud', 3, '2020-05-25 13:15:45'),
('IBM Cloud', 4, '2021-02-20 14:25:50'),
('Oracle Cloud', 5, '2021-04-22 16:30:55');


INSERT INTO words (category_id, word, description, user_id, created_at)
VALUES
(1, 'EC2', 'Virtual Server in the Cloud', 1, '2019-01-03 11:02:35'),
(2, 'VM', 'Virtual Machine', 2, '2020-01-04 12:05:00'),
(3, 'App Engine', 'PaaS for Apps', 3, '2020-05-25 13:15:45'),
(4, 'Kubernetes', 'Container Orchestration', 4, '2021-02-20 14:25:50'),
(5, 'OCI', 'Oracle Cloud Infrastructure', 5, '2021-04-22 16:30:55');
