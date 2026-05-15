-- Insert House Owner
INSERT INTO house_owner (id, name, email, phone_number, password) VALUES (1, 'Fateha Hossain', 'fateha@example.com', '01711111111', 'password123');
INSERT INTO house_owner (id, name, email, phone_number, password) VALUES (2, 'Anushka', 'anushka@example.com', '01722222222', 'password123');

-- Insert Flats
INSERT INTO flat (id, flat_no, rent, rent_type, no_of_room, available_date, available, contact_number, description, tenant_gender, address, ownername, house_owner_id) 
VALUES (1, 'A-101', 12000, 'Family', 3, '2026-06-01', true, '01711111111', 'Spacious flat with good ventilation, ideal for family or group of students.', 'All', 'Dhanmondi, Dhaka', 'Fateha Hossain', 1);

INSERT INTO flat (id, flat_no, rent, rent_type, no_of_room, available_date, available, contact_number, description, tenant_gender, address, ownername, house_owner_id) 
VALUES (2, 'B-202', 5000, 'Bachelor', 1, '2026-05-20', true, '01722222222', 'Cozy single room in a shared apartment. Female students preferred.', 'Female', 'Panthapath, Dhaka', 'Anushka', 2);

INSERT INTO flat (id, flat_no, rent, rent_type, no_of_room, available_date, available, contact_number, description, tenant_gender, address, ownername, house_owner_id) 
VALUES (3, 'C-303', 8000, 'Short', 2, '2026-05-15', true, '01711111111', 'Short term flat available for summer internships. Fully furnished.', 'All', 'Mirpur, Dhaka', 'Fateha Hossain', 1);

INSERT INTO flat (id, flat_no, rent, rent_type, no_of_room, available_date, available, contact_number, description, tenant_gender, address, ownername, house_owner_id) 
VALUES (4, 'D-404', 15000, 'Studio', 1, '2026-06-10', true, '01722222222', 'Modern studio apartment with attached bath and kitchen. Perfect for single living.', 'All', 'Gulshan, Dhaka', 'Anushka', 2);

INSERT INTO flat (id, flat_no, rent, rent_type, no_of_room, available_date, available, contact_number, description, tenant_gender, address, ownername, house_owner_id) 
VALUES (5, 'E-505', 6000, 'Shared', 2, '2026-05-25', true, '01711111111', 'Shared room for male students. Walking distance from university.', 'Male', 'Farmgate, Dhaka', 'Fateha Hossain', 1);
