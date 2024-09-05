package controller

import (
	"auth_service/bootstrap"
	"auth_service/payload"
	"auth_service/usecase"
	"auth_service/utils/password_util"
	"github.com/gin-gonic/gin"
	"net/http"
)

type LoginController struct {
	LoginUsecase usecase.LoginUsecase
	Env          *bootstrap.Env
}

func (lc *LoginController) Login(c *gin.Context) {
	var request payload.LoginRequest

	err := c.ShouldBindJSON(&request)
	if err != nil {
		c.JSON(http.StatusBadRequest, gin.H{})
		return
	}

	user, err := lc.LoginUsecase.GetUserByUsername(c.Request.Context(), request.Username)
	if err != nil {
		c.JSON(http.StatusBadRequest, gin.H{})
		return
	}

	if !password_util.CompareHashedPassword(request.Password, user.Password) {
		c.JSON(http.StatusUnauthorized, gin.H{})
		return
	}

	accessToken, err := lc.LoginUsecase.CreateAccessToken(&user, lc.Env.AccessTokenSecret, lc.Env.AccessTokenExpiryHour)
	if err != nil {
		c.JSON(http.StatusInternalServerError, gin.H{})
		return
	}

	refreshToken, err := lc.LoginUsecase.CreateRefreshToken(&user, lc.Env.RefreshTokenSecret, lc.Env.RefreshTokenExpiryHour)
	if err != nil {
		c.JSON(http.StatusInternalServerError, gin.H{})
		return
	}

	loginResponse := payload.LoginResponse{
		AccessToken:  accessToken,
		RefreshToken: refreshToken,
	}

	c.JSON(http.StatusOK, loginResponse)
	return
}
