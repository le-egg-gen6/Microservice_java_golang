package route

import (
	"auth_service/api/controller"
	"auth_service/bootstrap"
	"auth_service/model"
	"auth_service/mongo"
	"auth_service/repository"
	usecase "auth_service/usecase/impl"
	"github.com/gin-gonic/gin"
	"time"
)

func NewRefreshTokenRouter(env *bootstrap.Env, timeout time.Duration, db mongo.Database, group *gin.RouterGroup) {
	ur := repository.NewUserRepository(db, model.CollectionUser)
	rtu := usecase.NewRefreshTokenUsecase(ur, timeout)

	rtc := &controller.RefreshTokenController{
		RefreshTokenUsecase: rtu,
		Env:                 env,
	}

	group.POST("/refresh", rtc.RefreshToken)
}
