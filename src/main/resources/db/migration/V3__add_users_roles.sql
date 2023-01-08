create table if not exists users_roles
(
    user_id bigint not null
        constraint fk_user_id
            references users,
    role_id    bigint not null
        constraint fk_role_id
            references roles,
    constraint users_roles_pkey
        primary key (user_id, role_id)
);

alter table users_roles
    owner to postgres;

insert into users_roles(user_id, role_id)
values (0, 1),
       (0, 2)