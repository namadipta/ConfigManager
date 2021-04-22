# ConfigManager
UI https://github.com/BootstrapDash/StarAdmin-Free-Bootstrap-Admin-Template/


#Docker
docker container run --name mysqldb --network config-manager-network -e MYSQL_ROOT_PASSWORD=Namadi137@ -e MYSQL_DATABASE=cloudconfig -d mysql:8

docker image build -t config-manager-app .

docker container run --network config-manager-network --name config-manager-app -p 6880:8080 -d config-manager-app

