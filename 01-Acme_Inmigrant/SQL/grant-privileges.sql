grant select, insert, update, delete 
	on `Acme-Inmigrant`.* to 'acme-user'@'%';

grant select, insert, update, delete, create, drop, references, index, alter, 
        create temporary tables, lock tables, create view, create routine, 
        alter routine, execute, trigger, show view
    on `Acme-Inmigrant`.* to 'acme-manager'@'%';