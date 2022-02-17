# -*- mode: ruby -*-
# vi: set ft=ruby :

# Vagrantfile API/syntax version. Don't touch unless you know what you're doing!
VAGRANTFILE_API_VERSION = "2"


Vagrant.configure(VAGRANTFILE_API_VERSION) do |config|

  config.vm.box="ubuntu/trusty64" 

  config.vm.define "pppj" do |m|  	     
                 
      m.vm.hostname = "pppj.tul.cz"      

	    # your private network private ip -> acces it like http://192.168.56.200:8080 (for tomcat)
	    m.vm.network "private_network", ip: "192.168.56.200"

      m.vm.provider :virtualbox do |vb|
        vb.name = "pppj"
        vb.customize ["modifyvm", :id, "--memory", "2048"]
        vb.customize ["modifyvm", :id, "--cpus", "2"]        
     end  
   end

       
end
