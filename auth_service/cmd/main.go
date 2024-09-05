package main

import (
	"auth_service/api/route"
	"auth_service/bootstrap"
	"github.com/gin-gonic/gin"
	"log"
	"time"
)

func main() {
	app := bootstrap.App()

	env := app.Env

	db := app.Mongo.Database(env.DBName)

	defer app.CloseDBConnection()

	timeout := time.Duration(env.ContextTimeout) * time.Second

	server := gin.Default()

	route.Setup(env, timeout, db, server)

	err := server.Run(env.ServerAddress)
	if err != nil {
		log.Fatalln(err.Error())
	}
}
