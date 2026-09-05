# Google Jib — Docker Images Without Docker

A **Spring Boot application** demonstrating how to build and push Docker images using **Google Jib** — without installing Docker. Jib builds optimized OCI-compliant container images directly from Maven.

## Features

- **No Docker daemon needed** — Builds container images without Docker installed
- **Optimized layers** — Separates dependencies, classes, and resources into distinct layers
- **Direct push** — Pushes images straight to Docker Hub from Maven
- **Fast rebuilds** — Only changed layers are rebuilt
- **Spring Boot 3.2** — Lightweight REST API skeleton

## How It Works

```
┌──────────────┐     ┌──────────────┐     ┌──────────────┐
│  Maven Build │────▶│  Google Jib  │────▶│  Docker Hub  │
│  (mvn package)│    │  (no Docker) │     │  (Registry)  │
└──────────────┘     └──────────────┘     └──────────────┘
```

## Quick Start

```bash
git clone https://github.com/ayusharyan1309/google-jib-docker.git
cd google-jib-docker

# Build and push image (requires Docker Hub credentials)
mvn compile jib:build

# Or build to local Docker daemon (requires Docker running)
mvn compile jib:dockerBuild
```

## API Endpoint

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/hi` | Returns status message |

**Response:**
```
google jib is running and pushed
```

## Project Structure

```
├── pom.xml                              # Maven + Jib plugin config
├── mvnw / mvnw.cmd                      # Maven wrapper
├── src/main/java/.../
│   └── GoogleJibApplication.java        # Entry point + /hi endpoint
├── src/main/resources/
│   └── application.properties           # App config
└── target/
    └── jib-image.json                   # Build output metadata
```

## Tech Stack

| Component | Technology |
|-----------|------------|
| **Language** | Java 17 |
| **Framework** | Spring Boot 3.2.5 |
| **Build** | Maven |
| **Containerization** | Google Jib 3.4.0 |
| **Registry** | Docker Hub |
