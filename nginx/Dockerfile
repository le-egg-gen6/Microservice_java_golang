FROM nginx:stable-alpine3.20

COPY ./nginx.conf /etc/nginx/conf
EXPOSE 80 443

ENTRYPOINT ["nginx"]

CMD ["-g", "daemon off;"]