package controller

import (
	"auth_service/bootstrap"
	"auth_service/payload"
	"auth_service/usecase"
	"github.com/gin-gonic/gin"
	"net/http"
)

type ValidateTokenController struct {
	ValidateTokenUsecase usecase.ValidateTokenUsecase
	Env                  *bootstrap.Env
}

func (vtc *ValidateTokenController) ValidateToken(c *gin.Context) {
	var request payload.ValidateTokenRequest

	if err := c.ShouldBindJSON(&request); err != nil {
		c.JSON(
			http.StatusBadRequest,
			payload.NewBadRequestResponse("Invalid Request Body"),
		)
		return
	}
	id, err := vtc.ValidateTokenUsecase.ExtractIDFromToken(request.AccessToken, vtc.Env.AccessTokenSecret)
	if err != nil {

	}

	user, err := vtc.ValidateTokenUsecase.GetUserByID(c.Request.Context(), id)
	if err != nil {

	}

}
