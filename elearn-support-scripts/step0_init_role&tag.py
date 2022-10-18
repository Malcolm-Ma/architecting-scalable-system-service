import pandas as pd
from faker import Faker
from sqlalchemy import create_engine

engine = create_engine("mysql+pymysql://uogyy5pqikn9er6i:"
                       "K7g1mGLwObOHsX6AiBt@bheh1wym7xihu29rxgvj-mysql.services.clever-cloud.com:20982"
                       "/bheh1wym7xihu29rxgvj?charset=utf8mb4")
con = engine.connect()


# def init_role():
#     data = []
#     clmn = ['role_id', 'display_name', 'role_client', 'role_name']
#     data.append([1, 'Buyer', 1, 'buyer'])
#     data.append([2, 'Merchant', 1, 'merchant'])
#     df = pd.DataFrame(data, columns=clmn)
#     return df

def init_tag():
    fake = Faker('en')
    data = []
    clmn = ['tag_id', 'tag_name', 'tag_info']
    for i in range(10):
        data.append(([i,fake.word(),fake.sentence(nb_words=5)]))
    print(data)
    df = pd.DataFrame(data, columns=clmn)
    return df


def write_db(df, db_name):
    df.to_sql(name=db_name, con=con, if_exists='append', index=False)



if __name__ == '__main__':
    # initialize role
#     role = init_role()
#     write_db(role, 'role')
    # initialize tag
    tag = init_tag()
    write_db(tag,'tag')
    con.close()
