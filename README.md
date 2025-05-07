Testing my webhook trigger!






















# Student Survey Microservices App - SWE 645 HW3

This project is a Spring Boot-based microservices web application deployed using a full CI/CD pipeline. The app supports full CRUD operations on student survey data and uses MySQL hosted on Amazon RDS for persistent storage. The application is containerized using Docker, deployed on a Rancher-managed RKE2 Kubernetes cluster, and automated with Jenkins.

---

##  Technologies Used

- **Spring Boot** (Java 17)
- **MySQL (Amazon RDS)**
- **Docker**
- **Rancher (managing RKE2 Kubernetes cluster)**
- **Jenkins (CI/CD Automation)**
- **DockerHub** (`jsmolak93/student-survey-app`)
- **GitHub** (source control + webhook triggers)
- **Amazon EC2** (separate instances for Jenkins and Rancher)
- **Elastic IPs for public access**

---

## Public Access Points

| Service     | Elastic IP       | Port(s)       | Description                     |
|-------------|------------------|---------------|---------------------------------|
| Rancher     | `54.159.65.185`  | 80 / 443      | Rancher UI (RKE2 cluster)       |
| Jenkins     | `54.165.19.244`  | 8080          | Jenkins UI for CI/CD            |
| Web App     | via NodePort     | 30036         | Spring Boot survey app access   |

---

## CI/CD Pipeline (Jenkins)

- A webhook triggers Jenkins when code is pushed to GitHub.
- Jenkins clones the repository using stored GitHub credentials.
- Maven builds the JAR file.
- Docker builds and pushes the image to DockerHub.
- Jenkins uses `kubectl` with `kubeconfig` credentials to deploy the app to the Rancher-managed cluster.

All stages are automated using a declarative Jenkins pipeline. This setup ensures that **any push to GitHub** will automatically trigger a **clean rebuild, push, and redeploy** of the application with zero manual intervention.

---

## Amazon RDS (MySQL)

- Hosted in Amazon RDS (MySQL 8.0)
- Used to persist user-submitted survey data across pods and deployments.
- Connected via Spring Boot using JDBC.
- Database configuration is handled in `application.properties`.

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

## Summary

This assignment demonstrates full-stack DevOps and microservice deployment using modern cloud-native tools. The app was tested with real-time GitHub commits triggering Jenkins pipelines, which in turn rebuilt Docker images, pushed to DockerHub, and deployed on Rancher-managed Kubernetes infrastructure. **Elastic IPs were used to allow public web access**, and Amazon RDS ensured persistent database storage.
