# Checkout sources
    git clone https://github.com/akquinet/lunch-roulette.git

# Build
    mvn clean install

Afterwards the command "docker images" should show an image named "ats/lunchroulette", which you can run
like this:

    docker run --name lunchroulette --rm -p 8888:8080 ats/lunchroulette

# Browse
    http://localhost:8888/
    
# Add a location
    curl -i -v -X POST -H "Content-Type: application/json" -d \
        '{"id":0,"name":"McDonalds","style":"Unbekannt","aliases":["Burger King"], \
        "address":{"streetName":"Bülowstr.","streetNumber":"66","plz":"10783","city":"Berlin","telephoneNumber":""}, \
        "averageDuration":"PT30M","rating":2}]' \
        'http://localhost:8888/rest/locations/store'

Then you should be able to retrieve a JSON list with all existing locations:
    
    curl http://localhost:8888/rest/locations/

If you wish to backup the data between container runs you may add a volume mount flag

    docker run -p 8888:8080 ats/lunchroulette -v <somepath>/data:/var/lib/postgresql/data 
