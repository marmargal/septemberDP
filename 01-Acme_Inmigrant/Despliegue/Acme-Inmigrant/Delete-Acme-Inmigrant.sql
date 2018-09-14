start transaction;

use `Acme-Inmigrant`;

revoke all privileges on `Acme-Newspaper`.* from 'acme-user'@'%';
revoke all privileges on `Acme-Newspaper`.* from 'acme-manager'@'%';

drop user 'acme-user'@'%';
drop user 'acme-manager'@'%';

drop database `Acme-Inmigrant`;

commit;