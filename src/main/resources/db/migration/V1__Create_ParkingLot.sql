--create table CriminalCase (
--     id varchar(255) primary key,
--     name varchar(255) not null,
--     time long not null
-- )

create sequence hibernate_sequence start with 1 increment by 1;
create table parking_lot (id bigint not null, capacity integer not null, localtion varchar(255), name varchar(255), primary key (id));
alter table parking_lot add constraint UK_2w49woqis4x25gei7vnre7x1i unique (name);