create table if not exists `customer`(
    `customer_id` int auto_increment primary key,
    `name` varchar(100) not null,
    `email` varchar(100) not null,
    `mobile` varchar(20) not null,
    `created_at` date  not null,
    `updated_at` date default null,
    `updated_by` varchar(20) default null,
    `created_by` varchar(20) not null
);

create table if not exists `accounts`(
    `customer_id` int not null,
    `account_number` int auto_increment primary key,
    `account_type` varchar(100) not null,
    `branch_address` varchar(200) not null,
    `created_at` date not null,
    `updated_at` date default null,
    `created_by` varchar(20) not null,
    `updated_by` varchar(20) default null
);

insert into `customer` (`name`, `email`, `mobile`, `created_at`, `created_by`) values
('John Doe', 'john.doe@example.com', '1234567890', '2023-01-01', 'admin'),
('Jane Smith', 'jane.smith@example.com', '0987654321', '2023-01-02', 'admin');

insert into `accounts` (`customer_id`, `account_type`, `branch_address`, `created_at`, `created_by`) values
(1, 'Savings', '123 Main St, Anytown, USA', '2023-01-01', 'admin'),
(2, 'Checking', '456 Elm St, Othertown, USA', '2023-01-02', 'admin');