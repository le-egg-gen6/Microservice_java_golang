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

func NewSignupRouter(env *bootstrap.Env, timeout time.Duration, db mongo.Database, group *gin.RouterGroup) {
	ur := repository.NewUserRepository(db, model.CollectionUser)
	su := usecase.NewSignupUsecase(ur, timeout)
	sc := &controller.SignupController{
		SignupUsecase: su,
		Env:           env,
	}
	group.POST("/signup", sc.Signup)
}
