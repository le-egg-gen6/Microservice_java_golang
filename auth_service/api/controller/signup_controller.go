package controller

import (
	"auth_service/bootstrap"
	"auth_service/model"
	"auth_service/payload"
	"auth_service/usecase"
	"auth_service/utils/password_util"
	"github.com/gin-gonic/gin"
	"go.mongodb.org/mongo-driver/bson/primitive"
	"net/http"
)

type SignupController struct {
	SignupUsecase usecase.SignupUsecase
	Env           *bootstrap.Env
}

func (sc *SignupController) Signup(c *gin.Context) {
	var request payload.SignupRequest

	err := c.ShouldBindJSON(&request)
	if err != nil {
		c.JSON(http.StatusBadRequest, gin.H{})
		return
	}

	_, err = sc.SignupUsecase.GetUserByEmail(c.Request.Context(), request.Email)
	if err != nil {
		c.JSON(http.StatusConflict, gin.H{})
		return
	}

	encryptedPassword, err := password_util.GenerateEncryptedPassword(request.Password)
	if err != nil {
		c.JSON(http.StatusInternalServerError, gin.H{})
		return
	}

	user := model.User{
		ID:       primitive.NewObjectID(),
		Name:     request.Name,
		Email:    request.Email,
		Password: encryptedPassword,
	}

	err = sc.SignupUsecase.Create(c.Request.Context(), &user)
	if err != nil {
		c.JSON(http.StatusInternalServerError, gin.H{})
		return
	}

	accessToken, err := sc.SignupUsecase.CreateAccessToken(&user, sc.Env.AccessTokenSecret, sc.Env.AccessTokenExpiryHour)
	if err != nil {
		c.JSON(http.StatusInternalServerError, gin.H{})
		return
	}

	refreshToken, err := sc.SignupUsecase.CreateRefreshToken(&user, sc.Env.RefreshTokenSecret, sc.Env.RefreshTokenExpiryHour)
	if err != nil {
		c.JSON(http.StatusInternalServerError, gin.H{})
		return
	}

	signupResponse := payload.SignupResponse{
		AccessToken:  accessToken,
		RefreshToken: refreshToken,
	}

	c.JSON(http.StatusOK, signupResponse)
	return
}
