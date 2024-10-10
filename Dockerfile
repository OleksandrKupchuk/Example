FROM jenkins/jenkins:lts

USER root

RUN apt-get update && apt-get install -y wget gnupg2 \
    && wget -q -O - https://dl-ssl.google.com/linux/linux_signing_key.pub | apt-key add - \
    && sh -c 'echo "deb [arch=amd64] http://dl.google.com/linux/chrome/deb/ stable main" >> /etc/apt/sources.list.d/google-chrome.list' \
    && apt-get update && apt-get install -y google-chrome-stable unzip

RUN wget -N https://storage.googleapis.com/chrome-for-testing-public/129.0.6668.91/linux64/chromedriver-linux64.zip \
    && unzip chromedriver-linux64.zip \
    && mv chromedriver-linux64/chromedriver /usr/local/bin/chromedriver \
    && rm -rf chromedriver-linux64 \
    && rm chromedriver-linux64.zip \
    && chmod +x /usr/local/bin/chromedriver

USER jenkins