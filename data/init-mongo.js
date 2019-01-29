let res = [
    db.createCollection("concepts"),
    db.concepts.drop(),
    db.concepts.drop(),
    db.concepts.createIndex({
        code: 1
    }, {
        name: "code"
    }),
    db.concepts.createIndex({
        descriptions: 1
    }, {
        name: "descriptions"
    }),
    db.concepts.createIndex({
        description: 1
    }, {
        name: "description"
    }),
]