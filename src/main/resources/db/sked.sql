USE sked
INSERT INTO establishments(name, sector, opening_hour, closing_hour, version) VALUES
    ('CodeForAll', 'Technology', '08:00:00', '22:00:00', 0),
    ('Amelia', 'Restaurant', '10:00:00', '22:00:00', 0);

INSERT INTO shifts(shift, entry_hour, leave_hour, establishment_id, version) VALUES
    ('Morning', '08:00:00', '17:00:00', 1, 0),
    ('Afternoon', '10:00:00', '19:00:00', 1, 0),
    ('Evening', '13:00:00', '22:00:00', 1, 0),
    ('Morning', '08:00:00', '17:00:00', 2, 0),
    ('Afternoon', '13:00:00', '20:00:00', 2, 0),
    ('Evening', '15:00:00', '22:00:00', 2, 0);


INSERT INTO employees(name, department, supervisor_id, establishment_id, shift_id, version) VALUES
    ('Sara', 'Chef', 1, 2, 4, 0),
    ('Diogo', 'Sous Chef', 1, 2, 5, 0),
    ('Bernard', 'Grill Chef', 1, 2, 4, 0),
    ('Vinicius', 'Pastry Chef', 1, 2, 6, 0),
    ('Leith', 'CEO', 5, 1, 1, 0),
    ('Claudia', 'PM', 5, 1, 2, 0),
    ('Sergio', 'Dumbledore', 5, 1, 1, 0);