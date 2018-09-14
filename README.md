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

## Locations

Locations are places where to go to for lunch...

### Add a location
    curl -i -v -X POST -H "Content-Type: application/json" -d \
        '{"name":"McDonalds","style":"Unbekannt","aliases":["Burger King"],
        "address":{"streetName":"Bülowstr.","streetNumber":"66","plz":"10783","city":"Berlin","telephoneNumber":""},
        "averageDuration":"PT30M","rating":2}]' \
        'http://localhost:8888/rest/locations/store'

### Retrieve list with all existing locations:
    
    curl http://localhost:8888/rest/locations/

### Get single location via its name

    curl http://localhost:8888/rest/locations/McDonalds


## Users

Users register and join communities

### Add a user
    curl -i -v -X POST -H "Content-Type: application/json" -d \
        '{ "name" : "Markus", "email" : "markus.dahm@akquinet.de"}' \
        'http://localhost:8888/rest/users/store'

### Retrieve list with all existing users:
    
    curl http://localhost:8888/rest/users/

### Get single users via name

    curl http://localhost:8888/rest/users/Markus

## Communities

Communities group users together

### Add a comunity (including founder)
    curl -i -v -X POST -H "Content-Type: application/json" -d \
        '{
           "name": "akquinet tech@spree",
           "founder": {
             "name": "Sarah",
             "email": "sarah.ganter@akquinet.de"
           }
         }' \
        'http://localhost:8888/rest/communities/store'

### Add user to community

    curl -i -v -X PUT -H "Content-Type: application/json" -d \
        '{
           "name": "Markus",
           "email": "markus.dahm@akquinet.de"
         }' \
        'http://localhost:8888/rest/communities/akquinet%20tech@spree/add-user'

### Retrieve list with all existing communities:
    
    curl http://localhost:8888/rest/communities/

### Get single community via name

    curl http://localhost:8888/rest/communities/"akquinet%20tech@spree"



