create table monsters(
    id bigint auto_increment not null primary key,
    level bigint not null,
    name varchar(255) not null,
    experience bigint not null,
    physical bigint not null,
    fire bigint not null,
    energy bigint not null,
    ice bigint not null,
    poison bigint not null,
    death bigint not null,
    holy bigint not null,
    deleted boolean
);