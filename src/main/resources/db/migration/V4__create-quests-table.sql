create table quests(
    id bigint not null auto_increment primary key,
    name varchar(255) not null,
    required_level bigint not null,
    hunt_id bigint not null,
    money_reward bigint,
    item_reward varchar(255),
    training_reward bigint,

    foreign key(hunt_id) references hunts(id)
);

create table quest_attribute_reward(
    quest_id bigint not null,
    attribute_name varchar(255) not null,
    reward_points bigint,

    foreign key(quest_id) references quests(id),
    primary key (quest_id, attribute_name)

);