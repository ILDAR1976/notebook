DELETE FROM user_roles;
DELETE FROM records;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password, registered) VALUES
  ('user', 'user@yandex.ru', '{noop}password', now()),
  ('admin', 'admin@gmail.com', '{noop}admin', now()),
  ('u', 'u@u.u', '{noop}u', now());


INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO records (date_time, description, user_id)
VALUES ('2022-05-07T08:00:14.127Z', 'тел.8-555-895-54-45', 100000),
       ('2022-05-07T10:00:14.127Z', 'тел.8-555-195-24-35', 100000);
INSERT INTO records (date_time, description, user_id)
VALUES ('2022-05-07T18:00:14.127Z', 'тел.8-525-895-54-45', 100001),
       ('2022-05-07T15:00:14.127Z', 'тел.8-515-195-24-35', 100001);

