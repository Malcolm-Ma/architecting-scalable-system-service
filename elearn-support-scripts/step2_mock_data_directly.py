import hashlib

import numpy as np
from faker import Faker
import pandas as pd
import random
import datetime
from db_engine import connect_db
import requests

# init db connection
con = connect_db()

# init faker instance
fake = Faker('en')

merchant_id = [
"8ac482b9840f715e01840f7469380002",
"8ac482b9840f715e01840f76f27c0004",
"8ac482b9840f715e01840f780a890006",
"8ac482b9840f715e01840f78a0b60008",
"8ac482b9840f715e01840f78c2b6000a"
]

buyer_id = [
    "2c948a86840ef86301840f1d3835000c",
    "2c948a86840ef86301840f1d7704000e",
    "2c948a86840ef86301840f1d9cb90010",
    "2c948a86840ef86301840f1dcdf90012",
    "2c948a86840ef86301840f1f161e0014"
]

def user_avatar(l):
    for i in l:
        user_avatars = 'https://www.gravatar.com/avatar/' + hashlib.md5(i.encode('utf-8')).hexdigest() + '?d=identicon'
        print(user_avatars)

def mock_commodity_data(lines_num, user_id):
    clmn = ['commodity_id', 'commodity_create_time', 'commodity_discount', 'commodity_introduction',
            'commodity_name', 'commodity_price', 'commodity_sold_cnt', 'commodity_star',
            'commodity_status', 'commodity_update_time', 'commodity_cover', 'published_by']
    data = []
    for i in range(lines_num):
        # mock process
        commodity_id = fake.uuid4()[0:32]
        create_time = datetime.datetime.now()
        commodity_discount = round(0.1 * (random.randrange(0, 10, 1)), 2)
        commodity_introduction = fake.paragraph(nb_sentences=8,
                                                variable_nb_sentences=True,
                                                ext_word_list=None)
        commodity_name = fake.sentence(nb_words=random.randrange(2, 5, 1))
        commodity_price = (random.randrange(100, 1000, 50))
        commodity_sold_cnt = int(random.randrange(1, 2000, 1))
        commodity_star = random.randrange(3, 4, 1) + 0.1 * random.randrange(0, 10, 1)
        commodity_status = 1
        commodity_update_time = datetime.datetime.now()
        commodity_cover = requests.get("https://random.imagecdn.app/v1/image?width=1000&height=500&category=buildings"
                                       "&format=json").json()["url"]
        published_by = user_id[i]
        # put in data
        data_element = [commodity_id, create_time, commodity_discount, commodity_introduction, commodity_name,
                        commodity_price, commodity_sold_cnt, commodity_star, commodity_status, commodity_update_time,
                        commodity_cover, published_by]
        data.append(data_element)
    commodity_df = pd.DataFrame(data, columns=clmn)
    print(commodity_df)
    return commodity_df

def update_commodity_avatar(lines_num, commodity_id_list, image):
    clmn = ['commodity_id', 'commodity_create_time', 'commodity_discount', 'commodity_introduction',
            'commodity_name', 'commodity_price', 'commodity_sold_cnt', 'commodity_star',
            'commodity_status', 'commodity_update_time', 'commodity_cover']
    data = []
    # mock process
    for i in range(lines_num):
        commodity_id = commodity_id_list[i]
        create_time = datetime.datetime.now()
        commodity_discount = round(0.1 * (random.randrange(0, 10, 1)), 2)
        commodity_introduction = fake.paragraph(nb_sentences=8,
                                                variable_nb_sentences=True,
                                                ext_word_list=None)
        commodity_name = fake.sentence(nb_words=random.randrange(2, 5, 1))
        commodity_price = (random.randrange(100, 1000, 50))
        commodity_sold_cnt = int(random.randrange(1, 2000, 1))
        commodity_star = random.randrange(1, 4, 1) + 0.1 * random.randrange(0, 10, 1)
        commodity_status = 1
        commodity_update_time = datetime.datetime.now()
        commodity_cover = image[i]
        # published_by = merchant_id[random.randrange(0,4,1)]
        # put in data
        data_element = [commodity_id, create_time, commodity_discount, commodity_introduction, commodity_name,
                        commodity_price, commodity_sold_cnt, commodity_star, commodity_status, commodity_update_time,
                        commodity_cover]
        data.append(data_element)
    commodity_df = pd.DataFrame(data, columns=clmn)
    print(commodity_df)
    return commodity_df

def mock_review(buyer_id, commodity):
    clmn = ['review_id', 'review_comment', 'review_create_time', 'review_star', 'commodity_id', 'user_id']
    data = []
    for i in range(5000):
        review_id = fake.uuid4()[0:32]
        review_comment = fake.paragraph(nb_sentences=5, variable_nb_sentences=True, ext_word_list=None)
        review_create_time = fake.date_time_between(start_date='-1y')
        review_star = random.randrange(0, 5, 1)
        commodity_id = commodity[random.randrange(0, len(commodity) - 1, 1)]
        user_id = buyer_id[random.randrange(0, len(buyer_id) - 1, 1)]
        data_element = [review_id, review_comment, review_create_time, review_star, commodity_id, user_id]
        data.append(data_element)
    df = pd.DataFrame(data, columns=clmn)
    return df


def write_db(df, db_name):
    df.to_sql(name=db_name, con=con, if_exists='append', index=False)

def update_db(df, db_name):
    df.to_sql(name=db_name, con=con, if_exists='replace', index=False)

def read_db(sqlcom):
    df = pd.read_sql(sqlcom, con=con)
    df = np.array(df).tolist()
    user_id = []
    for i in range(len(df)):
        user_id.append(df[i][0])
    return user_id

def change_cover(commodity_id,image):
    clmn = ['commodity_cover']
    data = []
    for i in commodity_id:
        data.append()

def random_image(times):
    image = []
    for i in range(times):
        img = requests.get("https://random.imagecdn.app/v1/image?width=1000&height=500&category=buildings&format=json")
        img = img.json()['url']
        image.append(img)
    return image


def run_step2():
    # # read merchant id
    # sqlcom = 'select top 6 user_id from user'  # read merchant id
    # merchant_id = read_db(sqlcom)
    # # read buyer id
    # sqlcom = 'select user_id from user'  # read merchant id
    # buyer_id = read_db(sqlcom)

    # read commodity id
    # sqlcom = 'select commodity_id from commodity'  # read merchant id
    # commodity_id = read_db(sqlcom)

    # mock image
    # image = random_image(len(commodity_id))
    # print(image)

    # commodity = update_commodity_avatar(len(commodity_id), commodity_id, image)
    # update_db(commodity, 'commodity')


    # step 2.1 - mock commodity
    mock_time = 10
    for i in range(mock_time):
        commodity = mock_commodity_data(len(merchant_id), merchant_id)
        write_db(commodity, 'commodity')

    # # step 2.2 - mock review
    # review = mock_review(buyer_id, commodity_id)
    # write_db(review, 'review')


    con.close()


if __name__ == '__main__':
    run_step2()
    # user_avatar(merchant_id)
    # user_avatar(buyer_id)