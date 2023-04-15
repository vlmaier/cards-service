![](https://github.com/vlmaier/cards-service/actions/workflows/build.yml/badge.svg)

# Marvel SNAP Cards Service

Manages metadata and images from Marvel SNAP cards

<p float="left">
  <img src="https://user-images.githubusercontent.com/18353152/209874448-071e1786-9409-4fb4-8854-c49290e08ca0.png" width="900" alt="cards overview page"/>
</p>

## How to run

    git clone git@github.com:vlmaier/cards-service.git
    ./gradlew copyJarToDockerDir
    cd docker
    docker-compose up --build -d
