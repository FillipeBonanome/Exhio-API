create table vocations(
    id bigint not null auto_increment primary key,
    name varchar(255) not null,
    description text not null,
    simplicity decimal(8, 2) not null,
    area_damage decimal(8, 2) not null,
    single_damage decimal(8, 2) not null,
    mobility decimal(8, 2) not null,
    survivability decimal(8, 2) not null,
    resistance decimal(8, 2) not null,
    control decimal(8, 2) not null,
    spells decimal(8, 2) not null,
    support decimal(8, 2) not null
);

create table vocation_hunt(
    vocation_id bigint not null,
    hunt_id bigint not null,
    primary key(vocation_id, hunt_id),
    foreign key(vocation_id) references vocations(id),
    foreign key(hunt_id) references hunts(id)
);