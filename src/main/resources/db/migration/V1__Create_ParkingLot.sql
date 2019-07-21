--create table CriminalCase (
--     id varchar(255) primary key,
--     name varchar(255) not null,
--     time long not null
-- )

create sequence hibernate_sequence start with 1 increment by 1;
create table parking_lot (id bigint not null, capacity integer not null, localtion varchar(255), name varchar(255), primary key (id));
create table parking_order (order_id integer not null, car_num varchar(255), create_date varchar(255), is_status boolean default true, leave_date varchar(255), parkinglotid bigint, primary key (order_id));
alter table parking_lot add constraint UK_2w49woqis4x25gei7vnre7x1i unique (name);
alter table parking_order add constraint FKc33nuy0vqxt8x15lknta3724k foreign key (parkinglotid) references parking_lot;