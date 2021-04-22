# ConfigManager
UI https://github.com/BootstrapDash/StarAdmin-Free-Bootstrap-Admin-Template/


#Docker
docker container run --name mysqldb --network configmanager -e MYSQL_ROOT_PASSWORD=Namadi137@ -e MYSQL_DATABASE=cloudconfig -d mysql:8

docker image build -t config-manager .

docker container run --network configmanager --name config-manager-container -p 6880:8080 -d config-manager

