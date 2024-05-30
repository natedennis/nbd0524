insert into brand (name) values ('Stihl'),('Werner'),('DeWalt'),('Ridgid');
insert into tool_type (name, daily_charge, daily_charge_currency, weekend_charge, holiday_charge) values
             ('Ladder',1.99,'USD',true,false),
             ('Chainsaw',1.49,'USD',false,true),
             ('Jackhammer',2.99,'USD',false,false);
insert into tool (tool_code,tool_type_id,brand_id) values
                 ('CHNS',(select id from tool_type where name = 'Chainsaw'),(select id from brand where name = 'Stihl')),
                 ('LADW',(select id from tool_type where name = 'Ladder'),(select id from brand where name = 'Werner')),
                 ('JAKD',(select id from tool_type where name = 'Jackhammer'),(select id from brand where name = 'DeWalt')),
                 ('JAKR',(select id from tool_type where name = 'Jackhammer'),(select id from brand where name = 'Ridgid'));
