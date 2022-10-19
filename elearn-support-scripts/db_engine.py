from sqlalchemy import create_engine
import sys

db_remote_url = "uogyy5pqikn9er6i:K7g1mGLwObOHsX6AiBt@bheh1wym7xihu29rxgvj-mysql.services.clever-cloud.com:20982" \
                "/bheh1wym7xihu29rxgvj?charset=utf8mb4"

db_local_url = "root:root@localhost:3306/elearn"


def connect_db():
    db_url = db_remote_url
    if len(sys.argv) >= 2:
        if sys.argv[1] == 'local':
            db_url = db_local_url
        if len(sys.argv) >= 3 and type(sys.argv[2]) is str:
            db_url = sys.argv[2]

    engine = create_engine("mysql+pymysql://" + db_url)
    db_connect = engine.connect()
    print("Using DB Engine: {}".format(db_connect.engine))
    return db_connect
