create table dungeons(
    id bigint not null auto_increment primary key,
    name varchar(255) not null,
    required_level bigint not null,
    hunt_id bigint not null,

    foreign key(hunt_id) references hunts(id)
);

create table dungeon_monster(
    dungeon_id bigint not null,
    monster_id bigint not null,
    primary key (dungeon_id, monster_id),
    foreign key (dungeon_id) references dungeons(id),
    foreign key (monster_id) references monsters(id)
);