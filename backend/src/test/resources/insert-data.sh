#!/usr/bin/env bash
curl -i -v -X POST -H "Content-Type: application/json" -d \
    '{"id":1,"name":"McDonalds","style":"Fast food","aliases":["Burger King"], "address":{ "plz":"hallo", "streetName":"hallo", "streetNumber":"12", "city":"Berlin" } }' \
    'http://localhost:8888/lunchroulette/rest/locations/store'
