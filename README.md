### Vagrant setup manual

* Download and install VirtualBOX - https://www.virtualbox.org/wiki/Downloads
* Download latest Vagrant release - https://www.vagrantup.com/downloads.html
* Download SSH client  (for Windows)
    - Putty http://www.chiark.greenend.org.uk/~sgtatham/putty/download.html
 
* In project directory type **vagrant init**
* Ensure created Vagrantfile has following content

        VAGRANTFILE_API_VERSION = "2"

        Vagrant.configure(VAGRANTFILE_API_VERSION) do |config|

            config.vm.box="ubuntu/trusty64" 
            config.vm.define "pppj" do |m|        
                         
                m.vm.hostname = "pppj.tul.cz"                

                # VM private network ip -> acces it like http://192.168.56.200:8080 (for tomcat)
                m.vm.network "private_network", ip: "192.168.56.200"

                m.vm.provider :virtualbox do |vb|
                    vb.name = "pppj"
                    vb.customize ["modifyvm", :id, "--memory", "2048"]
                    vb.customize ["modifyvm", :id, "--cpus", "2"]        
                end  
           end               
        end

* Type **vagrant up** in project directory
* Wait for your VM to boot (should be visible in virtual box gui)

### MySQL install

1. Log in to PPPJ VM
1. Execute inside VM

        sudo apt-get install mysql-server-5.6

1. Try login
        
        sudo mysql -u root -p

1. Inside MySQL cli

         CREATE USER 'vagrant'@'%' IDENTIFIED BY 'vagrant';   
         GRANT ALL PRIVILEGES ON *.* TO 'vagrant'@'%' with grant option;

1. Now you have mysql user **vagrant** with **vagrant** password
1. Edit /etc/mysql/my.cnf

        sudo nano /etc/mysql/my.cnf

1. Change **bind_address**

        bind-address            = 0.0.0.0

1. Restart MySQL

        sudo restart mysql

1. **Leave VM**
1. Install MySQL Workbench http://dev.mysql.com/downloads/workbench/
1. Connect to MySQL server

        ip: 192.168.56.200
        port: 3306
        user: vagrant
        password: vagrant    

### MongoDB install

1. Log in to PPPJ VM
1. Execute inside VM

        sudo apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv 7F0CEB10

        echo "deb http://downloads-distro.mongodb.org/repo/ubuntu-upstart dist 10gen" | sudo tee /etc/apt/sources.list.d/mongodb.list

        sudo apt-get update
        sudo apt-get install -y mongodb-org              

1. Ensure mongo deamon (mongod) is running

         status mongod

1. Try connect using mongo console
    
        mongo

1. Exit mongo console (CTR+C or type quit())
1. Edit /etc/mongod.conf

        sudo nano /etc/mongod.conf

1. Comment out line with **bind_ip**

        #bind_ip = 127.0.0.1

1. Restart mongod

        sudo restart mongod

1. **Leave VM**
1. Install Robomongo http://robomongo.org/
1. Connect to mongo using Robomongo 

        ip: 192.168.56.200
        port: 27017

### Java 8 install

1. Log in to PPPJ VM
1. Execute inside VM

        sudo add-apt-repository ppa:webupd8team/java
        sudo apt-get update
        sudo apt-get install oracle-java8-installer
        sudo apt-get install oracle-java8-set-default

1. Reload ssh connection (exit and login into VM again)
1. Execute inside VM
    
        echo $JAVA_HOME

1. Should print _/usr/lib/jvm/java-8-oracle_        

### Tomcat 7 install

1. Log in to PPPJ VM
1. Execute inside VM

        sudo apt-get install tomcat7  tomcat7-docs tomcat7-examples tomcat7-admin
        
1. Will print error on start cause JAVA_HOME is not set
1. Edit /etc/default/tomcat7
    
       sudo nano /etc/default/tomcat7

1. Uncomment and edit following line

        JAVA_HOME=/usr/lib/jvm/java-8-oracle

1. Edit /etc/tomcat7/tomcat-users.xml
        
        sudo nano /etc/tomcat7/tomcat-users.xml

1. Insert following XML element inside _tomcat-users_ tag

        <user username="vagrant" password="vagrant" roles="manager-gui"/>

1. Restart Tomcat7
        
        sudo service tomcat7 restart
 
1. Leave VM
1. Visit http://192.168.56.200:8080/
2. Login http://192.168.56.200:8080/manager 

        user: vagrant
        passwork: vagrant

### Jenkins install

1. Log in to PPPJ VM
1. Execute inside VM

        wget -q -O - https://jenkins-ci.org/debian/jenkins-ci.org.key | sudo apt-key add -
        sudo sh -c 'echo deb http://pkg.jenkins-ci.org/debian binary/ > /etc/apt/sources.list.d/jenkins.list'
        sudo apt-get update
        sudo apt-get install jenkins

1. Edit /etc/default/jenkins
    
       sudo nano /etc/default/jenkins

1. Uncomment and edit following line

        HTTP_PORT=9100

1. Restart jenkins

        sudo service jenkins restart

1. Leave VM
1. Visit http://192.168.56.200:9100/

### RabbitMQ install

1. Log in to PPPJ VM
1. Execute inside VM

        wget -q -O - http://www.rabbitmq.com/rabbitmq-signing-key-public.asc | sudo apt-key add -
        sudo sh -c 'echo deb http://www.rabbitmq.com/debian/ testing main > /etc/apt/sources.list.d/rabbitmq.list'
        sudo apt-get update
        sudo apt-get install rabbitmq-server
        sudo rabbitmq-plugins enable rabbitmq_management

        sudo rabbitmqctl add_user vagrant vagrant
        sudo rabbitmqctl set_user_tags vagrant administrator
        sudo rabbitmqctl set_permissions -p / vagrant ".*" ".*" ".*"
        sudo rabbitmqctl delete_user guest


1. Login http://192.168.56.200:15672

        user: vagrant
        password: vagrant
