CREATE SCHEMA IF NOT EXISTS my_schema;

create table if not exists customers(
                          id           serial primary key,
                          name         varchar not null,
                          surname      varchar not null,
                          age          int     not null,
                          phone_number varchar not null
);

insert into customers(name, surname, age, phone_number)
values ('alexey', 'suranov', 27, '985-191'),
       ('andrey', 'petrov', 31, '985-192'),
       ('alexey', 'timashov', 22, '985-190'),
       ('alexey', 'voroncov', 40, '922-190'),
       ('anna', 'andreeva', 38, '915-190');

create table if not exists orders(
                       id           serial primary key,
                       date         timestamp not null,
                       customer_id  int       not null,
                       product_name varchar   not null,
                       amount       money,
                       foreign key (customer_id) references customers (id)
);

insert into orders(date, customer_id, product_name, amount)
values ('11.18.20', 1, 'someProduct', 300),
       ('12.22.20', 2, 'someProduct2', 555),
       ('08.01.20', 3, 'someProduct3', 2450),
       ('06.08.20', 1, 'someProduct4', 5151),
       ('10.17.20', 1, 'someProduct5', 4429);
