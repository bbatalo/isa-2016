insert into User (usr_email, usr_password, usr_type) values ('bobi@bobinjo.com', 'bobijevasifra', '0')
insert into User (usr_email, usr_password, usr_type) values ('prix@bobinjo.com', 'prixevasifra', '0')
insert into User (usr_email, usr_password, usr_type) values ('gagi@bobinjo.com', 'gagijevasifra', '0')
insert into User (usr_email, usr_password, usr_type) values ('admin@admin.com', 'admin', '1')
insert into User (usr_email, usr_password, usr_type) values ('perisa@menadzer.com', 'perisinasifra', '2')
insert into User (usr_email, usr_password, usr_type) values ('caki@menadzer.com', 'cakijevasifra', '2')
insert into User (usr_email, usr_password, usr_type) values ('milica@konobar.com', 'milicinasifra', '3')
insert into User (usr_email, usr_password, usr_type) values ('ognjen@kuvar.com', 'ognjenovasifra', '3')
insert into User (usr_email, usr_password, usr_type) values ('suncokreten@sanker', 'kretenovasifra', '3')

insert into customer (cst_address, cst_date_birth, cst_name, cst_surname, usr_id) values ('Somborska 8', '1994-11-10', 'Bojan', 'Batalo', '1')
insert into customer (cst_address, cst_date_birth, cst_name, cst_surname, usr_id) values ('Neka tamo ulica 3', '1994-9-18', 'Dušan', 'Radisavljević', '2')
insert into customer (cst_address, cst_date_birth, cst_name, cst_surname, usr_id) values ('Cara Dušana 69 hehe', '1994-5-5', 'Dragutin', 'Kljajić', '3')

insert into system_manager (sys_name, sys_surname, usr_id) values ('Milan', 'Kerac', '4')

insert into restoraunt_manager (rsm_name, rsm_surname, usr_id) values ('Perisa', 'Slepac', '5')
insert into restoraunt_manager (rsm_name, rsm_surname, usr_id) values ('Caki', 'Majmuncina', '6')

insert into employee (emp_name, emp_surname, emp_role, emp_pass_changed, emp_date_birth, emp_size_cloth, emp_size_shoes, usr_id) values ('Milica', 'Stanković', '0', false, '1995-5-5', '40', '38', '7')
insert into employee (emp_name, emp_surname, emp_role, emp_pass_changed, emp_date_birth, emp_size_cloth, emp_size_shoes, usr_id) values ('Ognjen', 'Velisavčić', '1', false, '1995-5-5', '46', '45', '8')
insert into employee (emp_name, emp_surname, emp_role, emp_pass_changed, emp_date_birth, emp_size_cloth, emp_size_shoes, usr_id) values ('Marija', 'Kovačević', '2', false, '1995-5-5', '38', '36', '9')
	
insert into bartender (bar_cocktails, usr_id) values (true, '7')
insert into waiter (wtr_trial, usr_id) values (true, '8')
insert into chef (chf_type, usr_id) values ('Kineski', '9')