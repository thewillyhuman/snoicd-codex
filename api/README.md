# API Documentation

The snoicd-codex API provide two endpoints for REST requests.

## `/api/codes/{codeId}`
**Definition:** This endpoint returns the term that matches the codeId.  
**Return object:**
```
{
  "snomedCode": string,
  "icd9Code": string,
  "icd10Code": string,
  "descriptions": [
    {
      "language": string,
      "description": string
    }
  ],
  "children": [
    {
      "snomedCode": string,
      "icd9Code": string,
      "icd10Code": string,
      "descriptions": [
        {
          "language": string,
          "description": string
        }
      ]
    }
  ]
}
```

## `/api/search?q=copper+fever`
**Definition:** This endpoint returns those terms that contains the search query in their descriptions.  
**Return object:**
```
[
  {
    "snomedCode": string,
    "icd9Code": string,
    "icd10Code": string,
    "descriptions": [
      {
        "language": string,
        "description": string
      }
    ],
    "children": [
      {
        "snomedCode": string,
        "icd9Code": string,
        "icd10Code": string,
        "descriptions": [
          {
            "language": string,
            "description": string
          }
        ]
      }, ...
    ]
  }, ...
]
```
