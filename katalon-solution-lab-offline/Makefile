current_dir = /vagrant/katalon-solution-lab-offline
KATALON_API_KEY = f9faa583-d0ea-48a9-b7dd-3b15fbb7e690
install:
	@echo "***installing...***"
	apt-get update
	apt-get install -y docker docker-compose
	apt-get install -y default-jdk

pull_image:
	@echo "***pulling images...***"
	docker pull jenkins/jenkins:2.204.5
	docker pull katalonstudio/katalon:7.2.1
	docker-compose -f $(current_dir)/saleor/docker-compose.yml build

start_jenkins:
	docker run --name jenkins -d -v $(current_dir)/var/jenkins_home/:/var/jenkins_home -p 8080:8080 -p 5000:5000 --env JENKINS_SLAVE_AGENT_PORT=5000 jenkins/jenkins:2.204.5

start_slave:
	nohup java -jar agent.jar -jnlpUrl http://127.0.0.1:8080/computer/vox/slave-agent.jnlp -secret 37c4cb79fee316774872db9a13c2ce841aeacfb8706385321cb990432ed7de60 -workDir "$(current_dir)/workspace" &

deploy_app:
	docker-compose -f /vagrant/katalon-solution-lab-offline/saleor/docker-compose.yml up -d

trigger_build:
	usermod -a -G docker vagrant
	chmod 777 /var/run/docker.sock
	curl -X POST http://admin:11829dc3dd4f78ae6f9bdd126000391227@localhost:8080/job/Katalon-Solution/build?token=99C2386F1FF21FDF --data-urlencode json='{"parameter": [{"name": "IS_DEPLOY_TO_PRODUCTION", "value": true}, {"name": "IS_COLLECTION", "value": false}, {"name": "PROFILE", "value": "local"}, {"name": "RETRY", "value": "0"}, {"name": "BROWSER", "value": "Chrome (headless)"}, {"name": "SUITES", "value": "Regression"}, {"name": "KATALON_API_KEY", "value": "$(KATALON_API_KEY)"}, {"name": "COLLECTION", "value": "RegressionSuite"}]}'

stop_jenkins:
	docker stop jenkins
	docker rm jenkins
	docker system prune -f
