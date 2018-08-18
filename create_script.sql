create table users (
	id bigint identity not null primary key,
	firstname varchar(100),
	middlename varchar(100),
	secondname varchar(100),
	phone varchar(20),
	specialization varchar(100),
	role_id int foreign key references roles(id)
);

create table roles ( 
	id bigint not null primary key,
	name varchar(50) not null,
	description varchar(100)
);

create table recipes (
	id bigint identity not null primary key,
	name varchar(50),
	value varchar(200),
	created_by bigint
);

create table users_recipes (
	user_id bigint not null foreign key references users(id),
	recipe_id bigint not null foreign key references recipes(id),
	expiration_date date
);