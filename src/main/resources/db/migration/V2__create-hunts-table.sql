create table hunts(
    id bigint not null auto_increment primary key,
    name text not null,
    recommended_level varchar(10) not null,
    description text not null,
    video_url text,
    premium tinyint not null,
    deleted tinyint
);

create table hunt_monster(
    hunt_id bigint not null,
    monster_id bigint not null,
    primary key(hunt_id, monster_id),
    foreign key(hunt_id) references hunts(id),
    foreign key(monster_id) references monsters(id)
);