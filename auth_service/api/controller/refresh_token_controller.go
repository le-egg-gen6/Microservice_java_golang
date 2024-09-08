package controller

import (
	"auth_service/bootstrap"
	"auth_service/payload"
	"auth_service/usecase"
	"github.com/gin-gonic/gin"
	"net/http"
)

type RefreshTokenController struct {
	RefreshTokenUsecase usecase.RefreshTokenUsecase
	Env                 *bootstrap.Env
}

func (rtc *RefreshTokenController) RefreshToken(c *gin.Context) {
	var request payload.RefreshTokenRequest

	err := c.ShouldBindJSON(&request)
	if err != nil {
		c.JSON(
			http.StatusBadRequest,
			payload.NewBadRequestResponse("Invalid Request Body"),
		)
		return
	}

	id, err := rtc.RefreshTokenUsecase.ExtractIDFromToken(request.RefreshToken, rtc.Env.RefreshTokenSecret)
	if err != nil {
		c.JSON(
			http.StatusUnauthorized,
			payload.NewUnauthorizedResponse("Invalid Token"),
		)
		return
	}

	user, err := rtc.RefreshTokenUsecase.GetUserByID(c.Request.Context(), id)
	if err != nil {
		c.JSON(
			http.StatusUnauthorized,
			payload.NewUnauthorizedResponse("Invalid Token"),
		)
		return
	}

	accessToken, err := rtc.RefreshTokenUsecase.CreateAccessToken(&user, rtc.Env.AccessTokenSecret, rtc.Env.AccessTokenExpiryHour)
	if err != nil {
		c.JSON(
			http.StatusInternalServerError,
			payload.NewServerErrorResponse("An Error Occurred, please try again"),
		)
		return
	}

	refreshToken, err := rtc.RefreshTokenUsecase.CreateRefreshToken(&user, rtc.Env.RefreshTokenSecret, rtc.Env.RefreshTokenExpiryHour)
	if err != nil {
		c.JSON(
			http.StatusInternalServerError,
			payload.NewServerErrorResponse("An Error Occurred, please try again"),
		)
		return
	}

	refreshTokenResponse := payload.RefreshTokenResponse{
		AccessToken:  accessToken,
		RefreshToken: refreshToken,
	}

	c.JSON(
		http.StatusOK,
		payload.NewDataResponse(refreshTokenResponse),
	)
	return
}
