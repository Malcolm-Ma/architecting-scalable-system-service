import requests

if __name__ == '__main__':
    # run_step2()
    img = requests.get("https://random.imagecdn.app/v1/image?width=1000&height=500&category=buildings&format=json")
    img = x.json()['url']
    print(x)