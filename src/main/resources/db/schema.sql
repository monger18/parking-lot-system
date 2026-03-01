
---------- DROP TABLES --------------------

DROP TABLE IF EXISTS payment CASCADE;
DROP TABLE IF EXISTS reservation CASCADE;
DROP TABLE IF EXISTS ticket CASCADE;
DROP TABLE IF EXISTS vehicle CASCADE;
DROP TABLE IF EXISTS parking_spot CASCADE;
DROP TABLE IF EXISTS parking_floor CASCADE;
DROP TABLE IF EXISTS audit_log CASCADE;

----PARKING FLOOR-------
CREATE TABLE parking_floor (
    id SERIAL PRIMARY KEY,
    floor_number INT UNIQUE NOT NULL,
    total_spots INT NOT NULL CHECK ( total_spots > 0 ),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

------------PARKING SPOT-------------
CREATE TABLE parking_spot (
    id SERIAL PRIMARY KEY,
    floor_id INT NOT NULL REFERENCES parking_floor(id) ON DELETE CASCADE,
    spot_number VARCHAR(20) NOT NULL,
    spot_type VARCHAR(20) NOT NULL CHECK (spot_type IN ('CAR', 'BIKE', 'TRUCK', 'ELECTRIC')),
    is_occupied BOOLEAN DEFAULT FALSE,
    version INT DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UNIQUE(floor_id, spot_number)
);

-----------VEHICLE-----------------
CREATE TABLE vehicle (
    id SERIAL PRIMARY KEY,
    plate_number VARCHAR(20) UNIQUE NOT NULL,
    vehicle_type VARCHAR(20) NOT NULL CHECK ( vehicle_type IN ('CAR', 'BIKE', 'TRUCK', 'ELECTRIC') ),
    owner_name VARCHAR(100),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
);

--------------TICKET------------------
CREATE TABLE ticket (
    id SERIAL PRIMARY KEY,
    vehicle_id INT NOT NULL REFERENCES vehicle(id),
    spot_id INT NOT NULL REFERENCES parking_spot(id),
    entry_time TIMESTAMP NOT NULL,
    exit_time TIMESTAMP,
    status VARCHAR(20) NOT NULL CHECK ( status IN ('ACTIVE', 'COMPLETED', 'CANCELLED') ),
    total_amount DECIMAL(10,2) CHECK ( total_amount >=0 ),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


---------Only one ticket per vehicle----------------
CREATE UNIQUE INDEX unique_active_ticket
ON ticket(vehicle_id)
WHERE status = 'ACTIVE';


CREATE TABLE reservation (
    id SERIAL PRIMARY KEY,
    vehicle_id INT NOT NULL REFERENCES vehicle(id),
    spot_id INT NOT NULL REFERENCES parking_spot(id),
    reserved_from TIMESTAMP NOT NULL,
    reserved_until TIMESTAMP NOT NULL,
    status VARCHAR(20) NOT NULL CHECK (status IN ('ACTIVE', 'COMPLETED', 'CANCELLED')),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CHECK ( reserved_until > reserved_from ),
);

CREATE INDEX idx_reservation_time
ON reservation(reserved_from, reserved_until);


-----------------PAYMENT---------------------
CREATE TABLE payment (
    id SERIAL PRIMARY KEY,
    ticket_id INT NOT NULL REFERENCES ticket(id) ON DELETE CASCADE,
    payment_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    amount DECIMAL(10,2) NOT NULL CHECK ( amount >= 0 ),
    payment_method VARCHAR(20) CHECK ( payment_method IN ('CASH', 'CARD', 'UPI')),
    status VARCHAR(20) CHECK (status IN ('SUCCESS', FAILED))
);

------------AUDIT LOG----------------
CREATE TABLE audit_log (
    id SERIAL PRIMARY KEY,
    action_type VARCHAR(50),
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_spot_type ON parking_spot(spot_type);
CREATE INDEX idx_ticket_status ON ticket(status);
CREATE INDEX idx_vehicle_plate ON vehicle(plate_number);





