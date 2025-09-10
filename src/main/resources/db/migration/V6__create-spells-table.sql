create table spells(
    id bigint not null auto_increment primary key,
    name varchar(255) not null,
    description text not null,
    video_url varchar(255)
);

create table spell_vocation(
    spell_id bigint not null,
    vocation_id bigint not null,
    primary key(spell_id, vocation_id),
    foreign key (spell_id) references spells(id),
    foreign key (vocation_id) references vocations(id)
);

create table spell_attributes(
    spell_id bigint not null,
    attribute_name varchar(255) not null,
    amount bigint not null,
    primary key(spell_id, attribute_name),
    foreign key(spell_id) references spells(id)
);