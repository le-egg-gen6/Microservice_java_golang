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
		c.JSON(
			http.StatusBadRequest,
			payload.NewBadRequestResponse("Invalid Request Body"),
		)
		return
	}

	user, err := lc.LoginUsecase.GetUserByUsername(c.Request.Context(), request.Username)
	if err != nil {
		c.JSON(
			http.StatusBadRequest,
			payload.NewBadRequestResponse("Invalid Username or Password"),
		)
		return
	}

	if !password_util.CompareHashedPassword(request.Password, user.Password) {
		c.JSON(
			http.StatusBadRequest,
			payload.NewBadRequestResponse("Invalid Username or Password"),
		)
		return
	}

	accessToken, err := lc.LoginUsecase.CreateAccessToken(&user, lc.Env.AccessTokenSecret, lc.Env.AccessTokenExpiryHour)
	if err != nil {
		c.JSON(
			http.StatusInternalServerError,
			payload.NewServerErrorResponse("An Error Occurred, please try again"),
		)
		return
	}

	refreshToken, err := lc.LoginUsecase.CreateRefreshToken(&user, lc.Env.RefreshTokenSecret, lc.Env.RefreshTokenExpiryHour)
	if err != nil {
		c.JSON(
			http.StatusInternalServerError,
			payload.NewServerErrorResponse("An Error Occurred, please try again"),
		)
		return
	}

	loginResponse := payload.LoginResponse{
		AccessToken:  accessToken,
		RefreshToken: refreshToken,
	}

	c.JSON(
		http.StatusOK,
		payload.NewDataResponse(loginResponse),
	)
	return
}
