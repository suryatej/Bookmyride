DROP TABLE IF EXISTS Taxi;

create table if not exists Taxi (
  taxiId identity,
  driver_Id varchar(20) not null,
  type varchar(20) not null,
  status varchar(20) not null,
  curr_lat DOUBLE not null,
  curr_lon DOUBLE not null
);

create table if not exists Taxi_booking (
   taxiBookingId identity,
   start_lat DOUBLE not null,
   start_lon DOUBLE not null,
   start_time timestamp not null,
   end_lat DOUBLE not null,
   end_lon DOUBLE not null,
   end_time timestamp,
   bookedTime timestamp not null,
   acceptedTime timestamp not null,
   customerId varchar(20) not null,
   bookingStatus varchar(10) not null,
   cancelTime timestamp,
   taxiId varchar(10) not null
);

alter table Taxi_booking
    add foreign key (taxiId) references Taxi(taxiId);