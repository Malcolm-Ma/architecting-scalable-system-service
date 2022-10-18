from step0_init_role_tag import run_step0
from step1_mock_user_by_using_API import run_step1
from step2_mock_data_directly import run_step2
from db_engine import connect_db

if __name__ == '__main__':
    connect_db()
    # run_step0()
    # run_step1()
    # run_step2()
