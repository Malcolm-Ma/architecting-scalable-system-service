from faker import Faker
import pandas as pd
import random
import datetime
from sqlalchemy import create_engine


def mock_commodity_data(lines_num):
    fake = Faker('en')
    clmn = ['commodity_id', 'commodity_create_time', 'commodity_discount', 'commodity_introduction',
            'commodity_name', 'commodity_price', 'commodity_sold_cnt', 'commodity_star',
            'commodity_status', 'commodity_update_time']
    data = []
    for i in range(lines_num):
        dt=datetime.datetime.now().strftime("%Y-%m-%d %H:%M:%S")
        # mock process
        commodity_id = fake.uuid4()
        create_time = dt
        commodity_discount = round(0.1 * (random.randrange(0, 10, 1)), 2)
        commodity_introduction = fake.paragraph(nb_sentences=500,
                                                variable_nb_sentences=True,
                                                ext_word_list=None)
        commodity_name = fake.sentence()
        commodity_price = (random.randrange(100, 1000, 50))
        commodity_sold_cnt = int(random.randrange(1, 2000, 1))
        commodity_star = random.randrange(1, 4, 1) + 0.1 * random.randrange(0, 10, 1)
        commodity_status = 1
        commodity_update_time = dt
        # put in data
        data_element = [commodity_id, create_time, commodity_discount, commodity_introduction, commodity_name,
                        commodity_price, commodity_sold_cnt, commodity_star, commodity_status, commodity_update_time]
        data.append(data_element)
    commodity_df = pd.DataFrame(data, columns=clmn)
    print(commodity_df)
    return commodity_df


def mock_user_data(lines_num):
    fake = Faker('en')
    clmn = ['user_id', 'user_age', 'user_avatar',
            'user_contact', 'user_created_time', 'user_email', 'user_email_verified', 'user_enabled',
            'user_firstname', 'user_introduction', 'user_lastname', 'user_username']
    data = []
    for i in range(lines_num):
        # mock process
        user_id = fake.uuid4()
        # cart_id = fake.uuid4()
        # role_id = random.randrange(0, 1, 1)
        user_age = random.randrange(18, 60, 1)
        user_avatar = "haha.cloud.com/"+ fake.pystr(18) + ".jpg"
        # if role_id == 0:
        #     user_consumer_level = random.randrange(0, 5, 1)
        # else:
        #     user_consumer_level = None
        user_contact = fake.country_calling_code() + fake.msisdn()[0:8]
        user_created_time = datetime.datetime.now()
        user_email = fake.ascii_company_email
        user_email_verified = True
        user_enabled = True
        user_firstname = fake.first_name()
        user_introduction = fake.paragraph(nb_sentences=5,
                                           variable_nb_sentences=True,
                                           ext_word_list=None)
        user_lastname = fake.last_name()
        # if role_id == 1:
        #     user_merchant_level = random.randrange(0, 5, 1)
        # else:
        #     user_merchant_level = None
        user_username = fake.user_name()
        # put in data
        data_element = [user_id,  user_age, user_avatar,
                        user_contact, user_created_time, user_email, user_email_verified,
                        user_enabled, user_firstname, user_introduction, user_lastname,
                        user_username]
        data.append(data_element)
    user_df = pd.DataFrame(data, columns=clmn)
    print(user_df)
    return user_df


def write_db(df, db_name):
    try:
        engine = create_engine("mysql+pymysql://uogyy5pqikn9er6i:"
                               "K7g1mGLwObOHsX6AiBt@bheh1wym7xihu29rxgvj-mysql.services.clever-cloud.com:20982"
                               "/bheh1wym7xihu29rxgvj?charset=utf8mb4")
        con = engine.connect()
    except Exception as e:
        print(e)
    df.to_sql(name=db_name, con=con, if_exists='append', index=False)
    con.close()


if __name__ == '__main__':
    commodity = mock_commodity_data(200)
    write_db(commodity, 'commodity')
    # haha = mock_user_data(2000)
    # write_db(haha, 'user')
