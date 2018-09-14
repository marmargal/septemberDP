start transaction;

use `Acme-Antenna`;

revoke all privileges on `Acme-Antenna`.* from 'acme-user'@'%';
revoke all privileges on `Acme-Antenna`.* from 'acme-manager'@'%';

drop user 'acme-user'@'%';
drop user 'acme-manager'@'%';

drop database `Acme-Antenna`;

commit;