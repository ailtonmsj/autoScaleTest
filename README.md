## autoScaleTest
A simple API to test the horizontal auto scale when CPU is in high usage

### stress cpu
#### the seconds parameter indicates how long the cpu will be under stress.
curl localhost:8080/stresscpu?seconds=10

### health check
curl localhost:8080/

### change health check response
curl localhost:8080/{healthcheckstatus}
#### examples:
curl localhost:8080/false\
curl localhost:8080/true


