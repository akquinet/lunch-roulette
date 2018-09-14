# Build and run Lunchroulette

## Checkout sources
    git clone https://github.com/akquinet/lunch-roulette.git

## Build
    mvn clean install

## Run

Afterwards the command "docker images" should show an image named "ats/lunchroulette", which you can run
like this:

    docker run --name lunchroulette --rm -p 8888:8080 ats/lunchroulette

### Backup

If you wish to backup the data between container runs you may add a volume mount flag

    docker run -p 8888:8080 ats/lunchroulette -v <somepath>/data:/var/lib/postgresql/data 

## Browse
    http://localhost:8888/

# REST API
Documentation of the available interfaces, tested via curl
  
## Add a location
    curl -i -v -X POST -H "Content-Type: application/json" -d \
        '{"id":0,"name":"McDonalds","style":"Unbekannt","aliases":["Burger King"], \
        "address":{"streetName":"Bülowstr.","streetNumber":"66","plz":"10783","city":"Berlin","telephoneNumber":""}, \
        "averageDuration":"PT30M","rating":2}]' \
        'http://localhost:8888/rest/locations/store'

## Retrieve a JSON list with all existing locations:
    
    curl http://localhost:8888/rest/locations/
