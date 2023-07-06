from pymongo import MongoClient

client = MongoClient(host='127.0.0.1',
                     port=27017,
                     username='root',
                     password='password')
print(client.list_database_names())

db = client['mydb']
users = db['users']
books = db['books']

for idx in list(range(1,10)) :
    data = {
        '_id' : idx,
        'group' : 1,
        'info' : {
            'name' : f'name_{idx}',
            'age' : 10
        }
    }
    users.update_one({ '_id' : idx }, { '$set': data }, upsert=True)

for idx in list(range(1,5)) :
    data = {
        '_id' : idx,
        'user_id' : idx,
        'info' : {
            'title' : f'title_{idx}'
        }
    }
    books.update_one({ '_id' : idx }, { '$set': data }, upsert=True)

for idx in list(range(1,5)) :
    info = db[f'info_{idx}']
    for user_idx in list(range(1,5)) :
        data = {
            '_id' : user_idx,
            'user_id' : user_idx,
            'info' : {
                'name' : f'info_{user_idx}'
            }
        }
        info.update_one({ '_id' : user_idx }, { '$set': data }, upsert=True)

print(users.find_one({ '_id': 1 }))
print(books.find_one({ 'user_id': 2 }))
print(db['info_1'].find_one({ 'user_id': 1 }))

client.close()