# Read Me First

# Camunda Get Animal Picture Application - Getting Started

## Prerequisites:
- Java 17+
- Maven 3.x
- Docker (optional)

### Reference Documentation
For further reference, please consider the following sections:
BPMN model is already added under resources folder


Create Client
If we want to connect to a Camunda Platform 8 SaaS cluster we need the clusterId from the Clusters details page, a clientId and clientSecret from a client credentials pair.

The credentials can be added to the application.yaml.

```properties
zeebe.client:
    mode: saas
    cloud:
        region: ont-1
        clusterId: 1166c7a2-c61e-451b-909d-b90a78e20aa1
        clientId: W99xTXyl6IzqzR0MXdJiL7LF_7LEokx0
        clientSecret: xJEXu63DSXyFg1KuaAZ-Zk28QTyg1UABnvlB.9Y35XLa32k1_Vp3M~i9Dvin7zWN
        authUrl: https://login.cloud.camunda.io/oauth/token
    broker:
        grpcAddress: grpcs://1166c7a2-c61e-451b-909d-b90a78e20aa1.ont-1.zeebe.camunda.io:443
        restAddress: https://ont-1.zeebe.camunda.io/1166c7a2-c61e-451b-909d-b90a78e20aa1
```

If you are using a self managed Camunda Platform 8 cluster, add your client_id, client_secret in application.yaml file



