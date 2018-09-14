start transaction;

use `Acme-Santiago`;

revoke all privileges on `Acme-Santiago`.* from 'acme-user'@'%';
revoke all privileges on `Acme-Santiago`.* from 'acme-manager'@'%';

drop user 'acme-user'@'%';
drop user 'acme-manager'@'%';

drop database `Acme-Santiago`;

commit;