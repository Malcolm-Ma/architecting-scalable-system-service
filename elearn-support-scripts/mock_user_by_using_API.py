import json

import requests
from faker import Faker
import random

def mock_user_data(user_nums, user_add_url):
    all_data = []
    for i in range(user_nums):
        fake = Faker('en')
        user_age = random.randrange(12, 60, 1)
        user_contact = fake.country_calling_code() + fake.msisdn()[0:8]
        user_email = fake.email()
        user_email_verified = True
        user_enabled = True
        user_firstname = fake.first_name()
        user_lastname = fake.last_name()
        user_username = fake.user_name()
        user_introduction = fake.paragraph(nb_sentences=5, variable_nb_sentences=True, ext_word_list=None)
        data = {"user_age": user_age, "user_contact": user_contact, "user_email": user_email,
                "user_email_verified": user_email_verified, "user_enabled": user_enabled,
                "user_firstname": user_firstname,
                "user_introduction": user_introduction, "user_lastname": user_lastname, "user_role_id": 1,
                "user_username": user_username}
        all_data.append(data)
        # data_json = json.dumps(data,ensure_ascii=False)
        # x = requests.post(user_add_url, json=data_json)
    print("finish mock")
    for i in range(user_nums):
        x = requests.post(user_add_url,json=all_data[i], stream=True)
        print(i)

if __name__ == '__main__':
    mock_user_data(10, 'http://localhost:8090/user/add')
