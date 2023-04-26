create table if not exists currencies
(
    id serial primary key,
    code varchar (5) not null unique,
    value numeric(16,6) not null,
    last_updated timestamptz not null
);