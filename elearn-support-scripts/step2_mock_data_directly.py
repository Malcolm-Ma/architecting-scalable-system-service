import numpy as np
from faker import Faker
import pandas as pd
import random
import datetime
from sqlalchemy import create_engine

engine = create_engine("mysql+pymysql://uogyy5pqikn9er6i:"
                       "K7g1mGLwObOHsX6AiBt@bheh1wym7xihu29rxgvj-mysql.services.clever-cloud.com:20982"
                       "/bheh1wym7xihu29rxgvj?charset=utf8mb4")
con = engine.connect()

fake = Faker('en')

def mock_commodity_data(lines_num,user_id):

    clmn = ['commodity_id', 'commodity_create_time', 'commodity_discount', 'commodity_introduction',
            'commodity_name', 'commodity_price', 'commodity_sold_cnt', 'commodity_star',
            'commodity_status', 'commodity_update_time', 'commodity_cover','published_by']
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
        commodity_star = random.randrange(1, 4, 1) + 0.1 * random.randrange(0, 10, 1)
        commodity_status = 1
        commodity_update_time = datetime.datetime.now()
        commodity_cover = 'https://dummyimage.com/400x300/9e479e/ffffff.jpg&text=' + commodity_name
        published_by = user_id[i]
        # put in data
        data_element = [commodity_id, create_time, commodity_discount, commodity_introduction, commodity_name,
                        commodity_price, commodity_sold_cnt, commodity_star, commodity_status, commodity_update_time,
                        commodity_cover,published_by]
        data.append(data_element)
    commodity_df = pd.DataFrame(data, columns=clmn)
    print(commodity_df)
    return commodity_df

def mock_review(buyer_id,commodity):
    clmn = ['review_id', 'review_comment','review_create_time','review_star','commodity_id','user_id']
    data = []
    for i in range(5000):
        review_id= fake.uuid4()[0:32]
        review_comment = fake.paragraph(nb_sentences=5,variable_nb_sentences=True, ext_word_list=None)
        review_create_time = fake.date_time_between(start_date='-1y')
        review_star = random.randrange(0,5,1)
        commodity_id = commodity[random.randrange(0,len(commodity)-1,1)]
        user_id = buyer_id[random.randrange(0,len(buyer_id)-1,1)]
        data_element = [review_id,review_comment,review_create_time,review_star,commodity_id,user_id]
        data.append(data_element)
    df = pd.DataFrame(data, columns=clmn)
    return df

def write_db(df, db_name):
    df.to_sql(name=db_name, con=con, if_exists='append', index=False)



def read_db(sqlcom):
    df = pd.read_sql(sqlcom, con=con)
    df = np.array(df).tolist()
    user_id = []
    for i in range(len(df)):
        user_id.append(df[i][0])
    return user_id


if __name__ == '__main__':
    # read merchant id
    sqlcom = 'select user_id from user'  # read merchant id
    merchant_id = read_db(sqlcom)
    # read buyer id
    sqlcom = 'select user_id from user'  # read merchant id
    buyer_id = read_db(sqlcom)
    # read commodity id
    sqlcom = 'select commodity_id from commodity'  # read merchant id
    commodity_id = read_db(sqlcom)

    # step 2.1 - mock commodity
    # mock_time = 5
    # for  i in range(mock_time):
    #     commodity = mock_commodity_data(len(merchant_id),merchant_id)
    #     write_db(commodity, 'commodity')
    # step 2.2 - mock review
    review = mock_review(buyer_id,commodity_id)
    write_db(review,'review')

    con.close()

