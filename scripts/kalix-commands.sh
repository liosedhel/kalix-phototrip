kalix projects list

kalix svc list

kalix service deploy \
    my-service \
    my-container-uri/container-name:tag-name

kalix svc list

kalix logs --raw phototrip

kalix svc components list phototrip

kalix svc c list-entity-ids phototrip com.virtuslab.phototrip.place.api.PlaceService

kalix svc c get-state phototrip com.virtuslab.phototrip.place.api.PlaceService Place1 --output json

kalix svc c list-events phototrip com.virtuslab.phototrip.place.api.PlaceService Place1 --output json

kalix svc views list phototrip