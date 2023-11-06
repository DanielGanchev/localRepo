INSERT INTO users (id, is_active, email, first_name, last_name, password)
VALUES
    (1, 1, 'admin@example.com', 'Admin', 'Adminov', '95c1933b8ffe84f085f2839899d1673260be58dbd9c2c787ac35515805502c996417596dae9a92880aaa50a046fc7151');
INSERT INTO users (id, is_active, email, first_name, last_name, password)
VALUES
    (2, 1, 'user@example.com', 'User', 'Userov', '95c1933b8ffe84f085f2839899d1673260be58dbd9c2c787ac35515805502c996417596dae9a92880aaa50a046fc7151');

INSERT INTO user_role (id, role)
VALUES
    (1, 'ADMIN'),
    (2, 'USER');

INSERT INTO users_roles (user_id, role_id)
values (1, 1)  ,
       (1, 2)

     , (2, 2);



INSERT INTO `brands` (`id`, `name`)
VALUES
    (1, 'Toyota'),
    (2, 'Ford'),
    (3, 'BMW'),
    (4, 'Audi');


INSERT INTO `models` (`id`, `category`, `brand_id`, `name`)
VALUES
    (1, 'CAR', 1, 'Camry'),
    (2, 'CAR', 1, 'Corolla'),
    (3, 'CAR', 2, 'Focus'),
    (4, 'CAR', 2, 'Fiesta'),
    (5, 'CAR', 3, 'X5'),
    (6, 'CAR', 3, 'X6'),
    (7, 'CAR', 4, 'A4'),
    (8, 'CAR', 4, 'A6');

INSERT INTO `offers` (`id`,`description`,`engine`,`image_url`,`mileage`,`price`,`transmission`,`uuid`,`year`,`model_id`)
VALUES
   ( '1', 'audi213123', 'PETROL', 'https://upload.wikimedia.org/wikipedia/commons/thumb/d/dc/Audi_A4_40_TFSI_B9_FL_Ibis_White_%281%29_%28cropped%29.jpg/560px-Audi_A4_40_TFSI_B9_FL_Ibis_White_%281%29_%28cropped%29.jpg', '50000', '2000.00', 'AUTOMATIC', 'cb605fd0-e40a-4607-a233-310383868b09', '2010', '7'),
    ('2', 'bwm feee', 'DIESEL', 'https://imgd.aeplcdn.com/642x336/n/cw/ec/41375/x6-exterior-right-front-three-quarter.jpeg?q=80', '200000', '60000.00', 'MANUAL', 'd78e8ae2-230f-47e6-97da-80894b4136bb', '2021', '6'),
    ('3', 'qkata kola', 'ELECTRIC', 'https://cars.usnews.com/static/images/Auto/izmo/i51570132/2018_ford_focus_angularfront.jpg', '30234', '12000.00', 'AUTOMATIC', '93757172-4d46-484e-8c8b-21e7839a57da', '1996', '3');