# API Documentation

The snoicd-codex API provide two endpoints for REST requests.

## `/api/search?q=copper+fever`
**Definition:** This endpoint returns those terms that contains the search query in their descriptions or code.  
**Return object:**
```
[{
  "code": String,
  "terminology_name": (snomed | icd_9 | icd_10),
  "descriptions": [
    String,
    ... ],
  "related_codes": [{
    "code": String,
    "terminology_name": (snomed | icd_9 | icd_10),
    "descriptions": [
      String,
      ...
    },... ]
  }, ...
]
```

## `/api/search?q=copper+fever&searchStrategy=(description|code)`
**Definition:** This endpoint returns those terms that contains the search query in their given searchStrategy parameter.  
**Return object:**
```
[{
  "code": String,
  "terminology_name": (snomed | icd_9 | icd_10),
  "descriptions": [
    String,
    ... ],
  "related_codes": [{
    "code": String,
    "terminology_name": (snomed | icd_9 | icd_10),
    "descriptions": [
      String,
      ...
    },... ]
  }, ...
]
```
