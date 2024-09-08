package route

import (
	"auth_service/api/middleware"
	"auth_service/bootstrap"
	"auth_service/mongo"
	"github.com/gin-gonic/gin"
	"time"
)

func Setup(env *bootstrap.Env, timeout time.Duration, db mongo.Database, gin *gin.Engine) {
	publicRouter := gin.Group("")
	// All Public APIs
	NewSignupRouter(env, timeout, db, publicRouter)
	NewLoginRouter(env, timeout, db, publicRouter)
	NewRefreshTokenRouter(env, timeout, db, publicRouter)
	// All Internal APIs
	internalRouter := gin.Group("/internal")
	internalRouter.Use(middleware.InternalApiMiddlewareFilter(env.InternalTokenSecret))
	NewValidateTokenRouter(env, timeout, db, internalRouter)
}
