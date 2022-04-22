DELETE FROM user_roles;
DELETE FROM meals;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password, registered) VALUES
  ('User', 'user@yandex.ru', '{noop}password', now()),
  ('Admin', 'admin@gmail.com', '{noop}admin', now());


INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO book (date_time, name, description, user_id)
VALUES ('2022-04-22 08:00:00', 'Иван', 'тел.8-555-895-54-45', 100000),
       ('2022-04-22 10:00:00', 'Сидр', 'тел.8-555-195-24-35', 100000);
