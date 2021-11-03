delete from planets;
delete from overlords;
insert into overlords (id, name, age) values ('4028805f7ce235be017ce2364d7f0000', 'MaksMerfy', 30);
insert into overlords (id, name, age) values ('	4028805f7ce56dd6017ce56e01e80000', 'testLoafer', 20);
insert into planets (id, name, overlord_id) values ('4028805f7ce4ef21017ce4ef50800000', 'earth', null);
insert into planets (id, name, overlord_id) values ('4028805f7ce50a7e017ce50ac5410000', 'venera', '4028805f7ce235be017ce2364d7f0000');
insert into planets (id, name, overlord_id) values ('4028805f7ce56dd6017ce56e01e80000', 'testForDestroy', null);
