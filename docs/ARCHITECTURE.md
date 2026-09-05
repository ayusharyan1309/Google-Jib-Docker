# Architecture

## How Google Jib Works

Google Jib builds Docker images as Maven plugin — no Dockerfile, no Docker daemon required.

```
┌──────────────────────────────────────────────────────────┐
│                    JIB BUILD PROCESS                      │
│                                                           │
│  ┌──────────┐  ┌──────────┐  ┌──────────┐  ┌──────────┐│
│  │ Classes  │  │ Resources│  │ Deps JAR │  │ Deps Lib ││
│  │  Layer   │  │  Layer   │  │  Layer   │  │  Layer   ││
│  └────┬─────┘  └────┬─────┘  └────┬─────┘  └────┬─────┘│
│       │              │             │              │       │
│       └──────────────┴─────────────┴──────────────┘       │
│                          │                                │
│                    ┌─────▼─────┐                          │
│                    │  Jib      │                          │
│                    │  Builder  │                          │
│                    └─────┬─────┘                          │
│                          │                                │
│              ┌───────────┼───────────┐                    │
│              ▼           ▼           ▼                    │
│        ┌──────────┐ ┌──────────┐ ┌──────────┐           │
│        │ Docker   │ │ Registry │ │ Tarball  │           │
│        │ Daemon   │ │ (Docker  │ │ (local)  │           │
│        │ (local)  │ │  Hub)    │ │          │           │
│        └──────────┘ └──────────┘ └──────────┘           │
└──────────────────────────────────────────────────────────┘
```

## Jib vs Traditional Docker

```
Traditional:                    Jib:
┌──────────────┐               ┌──────────────┐
│ Dockerfile   │               │ Maven Plugin │
│     │        │               │     │        │
│     ▼        │               │     ▼        │
│ docker build │               │ Build layers │
│     │        │               │     │        │
│     ▼        │               │     ▼        │
│ Docker daemon│               │ Push to      │
│ required     │               │ registry     │
└──────────────┘               └──────────────┘
```

## Layer Optimization

```
┌────────────────────────────────────┐
│  Layer 4: Application classes      │  ← Small, changes often
├────────────────────────────────────┤
│  Layer 3: Resources                │  ← Small, changes sometimes
├────────────────────────────────────┤
│  Layer 2: Dependencies (snapshot)  │  ← Medium, changes on dep update
├────────────────────────────────────┤
│  Layer 1: Dependencies (stable)    │  ← Large, rarely changes
└────────────────────────────────────┘

Result: Fast rebuilds (only changed layers), smaller pushes
```

## Maven Configuration

```xml
<plugin>
    <groupId>com.google.cloud.tools</groupId>
    <artifactId>jib-maven-plugin</artifactId>
    <version>3.4.0</version>
    <configuration>
        <to>
            <image>registry.hub.docker.com/ayusharyan13/google-jib-image</image>
        </to>
    </configuration>
</plugin>
```

## Commands

| Command | Description |
|---------|-------------|
| `mvn compile jib:build` | Build and push to Docker Hub |
| `mvn compile jib:dockerBuild` | Build to local Docker daemon |
| `mvn compile jib:buildTar` | Build as tarball archive |
