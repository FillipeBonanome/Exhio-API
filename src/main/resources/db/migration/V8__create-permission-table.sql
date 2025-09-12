create table permissions(
    id bigint not null auto_increment primary key,
    name varchar(255) unique not null
);

insert into permissions (name) values
('CREATE_DUNGEON'),
('DELETE_DUNGEON'),
('UPDATE_DUNGEON'),

('CREATE_HUNT'),
('DELETE_HUNT'),
('UPDATE_HUNT'),

('CREATE_MONSTER'),
('DELETE_MONSTER'),
('UPDATE_MONSTER'),
('RESTORE_MONSTER'),

('CREATE_QUEST'),
('DELETE_QUEST'),
('UPDATE_QUEST'),

('CREATE_SPELL'),
('DELETE_SPELL'),
('UPDATE_SPELL'),

('CREATE_VOCATION'),
('UPDATE_VOCATION');

create table user_permission(
    user_id bigint not null,
    permission_id bigint not null,
    primary key(user_id, permission_id),
    foreign key(user_id) references users(id),
    foreign key(permission_id) references permissions(id)
);
