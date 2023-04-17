create table if not exists transactions
(
    id            serial primary key,
    count         numeric(10, 4) not null default 0,
    currency_name varchar(32)    not null,
    customer_id   integer        not null
    );