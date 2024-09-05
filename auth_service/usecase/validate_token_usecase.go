package usecase

import (
	"auth_service/model"
	"context"
)

type ValidateTokenUsecase interface {
	GetUserByID(ctx context.Context, id string) (model.User, error)
	ExtractIDFromToken(requestToken string, secret string) (string, error)
}
