BASE_FOLDER = "/vagrant/katalon-solution-lab-offline"
Vagrant.configure("2") do |config|
  config.vm.box = "hashicorp-vagrant/ubuntu-16.04"
  #config.vm.box_check_update = false
  config.vm.network "forwarded_port", guest: 80, host: 80
  config.vm.network "forwarded_port", guest: 8080, host: 8080
  config.vm.network "forwarded_port", guest: 8000, host: 8000
  config.vm.network :forwarded_port, guest: 22, host: 2222, id: "ssh"
  config.vm.provider "virtualbox" do |vb|
     vb.customize ["modifyvm", :id, "--memory", 4000]
     vb.customize ["modifyvm", :id, "--name", "Katalon-Lab"]
  end
  config.vm.provision "shell", inline: <<-SHELL
    apt-get update
    apt-get install -y make
    cd /vagrant/katalon-solution-lab-offline
	make install
    	gpasswd -a $USER docker
	make pull_image
	make stop_jenkins
    make start_jenkins
	make start_slave
	curl localhost:8080 -u admin:admin | grep Dashboard 2>&1 > /dev/null
	while [ $? -eq 1 ]; do echo "wating..."; sleep 5; curl localhost:8080 -u admin:admin | grep Dashboard 2>&1 > /dev/null; done
  SHELL
end