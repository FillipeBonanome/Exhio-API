create table users(
    id bigint not null auto_increment primary key,
    login varchar(255) not null unique,
    password varchar(255) not null,
    email varchar(255) not null unique
);