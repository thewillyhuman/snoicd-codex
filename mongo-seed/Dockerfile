FROM mongo

COPY init.json /init.json
CMD mongoimport --host mongodb --db snoicd-codex --collection knowledge-base --type json --file /init.json --jsonArray
