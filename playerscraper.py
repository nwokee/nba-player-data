# needed libraries
from os import remove
from urllib.request import urlopen
from bs4 import BeautifulSoup
import pandas as pd

for year in range(1982,2025):
    # URL to scrape
    url = f"https://www.basketball-reference.com/leagues/NBA_{year}_advanced.html"

    # collect HTML data
    html = urlopen(url)

    # create beautiful soup object from HTML
    soup = BeautifulSoup(html, "html.parser")


    # use getText()to extract the headers into a list
    headers = [th.getText() for th in soup.findAll('tr', limit=1)[0].findAll('th')]
    headers = headers[1:]
    rows = soup.findAll('tr')[1:]


    rows_data = [[td.getText() for td in rows[i].findAll('td')] for i in range(len(rows))]



    rows_data = [ele for ele in rows_data if len(ele) != 0]

    for r in range(0,len(rows_data)):
        if rows_data[r][3] == 'TOT':
            i = ''
            while rows_data[r+1][0] == rows_data[r][0]:
                i = i + rows_data[r+1][3]
                del rows_data[r+1]
                if len(rows_data) == r + 1:
                    break
            rows_data[r][3] = i

        if len(rows_data) == r+1:
            break
    # create the dataframe
    nba_players = pd.DataFrame(rows_data, columns = headers)
    # export dataframe to a CSV
    nba_players.to_csv(f"nba_advanced_players_{year}.csv", index=False)


