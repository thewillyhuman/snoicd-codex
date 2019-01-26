let res = [
  db.createCollection("internalKB"),
  db.internalKB.drop(),
  db.internalKB.drop(),
  db.internalKB.createIndex({ snomedCode: 1 }, { name: "snomedCode"}),
  db.internalKB.createIndex({ icd9Code: 1 }, { name: "icd9Code"}),
  db.internalKB.createIndex({ icd10Code: 1 }, { name: "icd10Code"}),
]
