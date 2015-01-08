insert into banner (title, text, link) values ('Iphone', 'Big big big', 'http://google.com');
insert into banner (title, text, link) values ('Ipad', 'Big big big 2', 'http://google.com');
insert into banner (title, text, link) values ('Samsung', 'Big big big 3', 'http://google.com');
insert into banner (title, text, link) values ('Blackberry', 'Big big big 4', 'http://google.com');

insert into tab (title, text, level) values ('Existing Customer', '', 1);
insert into tab (title, text, level) values ('New Customer', '', 1);

insert into tab (title, text, parent_id, level) values ('Store','View our latest phones or recontract', 1, 2);
insert into tab (title, text, parent_id, level) values ('Promotions','See our latest deals', 1, 2);
insert into tab (title, text, parent_id, level) values ('Support','Get your solution here', 1, 2);
insert into tab (title, text, parent_id, level) values ('Hub iD Sign-in','Manage your account', 1, 2);

insert into tab (title, text, parent_id, level) values ('Why StarHub','Learn about our services', 2, 2);
insert into tab (title, text, parent_id, level) values ('Mobile Plans','View our Postpaid Plans', 2, 2);
insert into tab (title, text, parent_id, level) values ('Promotions','See our latest deals', 2, 2);
insert into tab (title, text, parent_id, level) values ('Home Hubbing','Bundle your services & save', 2, 2);
