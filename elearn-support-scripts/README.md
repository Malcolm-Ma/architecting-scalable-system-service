# elearn-support-scripts

_Scripts for mocking data in dev/test database_

## Preset Running

Please make sure the `elearn` back-end server is running on the localhost

## Install Dependency

Use `conda` for example

```shell
conda install faker
```

## Run Script

```shell
cd elearn-support-scripts
```

### Mock Data for localhost MySQL

```shell
python run_script.py local
```

Note that the default settings of `username` and `password` for localhost MySQL are both `root`.

For custom settings of localhost DB, please use the command below,
the format of custom host is `<username>:<password>@localhost:<port>/<db_name>`
```shell
python run_script.py local <custom_host>
```

### Mock Data for online testing DB

```shell
python run_script.py
```

If you would like to configure the URL for online DB, please go to [db_engine.py](db_engine.py)
and change the value of `db_remote_url`

### Mock Data by Step

`elearn-support-scripts` also supports mocking data by step,
you are able to decide which part of the db you would like to init

```shell
python step0_init_role_tag.py
python step1_mock_user_by_using_API.py
python step2_mock_data_directly.py
```
