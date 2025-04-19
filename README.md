# Student Survey Microservices App - SWE 645 HW3

This project is a Spring Boot-based microservices web application deployed using a CI/CD pipeline. The app supports full CRUD operations on survey data and integrates MySQL for persistence. The entire pipeline is containerized with Docker, deployed via Kubernetes using K3s, and automated with Jenkins.

---

##  Technologies Used

- **Spring Boot** (Java 17)
- **MySQL** (Amazon RDS)
- **Docker**
- **Kubernetes (K3s)**
- **Jenkins**
- **GitHub (Webhook-enabled)**
- **Amazon EC2 (Jenkins + K3s nodes)**
- **Elastic IPs for public access**

---

## CI/CD Pipeline

### Continuous Integration:
- Code is pushed to GitHub
- Jenkins automatically pulls changes, builds the JAR file with Maven, and packages it using Docker

### Continuous Deployment:
- Docker image is pushed to DockerHub: `jsmolak93/student-survey-app`
- Jenkins applies the updated Kubernetes YAML via `kubectl`
- K3s cluster redeploys the app with the latest image

---

## App Endpoints

| Method | Endpoint          | Description                  |
|--------|-------------------|------------------------------|
| GET    | `/surveys`        | Get all surveys              |
| GET    | `/surveys/{id}`   | Get a survey by ID           |
| POST   | `/surveys`        | Create a new survey          |
| PUT    | `/surveys/{id}`   | Update an existing survey    |
| DELETE | `/surveys/{id}`   | Delete a survey              |



---

## Kubernetes Deployment

```yaml
# deployment.yaml (simplified)
apiVersion: apps/v1
kind: Deployment
metadata:
  name: student-survey-deployment
spec:
  replicas: 3
  selector:
    matchLabels:
      app: student-survey
  template:
    metadata:
      labels:
        app: student-survey
    spec:
      containers:
      - name: survey-app
        image: jsmolak93/student-survey-app:latest
        ports:
        - containerPort: 8080
---
apiVersion: v1
kind: Service
metadata:
  name: student-survey-service
spec:
  type: NodePort
  selector:
    app: student-survey
  ports:
    - port: 80
      targetPort: 8080
      nodePort: 30036
