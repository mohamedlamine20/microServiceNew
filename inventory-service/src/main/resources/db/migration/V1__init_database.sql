-- create table hibernate_sequence
-- (
--     next_val bigint null
-- );
create table inventory
(
    id       bigint       not null
        primary key,
    quantity int          null,
    sku_code varchar(255) null
);



